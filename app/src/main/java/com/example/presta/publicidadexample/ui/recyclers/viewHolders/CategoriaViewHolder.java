package com.example.presta.publicidadexample.ui.recyclers.viewHolders;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.helpers.UnitsHelper;
import com.example.presta.publicidadexample.ui.activities.CategoriaActivity;
import com.squareup.picasso.Picasso;

import java.util.Random;


/**
 * Created by Presta on 18/04/2016.
 */
public class CategoriaViewHolder extends RecyclerView.ViewHolder {

    final Context context;
    ImageView categoriaImagen;
    TextView nombre;
    String imgURL;
    Integer id;
    CardView card;

    public CategoriaViewHolder(final View itemView, final Context context) {
        super(itemView);

        this.context = context;

        categoriaImagen = (ImageView) itemView.findViewById(R.id.img);
        nombre = (TextView) itemView.findViewById(R.id.txt_nombre);
        card = (CardView) itemView.findViewById(R.id.categoria_card);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(itemView.getContext(), CategoriaActivity.class);

                Bundle param = new Bundle();
                param.putCharSequence("categoria", nombre.getText());
                param.putCharSequence("urlImg", imgURL);

                Log.i("Categoria", "paso por aca");

                intent.putExtras(param);

                // Si la SDK >= 21 puedo usar animacion para la trancisión entre las vistas.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, nombre, "shareCatTxt");
                   /* ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                            Pair.create((View)categoriaImagen, "shareCatImg"),
                            Pair.create((View)nombre, "shareCatTxt"));*/

                    itemView.getContext().startActivity(intent, options.toBundle());
                } else {
                    itemView.getContext().startActivity(intent);
                }

            }
        });

    }

    public void setNombre(String nom) {
        nombre.setText(nom);
    }

    public void setImg(String url) {
        imgURL = url;


        // Esto es para fijar el alto de la imagen al alto que va a tener cuando se descargue la img.
        Integer ancho = Math.round(UnitsHelper.convertDpToPixel(context.getResources().getDimension(R.dimen.diametro_categoria_home), context));
        Integer alto = ancho;

        categoriaImagen.setMinimumHeight(alto);
        imgURL = url;
        if (url != null) {
            Picasso.with(context)
                    .load(url)
                    .fit()
                    .centerCrop()
                    .into(categoriaImagen);
        } else {
            Picasso.with(context)
                    .load(R.drawable.img_placeholder)
                    .into(categoriaImagen);
        }


    }

}
