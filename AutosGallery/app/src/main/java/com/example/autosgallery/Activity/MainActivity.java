package com.example.autosgallery.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.example.autosgallery.Adapters.FavoriSliderAdapter;
import com.example.autosgallery.Adapters.SliderAdapter;
import com.example.autosgallery.Models.FavoriSliderPojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;
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
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences sharedPreferences;
    String navHeaderText;
    TextView navHeaderTextView;
    Button ilanverbuton, ilanlarimMenuButon,ilanlarButon,iletisimBilgileri;
    String uye_id;  // slider için
    FavoriSliderAdapter favoriSliderAdapter;
    ViewPager mainActivitySliderFavori;
    CircleIndicator mainActivitysliderNokta;
    

    SharedPreferences.Editor editor;   // uygulamadan çıkış yapmak için

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
        uye_id= sharedPreferences.getString("uye_id",null);

        tanimlamalar();
        getFavoriSlider();

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

        // slider
        mainActivitySliderFavori=findViewById(R.id.mainActivitySliderFavori);
        mainActivitysliderNokta=findViewById(R.id.mainActivitysliderNokta);

        ilanverbuton=findViewById(R.id.ilanVerButon);
        ilanverbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,IlanBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

        // kişiye özel ilanlarin listelenmesi
        ilanlarimMenuButon=findViewById(R.id.ilanlarimMenuButon);
        ilanlarimMenuButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,IlanlarimActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);

            }
        });

        ilanlarButon=findViewById(R.id.ilanlarButon);
        ilanlarButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,IlanlarActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
        iletisimBilgileri=findViewById(R.id.iletisimBilgileri);
        iletisimBilgileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,KisiBilgiActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }

    public void getFavoriSlider(){
        Call<List<FavoriSliderPojo>> request= ManagerAll.getInstance().mainSetSlider(uye_id);
        request.enqueue(new Callback<List<FavoriSliderPojo>>() {
            @Override
            public void onResponse(Call<List<FavoriSliderPojo>> call, Response<List<FavoriSliderPojo>> response) {
                if(response.body().get(0).isTf()){
                    if(response.body().size()>0)
                    {
                        favoriSliderAdapter = new FavoriSliderAdapter(response.body(), MainActivity.this, MainActivity.this);
                        mainActivitySliderFavori.setAdapter(favoriSliderAdapter);
                        mainActivitysliderNokta.setViewPager(mainActivitySliderFavori);  // viewpage i noktalara set etme
                        mainActivitysliderNokta.bringToFront();  // noktaları resmin önine getirme
                    }

                }
                else{
                    favoriSliderAdapter = new FavoriSliderAdapter(response.body(), MainActivity.this, MainActivity.this);
                    mainActivitySliderFavori.setAdapter(favoriSliderAdapter);
                    mainActivitysliderNokta.setViewPager(mainActivitySliderFavori);  // viewpage i noktalara set etme
                    mainActivitysliderNokta.bringToFront();  // noktaları resmin önine getirme
                }

            }

            @Override
            public void onFailure(Call<List<FavoriSliderPojo>> call, Throwable t) {

                Log.d("mainSliderhata",t.getMessage());
            }
        });
    }

    // favoriye ekleme çıkarma sonucunda main activity deki slider anlık güncelleme yapması için method
    @Override
    protected void onRestart(){
        super.onRestart();
        getFavoriSlider();
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