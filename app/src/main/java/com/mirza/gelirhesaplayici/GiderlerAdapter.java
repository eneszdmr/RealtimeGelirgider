package com.mirza.gelirhesaplayici;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GiderlerAdapter extends RecyclerView.Adapter<GiderlerAdapter.CardTasarimTutucu> {
    private Context mcontext;
    private List<Giderler> giderlerList;
    private Veritabani vt;

    public GiderlerAdapter(Context mcontext, List<Giderler> giderlerList, Veritabani vt) {
        this.mcontext = mcontext;
        this.giderlerList = giderlerList;
        this.vt=vt;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gelir_cardtasarim, parent, false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardTasarimTutucu holder, int position) {
        final Giderler gider = giderlerList.get(position);

        holder.textViewName.setText(gider.getGider());
        holder.textViewTutar.setText(String.valueOf(gider.getGiderTutar()));
        holder.imageViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(mcontext, holder.imageViewMore);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_sil:
                                Snackbar.make(holder.imageViewMore, "Gider silinsin mi?", Snackbar.LENGTH_SHORT)
                                        .setAction("Evet", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                new GiderlerDatabaseAccessObject().GiderSil(vt, gider.getGiderid());

                                                giderlerList = new GiderlerDatabaseAccessObject().tumGiderler(vt);
                                                notifyDataSetChanged();


                                                Toast.makeText(mcontext.getApplicationContext(), gider.getGider() + " - " + gider.getGiderTutar() + " silinmiştir.", Toast.LENGTH_SHORT).show();

                                            }
                                        })
                                        .show();
                                return true;
                            case R.id.action_guncelle:
                                alertgoster(gider);

                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return giderlerList.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewTutar;
        private ImageView imageViewMore;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewTutar = itemView.findViewById(R.id.textViewTutar);
            imageViewMore = itemView.findViewById(R.id.imageViewMore);
        }
    }



    public void alertgoster(final Giderler gider1) {
        LayoutInflater layout = LayoutInflater.from(mcontext);
        View tasarim = layout.inflate(R.layout.alert_gider, null);

        final EditText editTextGelir = tasarim.findViewById(R.id.editTextGider);
        final EditText editTextGelirTutar = tasarim.findViewById(R.id.editTextGiderTutar);

        editTextGelir.setText(gider1.getGider());
        editTextGelirTutar.setText(String.valueOf(gider1.getGiderTutar()));

        final AlertDialog.Builder gider = new AlertDialog.Builder(mcontext);
        gider.setTitle("Güncelle");
        gider.setView(tasarim);

        gider.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String gider_ekle = editTextGelir.getText().toString().trim();
                Double gider_tutar = Double.parseDouble(editTextGelirTutar.getText().toString().trim());




                new GiderlerDatabaseAccessObject().GiderGuncelle(vt, gider1.getGiderid(), gider_ekle, gider_tutar);

                giderlerList = new GiderlerDatabaseAccessObject().tumGiderler(vt);
                notifyDataSetChanged();

                Toast.makeText(mcontext.getApplicationContext(), gider_ekle + " - " + gider_tutar + " olarak Güncellenmiştir", Toast.LENGTH_SHORT).show();


            }
        });

        gider.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        gider.create().show();


    }
}
