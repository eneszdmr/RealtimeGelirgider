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

public class GelirlerAdapter extends RecyclerView.Adapter<GelirlerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Gelirler> gelirlerList;
    private Veritabani vt;

    public GelirlerAdapter(Context mContext, List<Gelirler> gelirlerList, Veritabani vt) {
        this.mContext = mContext;
        this.gelirlerList = gelirlerList;
        this.vt = vt;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gelir_cardtasarim, parent, false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardTasarimTutucu holder, int position) {
        final Gelirler gelir = gelirlerList.get(position);

        holder.textViewName.setText(gelir.getGelir());
        holder.textViewTutar.setText(String.valueOf(gelir.getGelirTutar()));
        holder.imageViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.imageViewMore);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_sil:
                                Snackbar.make(holder.imageViewMore, "Gelir silinsin mi?", Snackbar.LENGTH_SHORT)
                                        .setAction("Evet", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                new GelirlerDatabaseAccessObject().GelirSil(vt, gelir.getGelirid());

                                                gelirlerList = new GelirlerDatabaseAccessObject().tumGelirler(vt);
                                                notifyDataSetChanged();
                                                Toast.makeText(mContext.getApplicationContext(), gelir.getGelir() + " - " + gelir.getGelirTutar() + " silinmiştir.", Toast.LENGTH_SHORT).show();

                                            }
                                        })
                                        .show();
                                return true;
                            case R.id.action_guncelle:
                                alertgoster(gelir);
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
        return gelirlerList.size();
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

    public void alertgoster(final Gelirler gelir1) {
        LayoutInflater layout = LayoutInflater.from(mContext);
        View tasarim = layout.inflate(R.layout.alert_gelir, null);

        final EditText editTextGelir = tasarim.findViewById(R.id.editTextGider);
        final EditText editTextGelirTutar = tasarim.findViewById(R.id.editTextGiderTutar);

        editTextGelir.setText(gelir1.getGelir());
        editTextGelirTutar.setText(String.valueOf(gelir1.getGelirTutar()));

        final AlertDialog.Builder gelir = new AlertDialog.Builder(mContext);
        gelir.setTitle("Güncelle");
        gelir.setView(tasarim);

        gelir.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String gelir_ekle = editTextGelir.getText().toString().trim();
                Double gelir_tutar = Double.parseDouble(editTextGelirTutar.getText().toString().trim());




                new GelirlerDatabaseAccessObject().GelirGuncelle(vt, gelir1.getGelirid(), gelir_ekle, gelir_tutar);

                gelirlerList = new GelirlerDatabaseAccessObject().tumGelirler(vt);
                notifyDataSetChanged();

                Toast.makeText(mContext.getApplicationContext(), gelir_ekle + " - " + gelir_tutar + " olarak Güncellenmiştir", Toast.LENGTH_SHORT).show();


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
