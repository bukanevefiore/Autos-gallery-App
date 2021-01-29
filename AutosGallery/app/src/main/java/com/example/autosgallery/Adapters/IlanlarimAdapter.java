package com.example.autosgallery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.autosgallery.Models.IlanlarimPojo;
import com.example.autosgallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IlanlarimAdapter extends BaseAdapter {
    List<IlanlarimPojo> list;
    Context context;  // layout oluşturulması için

    public IlanlarimAdapter(List<IlanlarimPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // kişisel ilan listelenmesi layoutunun oluşturulması
        convertView= LayoutInflater.from(context).inflate(R.layout.ilanlarimlayout,parent,false);
        // layout tanimlamaları
        ImageView resim;
        TextView baslik,fiyat;
        resim=convertView.findViewById(R.id.ilanlarimIlanResim);
        baslik=convertView.findViewById(R.id.ilanlarimIlanBaslik);
        fiyat=convertView.findViewById(R.id.ilanlarimIlanFiyat);

        // baslik ve fiyat için set etme
        baslik.setText(list.get(position).getBaslik());
        fiyat.setText(list.get(position).getFiyat());
        // picasso kütp. ile resmimizi alıyoruz
        Picasso.get().load("http://192.168.1.4/autogallery/"+list.get(position).getResim()).into(resim);

        return convertView;
    }
}
