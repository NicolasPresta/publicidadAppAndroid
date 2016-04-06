package com.example.presta.publicidadexample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.rest.get.ApiGetAdapter;
import com.example.presta.publicidadexample.rest.get.jsonModel.PublicidadesResponse;
import com.example.presta.publicidadexample.ui.recyclers.adapters.PublicidadRecyclerAdapter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Presta on 05/04/2016.
 */
public class NovedadesActivity extends AppCompatActivity {

    //region "-- ATRIBUTOS --"

    // componentes de mainLayout
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navView;

    private RecyclerView mRecycler;
    private PublicidadRecyclerAdapter adapter;

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novedades);

        cargarMenuLateral();
        cargarToolBar();

        adapter = new PublicidadRecyclerAdapter(this);

        mRecycler = (RecyclerView) this.findViewById(R.id.list_publicidades);

        // Hace el setup del RecyclerView
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(adapter);

        // Consulta las publicidades a la API
        requestInicialPublicidades();

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
    //endregion

    //region "-- PRIVATE METHODS --"

    private void requestInicialPublicidades() {
        ApiGetAdapter.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PublicidadesResponse>() {
                    @Override
                    public void onNext(PublicidadesResponse publicidadesResponse) {
                        adapter.addAll(publicidadesResponse.getPublicidades());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    //endregion

    private void cargarMenuLateral() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;

                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Class activityClass;

        switch (menuItem.getItemId()) {
            case R.id.menu_inicio:
                activityClass = NovedadesActivity.class;
                break;
            case R.id.menu_tarjeta:
                activityClass = NovedadesActivity.class;
                break;
            case R.id.menu_sucursales:
                activityClass = NovedadesActivity.class;
                break;
            case R.id.menu_fila:
                activityClass = NovedadesActivity.class;
                break;
            default:
                activityClass = NovedadesActivity.class;
        }

        Intent intent = new Intent(this, activityClass);
        this.startActivity(intent);

        // Close the navigation drawer
        drawerLayout.closeDrawers();
    }

    private void cargarToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_share);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Novedades");
    }

}
