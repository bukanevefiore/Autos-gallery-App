package com.example.autosgallery.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.autosgallery.R;

public class AlertDialogClass {

    // ilan_id üzerinden ilan silme işlemi için method
    // method paremetresinde activity olmasının sebebi oluşturduğumuz alertlayout tasarımını kullanabilmek amaaçlı
    public void ilanlarimAlertDialog(Activity activity,String ilan_id){
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertlayout,null);

        Button button=view.findViewById(R.id.buton);
        Button buttonCik=view.findViewById(R.id.butonCik);

        AlertDialog.Builder alert=new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog=alert.create();

        buttonCik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
