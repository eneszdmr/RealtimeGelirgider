package com.mirza.gelirhesaplayici;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class FragmentHomepage extends Fragment {
    CardView cardHome;
    CardView cardRapor;
    CardView cardStatic;
    CardView cardAlarm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_homepage, container, false);
        cardHome = rootView.findViewById(R.id.cardHome);
        cardRapor = rootView.findViewById(R.id.cardRapor);
        cardStatic = rootView.findViewById(R.id.cardStatic);
        cardAlarm = rootView.findViewById(R.id.cardAlarm);

        cardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Home tıklanıldı");

            }
        });
        cardAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Çıkış tıklanıldı");

            }
        });
        cardRapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Rapor tıklanıldı");

            }
        });
        cardStatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("İstatistik tıklanıldı");

            }
        });

        return rootView;
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


}
