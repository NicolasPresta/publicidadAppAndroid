package com.example.presta.publicidadexample.ui.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.helpers.AlarmGPSStart;
import com.example.presta.publicidadexample.common.helpers.CommonVariables;
import com.example.presta.publicidadexample.rest.post.OnPostCompleted;
import com.example.presta.publicidadexample.dataAccess.dao.DaoSessionAccesor;
import com.example.presta.publicidadexample.dataAccess.model.AppConfig;
import com.example.presta.publicidadexample.dataAccess.model.AppConfigDao;
import com.example.presta.publicidadexample.dataAccess.model.PhoneData;
import com.example.presta.publicidadexample.dataAccess.model.PhoneDataDao;
import com.example.presta.publicidadexample.dataAccess.model.UserData;
import com.example.presta.publicidadexample.dataAccess.model.UserDataDao;
import com.example.presta.publicidadexample.rest.ApiConstants;
import com.example.presta.publicidadexample.rest.post.PostRequestTask;
import com.example.presta.publicidadexample.ui.fragments.DestacadosFragment;
import com.example.presta.publicidadexample.ui.fragments.HomeFragment;
import com.example.presta.publicidadexample.ui.fragments.SucursalesFragment;

import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Presta on 10/03/2016.
 */
public class MainActivity extends AppCompatActivity
        implements OnPostCompleted {

    //region "-- ATRIBUTOS PRIVADOS --"


    // componentes de mainLayout
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navView;

    // componentes de primerUsoLayout
    DatePicker dateCumple;
    RadioButton rbHombre;
    RadioButton rbMujer;
    Button btContinuar;

    // acceso a datos
    AppConfigDao appConfigDao;
    AppConfig appConfig;

    PhoneDataDao phoneDataDao;
    PhoneData phoneData;

    UserDataDao userDataDao;
    UserData userData;

    // fragmentos
    Fragment fragmentHome = null;
    Fragment fragmentDestacados3 = null;

    //endregion

    //region "-- OVERRIDES --"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("PASOPORACA", "onCreate");

        // Cargar uuid en una variable compartida por toda la app.
        cargarUuid();

        // Inizializa el acceso a la base de datos SQLLitle
        appConfigDao = DaoSessionAccesor.GetDaoSession(this).getAppConfigDao();
        phoneDataDao = DaoSessionAccesor.GetDaoSession(this).getPhoneDataDao();
        userDataDao = DaoSessionAccesor.GetDaoSession(this).getUserDataDao();

        // Carga la configuración base de la app
        cargarConfigApp();

        // Si es el primer uso entonces cargamos el form para ingresar edad y sexo
        if (appConfig.getEsPrimerUso()) {
            cargarPrimerUsoLayout();

            // Iniciamos el monitoreo del GPS
            AlarmGPSStart.setAlarm(this);

        } else {
            // si no cargamos el layout principal
            cargarMainLayout();
        }

        // Carga los datos del celular, y verifica si no se envió al servidor intenta enviarlo.
        sincronizarPhoneData();

        // Carga los datos de las cuentas del usuario, y verifica si no se envió al servidor intenta enviarlo.
        sincronizarUserData();


    }

    @Override
    public void onPostCompleted(String metodo, Integer result) {

        // Si la ejecución del post del phoneData fue exitosa, marcamos el registro como ya actualizado y nunca mas nos preocuparemos por él :)
        if (metodo == ApiConstants.POST_METHOD_PHONEDATA)
            if (result == HttpURLConnection.HTTP_OK) {
                Log.i("POST", "los phoneData se sincronizaron ok");
                phoneData.setDatosSincronizados(true);
                phoneDataDao.update(phoneData);
            }

        // Idem para userData
        if (metodo == ApiConstants.POST_METHOD_USERDATA)
            if (result == HttpURLConnection.HTTP_OK) {
                Log.i("POST", "los userData se sincronizaron ok");
                userData.setDatosSincronizados(true);
                userDataDao.update(userData);
            }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            //...
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("PASOPORACA", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("PASOPORACA", "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("PASOPORACA", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("PASOPORACA", "onRestart");
    }

    //endregion

    //region "-- CARGA DESDE SQL --"

    private void cargarPhoneData() {

        List phoneDatalist = phoneDataDao.loadAll();

        if (phoneDatalist.size() == 0) {

            // Carga el objeto PhoneData en la base de datos en caso de que el mismo no exista.
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            phoneData = new PhoneData();
            phoneData.setDeviceId(tm.getDeviceId());
            phoneData.setSubscriberId(tm.getSubscriberId());
            phoneData.setSimSerialNumber(tm.getSimSerialNumber());
            phoneData.setLine1Number(tm.getLine1Number());
            phoneData.setNetworkOperatorName(tm.getNetworkOperatorName());
            phoneData.setNetworkCountryIso(tm.getNetworkCountryIso());

            phoneData.setSDK_INT(Build.VERSION.SDK_INT);
            phoneData.setMANUFACTURER(Build.MANUFACTURER);
            phoneData.setMODEL(Build.MODEL);

            phoneData.setDatosSincronizados(false);

            phoneDataDao.insert(phoneData);

        } else {
            phoneData = (PhoneData) phoneDatalist.get(0);
        }
    }

    private void cargarConfigApp() {

        List appConfigs = appConfigDao.loadAll();
        if (appConfigs.size() == 0) {
            appConfig = new AppConfig();
            appConfig.setEsPrimerUso(true);
            appConfigDao.insert(appConfig);
        } else {
            appConfig = (AppConfig) appConfigs.get(0);
        }

    }

    private Boolean cargarUserData() {
        List userDatas = userDataDao.loadAll();
        if (userDatas.size() == 0) {
            return false;
        } else {
            userData = (UserData) userDatas.get(0);
            return true;
        }
    }

    //endregion

    //region "-- EVENTOS DE COMPONENTES GRAFICOS --"

    public void rbOnClick(View v) {
        btContinuar.setVisibility(View.VISIBLE);
    }

    public void continuarOnClick(View v) {
        appConfig.setEsPrimerUso(false);
        appConfigDao.update(appConfig);


        userData = new UserData();

        if (rbHombre.isChecked())
            userData.setSexo("H");
        else
            userData.setSexo("M");

        int day = dateCumple.getDayOfMonth();
        int month = dateCumple.getMonth() + 1;
        int year = dateCumple.getYear();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day, 0, 0, 0);

        userData.setFechaNacimiento(cal.getTime());

        // Hacemos el resto de la carga (la lectura de las cuentas) en otro hilo, para no interrumpir el hilo de la UI
        new CargarUserDataTask().execute();

        cargarMainLayout();
    }

    //endregion

    //region "-- MANEJO DE LAYOUT --"

    private void cargarMenuLateral() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        seleccionItemMenu(menuItem);
                        return true;

                    }
                });
    }

    public void seleccionItemMenu(MenuItem menuItem) {

        // Muestra el fragmento correspondiente en el contenedor principal.

        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.menu_home: {
                if (fragmentHome == null)
                    fragmentHome = new HomeFragment();
                fragment = fragmentHome;
                break;
            }
           /* case R.id.menu_tarjeta: {
                if (fragmentSucursales == null)
                    fragmentSucursales = new SucursalesFragment();
                fragment = fragmentSucursales;
                break;
            }
            case R.id.menu_sucursales: {
                if (fragmentDestacados2 == null)
                    fragmentDestacados2 = new DestacadosFragment();
                fragment = fragmentDestacados2;
                break;
            }
            case R.id.menu_fila: {
                if (fragmentDestacados3 == null)
                    fragmentDestacados3 = new DestacadosFragment();
                fragment = fragmentDestacados3;
                break;
            }*/
            default: {
                fragment = fragmentDestacados3;
                break;
            }
        }


        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment)
                .addToBackStack(null)
                .commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);

        // Set action bar title
        setTitle(menuItem.getTitle());

        // Close the navigation drawer
        drawerLayout.closeDrawers();
    }

    private void cargarMainLayout() {
        setContentView(R.layout.activity_main);
        cargarToolBar();
        cargarMenuLateral();

        // Cargamos el frame principal
        fragmentHome = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, fragmentHome)
                .commit();
    }

    private void cargarToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Carga los componentes graficos para cuando es la primera vez que se inicia la app
    private void cargarPrimerUsoLayout() {
        setContentView(R.layout.activity_primer_uso);

        dateCumple = (DatePicker) findViewById(R.id.date_cumple);
        btContinuar = (Button) findViewById(R.id.btn_Continuar);
        rbHombre = (RadioButton) findViewById(R.id.rbHombre);
        rbMujer = (RadioButton) findViewById(R.id.rbMujer);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);


        c.set(year - 80, 0, 1);
        dateCumple.setMinDate(c.getTimeInMillis());
        c.set(year - 8, 11, 30);
        dateCumple.setMaxDate(c.getTimeInMillis());

        dateCumple.updateDate(year - 18, 0, 1);

        dateCumple.refreshDrawableState();
    }

    //endregion

    //region "-- ACCESO DATOS CELULAR --"

    private void cargarUuid() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        CommonVariables.SetUuid(tm.getSubscriberId());
    }

    private String GetDatosDeCuentas() {
        String cuentas = "{ cuentas: [";

        try {

            Account[] accounts = AccountManager.get(this).getAccounts();
            for (Account account : accounts) {
                String cuenta = "{\"";
                cuenta = cuenta + account.type + "\":\"";
                cuenta = cuenta + account.name + "\"},";
                cuentas = cuentas + cuenta;
            }
        } catch (Exception e) {
            Log.i("READ_Exception", "Exception:" + e);
        }

        Log.i("READ.Accounts", "mails:" + cuentas);

        cuentas = cuentas.substring(0, cuentas.length() - 1);
        cuentas = cuentas + "]}";

        return cuentas;
    }

    private class CargarUserDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            userData.setCuentas(GetDatosDeCuentas());
            return "";
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String param) {
            userData.setDatosSincronizados(false);

            userDataDao.insert(userData);
            sincronizarUserData();
        }
    }

    //endregion

    //region "-- POST AL SERVER --"

    private void sincronizarPhoneData() {

        // Cargamos los datos del phoneData
        cargarPhoneData();

        // Si no está sincronizado con el servidor, que no se diga más, a sincronizarlo papa!
        if (!phoneData.getDatosSincronizados()) {

            Map<String, String> map = new HashMap<String, String>();
            map.put("DeviceId", phoneData.getDeviceId());
            map.put("SubscriberId", phoneData.getSubscriberId());
            map.put("SimSerialNumber", phoneData.getSimSerialNumber());
            map.put("Line1Number", phoneData.getLine1Number());
            map.put("NetworkCountryIso", phoneData.getNetworkCountryIso());
            map.put("NetworkOperatorName", phoneData.getNetworkOperatorName());

            map.put("SDK_INT", phoneData.getSDK_INT().toString());
            map.put("MANUFACTURER", phoneData.getMANUFACTURER());
            map.put("MODEL", phoneData.getMODEL());

            String param = PostRequestTask.createQueryStringForParameters(map);

            new PostRequestTask(this).execute(ApiConstants.POST_METHOD_PHONEDATA, param);
        }
    }

    private void sincronizarUserData() {

        // Cargamos los datos del phoneData
        if (cargarUserData()) {
            // Si los datos se cargan ok y no están sincronizados, los tratamos de sincronizar...
            if (!userData.getDatosSincronizados()) {

                Map<String, String> map = new HashMap<String, String>();
                map.put("Sexo", userData.getSexo());
                map.put("FechaNacimiento", userData.getFechaNacimiento().toString());
                map.put("Cuentas", userData.getCuentas());

                String param = PostRequestTask.createQueryStringForParameters(map);

                new PostRequestTask(this).execute(ApiConstants.POST_METHOD_USERDATA, param);
            }
        }
    }

    //endregion
}



