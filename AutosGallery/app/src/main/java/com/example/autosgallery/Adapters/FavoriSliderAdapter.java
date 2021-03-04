package com.example.autosgallery.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.autosgallery.Activity.IlanDetayActivity;
import com.example.autosgallery.Activity.IlanlarActivity;
import com.example.autosgallery.Models.FavoriSliderPojo;
import com.example.autosgallery.Models.SliderPojo;
import com.example.autosgallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriSliderAdapter extends PagerAdapter {

    List<FavoriSliderPojo> list;
    Context context;
    LayoutInflater layoutInflater;
    Activity activity;

    public FavoriSliderAdapter(List<FavoriSliderPojo> list, Context context,Activity activity) {
        this.list = list;
        this.context = context;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.sliderlayout,container,false);
        ImageView imageView=view.findViewById(R.id.sliderImageView);
        Picasso.get().load("http://localhost/autogallery/"+list.get(position).getResimyolu()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // favori tablosunda kişiye ait favori ilan varsa ilan detaya geçiş yapar yoksa resime tıklanamaz
                if(list.get(position).getIlanid()!= null)
                {
                    Intent intent = new Intent(activity, IlanDetayActivity.class);
                    intent.putExtra("ilanid", list.get(position).getIlanid().toString());
                    activity.startActivity(intent);
                }
            }
        });
        container.addView(view);
        return view;
    }
    // favoriye ekleme çıkarma sonucunda main activity deki sliderda anlık güncelleme yapması için method
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}
