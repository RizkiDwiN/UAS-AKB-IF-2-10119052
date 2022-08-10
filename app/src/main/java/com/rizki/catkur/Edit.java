package com.rizki.catkur;
/**
 * Tanggal Pengerjaan : 10 AGUSTUS 2022 , 13.56 WIB
 * NIM : 10119052
 * Nama : Rizki Dwi Nugraha
 * Kelas : IF-2
 **/
import androidx.annotation.NonNull;
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

public class Edit extends AppCompatActivity {
    Toolbar toolbar;
    EditText catkurTitle, catkurDetails;
    Calendar c;
    String hariIni;
    String waktuSekarang;
    CatkurDatabase db;
    Catkur catkur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent i = getIntent();
        Long id = i.getLongExtra("ID",0);
        db = new CatkurDatabase(this);
        catkur = db.getCatkur(id);



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(catkur.getTitle());
        catkurTitle = findViewById(R.id.catkurTitle);
        catkurDetails = findViewById(R.id.catkurDetails);

        catkurTitle.setText(catkur.getTitle());
        catkurDetails.setText(catkur.getContent());


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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save){
            if (catkurTitle.getText().length() != 0){
                catkur.setTitle(catkurTitle.getText().toString());
                catkur.setContent(catkurDetails.getText().toString());
                int ID = db.editCatkur(catkur);
                if (ID == catkur.getID()){
                    Toast.makeText(this, "Catatan berhasil diubah", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else{
                    Toast.makeText(this, "Catatan gagal diubah", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(getApplicationContext(),Details.class);
                i.putExtra("ID",catkur.getID());
                startActivity(i);


            }else {
                catkurTitle.setError("Judul tidak boleh kosong!");
            }
        }else if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "Dibatalkan", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}