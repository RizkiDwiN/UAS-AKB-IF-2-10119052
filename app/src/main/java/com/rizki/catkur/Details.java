package com.rizki.catkur;
/**
 * Tanggal Pengerjaan : 10 AGUSTUS 2022 , 13.56 WIB
 * NIM : 10119052
 * Nama : Rizki Dwi Nugraha
 * Kelas : IF-2
 **/
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.appcompat.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;


public class Details extends AppCompatActivity {
    TextView mDetails;
    CatkurDatabase db;
    Catkur catkur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDetails = findViewById(R.id.cDetails);

        Intent i = getIntent();
        long id = i.getLongExtra("ID", 0);
        db = new CatkurDatabase(this);
        catkur = db.getCatkur(id);
        getSupportActionBar().setTitle(catkur.getTitle());
        mDetails.setText(catkur.getContent());
        mDetails.setMovementMethod(new ScrollingMovementMethod());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteCatkur(catkur.getID());
                Toast.makeText(getApplicationContext(), "Catatan Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.editCatkur) {
            Toast.makeText(Details.this, "Tombol Hapus", Toast.LENGTH_SHORT).show();
            Intent  i = new Intent(this,Edit.class);
            i.putExtra("ID",catkur.getID());
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
