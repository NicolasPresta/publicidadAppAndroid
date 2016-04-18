package com.example.presta.publicidadexample.ui.recyclers.viewHolders;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.entities.Promocion;
import com.example.presta.publicidadexample.ui.CustomSliderView;
import com.example.presta.publicidadexample.ui.activities.PromocionDetalleActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Presta on 08/04/2016.
 */
public class PromocionesViewHolder extends RecyclerView.ViewHolder
        implements BaseSliderView.OnSliderClickListener{

    final Context context;
    ArrayList<Promocion> promociones;
    SliderLayout mSlider;
    Integer id;

    public PromocionesViewHolder(final View itemView, final Context context) {
        super(itemView);

        this.context = context;
        mSlider = (SliderLayout) itemView.findViewById(R.id.slider);
        mSlider.stopAutoCycle();
        //setIsRecyclable(false);
    }

    public void setId(Integer idParam) {
        id = idParam;
    }

    public void setPromociones(ArrayList<Promocion> prom) {
        promociones = prom;
        mSlider.removeAllSliders();

        for (Promocion promo: promociones) {

            CustomSliderView sliderView = new CustomSliderView(context);
            // initialize a SliderLayout
            sliderView
                    .description(promo.getTitulo())
                    .image(promo.getImagen())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            sliderView.bundle(new Bundle());
            sliderView.getBundle().putString("img", promo.getImagen());
            sliderView.getBundle().putInt("id", promo.getId());

            mSlider.addSlider(sliderView);

            //mSlider.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
            mSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
            //mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mSlider.setCustomIndicator((PagerIndicator) itemView.findViewById(R.id.custom_indicator));
            mSlider.setCustomAnimation(new DescriptionAnimation());
            mSlider.setDuration(3000);
        }

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(context, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(itemView.getContext(), PromocionDetalleActivity.class);

        Bundle param = new Bundle();
        param.putCharSequence("imgURL", slider.getBundle().getString("img"));
        param.putInt("id",  slider.getBundle().getInt("id"));

        intent.putExtras(param);

        //Si la SDK >= 21 puedo usar animacion para la trancisión entre las vistas.
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, ((CustomSliderView)slider).getImageView(), "shareImg");
            itemView.getContext().startActivity(intent, options.toBundle());
        } else {
            itemView.getContext().startActivity(intent);
        }
    }


}
