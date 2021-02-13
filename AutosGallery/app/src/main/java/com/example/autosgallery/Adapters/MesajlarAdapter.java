package com.example.autosgallery.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.autosgallery.Activity.ChatActivity;
import com.example.autosgallery.Activity.MesajlarActivity;
import com.example.autosgallery.Dialog.OtherId;
import com.example.autosgallery.Models.UserPojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MesajlarAdapter extends BaseAdapter  {

    List<String> otherIdList;
    String userId;
    Context context;
    Activity activity;

    public MesajlarAdapter(List<String> otherIdList, String userId, Context context, Activity activity) {
        this.otherIdList = otherIdList;
        this.userId = userId;
        this.context = context;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return otherIdList.size();
    }

    @Override
    public Object getItem(int position) {
        return otherIdList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.othet,parent,false);
        TextView textView;
        textView=convertView.findViewById(R.id.othetText);
        istekAt(otherIdList.get(position).toString(),textView);

        // Bu activity de kişilerin gmailine tıkladığımızda chat kısmına gidebilmemiz için oluşturuldu
        LinearLayout linearLayout =convertView.findViewById(R.id.mesajlarListe);
        linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(activity, ChatActivity.class);
               OtherId.setOtherId(otherIdList.get(position).toString()); // tıkladığımız iteme göre otherlist i setleme işlemi ile chat e geçiş
               activity.startActivity(intent);
           }
        });
        return convertView;
    }

    public void istekAt(String uye_id,final TextView textView){
        Call<UserPojo> request= ManagerAll.getInstance().getInformation(uye_id);
        request.enqueue(new Callback<UserPojo>() {
            @Override
            public void onResponse(Call<UserPojo> call, Response<UserPojo> response) {
                if(response.isSuccessful()){
                    textView.setText(response.body().getKadi()); // kullanıcı adını servisten alıp textviewe set işlemi

                }
            }

            @Override
            public void onFailure(Call<UserPojo> call, Throwable t) {

            }
        });
    }
}
