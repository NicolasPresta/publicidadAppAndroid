package com.example.presta.publicidadexample.ui.recyclers.viewHolders;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.entities.Categoria;
import com.example.presta.publicidadexample.ui.recyclers.adapters.CategoriasRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Presta on 17/04/2016.
 */
public class CategoriasViewHolder extends RecyclerView.ViewHolder {

    final Context context;
    private RecyclerView mRecycler;
    private CategoriasRecyclerAdapter adapter;

    public CategoriasViewHolder(final View itemView, final Context context) {
        super(itemView);

        this.context = context;
        adapter = new CategoriasRecyclerAdapter(context);

        mRecycler = (RecyclerView) itemView.findViewById(R.id.recycler_categorias);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.setLayoutManager(layoutManager);


        // Hace el setup del RecyclerView
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(adapter);

    }

    public void setCategorias(ArrayList<Categoria> cat) {
        adapter.addAll(cat);
    }
}