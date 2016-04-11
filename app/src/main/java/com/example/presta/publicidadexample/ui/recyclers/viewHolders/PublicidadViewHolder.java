package com.example.presta.publicidadexample.ui.recyclers.viewHolders;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.helpers.UnitsHelper;
import com.example.presta.publicidadexample.ui.activities.PromocionDetalleActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by Presta on 05/04/2016.
 */

public class PublicidadViewHolder extends RecyclerView.ViewHolder {

    final Context context;
    ImageView publicidadImagen;
    CardView card;
    String imgURL;
    Integer id;

    public PublicidadViewHolder(final View itemView, final Context context) {
        super(itemView);

        this.context = context;

        publicidadImagen = (ImageView) itemView.findViewById(R.id.img_publicidad);
        card = (CardView) itemView.findViewById(R.id.card);

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

    }

    public void setId(Integer idParam) {
        id = idParam;
    }

    public void setImg(String url, Float proporcion) {

        Integer alto = Math.round(proporcion * (UnitsHelper.getScreenWidthDp(context) - (2 * (int) context.getResources().getDimension(R.dimen.publicidad_card_margin))));

        publicidadImagen.setMinimumHeight(alto);
        imgURL = url;
        if (url != null) {
            Picasso.with(context)
                    .load(url)
                            //.placeholder(R.drawable.img_placeholder)
                    .into(publicidadImagen);
        } else {
            Picasso.with(context)
                    .load(R.drawable.img_placeholder)
                    .into(publicidadImagen);
        }


    }

    public void setImgAncho(Integer ancho) {

    }

    public void setImgAlto(Integer alto) {
        /*Integer altoDp = Math.round(UnitsHelper.convertPixelsToDp(alto, context));

        card.setLayoutParams(new CardView.LayoutParams(
                CardView.LayoutParams.MATCH_PARENT, altoDp));

        card.setMinimumHeight(altoDp);*/
    }
}
