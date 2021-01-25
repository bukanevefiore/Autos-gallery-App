package com.example.autosgallery.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.example.autosgallery.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences sharedPreferences;
    String navHeaderText;
    TextView navHeaderTextView;
    Button ilanverbuton;
    

    SharedPreferences.Editor editor;   // uygulamadan çıkış yapmak için

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        tanimlamalar();

  /*
        // naw header başlığına giriş yapan kullanıcının gmailini eklemek için kullanıyoruz
        sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
        navHeaderText=sharedPreferences.getString("uye_KullaniciAdi",null);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // gmail i navigation da gösterme
        View headerView=navigationView.getHeaderView(0);
        navHeaderTextView=headerView.findViewById(R.id.navHeaderTextt);
        try {
            navHeaderTextView.setText(navHeaderText);

        }
        catch (Exception e )  {
            Log.i("retrofit3", String.valueOf(e.getStackTrace()));
        }


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_slideshow, R.id.nav_gallery)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

*/


    }


    public void tanimlamalar(){
        ilanverbuton=findViewById(R.id.ilanVerButon);
        ilanverbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,IlanBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();



    }

}