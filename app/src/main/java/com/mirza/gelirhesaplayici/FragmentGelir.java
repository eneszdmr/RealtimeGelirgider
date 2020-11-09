package com.mirza.gelirhesaplayici;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentGelir extends Fragment {
    private Toolbar toolbarGelir;
    private FloatingActionButton fabGelir;
    private RecyclerView rvgelir;
    private ArrayList<Gelirler> gelirlerArrayList;
    private GelirlerAdapter adapter;
    private Veritabani vt;
    public double toplamgelir = 0.0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gelir, container, false);



        fabGelir = rootView.findViewById(R.id.fabGelir);
        toolbarGelir = rootView.findViewById(R.id.toolbarGelir);
        rvgelir = rootView.findViewById(R.id.rvgelir);
        toolbarGelir.setTitle("Gelir Ekle");


        vt = new Veritabani(getActivity());
        rvgelir.setHasFixedSize(true);
        rvgelir.setLayoutManager(new LinearLayoutManager(getActivity()));
        gelirlerArrayList = new GelirlerDatabaseAccessObject().tumGelirler(vt);
        adapter = new GelirlerAdapter(getActivity(), gelirlerArrayList, vt);
        rvgelir.setAdapter(adapter);
        fabGelir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertgoster();


            }
        });

        for (Gelirler gel : gelirlerArrayList) {
            toplamgelir = toplamgelir + gel.getGelirTutar();
        }
        toolbarGelir.setSubtitle("toplam Gelir: " + toplamgelir);
        return rootView;
    } //oncreate finish




    public void alertgoster() {
        LayoutInflater layout = LayoutInflater.from(getActivity());
        View tasarim = layout.inflate(R.layout.alert_gelir, null);

        final EditText editTextGelir = tasarim.findViewById(R.id.editTextGider);
        final EditText editTextGelirTutar = tasarim.findViewById(R.id.editTextGiderTutar);

        final AlertDialog.Builder gelir = new AlertDialog.Builder(getActivity());
        gelir.setTitle("Gelir Ekle");
        gelir.setView(tasarim);

        gelir.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                String gelir_ekle = editTextGelir.getText().toString().trim();
                Double gelir_tutar = Double.parseDouble(editTextGelirTutar.getText().toString().trim());

                new GelirlerDatabaseAccessObject().GelirEkle(vt, gelir_ekle, gelir_tutar);
                gelirlerArrayList = new GelirlerDatabaseAccessObject().tumGelirler(vt);
                adapter = new GelirlerAdapter(getActivity(), gelirlerArrayList, vt);
                rvgelir.setAdapter(adapter);
                Toast.makeText(getContext().getApplicationContext(), gelir_ekle + " - " + gelir_tutar + " olarak Eklendi", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getActivity(),MainActivity.class);
                intent.putExtra("gelir",toplamgelir);
                intent.putExtra("tutar",editTextGelirTutar.getText().toString());
                startActivity(intent);


            }
        });

        gelir.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        gelir.create().show();
    }
}


