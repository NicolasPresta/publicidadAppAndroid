package com.example.presta.publicidadexample.ui.recyclers.viewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presta.publicidadexample.R;
import com.squareup.picasso.Picasso;


/**
 * Created by Presta on 18/04/2016.
 */
public class CategoriaViewHolder extends RecyclerView.ViewHolder {

    final Context context;
    ImageView categoriaImagen;
    TextView nombre;
    String imgURL;
    Integer id;

    public CategoriaViewHolder(final View itemView, final Context context) {
        super(itemView);

        this.context = context;

        categoriaImagen = (ImageView) itemView.findViewById(R.id.img);
        nombre = (TextView) itemView.findViewById(R.id.txt_nombre);

        /* TOOD: ir al activity de categoria filtrando por esa categoria
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(itemView.getContext(), PromocionDetalleActivity.class);

                Bundle param = new Bundle();
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
        */
    }

    public void setNombre(String nom) {
        nombre.setText(nom);
    }

    public void setImg(String url) {
        imgURL = url;
        if (url != null) {
            Picasso.with(context)
                    .load(url)
                    //.placeholder(R.drawable.img_placeholder)
                    .into(categoriaImagen);
        } else {
            Picasso.with(context)
                    .load(R.drawable.img_placeholder)
                    .into(categoriaImagen);
        }


    }

}
