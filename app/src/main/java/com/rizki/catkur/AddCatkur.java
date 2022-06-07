package com.rizki.catkur;
/**
 * Tanggal Pengerjaan : 06 JUNI 2022 , 22.09 WIB
 * NIM : 10119052
 * Nama : Rizki Dwi Nugraha
 * Kelas : IF-2
 **/
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class AddCatkur extends AppCompatActivity {
    Toolbar toolbar;
    EditText catkurTitle, catkurDetails;
    Calendar c;
    String hariIni;
    String waktuSekarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catkur);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Catatan Baru");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        catkurTitle = findViewById(R.id.catkurTitle);
        catkurDetails = findViewById(R.id.catkurDetails);

        catkurTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() != 0){
                    getSupportActionBar() .setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Mendapatkan Tanggal dan waktu hari ini
        c = Calendar.getInstance();
        hariIni = c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DAY_OF_MONTH);
        waktuSekarang = pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE));

        Log.d( "calendar", "Tanggal dan Waktu" + hariIni + " dan " + waktuSekarang);

    }

    private String pad(int i) {
        if(i<10)
            return "0"+i;
        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "Catatan Tidak Di Simpan",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        if(item.getItemId() == R.id.save){
            Catkur catkur = new Catkur(catkurTitle.getText().toString(),catkurDetails.getText().toString(),hariIni,waktuSekarang);
            CatkurDatabase db = new CatkurDatabase(this);
            db.addCatkur(catkur);
            Toast.makeText(this, "Catatan Berhasil Disimpan",Toast.LENGTH_SHORT).show();
            goToMain();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}