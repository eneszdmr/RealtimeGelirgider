package com.mirza.gelirhesaplayici;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity  {
    private BottomNavigationView bottom_navigation;
    private Fragment temporaryFragment;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

Intent intent=getIntent();
String gelir=intent.getStringExtra("gelir");
String tutar=intent.getStringExtra("tutar");


FragmentManager fm=getSupportFragmentManager();
FragmentHomepage fragment=new FragmentHomepage();
fm.beginTransaction().replace(R.id.frame_holder,fragment).commit();

Bundle bundle=new Bundle();
bundle.putString("gelir",gelir);
bundle.putString("tutar",tutar);

fragment.setArguments(bundle);



        getSupportFragmentManager().beginTransaction().add(R.id.frame_holder, new FragmentHomepage()).commit();

        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setSelectedItemId(R.id.action_home);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.action_home) {
                    temporaryFragment = new FragmentHomepage();
                }
                if (item.getItemId() == R.id.action_gelir) {
                    temporaryFragment = new FragmentGelir();
                }
                if (item.getItemId() == R.id.action_gider) {
                    temporaryFragment = new FragmentGider();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_holder, temporaryFragment).commit();

                return true;
            }
        });
    }
}