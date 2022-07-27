package com.marwa.moviesproject.modules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.auth.FirebaseAuth;
import com.marwa.moviesproject.R;
import com.marwa.moviesproject.network.checkConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class splash extends AppCompatActivity {
    FirebaseAuth auth=null;
    @BindView(R.id.movie)
            TextView movie;
    @BindView(R.id.connexion)
            TextView connexion;
    @BindView(R.id.swipeup)
            SwipeRefreshLayout swipeup;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        YoYo.with(Techniques.ZoomInLeft)
                .duration(2000)
                .playOn(movie);
        auth= FirebaseAuth.getInstance();
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeup.setRefreshing(true);
                if (checkConnection.isConnected(splash.this)) {
                    swipeup.setRefreshing(false);
                        Intent inttologin = new Intent(splash.this, Authentification.class);
                        startActivity(inttologin);
                        finish();

                }
                else {
                    swipeup.setRefreshing(false);
                    connexion.setVisibility(View.VISIBLE);
                    swipeup.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            if (checkConnection.isConnected(splash.this)) {
                                Intent inttologin = new Intent(splash.this, Authentification.class);
                                startActivity(inttologin);
                                finish();
                            }
                            swipeup.setRefreshing(false);
                        }
                    });



                }
            }

        },4000);



    }


}