package com.example.presta.publicidadexample.ui.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presta.publicidadexample.ui.activities.PromocionDetalleActivity;
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
        holder.setId(currentPublicidad.getId());
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

    public class TagArtistViewHolder extends RecyclerView.ViewHolder {

        TextView publicidadTitulo;
        TextView publicidadDescripcion;
        ImageView publicidadImagen;
        String imgURL;
        Integer id;

        public TagArtistViewHolder(final View itemView) {
            super(itemView);

            publicidadTitulo = (TextView) itemView.findViewById(R.id.txt_titulo);
            publicidadDescripcion = (TextView) itemView.findViewById(R.id.txt_descripcion);
            publicidadImagen = (ImageView) itemView.findViewById(R.id.img_publicidad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(itemView.getContext(), PromocionDetalleActivity.class);

                    Bundle param = new Bundle();
                    param.putCharSequence("titulo", publicidadTitulo.getText());
                    param.putCharSequence("descripcion", publicidadDescripcion.getText());
                    param.putCharSequence("imgURL", imgURL);
                    param.putInt("id", id);

                    intent.putExtras(param);

                    // Si la SDK >= 21 puedo usar animacion para la trancisión entre las vistas.
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, publicidadImagen, "shareImg");
                        itemView.getContext().startActivity(intent, options.toBundle());
                    } else {
                        itemView.getContext().startActivity(intent);
                    }

                }
            });

        }

        public void setTitulo(String titulo) {
            publicidadTitulo.setText(titulo);
        }

        public void setId(Integer idParam) {
            id = idParam;
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

