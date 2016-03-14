package com.example.presta.publicidadexample.ui.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presta.publicidadexample.PromocionDetalleActivity;
import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.entities.Publicidad;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Presta on 10/03/2016.
 */
public class PublicidadAdapter extends RecyclerView.Adapter<PublicidadAdapter.TagArtistViewHolder> {

    ArrayList<Publicidad> publicidades;
    Context context;

    public PublicidadAdapter(Context context) {
        this.context = context;
        this.publicidades = new ArrayList<>();
    }

    // Este metodo se ejecuta cada vez que un elemento se tiene que dibujar
    @Override
    public TagArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_publicidad, parent, false);


        return new TagArtistViewHolder(itemView);
    }

    // Este metodo se ejecuta cada vez que un elemento tiene que conectarse a la fuente de datos
    @Override
    public void onBindViewHolder(TagArtistViewHolder holder, int position) {
        Publicidad currentPublicidad = publicidades.get(position);

        holder.setTitulo(currentPublicidad.getTitulo());
        holder.setDescripcion(currentPublicidad.getDescripcion());
        holder.setImg(currentPublicidad.getImg());
        holder.setPosition(position);
    }

    // La cantidad de items de la lista
    @Override
    public int getItemCount() {
        return publicidades.size();
    }

    public void addAll(@NonNull ArrayList<Publicidad> publicidadesNuevas) {

        if (publicidadesNuevas == null)
            throw new NullPointerException("Los item están en nulos");

        publicidades.addAll(publicidadesNuevas);
        Log.i("test1", "pasa por aca");
        notifyDataSetChanged();
    }

    @TargetApi(21)
    public class TagArtistViewHolder extends RecyclerView.ViewHolder {

        TextView publicidadTitulo;
        TextView publicidadDescripcion;
        ImageView publicidadImagen;
        String imgURL;
        Integer position;

        public TagArtistViewHolder(final View itemView) {
            super(itemView);

            publicidadTitulo = (TextView) itemView.findViewById(R.id.txt_titulo);
            publicidadDescripcion = (TextView) itemView.findViewById(R.id.txt_descripcion);
            publicidadImagen = (ImageView) itemView.findViewById(R.id.img_publicidad);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View publicidadImagen2 = view.findViewById(R.id.img_publicidad);
                        publicidadImagen2.setTransitionName("shareImg" + position);
                        Intent intent = new Intent(itemView.getContext(), PromocionDetalleActivity.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, publicidadImagen2, "shareImg" + position);
                        Bundle param = new Bundle();
                        param.putCharSequence("titulo", publicidadTitulo.getText());
                        param.putCharSequence("descripcion", publicidadDescripcion.getText());
                        param.putCharSequence("imgURL", imgURL);
                        param.putInt("position", position);
                        intent.putExtras(param);
                        itemView.getContext().startActivity(intent, options.toBundle());
                    }
                });

        }

        public void setTitulo(String titulo) {
            publicidadTitulo.setText(titulo);
        }

        public void setPosition(Integer positionParam) {
            position = positionParam;
        }

        public void setDescripcion(String descripcion) {
            publicidadDescripcion.setText(descripcion);
        }

        public void setImg(String url) {
            imgURL = url;
            if (url != null) {
                Picasso.with(context)
                        .load(url)
                        .placeholder(R.drawable.img_placeholder)
                        .into(publicidadImagen);
            } else {
                Picasso.with(context)
                        .load(R.drawable.img_placeholder)
                        .into(publicidadImagen);
            }
        }

    }
}

