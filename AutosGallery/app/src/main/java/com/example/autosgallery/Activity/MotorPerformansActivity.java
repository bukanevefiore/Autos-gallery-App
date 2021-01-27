package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autosgallery.Models.IlanVerPojo;
import com.example.autosgallery.R;

public class MotorPerformansActivity extends AppCompatActivity {

    EditText motorTipiEditText,motorHacmiEditText,suratEditText;
    Button motorBilgisiButon,motorBilgisiButonGeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_performans);
        tanimlamalar();
    }

    public void tanimlamalar(){
        motorTipiEditText=findViewById(R.id.motorTipiEditText);
        motorHacmiEditText=findViewById(R.id.motorHacmiEditText);
        suratEditText=findViewById(R.id.suratEditText);

        motorTipiEditText.setText(IlanVerPojo.getMotortipi());
        motorHacmiEditText.setText(IlanVerPojo.getMotorhacmi());
        suratEditText.setText(IlanVerPojo.getSurat());

        motorBilgisiButon=findViewById(R.id.motorBilgisiButon);
        motorBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!motorTipiEditText.getText().toString().equals("") && !motorHacmiEditText.getText().toString().equals("") && !suratEditText.getText().toString().equals("")) {
                IlanVerPojo.setMotortipi(motorTipiEditText.getText().toString());
                IlanVerPojo.setMotorhacmi(motorHacmiEditText.getText().toString());
                IlanVerPojo.setSurat(suratEditText.getText().toString());


                Intent intent=new Intent(MotorPerformansActivity.this,YakitActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Tüm bilgileri girin",Toast.LENGTH_LONG).show();
                }
            }
        });
        motorBilgisiButonGeri=findViewById(R.id.motorBilgisiButonGeri);
        motorBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(MotorPerformansActivity.this, AracBilgileriActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in_ters, R.anim.anim_out_ters);
                    finish();

            }
        });
    }
}