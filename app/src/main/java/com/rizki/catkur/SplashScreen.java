package com.rizki.catkur;
/**
 * Tanggal Pengerjaan : 10 AGUSTUS 2022 , 13.56 WIB
 * NIM : 10119052
 * Nama : Rizki Dwi Nugraha
 * Kelas : IF-2
 **/
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    /** Ini merupakan waktu loading splash saat menuju activity login **/
    private int waktu_splash = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /**setelah loading splash selesai maka akan di teruskan ke activity login**/
                Intent i =new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        },waktu_splash);
    }
}