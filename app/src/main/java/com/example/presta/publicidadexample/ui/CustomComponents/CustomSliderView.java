package com.example.presta.publicidadexample.ui.CustomComponents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.example.presta.publicidadexample.R;

/**
 * Created by Presta on 08/04/2016.
 */
public class CustomSliderView extends BaseSliderView {

    public CustomSliderView(Context context) {
        super(context);
    }

    ImageView target;

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.render_image_slider,null);
        target = (ImageView)v.findViewById(R.id.slider_img);
        bindEventAndShow(v, target);
        return v;
    }

    public ImageView getImageView(){
        return target;
    }
}

