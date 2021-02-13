package com.example.autosgallery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autosgallery.Models.MesajModel;
import com.example.autosgallery.R;

import java.util.List;

public class MesajAdapter extends RecyclerView.Adapter {

    List<MesajModel> list;
    boolean state=false;
    static final int user=5,other=8;
    Context context;
    String userId;

    public MesajAdapter(List<MesajModel> list, Context context,String userId) {
        this.list = list;
        this.state = state;
        this.context = context;
        this.userId=userId;
    }

      // layout tanimlaması
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==user){
            view= LayoutInflater.from(context).inflate(R.layout.user,parent,false);
            return new ViewHolder(view);
        }else{
            view=LayoutInflater.from(context).inflate(R.layout.othet,parent,false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MesajModel m=list.get(position);
        ((ViewHolder)holder).setle(m);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView mesajbody;

        public ViewHolder(View itemView){
            super(itemView);
            if(state)
            {
                mesajbody=itemView.findViewById(R.id.userText);
            }else
                {
                mesajbody=itemView.findViewById(R.id.othetText);
            }
        }
        void setle(MesajModel msj)
        {
            mesajbody.setText(msj.getMesaj().toString());
        }
    }

    // veri tabanındaki from a göre layout döndürülmesini sağlayan değişkenler
    @Override
    public int getItemViewType(int position){
        if(list.get(position).getFrom().equals(userId))
        {
            state=true;
            return user;
        }else
        {
            state=false;
            return other;
        }
    }
}
