package com.mirza.gelirhesaplayici;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentGider extends Fragment {
    private Toolbar toolbarGider;
    private FloatingActionButton fabGider;
    private RecyclerView rvgider;
    private ArrayList<Giderler> giderlerArrayList;
    private GiderlerAdapter adapter;
    private Veritabani vt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gider, container, false);

        toolbarGider = rootView.findViewById(R.id.toolbarGider);
        fabGider = rootView.findViewById(R.id.fabGider);
        rvgider = rootView.findViewById(R.id.rvgider);
        toolbarGider.setTitle("Gider Ekle");

        vt = new Veritabani(getActivity());

        rvgider.setHasFixedSize(true);
        rvgider.setLayoutManager(new LinearLayoutManager(getActivity()));

        giderlerArrayList=new GiderlerDatabaseAccessObject().tumGiderler(vt);


        adapter=new GiderlerAdapter(getActivity(),giderlerArrayList,vt);
        rvgider.setAdapter(adapter);




        fabGider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertgidergoster();

            }
        });

        double toplamgider=0.0;
        for (Giderler gid:giderlerArrayList){
            toplamgider=toplamgider+gid.getGiderTutar();
        }
        toolbarGider.setSubtitle("toplam Gider :"+toplamgider);


        return rootView;
    }

    public void alertgidergoster() {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());     //dışarıdan tasarım almak için
        View tasarim = layoutInflater.inflate(R.layout.alert_gider, null);

        final EditText editTextGider = tasarim.findViewById(R.id.editTextGider);
        final EditText editTextGiderTutar = tasarim.findViewById(R.id.editTextGiderTutar);

        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());      //Alert Dialog oluşturmak için
        ad.setTitle("Gider Ekleyin");
        ad.setView(tasarim); //tasarım belirt
        ad.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String gider_ekle = editTextGider.getText().toString().trim();
                Double gider_tutar = Double.parseDouble(editTextGiderTutar.getText().toString().trim());

                new GiderlerDatabaseAccessObject().GiderEkle(vt,gider_ekle,gider_tutar);

                giderlerArrayList=new GiderlerDatabaseAccessObject().tumGiderler(vt);

                adapter=new GiderlerAdapter(getActivity(),giderlerArrayList,vt);
                rvgider.setAdapter(adapter);

                Toast.makeText(getContext().getApplicationContext(), gider_ekle + " - " + gider_tutar + " olarak Eklendi", Toast.LENGTH_SHORT).show();


            }
        });
        ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        ad.create().show();  // Alert Dialogu göstermek için zorunlu


    }
}
