package com.example.presta.publicidadexample.ui.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.CommonVariables;
import com.example.presta.publicidadexample.ui.adapter.PageAdapter;
import com.example.presta.publicidadexample.ui.fragments.TabPublicidadFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Presta on 10/03/2016.
 */
public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    DatePicker dateCumple;
    Button btContinuar;

    ArrayList<String> contactList;
    Cursor cursor;
    int counter;
    Boolean primeraVezAbiertaApp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (primeraVezAbiertaApp) {
            cargarPrimerUsoLayout();
        } else {
            cargarMainLayout();
        }

        ObtenerInfoDispositivo(); // READ_PHONE_STATE
        //ObtenerInfoCuentas(); // GET_ACCOUNTS
        //ObtenerInfoContactos(); // READ_CONTACTS

    }

    private void cargarPrimerUsoLayout() {
        setContentView(R.layout.activity_primer_uso);

        dateCumple = (DatePicker) findViewById(R.id.date_cumple);
       /* Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);

        c.set(year - 80, 1, 1);
        dateCumple.setMinDate(c.getTimeInMillis());
        c.set(year - 8, 1, 1);
        dateCumple.setMaxDate(c.getTimeInMillis());*/

        btContinuar = (Button) findViewById(R.id.btn_Continuar);

    }

    public void rbOnClick(View v) {
        btContinuar.setVisibility(View.VISIBLE);
    }

    public void continuarOnClick(View v) {
        cargarMainLayout();
    }

    private void cargarMainLayout() {
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        setupViewPager();

        if (toolbar != null)
            setSupportActionBar(toolbar);
    }

    private void ObtenerInfoContactos() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getContacts();
            }
        }).start();
    }

    public void getContacts() {
        Log.i("READ_Contact", "estoy en el metodo");
        contactList = new ArrayList<String>();
        String phoneNumber = null;
        String email = null;
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;
        StringBuffer output;
        ContentResolver contentResolver = getContentResolver();
        cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
        Log.i("READ_Contact", "Llegue a instanciar el cursor");
        // Iterate every contact in the phone
        if (cursor.getCount() > 0) {
            counter = 0;
            Log.i("READ_Contact", "Estoy dentro del cursor");
            while (cursor.moveToNext()) {

                output = new StringBuffer();
                // Update the progress message
                /*updateBarHandler.post(new Runnable() {
                    public void run() {
                        pDialog.setMessage("Reading contacts : " + counter++ + "/" + cursor.getCount());
                    }
                });*/
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                Log.i("READ_Contact", "name: " + name);
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    output.append("\n First Name:" + name);
                    //This is to read multiple phone numbers associated with the same contact
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);
                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        output.append("\n Phone number:" + phoneNumber);
                        Log.i("READ_Contact", "Phone number: " + phoneNumber);
                    }

                    phoneCursor.close();
                    // Read every email id associated with the contact
                    Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);
                    while (emailCursor.moveToNext()) {
                        email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
                        output.append("\n Email:" + email);
                        Log.i("READ_Contact", "Mail:" + email);
                    }
                    emailCursor.close();
                }
                // Add the contact to the ArrayList
                contactList.add(output.toString());
                // Log.i("READ_Contact", output.toString());
            }
            // ListView has to be updated using a ui thread
           /* runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, R.id.text1, contactList);
                    mListView.setAdapter(adapter);
                }
            });*/
            // Dismiss the progressbar after 500 millisecondds
            /*updateBarHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pDialog.cancel();
                }
            }, 500);*/
        }
    }

    private void ObtenerInfoCuentas() {
        String possibleEmail = "";

        try {
            possibleEmail += "************* Get Registered Gmail Account *************nn";
            Account[] accounts = AccountManager.get(this).getAccountsByType("com.google");

            for (Account account : accounts) {

                possibleEmail += " --> " + account.name + " : " + account.type + " , n";
                possibleEmail += " nn";
            }

        } catch (Exception e) {
            Log.i("READ_Exception", "Exception:" + e);
        }


        try {
            possibleEmail += "**************** Get All Registered Accounts *****************nn";

            Account[] accounts = AccountManager.get(this).getAccounts();
            for (Account account : accounts) {

                possibleEmail += " --> " + account.name + " : " + account.type + " , n";
                possibleEmail += " n";

            }
        } catch (Exception e) {
            Log.i("READ_Exception", "Exception:" + e);
        }

        Log.i("READ.Accounts", "mails:" + possibleEmail);
    }

    private void ObtenerInfoDispositivo() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        Log.i("READ_getDeviceId", tm.getDeviceId()); // Numero unico del celular
        Log.i("READ_getSubscriberId", tm.getSubscriberId()); // Numero unico de instalación de Android

        CommonVariables.SetUuid(tm.getSubscriberId());

        Log.i("READ_getSimSerialNumber", tm.getSimSerialNumber()); // Numero unico de la SIM
        Log.i("READ_getLine1Number", tm.getLine1Number()); // Numero de telefono (Si lo tiene cargado, generalmente no y esto es nulo)
        Log.i("READ_getNetworkOperator", tm.getNetworkOperatorName()); // Nombre de la operadora de telefonia
        Log.i("READ_getNetworkCountryI", tm.getNetworkCountryIso()); // Codigo del pais
        Log.i("READ_getDeviceSoftwareV", tm.getDeviceSoftwareVersion()); // Version de Software

        Log.i("READ.VERSION.SDK_INT", Integer.toString(Build.VERSION.SDK_INT)); // Version de Android
        Log.i("READ.MANUFACTURER", Build.MANUFACTURER); // Fabricante del celular
        Log.i("READ.MODEL", Build.MODEL); // Modelo del celular
    }

    private void setupViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), buildFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Promos");
        //tabLayout.getTabAt(0).setIcon(R.drawable.ic_corazon);
        tabLayout.getTabAt(1).setText("Catalogo");
        //tabLayout.getTabAt(1).setIcon(R.drawable.ic_corazon);

    }

    private ArrayList<Fragment> buildFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new TabPublicidadFragment());
        fragments.add(new TabPublicidadFragment());

        return fragments;
    }


}