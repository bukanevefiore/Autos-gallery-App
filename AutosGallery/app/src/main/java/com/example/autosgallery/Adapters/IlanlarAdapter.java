package com.example.autosgallery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.autosgallery.Models.IlanlarPojo;
import com.example.autosgallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IlanlarAdapter extends BaseAdapter {

    List<IlanlarPojo> ilanlarPojos;
    Context context;

    public IlanlarAdapter(List<IlanlarPojo> ilanlarPojos, Context context) {
        this.ilanlarPojos = ilanlarPojos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ilanlarPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return ilanlarPojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(context).inflate(R.layout.tumilanlarlayout,parent,false);
        TextView baslik,fiyat,adres;
        ImageView resim;
        baslik=convertView.findViewById(R.id.ilanlarIlanBaslik);
        fiyat=convertView.findViewById(R.id.ilanlarIlanFiyat);
        adres=convertView.findViewById(R.id.ilanlarIlanAdres);
        resim=convertView.findViewById(R.id.ilanlarIlanResim);

        baslik.setText(ilanlarPojos.get(position).getBaslik());
        fiyat.setText(ilanlarPojos.get(position).getFiyat());
        adres.setText(ilanlarPojos.get(position).getIl()+" "+ilanlarPojos.get(position).getIlce()+""+ilanlarPojos.get(position).getMahalle());

        // picasso kütp. ile resmimizi alıyoruz
        Picasso.get().load("http://192.168.1.4/autogallery/"+ilanlarPojos.get(position).getResim()).resize(100,100).into(resim);

        return convertView;
    }
}
