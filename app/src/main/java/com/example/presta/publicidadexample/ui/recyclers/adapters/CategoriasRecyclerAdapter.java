package com.example.presta.publicidadexample.ui.recyclers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.entities.Categoria;
import com.example.presta.publicidadexample.common.entities.HomeItem;
import com.example.presta.publicidadexample.common.entities.Producto;
import com.example.presta.publicidadexample.common.entities.Promocion;
import com.example.presta.publicidadexample.common.entities.Publicidad;
import com.example.presta.publicidadexample.common.enums.CategoriaItemTypeEnum;
import com.example.presta.publicidadexample.common.enums.ClaseHomeItemEnum;
import com.example.presta.publicidadexample.ui.recyclers.viewHolders.CategoriaViewHolder;
import com.example.presta.publicidadexample.ui.recyclers.viewHolders.ProductoViewHolder;
import com.example.presta.publicidadexample.ui.recyclers.viewHolders.PromocionViewHolder;
import com.example.presta.publicidadexample.ui.recyclers.viewHolders.PromocionesViewHolder;
import com.example.presta.publicidadexample.ui.recyclers.viewHolders.PublicidadViewHolder;

import java.util.ArrayList;

/**
 * Created by Presta on 18/04/2016.
 */
public class CategoriasRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Categoria> items;
    Context context;
    CategoriaItemTypeEnum clase;

    public CategoriasRecyclerAdapter(Context context, CategoriaItemTypeEnum clase) {
        this.context = context;
        this.items = new ArrayList<>();
        this.clase = clase;
    }

    // Este metodo se ejecuta cada vez que un elemento se tiene que dibujar
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == CategoriaItemTypeEnum.HORIZONTAL.ordinal()) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_categoria, parent, false);

            return new CategoriaViewHolder(itemView, context);
        }

        if (viewType == CategoriaItemTypeEnum.VERTICAL.ordinal()) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_categoria_vertical, parent, false);

            return new CategoriaViewHolder(itemView, context);
        }

        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return clase.ordinal();
    }

    // Este metodo se ejecuta cada vez que un elemento tiene que conectarse a la fuente de datos
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoriaViewHolder viewHolderCategoria = (CategoriaViewHolder) holder;
        Categoria currentCategoria = items.get(position);

        viewHolderCategoria.setNombre(currentCategoria.getNombre());
        viewHolderCategoria.setImg(currentCategoria.getImagen());
    }

    // La cantidad de items de la lista
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(@NonNull ArrayList<Categoria> itemsNuevos) {
        // Solo los agrego la primera vez.
        if (items.size() == 0) {
            items.addAll(itemsNuevos);
            notifyDataSetChanged();
        }
    }

}