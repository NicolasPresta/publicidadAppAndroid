package com.example.presta.publicidadexample.ui.recyclers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.entities.HomeItem;
import com.example.presta.publicidadexample.common.entities.Producto;
import com.example.presta.publicidadexample.common.entities.Promocion;
import com.example.presta.publicidadexample.common.entities.Publicidad;
import com.example.presta.publicidadexample.common.enums.ClaseHomeItemEnum;
import com.example.presta.publicidadexample.ui.recyclers.viewHolders.ProductoViewHolder;
import com.example.presta.publicidadexample.ui.recyclers.viewHolders.PromocionViewHolder;
import com.example.presta.publicidadexample.ui.recyclers.viewHolders.PublicidadViewHolder;

import java.util.ArrayList;

/**
 * Created by Presta on 10/03/2016.
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<HomeItem> items;
    Context context;

    public HomeRecyclerAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    // Este metodo se ejecuta cada vez que un elemento se tiene que dibujar
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ClaseHomeItemEnum.PROMOCION.ordinal()) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_promocion, parent, false);

            return new PromocionViewHolder(itemView, context);
        }

        if (viewType == ClaseHomeItemEnum.PUBLICIDAD.ordinal()) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_publicidad, parent, false);

            return new PublicidadViewHolder(itemView, context);
        }

        if (viewType == ClaseHomeItemEnum.PRODUCTO.ordinal()) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_producto, parent, false);

            return new ProductoViewHolder(itemView, context);
        }

        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getClase();
    }

    // Este metodo se ejecuta cada vez que un elemento tiene que conectarse a la fuente de datos
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        if (viewType == ClaseHomeItemEnum.PROMOCION.ordinal()) {
            PromocionViewHolder viewHolderPromocion = (PromocionViewHolder) holder;
            Promocion currentPublicidad = (Promocion)items.get(position).getEntidad();

            viewHolderPromocion.setTitulo(currentPublicidad.getTitulo());
            viewHolderPromocion.setDescripcion(currentPublicidad.getDescripcion());
            viewHolderPromocion.setImg(currentPublicidad.getImagen());
            viewHolderPromocion.setId(currentPublicidad.getId());
        }

        if (viewType == ClaseHomeItemEnum.PUBLICIDAD.ordinal()) {
            PublicidadViewHolder viewHolderPublicidad = (PublicidadViewHolder) holder;
            Publicidad currentPublicidad = (Publicidad)items.get(position).getEntidad();

            viewHolderPublicidad.setTitulo(currentPublicidad.getTitulo());
            viewHolderPublicidad.setDescripcion(currentPublicidad.getDescripcion());
            viewHolderPublicidad.setImg(currentPublicidad.getImg());
            viewHolderPublicidad.setId(currentPublicidad.getId());
        }

        if (viewType == ClaseHomeItemEnum.PRODUCTO.ordinal()) {
            ProductoViewHolder viewHolderProducto = (ProductoViewHolder) holder;
            Producto currentProducto = (Producto)items.get(position).getEntidad();

            viewHolderProducto.setNombre(currentProducto.getNombre());
            viewHolderProducto.setPrecio(currentProducto.getPrecio());
            viewHolderProducto.setImg(currentProducto.getImagen());
        }

    }

    // La cantidad de items de la lista
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(@NonNull ArrayList<HomeItem> itemsNuevos) {
        items.addAll(itemsNuevos);
        notifyDataSetChanged();
    }

}

