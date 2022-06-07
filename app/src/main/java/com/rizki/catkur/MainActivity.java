package com.rizki.catkur;
/**
 * Tanggal Pengerjaan : 06 JUNI 2022 , 22.09 WIB
 * NIM : 10119052
 * Nama : Rizki Dwi Nugraha
 * Kelas : IF-2
 **/
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Catkur> catkur;

    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selecttedFragment = null;

                switch (menuItem.getItemId()){
                    case R.id.home:
                        selecttedFragment = new FragmentHome();
                        break;
                    case R.id.profil:
                        selecttedFragment = new FragmentProfile();
                        break;
                    case R.id.info:
                        selecttedFragment = new FragmentInfo();
                        break;

                }


                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selecttedFragment).commit();

                return true;
            }
        });
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CatkurDatabase db = new CatkurDatabase(this);
        catkur = db.getCatkur();
        recyclerView = findViewById(R.id.listOfCatkur);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,catkur);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add){
            Intent i = new Intent(this,AddCatkur.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}