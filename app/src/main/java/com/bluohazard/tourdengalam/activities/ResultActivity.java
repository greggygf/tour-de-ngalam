package com.bluohazard.tourdengalam.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.detail.DetailRecommendationActivity;
import com.bluohazard.tourdengalam.activities.menu.SurveyRecommendationActivity;
import com.bluohazard.tourdengalam.models.Recommendation;
import com.bluohazard.tourdengalam.viewholders.RecommendationViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ResultActivity extends AppCompatActivity {

    double valueJarakToHarga, valueJarakToFasilitas, valueJarakToAkses, valueHargaToFasilitas, valueHargaToAkses, valueFasilitasToAkses;
    double valueHargaToJarak, valueFasilitasToJarak, valueAksesToJarak, valueFasilitasToHarga, valueAksesToHarga, valueAksesToFasilitas;

    double[][] matrix = new double[4][4];
    double[][] matrixC = new double[4][4];

    double kolomA, kolomB, kolomC, kolomD;
    double bobotPrioritasA, bobotPrioritasB, bobotPrioritasC, bobotPrioritasD;

    double nilaiAlternatifJarakGunungBromo, nilaiAlternatifJarakGunungSemeru, nilaiAlternatifJarakJatimPark3, nilaiAlternatifJarakMuseumAngkut, nilaiAlternatifJarakPantaiSempu, nilaiAlternatifJarakPantaiSendiki;
    double nilaiAlternatifHargaGunungBromo, nilaiAlternatifHargaGunungSemeru, nilaiAlternatifHargaJatimPark3, nilaiAlternatifHargaMuseumAngkut, nilaiAlternatifHargaPantaiSempu, nilaiAlternatifHargaPantaiSendiki;
    double nilaiAlternatifFasilitasGunungBromo, nilaiAlternatifFasilitasGunungSemeru, nilaiAlternatifFasilitasJatimPark3, nilaiAlternatifFasilitasMuseumAngkut, nilaiAlternatifFasilitasPantaiSempu, nilaiAlternatifFasilitasPantaiSendiki;
    double nilaiAlternatifAksesGunungBromo, nilaiAlternatifAksesGunungSemeru, nilaiAlternatifAksesJatimPark3, nilaiAlternatifAksesMuseumAngkut, nilaiAlternatifAksesPantaiSempu, nilaiAlternatifAksesPantaiSendiki;

    double bobotPrioritasJarakGunungBromo, bobotPrioritasHargaGunungBromo, bobotPrioritasFasilitasGunungBromo, bobotPrioritasAksesGunungBromo;
    double bobotPrioritasJarakGunungSemeru, bobotPrioritasHargaGunungSemeru, bobotPrioritasFasilitasGunungSemeru, bobotPrioritasAksesGunungSemeru;
    double bobotPrioritasJarakJatimPark3, bobotPrioritasHargaJatimPark3, bobotPrioritasFasilitasJatimPark3, bobotPrioritasAksesJatimPark3;
    double bobotPrioritasJarakMuseumAngkut, bobotPrioritasHargaMuseumAngkut, bobotPrioritasFasilitasMuseumAngkut, bobotPrioritasAksesMuseumAngkut;
    double bobotPrioritasJarakPantaiSempu, bobotPrioritasHargaPantaiSempu, bobotPrioritasFasilitasPantaiSempu, bobotPrioritasAksesPantaiSempu;
    double bobotPrioritasJarakPantaiSendiki, bobotPrioritasHargaPantaiSendiki, bobotPrioritasFasilitasPantaiSendiki, bobotPrioritasAksesPantaiSendiki;

    double nilaiTotalGunungBromo = 0, nilaiTotalGunungSemeru = 0, nilaiTotalJatimPark3 = 0, nilaiTotalMuseumAngkut = 0, nilaiTotalPantaiSempu = 0, nilaiTotalPantaiSendiki = 0;

    double nilaiAlternatif2JarakGunungBromo, nilaiAlternatif2JarakGunungSemeru, nilaiAlternatif2JarakJatimPark3, nilaiAlternatif2JarakMuseumAngkut, nilaiAlternatif2JarakPantaiSempu, nilaiAlternatif2JarakPantaiSendiki;
    double nilaiAlternatif2HargaGunungBromo, nilaiAlternatif2HargaGunungSemeru, nilaiAlternatif2HargaJatimPark3, nilaiAlternatif2HargaMuseumAngkut, nilaiAlternatif2HargaPantaiSempu, nilaiAlternatif2HargaPantaiSendiki;
    double nilaiAlternatif2FasilitasGunungBromo, nilaiAlternatif2FasilitasGunungSemeru, nilaiAlternatif2FasilitasJatimPark3, nilaiAlternatif2FasilitasMuseumAngkut, nilaiAlternatif2FasilitasPantaiSempu, nilaiAlternatif2FasilitasPantaiSendiki;
    double nilaiAlternatif2AksesGunungBromo, nilaiAlternatif2AksesGunungSemeru, nilaiAlternatif2AksesJatimPark3, nilaiAlternatif2AksesMuseumAngkut, nilaiAlternatif2AksesPantaiSempu, nilaiAlternatif2AksesPantaiSendiki;

    double bobotPrioritas2JarakGunungBromo, bobotPrioritas2HargaGunungBromo, bobotPrioritas2FasilitasGunungBromo, bobotPrioritas2AksesGunungBromo;
    double bobotPrioritas2JarakGunungSemeru, bobotPrioritas2HargaGunungSemeru, bobotPrioritas2FasilitasGunungSemeru, bobotPrioritas2AksesGunungSemeru;
    double bobotPrioritas2JarakJatimPark3, bobotPrioritas2HargaJatimPark3, bobotPrioritas2FasilitasJatimPark3, bobotPrioritas2AksesJatimPark3;
    double bobotPrioritas2JarakMuseumAngkut, bobotPrioritas2HargaMuseumAngkut, bobotPrioritas2FasilitasMuseumAngkut, bobotPrioritas2AksesMuseumAngkut;
    double bobotPrioritas2JarakPantaiSempu, bobotPrioritas2HargaPantaiSempu, bobotPrioritas2FasilitasPantaiSempu, bobotPrioritas2AksesPantaiSempu;
    double bobotPrioritas2JarakPantaiSendiki, bobotPrioritas2HargaPantaiSendiki, bobotPrioritas2FasilitasPantaiSendiki, bobotPrioritas2AksesPantaiSendiki;

    double nilaiTotal2GunungBromo = 0, nilaiTotal2GunungSemeru = 0, nilaiTotal2JatimPark3 = 0, nilaiTotal2MuseumAngkut = 0, nilaiTotal2PantaiSempu = 0, nilaiTotal2PantaiSendiki = 0;

    double nilaiAlternatif3JarakGunungBromo, nilaiAlternatif3JarakGunungSemeru, nilaiAlternatif3JarakJatimPark3, nilaiAlternatif3JarakMuseumAngkut, nilaiAlternatif3JarakPantaiSempu, nilaiAlternatif3JarakPantaiSendiki;
    double nilaiAlternatif3HargaGunungBromo, nilaiAlternatif3HargaGunungSemeru, nilaiAlternatif3HargaJatimPark3, nilaiAlternatif3HargaMuseumAngkut, nilaiAlternatif3HargaPantaiSempu, nilaiAlternatif3HargaPantaiSendiki;
    double nilaiAlternatif3FasilitasGunungBromo, nilaiAlternatif3FasilitasGunungSemeru, nilaiAlternatif3FasilitasJatimPark3, nilaiAlternatif3FasilitasMuseumAngkut, nilaiAlternatif3FasilitasPantaiSempu, nilaiAlternatif3FasilitasPantaiSendiki;
    double nilaiAlternatif3AksesGunungBromo, nilaiAlternatif3AksesGunungSemeru, nilaiAlternatif3AksesJatimPark3, nilaiAlternatif3AksesMuseumAngkut, nilaiAlternatif3AksesPantaiSempu, nilaiAlternatif3AksesPantaiSendiki;

    double bobotPrioritas3JarakGunungBromo, bobotPrioritas3HargaGunungBromo, bobotPrioritas3FasilitasGunungBromo, bobotPrioritas3AksesGunungBromo;
    double bobotPrioritas3JarakGunungSemeru, bobotPrioritas3HargaGunungSemeru, bobotPrioritas3FasilitasGunungSemeru, bobotPrioritas3AksesGunungSemeru;
    double bobotPrioritas3JarakJatimPark3, bobotPrioritas3HargaJatimPark3, bobotPrioritas3FasilitasJatimPark3, bobotPrioritas3AksesJatimPark3;
    double bobotPrioritas3JarakMuseumAngkut, bobotPrioritas3HargaMuseumAngkut, bobotPrioritas3FasilitasMuseumAngkut, bobotPrioritas3AksesMuseumAngkut;
    double bobotPrioritas3JarakPantaiSempu, bobotPrioritas3HargaPantaiSempu, bobotPrioritas3FasilitasPantaiSempu, bobotPrioritas3AksesPantaiSempu;
    double bobotPrioritas3JarakPantaiSendiki, bobotPrioritas3HargaPantaiSendiki, bobotPrioritas3FasilitasPantaiSendiki, bobotPrioritas3AksesPantaiSendiki;

    double nilaiTotal3GunungBromo = 0, nilaiTotal3GunungSemeru = 0, nilaiTotal3JatimPark3 = 0, nilaiTotal3MuseumAngkut = 0, nilaiTotal3PantaiSempu = 0, nilaiTotal3PantaiSendiki = 0;

    double nilaiMainGunungBromo = 0, nilaiMainGunungSemeru = 0, nilaiMainJatimPark3 = 0, nilaiMainMuseumAngkut = 0, nilaiMainPantaiSempu = 0, nilaiMainPantaiSendiki = 0;

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Recommendation, RecommendationViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Rangebar Jarak Harga
        valueHargaToJarak = 1 / Double.valueOf("" + getIntent().getStringExtra("value-jarak-harga"));

        // Rangebar Jarak Fasilitas
        valueFasilitasToJarak = 1 / Double.valueOf("" + getIntent().getStringExtra("value-jarak-fasilitas"));

        // Rangebar Jarak Akses
        valueAksesToJarak = 1 / Double.valueOf("" + getIntent().getStringExtra("value-jarak-akses"));

        // Rangebar Harga Fasilitas
        valueFasilitasToHarga = 1 / Double.valueOf("" + getIntent().getStringExtra("value-harga-fasilitas"));

        // Rangebar Harga Akses
        valueAksesToHarga = 1 / Double.valueOf("" + getIntent().getStringExtra("value-harga-akses"));

        // Rangebar Fasilitas Akses
        valueAksesToFasilitas = 1 / Double.valueOf("" + getIntent().getStringExtra("value-fasilitas-akses"));

        // status slider

        String statusJarakHarga = getIntent().getStringExtra("jarak-harga");
        String statusJarakFasilitas = getIntent().getStringExtra("jarak-fasilitas");
        String statusJarakAkses = getIntent().getStringExtra("jarak-akses");
        String statusHargaFasilitas = getIntent().getStringExtra("harga-fasilitas");
        String statusHargaAkses = getIntent().getStringExtra("harga-akses");
        String statusFasilitasAkses = getIntent().getStringExtra("fasilitas-akses");


        // Perhitungan AHP Untuk Kriteria

        matrix[0][0] = 1;
        matrix[1][1] = 1;
        matrix[2][2] = 1;
        matrix[3][3] = 1;

        // Slider 1

        if (statusJarakHarga.equals("0")) {
            matrix[0][1] = Double.parseDouble(getIntent().getStringExtra("value-jarak-harga"));
            matrix[1][0] = valueHargaToJarak;
        } else if (statusJarakHarga.equals("1")) {
            matrix[0][1] = valueHargaToJarak;
            matrix[1][0] = Double.parseDouble(getIntent().getStringExtra("value-jarak-harga"));
        }

        // Slider 2

        if (statusJarakFasilitas.equals("0")) {
            matrix[0][2] = Double.parseDouble(getIntent().getStringExtra("value-jarak-fasilitas"));
            matrix[2][0] = valueFasilitasToJarak;
        } else if (statusJarakFasilitas.equals("1")) {
            matrix[0][2] = valueFasilitasToJarak;
            matrix[2][0] = Double.parseDouble(getIntent().getStringExtra("value-jarak-fasilitas"));
        }

        // Slider 3

        if (statusJarakAkses.equals("0")) {
            matrix[0][3] = Double.parseDouble(getIntent().getStringExtra("value-jarak-akses"));
            matrix[3][0] = valueAksesToJarak;
        } else if (statusJarakAkses.equals("1")) {
            matrix[0][3] = valueAksesToJarak;
            matrix[3][0] = Double.parseDouble(getIntent().getStringExtra("value-jarak-akses"));
        }

        // Slider 4

        if (statusHargaFasilitas.equals("0")) {
            matrix[1][2] = Double.parseDouble(getIntent().getStringExtra("value-harga-fasilitas"));
            matrix[2][1] = valueFasilitasToHarga;
        } else if (statusHargaFasilitas.equals("1")) {
            matrix[1][2] = valueFasilitasToHarga;
            matrix[2][1] = Double.parseDouble(getIntent().getStringExtra("value-harga-fasilitas"));
        }

        // Slider 5

        if (statusHargaAkses.equals("0")) {
            matrix[1][3] = Double.parseDouble(getIntent().getStringExtra("value-harga-akses"));
            matrix[3][1] = valueAksesToHarga;
        } else if (statusHargaAkses.equals("1")) {
            matrix[1][2] = valueAksesToHarga;
            matrix[2][1] = Double.parseDouble(getIntent().getStringExtra("value-harga-akses"));
        }

        // Slider 6

        if (statusFasilitasAkses.equals("0")) {
            matrix[2][3] = Double.parseDouble(getIntent().getStringExtra("value-fasilitas-akses"));
            matrix[3][2] = valueAksesToFasilitas;
        } else if (statusFasilitasAkses.equals("1")) {
            matrix[2][3] = valueAksesToFasilitas;
            matrix[3][2] = Double.parseDouble(getIntent().getStringExtra("value-fasilitas-akses"));
        }

        // Step 1

        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                System.out.print("[" + matrix[a][b] + "]");
            }
        }

        // Step 2 ( Dikuadratkan )

        kolomA = matrix[0][0] + matrix[1][0] + matrix[2][0] + matrix[3][0];
        kolomB = matrix[0][1] + matrix[1][1] + matrix[2][1] + matrix[3][1];
        kolomC = matrix[0][2] + matrix[1][2] + matrix[2][2] + matrix[3][2];
        kolomD = matrix[0][3] + matrix[1][3] + matrix[2][3] + matrix[3][3];

        // Step 3 ( dijumlahkan tiap baris )

        matrixC[0][0] = matrix[0][0] / kolomA;
        matrixC[1][0] = matrix[1][0] / kolomA;
        matrixC[2][0] = matrix[2][0] / kolomA;
        matrixC[3][0] = matrix[3][0] / kolomA;

        matrixC[0][1] = matrix[0][1] / kolomB;
        matrixC[1][1] = matrix[1][1] / kolomB;
        matrixC[2][1] = matrix[2][1] / kolomB;
        matrixC[3][1] = matrix[3][1] / kolomB;

        matrixC[0][2] = matrix[0][2] / kolomC;
        matrixC[1][2] = matrix[1][2] / kolomC;
        matrixC[2][2] = matrix[2][2] / kolomC;
        matrixC[3][2] = matrix[3][2] / kolomC;

        matrixC[0][3] = matrix[0][3] / kolomD;
        matrixC[1][3] = matrix[1][3] / kolomD;
        matrixC[2][3] = matrix[2][3] / kolomD;
        matrixC[3][3] = matrix[3][3] / kolomD;

        // Step 4 ( dibagi dengan jumlah )

        bobotPrioritasA = (matrixC[0][0] + matrixC[0][1] + matrixC[0][2] + matrixC[0][3]) / 4;
        bobotPrioritasB = (matrixC[1][0] + matrixC[1][1] + matrixC[1][2] + matrixC[1][3]) / 4;
        bobotPrioritasC = (matrixC[2][0] + matrixC[2][1] + matrixC[2][2] + matrixC[2][3]) / 4;
        bobotPrioritasD = (matrixC[3][0] + matrixC[3][1] + matrixC[3][2] + matrixC[3][3]) / 4;

        // Memasukkan data hasilbagi ke dalam database

        mDatabase.child("kriteria").child("jarak").setValue(bobotPrioritasA);
        mDatabase.child("kriteria").child("harga").setValue(bobotPrioritasB);
        mDatabase.child("kriteria").child("fasilitas").setValue(bobotPrioritasC);
        mDatabase.child("kriteria").child("akses").setValue(bobotPrioritasD);

        mDatabase.child("alternatif").child("alternatif-jarak").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Gunung Bromo

                if (dataSnapshot.child("alternatif-jarak-1").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatifJarakGunungBromo = dataSnapshot.child("alternatif-jarak-1").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritasJarakGunungBromo = bobotPrioritasA * nilaiAlternatifJarakGunungBromo;

                    nilaiTotalGunungBromo = nilaiTotalGunungBromo + bobotPrioritasJarakGunungBromo;
                }

                if (dataSnapshot.child("alternatif-jarak-2").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatif2JarakGunungBromo = dataSnapshot.child("alternatif-jarak-2").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritas2JarakGunungBromo = bobotPrioritasA * nilaiAlternatif2JarakGunungBromo;

                    nilaiTotal2GunungBromo = nilaiTotal2GunungBromo + bobotPrioritas2JarakGunungBromo;
                }

                if (dataSnapshot.child("alternatif-jarak-3").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatif3JarakGunungBromo = dataSnapshot.child("alternatif-jarak-3").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritas3JarakGunungBromo = bobotPrioritasA * nilaiAlternatif3JarakGunungBromo;

                    nilaiTotal3GunungBromo = nilaiTotal3GunungBromo + bobotPrioritas3JarakGunungBromo;
                }

                // Gunung Semeru

                if (dataSnapshot.child("alternatif-jarak-1").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatifJarakGunungSemeru = dataSnapshot.child("alternatif-jarak-1").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritasJarakGunungSemeru = bobotPrioritasA * nilaiAlternatifJarakGunungSemeru;

                    nilaiTotalGunungSemeru = nilaiTotalGunungSemeru + bobotPrioritasJarakGunungSemeru;
                }

                if (dataSnapshot.child("alternatif-jarak-2").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatif2JarakGunungSemeru = dataSnapshot.child("alternatif-jarak-2").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritas2JarakGunungSemeru = bobotPrioritasA * nilaiAlternatif2JarakGunungSemeru;

                    nilaiTotal2GunungSemeru = nilaiTotal2GunungSemeru + bobotPrioritas2JarakGunungSemeru;
                }

                if (dataSnapshot.child("alternatif-jarak-3").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatif3JarakGunungSemeru = dataSnapshot.child("alternatif-jarak-3").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritas3JarakGunungSemeru = bobotPrioritasA * nilaiAlternatif3JarakGunungSemeru;

                    nilaiTotal3GunungSemeru = nilaiTotal3GunungSemeru + bobotPrioritas3JarakGunungSemeru;
                }

                // Jatim Park 3

                if (dataSnapshot.child("alternatif-jarak-1").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatifJarakJatimPark3 = dataSnapshot.child("alternatif-jarak-1").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritasJarakJatimPark3 = bobotPrioritasA * nilaiAlternatifJarakJatimPark3;

                    nilaiTotalJatimPark3 = nilaiTotalJatimPark3 + bobotPrioritasJarakJatimPark3;
                }

                if (dataSnapshot.child("alternatif-jarak-2").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatif2JarakJatimPark3 = dataSnapshot.child("alternatif-jarak-2").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritas2JarakJatimPark3 = bobotPrioritasA * nilaiAlternatif2JarakJatimPark3;

                    nilaiTotal2JatimPark3 = nilaiTotal2JatimPark3 + bobotPrioritas2JarakJatimPark3;
                }

                if (dataSnapshot.child("alternatif-jarak-3").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatif3JarakJatimPark3 = dataSnapshot.child("alternatif-jarak-3").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritas3JarakJatimPark3 = bobotPrioritasA * nilaiAlternatif3JarakJatimPark3;

                    nilaiTotal3JatimPark3 = nilaiTotal3JatimPark3 + bobotPrioritas3JarakJatimPark3;
                }

                // Museum Angkut

                if (dataSnapshot.child("alternatif-jarak-1").child("museum-angkut").getValue() != null) {
                    nilaiAlternatifJarakMuseumAngkut = dataSnapshot.child("alternatif-jarak-1").child("museum-angkut").getValue(Double.class);
                    bobotPrioritasJarakMuseumAngkut = bobotPrioritasA * nilaiAlternatifJarakMuseumAngkut;

                    nilaiTotalMuseumAngkut = nilaiTotalMuseumAngkut + bobotPrioritasJarakMuseumAngkut;
                }

                if (dataSnapshot.child("alternatif-jarak-2").child("museum-angkut").getValue() != null) {
                    nilaiAlternatif2JarakMuseumAngkut = dataSnapshot.child("alternatif-jarak-2").child("museum-angkut").getValue(Double.class);
                    bobotPrioritas2JarakMuseumAngkut = bobotPrioritasA * nilaiAlternatif2JarakMuseumAngkut;

                    nilaiTotal2MuseumAngkut = nilaiTotal2MuseumAngkut + bobotPrioritas2JarakMuseumAngkut;
                }

                if (dataSnapshot.child("alternatif-jarak-3").child("museum-angkut").getValue() != null) {
                    nilaiAlternatif3JarakMuseumAngkut = dataSnapshot.child("alternatif-jarak-3").child("museum-angkut").getValue(Double.class);
                    bobotPrioritas3JarakMuseumAngkut = bobotPrioritasA * nilaiAlternatif3JarakMuseumAngkut;

                    nilaiTotal3MuseumAngkut = nilaiTotal3MuseumAngkut + bobotPrioritas3JarakMuseumAngkut;
                }

                // Pantai Sempu

                if (dataSnapshot.child("alternatif-jarak-1").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatifJarakPantaiSempu = dataSnapshot.child("alternatif-jarak-1").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritasJarakPantaiSempu = bobotPrioritasA * nilaiAlternatifJarakPantaiSempu;

                    nilaiTotalPantaiSempu = nilaiTotalPantaiSempu + bobotPrioritasJarakPantaiSempu;
                }

                if (dataSnapshot.child("alternatif-jarak-2").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatif2JarakPantaiSempu = dataSnapshot.child("alternatif-jarak-2").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritas2JarakPantaiSempu = bobotPrioritasA * nilaiAlternatif2JarakPantaiSempu;

                    nilaiTotal2PantaiSempu = nilaiTotal2PantaiSempu + bobotPrioritas2JarakPantaiSempu;
                }

                if (dataSnapshot.child("alternatif-jarak-3").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatif3JarakPantaiSempu = dataSnapshot.child("alternatif-jarak-3").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritas3JarakPantaiSempu = bobotPrioritasA * nilaiAlternatif3JarakPantaiSempu;

                    nilaiTotal3PantaiSempu = nilaiTotal3PantaiSempu + bobotPrioritas3JarakPantaiSempu;
                }

                // Pantai Sendiki

                if (dataSnapshot.child("alternatif-jarak-1").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatifJarakPantaiSendiki = dataSnapshot.child("alternatif-jarak-1").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritasJarakPantaiSendiki = bobotPrioritasA * nilaiAlternatifJarakPantaiSendiki;

                    nilaiTotalPantaiSendiki = nilaiTotalPantaiSendiki + bobotPrioritasJarakPantaiSendiki;
                }

                if (dataSnapshot.child("alternatif-jarak-2").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatif2JarakPantaiSendiki = dataSnapshot.child("alternatif-jarak-2").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritas2JarakPantaiSendiki = bobotPrioritasA * nilaiAlternatif2JarakPantaiSendiki;

                    nilaiTotal2PantaiSendiki = nilaiTotal2PantaiSendiki + bobotPrioritas2JarakPantaiSendiki;
                }

                if (dataSnapshot.child("alternatif-jarak-3").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatif3JarakPantaiSendiki = dataSnapshot.child("alternatif-jarak-3").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritas3JarakPantaiSendiki = bobotPrioritasA * nilaiAlternatif3JarakPantaiSendiki;

                    nilaiTotal3PantaiSendiki = nilaiTotal3PantaiSendiki + bobotPrioritas3JarakPantaiSendiki;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child("alternatif").child("alternatif-harga").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Gunung Bromo

                if (dataSnapshot.child("alternatif-harga-1").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatifHargaGunungBromo = dataSnapshot.child("alternatif-harga-1").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritasHargaGunungBromo = bobotPrioritasB * nilaiAlternatifHargaGunungBromo;

                    nilaiTotalGunungBromo = nilaiTotalGunungBromo + bobotPrioritasHargaGunungBromo;
                }

                if (dataSnapshot.child("alternatif-harga-2").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatif2HargaGunungBromo = dataSnapshot.child("alternatif-harga-2").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritas2HargaGunungBromo = bobotPrioritasB * nilaiAlternatif2HargaGunungBromo;

                    nilaiTotal2GunungBromo = nilaiTotal2GunungBromo + bobotPrioritas2HargaGunungBromo;
                }

                if (dataSnapshot.child("alternatif-harga-3").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatif3HargaGunungBromo = dataSnapshot.child("alternatif-harga-3").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritas3HargaGunungBromo = bobotPrioritasB * nilaiAlternatif3HargaGunungBromo;

                    nilaiTotal3GunungBromo = nilaiTotal3GunungBromo + bobotPrioritas3HargaGunungBromo;
                }

                // Gunung Semeru

                if (dataSnapshot.child("alternatif-harga-1").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatifHargaGunungSemeru = dataSnapshot.child("alternatif-harga-1").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritasHargaGunungSemeru = bobotPrioritasB * nilaiAlternatifHargaGunungSemeru;

                    nilaiTotalGunungSemeru = nilaiTotalGunungSemeru + bobotPrioritasHargaGunungSemeru;
                }

                if (dataSnapshot.child("alternatif-harga-2").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatif2HargaGunungSemeru = dataSnapshot.child("alternatif-harga-2").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritas2HargaGunungSemeru = bobotPrioritasB * nilaiAlternatif2HargaGunungSemeru;

                    nilaiTotal2GunungSemeru = nilaiTotal2GunungSemeru + bobotPrioritas2HargaGunungSemeru;
                }

                if (dataSnapshot.child("alternatif-harga-3").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatif3HargaGunungSemeru = dataSnapshot.child("alternatif-harga-3").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritas3HargaGunungSemeru = bobotPrioritasB * nilaiAlternatif3HargaGunungSemeru;

                    nilaiTotal3GunungSemeru = nilaiTotal3GunungSemeru + bobotPrioritas3HargaGunungSemeru;
                }

                // Jatim Park 3

                if (dataSnapshot.child("alternatif-harga-1").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatifHargaJatimPark3 = dataSnapshot.child("alternatif-harga-1").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritasHargaJatimPark3 = bobotPrioritasB * nilaiAlternatifHargaJatimPark3;

                    nilaiTotalJatimPark3 = nilaiTotalJatimPark3 + bobotPrioritasHargaJatimPark3;
                }

                if (dataSnapshot.child("alternatif-harga-2").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatif2HargaJatimPark3 = dataSnapshot.child("alternatif-harga-2").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritas2HargaJatimPark3 = bobotPrioritasB * nilaiAlternatif2HargaJatimPark3;

                    nilaiTotal2JatimPark3 = nilaiTotal2JatimPark3 + bobotPrioritas2HargaJatimPark3;
                }

                if (dataSnapshot.child("alternatif-harga-3").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatif3HargaJatimPark3 = dataSnapshot.child("alternatif-harga-3").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritas3HargaJatimPark3 = bobotPrioritasB * nilaiAlternatif3HargaJatimPark3;

                    nilaiTotal3JatimPark3 = nilaiTotal3JatimPark3 + bobotPrioritas3HargaJatimPark3;
                }

                // Museum Angkut

                if (dataSnapshot.child("alternatif-harga-1").child("museum-angkut").getValue() != null) {
                    nilaiAlternatifHargaMuseumAngkut = dataSnapshot.child("alternatif-harga-1").child("museum-angkut").getValue(Double.class);
                    bobotPrioritasHargaMuseumAngkut = bobotPrioritasB * nilaiAlternatifHargaMuseumAngkut;

                    nilaiTotalMuseumAngkut = nilaiTotalMuseumAngkut + bobotPrioritasHargaMuseumAngkut;
                }

                if (dataSnapshot.child("alternatif-harga-2").child("museum-angkut").getValue() != null) {
                    nilaiAlternatif2HargaMuseumAngkut = dataSnapshot.child("alternatif-harga-2").child("museum-angkut").getValue(Double.class);
                    bobotPrioritas2HargaMuseumAngkut = bobotPrioritasB * nilaiAlternatif2HargaMuseumAngkut;

                    nilaiTotal2MuseumAngkut = nilaiTotal2MuseumAngkut + bobotPrioritas2HargaMuseumAngkut;
                }

                if (dataSnapshot.child("alternatif-harga-3").child("museum-angkut").getValue() != null) {
                    nilaiAlternatif3HargaMuseumAngkut = dataSnapshot.child("alternatif-harga-3").child("museum-angkut").getValue(Double.class);
                    bobotPrioritas3HargaMuseumAngkut = bobotPrioritasB * nilaiAlternatif3HargaMuseumAngkut;

                    nilaiTotal3MuseumAngkut = nilaiTotal3MuseumAngkut + bobotPrioritas3HargaMuseumAngkut;
                }

                // Pantai Sempu

                if (dataSnapshot.child("alternatif-harga-1").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatifHargaPantaiSempu = dataSnapshot.child("alternatif-harga-1").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritasHargaPantaiSempu = bobotPrioritasB * nilaiAlternatifHargaPantaiSempu;

                    nilaiTotalPantaiSempu = nilaiTotalPantaiSempu + bobotPrioritasHargaPantaiSempu;
                }

                if (dataSnapshot.child("alternatif-harga-2").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatif2HargaPantaiSempu = dataSnapshot.child("alternatif-harga-2").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritas2HargaPantaiSempu = bobotPrioritasB * nilaiAlternatif2HargaPantaiSempu;

                    nilaiTotal2PantaiSempu = nilaiTotal2PantaiSempu + bobotPrioritas2HargaPantaiSempu;
                }

                if (dataSnapshot.child("alternatif-harga-3").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatif3HargaPantaiSempu = dataSnapshot.child("alternatif-harga-3").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritas3HargaPantaiSempu = bobotPrioritasB * nilaiAlternatif3HargaPantaiSempu;

                    nilaiTotal3PantaiSempu = nilaiTotal3PantaiSempu + bobotPrioritas3HargaPantaiSempu;
                }

                // Pantai Sendiki

                if (dataSnapshot.child("alternatif-harga-1").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatifHargaPantaiSendiki = dataSnapshot.child("alternatif-harga-1").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritasHargaPantaiSendiki = bobotPrioritasB * nilaiAlternatifHargaPantaiSendiki;

                    nilaiTotalPantaiSendiki = nilaiTotalPantaiSendiki + bobotPrioritasHargaPantaiSendiki;
                }

                if (dataSnapshot.child("alternatif-harga-2").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatif2HargaPantaiSendiki = dataSnapshot.child("alternatif-harga-2").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritas2HargaPantaiSendiki = bobotPrioritasB * nilaiAlternatif2HargaPantaiSendiki;

                    nilaiTotal2PantaiSendiki = nilaiTotal2PantaiSendiki + bobotPrioritas2HargaPantaiSendiki;
                }

                if (dataSnapshot.child("alternatif-harga-3").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatif3HargaPantaiSendiki = dataSnapshot.child("alternatif-harga-3").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritas3HargaPantaiSendiki = bobotPrioritasB * nilaiAlternatif3HargaPantaiSendiki;

                    nilaiTotal3PantaiSendiki = nilaiTotal3PantaiSendiki + bobotPrioritas3HargaPantaiSendiki;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child("alternatif").child("alternatif-fasilitas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Gunung Bromo

                if (dataSnapshot.child("alternatif-fasilitas-1").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatifFasilitasGunungBromo = dataSnapshot.child("alternatif-fasilitas-1").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritasFasilitasGunungBromo = bobotPrioritasC * nilaiAlternatifFasilitasGunungBromo;
                    nilaiTotalGunungBromo = nilaiTotalGunungBromo + bobotPrioritasFasilitasGunungBromo;
                }

                if (dataSnapshot.child("alternatif-fasilitas-2").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatif2FasilitasGunungBromo = dataSnapshot.child("alternatif-fasilitas-2").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritas2FasilitasGunungBromo = bobotPrioritasC * nilaiAlternatif2FasilitasGunungBromo;
                    nilaiTotal2GunungBromo = nilaiTotal2GunungBromo + bobotPrioritas2FasilitasGunungBromo;
                }

                if (dataSnapshot.child("alternatif-fasilitas-3").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatif3FasilitasGunungBromo = dataSnapshot.child("alternatif-fasilitas-3").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritas3FasilitasGunungBromo = bobotPrioritasC * nilaiAlternatif3FasilitasGunungBromo;
                    nilaiTotal3GunungBromo = nilaiTotal3GunungBromo + bobotPrioritas3FasilitasGunungBromo;
                }

                // Gunung Semeru

                if (dataSnapshot.child("alternatif-fasilitas-1").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatifFasilitasGunungSemeru = dataSnapshot.child("alternatif-fasilitas-1").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritasFasilitasGunungSemeru = bobotPrioritasC * nilaiAlternatifFasilitasGunungSemeru;

                    nilaiTotalGunungSemeru = nilaiTotalGunungSemeru + bobotPrioritasFasilitasGunungSemeru;
                }

                if (dataSnapshot.child("alternatif-fasilitas-2").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatif2FasilitasGunungSemeru = dataSnapshot.child("alternatif-fasilitas-2").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritas2FasilitasGunungSemeru = bobotPrioritasC * nilaiAlternatif2FasilitasGunungSemeru;

                    nilaiTotal2GunungSemeru = nilaiTotal2GunungSemeru + bobotPrioritas2FasilitasGunungSemeru;
                }

                if (dataSnapshot.child("alternatif-fasilitas-3").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatif3FasilitasGunungSemeru = dataSnapshot.child("alternatif-fasilitas-3").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritas3FasilitasGunungSemeru = bobotPrioritasC * nilaiAlternatif3FasilitasGunungSemeru;

                    nilaiTotal3GunungSemeru = nilaiTotal3GunungSemeru + bobotPrioritas3FasilitasGunungSemeru;
                }

                // Jatim Park 3

                if (dataSnapshot.child("alternatif-fasilitas-1").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatifFasilitasJatimPark3 = dataSnapshot.child("alternatif-fasilitas-1").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritasFasilitasJatimPark3 = bobotPrioritasC * nilaiAlternatifFasilitasJatimPark3;

                    nilaiTotalJatimPark3 = nilaiTotalJatimPark3 + bobotPrioritasFasilitasJatimPark3;
                }

                if (dataSnapshot.child("alternatif-fasilitas-2").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatif2FasilitasJatimPark3 = dataSnapshot.child("alternatif-fasilitas-2").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritas2FasilitasJatimPark3 = bobotPrioritasC * nilaiAlternatif2FasilitasJatimPark3;

                    nilaiTotal2JatimPark3 = nilaiTotal2JatimPark3 + bobotPrioritas2FasilitasJatimPark3;
                }

                if (dataSnapshot.child("alternatif-fasilitas-3").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatif3FasilitasJatimPark3 = dataSnapshot.child("alternatif-fasilitas-3").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritas3FasilitasJatimPark3 = bobotPrioritasC * nilaiAlternatif3FasilitasJatimPark3;

                    nilaiTotal3JatimPark3 = nilaiTotal3JatimPark3 + bobotPrioritas3FasilitasJatimPark3;
                }

                // Museum Angkut

                if (dataSnapshot.child("alternatif-fasilitas-1").child("museum-angkut").getValue() != null) {
                    nilaiAlternatifFasilitasMuseumAngkut = dataSnapshot.child("alternatif-fasilitas-1").child("museum-angkut").getValue(Double.class);
                    bobotPrioritasFasilitasMuseumAngkut = bobotPrioritasC * nilaiAlternatifFasilitasMuseumAngkut;

                    nilaiTotalMuseumAngkut = nilaiTotalMuseumAngkut + bobotPrioritasFasilitasMuseumAngkut;
                }

                if (dataSnapshot.child("alternatif-fasilitas-2").child("museum-angkut").getValue() != null) {
                    nilaiAlternatif2FasilitasMuseumAngkut = dataSnapshot.child("alternatif-fasilitas-2").child("museum-angkut").getValue(Double.class);
                    bobotPrioritas2FasilitasMuseumAngkut = bobotPrioritasC * nilaiAlternatif2FasilitasMuseumAngkut;

                    nilaiTotal2MuseumAngkut = nilaiTotal2MuseumAngkut + bobotPrioritas2FasilitasMuseumAngkut;
                }

                if (dataSnapshot.child("alternatif-fasilitas-3").child("museum-angkut").getValue() != null) {
                    nilaiAlternatif3FasilitasMuseumAngkut = dataSnapshot.child("alternatif-fasilitas-3").child("museum-angkut").getValue(Double.class);
                    bobotPrioritas3FasilitasMuseumAngkut = bobotPrioritasC * nilaiAlternatif3FasilitasMuseumAngkut;

                    nilaiTotal3MuseumAngkut = nilaiTotal3MuseumAngkut + bobotPrioritas3FasilitasMuseumAngkut;
                }

                // Pantai Sempu

                if (dataSnapshot.child("alternatif-fasilitas-1").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatifFasilitasPantaiSempu = dataSnapshot.child("alternatif-fasilitas-1").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritasFasilitasPantaiSempu = bobotPrioritasC * nilaiAlternatifFasilitasPantaiSempu;

                    nilaiTotalPantaiSempu = nilaiTotalPantaiSempu + bobotPrioritasFasilitasPantaiSempu;
                }

                if (dataSnapshot.child("alternatif-fasilitas-2").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatif2FasilitasPantaiSempu = dataSnapshot.child("alternatif-fasilitas-2").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritas2FasilitasPantaiSempu = bobotPrioritasC * nilaiAlternatif2FasilitasPantaiSempu;

                    nilaiTotal2PantaiSempu = nilaiTotal2PantaiSempu + bobotPrioritas2FasilitasPantaiSempu;
                }

                if (dataSnapshot.child("alternatif-fasilitas-3").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatif3FasilitasPantaiSempu = dataSnapshot.child("alternatif-fasilitas-3").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritas3FasilitasPantaiSempu = bobotPrioritasC * nilaiAlternatif3FasilitasPantaiSempu;

                    nilaiTotal3PantaiSempu = nilaiTotal3PantaiSempu + bobotPrioritas3FasilitasPantaiSempu;
                }

                // Pantai Sendiki

                if (dataSnapshot.child("alternatif-fasilitas-1").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatifFasilitasPantaiSendiki = dataSnapshot.child("alternatif-fasilitas-1").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritasFasilitasPantaiSendiki = bobotPrioritasC * nilaiAlternatifFasilitasPantaiSendiki;

                    nilaiTotalPantaiSendiki = nilaiTotalPantaiSendiki + bobotPrioritasFasilitasPantaiSendiki;
                }

                if (dataSnapshot.child("alternatif-fasilitas-2").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatif2FasilitasPantaiSendiki = dataSnapshot.child("alternatif-fasilitas-2").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritas2FasilitasPantaiSendiki = bobotPrioritasC * nilaiAlternatif2FasilitasPantaiSendiki;

                    nilaiTotal2PantaiSendiki = nilaiTotal2PantaiSendiki + bobotPrioritas2FasilitasPantaiSendiki;
                }

                if (dataSnapshot.child("alternatif-fasilitas-3").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatif3FasilitasPantaiSendiki = dataSnapshot.child("alternatif-fasilitas-3").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritas3FasilitasPantaiSendiki = bobotPrioritasC * nilaiAlternatif3FasilitasPantaiSendiki;

                    nilaiTotal3PantaiSendiki = nilaiTotal3PantaiSendiki + bobotPrioritas3FasilitasPantaiSendiki;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child("alternatif").child("alternatif-akses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Gunung Bromo

                if (dataSnapshot.child("alternatif-akses-1").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatifAksesGunungBromo = dataSnapshot.child("alternatif-akses-1").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritasAksesGunungBromo = bobotPrioritasD * nilaiAlternatifAksesGunungBromo;
                    nilaiTotalGunungBromo = nilaiTotalGunungBromo + bobotPrioritasAksesGunungBromo;

                    mDatabase.child("recommendation").child("gunung-bromo").child("value-1").setValue(nilaiTotalGunungBromo);
                }

                if (dataSnapshot.child("alternatif-akses-2").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatif2AksesGunungBromo = dataSnapshot.child("alternatif-akses-2").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritas2AksesGunungBromo = bobotPrioritasD * nilaiAlternatif2AksesGunungBromo;
                    nilaiTotal2GunungBromo = nilaiTotal2GunungBromo + bobotPrioritas2AksesGunungBromo;

                    mDatabase.child("recommendation").child("gunung-bromo").child("value-2").setValue(nilaiTotal2GunungBromo);
                }

                if (dataSnapshot.child("alternatif-akses-3").child("gunung-bromo").getValue() != null) {
                    nilaiAlternatif3AksesGunungBromo = dataSnapshot.child("alternatif-akses-3").child("gunung-bromo").getValue(Double.class);
                    bobotPrioritas3AksesGunungBromo = bobotPrioritasD * nilaiAlternatif3AksesGunungBromo;
                    nilaiTotal3GunungBromo = nilaiTotal3GunungBromo + bobotPrioritas3AksesGunungBromo;

                    nilaiMainGunungBromo = (nilaiTotalGunungBromo + nilaiTotal2GunungBromo + nilaiTotal3GunungBromo) / 3;

                    mDatabase.child("recommendation").child("gunung-bromo").child("value-3").setValue(nilaiTotal3GunungBromo);
                    mDatabase.child("recommendation").child("gunung-bromo").child("value-main").setValue(nilaiMainGunungBromo);
                }

                // Gunung Semeru

                if (dataSnapshot.child("alternatif-akses-1").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatifAksesGunungSemeru = dataSnapshot.child("alternatif-akses-1").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritasAksesGunungSemeru = bobotPrioritasD * nilaiAlternatifAksesGunungSemeru;
                    nilaiTotalGunungSemeru = nilaiTotalGunungSemeru + bobotPrioritasAksesGunungSemeru;

                    mDatabase.child("recommendation").child("gunung-semeru").child("value-1").setValue(nilaiTotalGunungSemeru);
                }

                if (dataSnapshot.child("alternatif-akses-2").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatif2AksesGunungSemeru = dataSnapshot.child("alternatif-akses-2").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritas2AksesGunungSemeru = bobotPrioritasD * nilaiAlternatif2AksesGunungSemeru;
                    nilaiTotal2GunungSemeru = nilaiTotal2GunungSemeru + bobotPrioritas2AksesGunungSemeru;

                    mDatabase.child("recommendation").child("gunung-semeru").child("value-2").setValue(nilaiTotal2GunungSemeru);
                }

                if (dataSnapshot.child("alternatif-akses-3").child("gunung-semeru").getValue() != null) {
                    nilaiAlternatif3AksesGunungSemeru = dataSnapshot.child("alternatif-akses-3").child("gunung-semeru").getValue(Double.class);
                    bobotPrioritas3AksesGunungSemeru = bobotPrioritasD * nilaiAlternatif3AksesGunungSemeru;
                    nilaiTotal3GunungSemeru = nilaiTotal3GunungSemeru + bobotPrioritas3AksesGunungSemeru;

                    nilaiMainGunungSemeru = (nilaiTotalGunungSemeru + nilaiTotal2GunungSemeru + nilaiTotal3GunungSemeru) / 3;

                    mDatabase.child("recommendation").child("gunung-semeru").child("value-3").setValue(nilaiTotal3GunungSemeru);
                    mDatabase.child("recommendation").child("gunung-semeru").child("value-main").setValue(nilaiMainGunungSemeru);
                }

                // Jatim Park 3

                if (dataSnapshot.child("alternatif-akses-1").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatifAksesJatimPark3 = dataSnapshot.child("alternatif-akses-1").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritasAksesJatimPark3 = bobotPrioritasD * nilaiAlternatifAksesJatimPark3;
                    nilaiTotalJatimPark3 = nilaiTotalJatimPark3 + bobotPrioritasAksesJatimPark3;

                    mDatabase.child("recommendation").child("jatim-park-3").child("value-1").setValue(nilaiTotalJatimPark3);
                }

                if (dataSnapshot.child("alternatif-akses-2").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatif2AksesJatimPark3 = dataSnapshot.child("alternatif-akses-2").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritas2AksesJatimPark3 = bobotPrioritasD * nilaiAlternatif2AksesJatimPark3;
                    nilaiTotal2JatimPark3 = nilaiTotal2JatimPark3 + bobotPrioritas2AksesJatimPark3;

                    mDatabase.child("recommendation").child("jatim-park-3").child("value-2").setValue(nilaiTotal2JatimPark3);
                }

                if (dataSnapshot.child("alternatif-akses-3").child("jatim-park-3").getValue() != null) {
                    nilaiAlternatif3AksesJatimPark3 = dataSnapshot.child("alternatif-akses-3").child("jatim-park-3").getValue(Double.class);
                    bobotPrioritas3AksesJatimPark3 = bobotPrioritasD * nilaiAlternatif3AksesJatimPark3;
                    nilaiTotal3JatimPark3 = nilaiTotal3JatimPark3 + bobotPrioritas3AksesJatimPark3;

                    nilaiMainJatimPark3 = (nilaiTotalJatimPark3 + nilaiTotal2JatimPark3 + nilaiTotal3JatimPark3) / 3;

                    mDatabase.child("recommendation").child("jatim-park-3").child("value-3").setValue(nilaiTotal3JatimPark3);
                    mDatabase.child("recommendation").child("jatim-park-3").child("value-main").setValue(nilaiMainJatimPark3);
                }

                // Museum Angkut

                if (dataSnapshot.child("alternatif-akses-1").child("museum-angkut").getValue() != null) {
                    nilaiAlternatifAksesMuseumAngkut = dataSnapshot.child("alternatif-akses-1").child("museum-angkut").getValue(Double.class);
                    bobotPrioritasAksesMuseumAngkut = bobotPrioritasD * nilaiAlternatifAksesMuseumAngkut;
                    nilaiTotalMuseumAngkut = nilaiTotalMuseumAngkut + bobotPrioritasAksesMuseumAngkut;

                    mDatabase.child("recommendation").child("museum-angkut").child("value-1").setValue(nilaiTotalMuseumAngkut);
                }

                if (dataSnapshot.child("alternatif-akses-2").child("museum-angkut").getValue() != null) {
                    nilaiAlternatif2AksesMuseumAngkut = dataSnapshot.child("alternatif-akses-2").child("museum-angkut").getValue(Double.class);
                    bobotPrioritas2AksesMuseumAngkut = bobotPrioritasD * nilaiAlternatif2AksesMuseumAngkut;
                    nilaiTotal2MuseumAngkut = nilaiTotal2MuseumAngkut + bobotPrioritas2AksesMuseumAngkut;

                    mDatabase.child("recommendation").child("museum-angkut").child("value-2").setValue(nilaiTotal2MuseumAngkut);
                }

                if (dataSnapshot.child("alternatif-akses-3").child("museum-angkut").getValue() != null) {
                    nilaiAlternatif3AksesMuseumAngkut = dataSnapshot.child("alternatif-akses-3").child("museum-angkut").getValue(Double.class);
                    bobotPrioritas3AksesMuseumAngkut = bobotPrioritasD * nilaiAlternatif3AksesMuseumAngkut;
                    nilaiTotal3MuseumAngkut = nilaiTotal3MuseumAngkut + bobotPrioritas3AksesMuseumAngkut;

                    nilaiMainMuseumAngkut = (nilaiTotalMuseumAngkut + nilaiTotal2MuseumAngkut + nilaiTotal3MuseumAngkut) / 3;

                    mDatabase.child("recommendation").child("museum-angkut").child("value-3").setValue(nilaiTotal3MuseumAngkut);
                    mDatabase.child("recommendation").child("museum-angkut").child("value-main").setValue(nilaiMainMuseumAngkut);
                }

                // Pantai Sempu

                if (dataSnapshot.child("alternatif-akses-1").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatifAksesPantaiSempu = dataSnapshot.child("alternatif-akses-1").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritasAksesPantaiSempu = bobotPrioritasD * nilaiAlternatifAksesPantaiSempu;
                    nilaiTotalPantaiSempu = nilaiTotalPantaiSempu + bobotPrioritasAksesPantaiSempu;

                    mDatabase.child("recommendation").child("pantai-sempu").child("value-1").setValue(nilaiTotalPantaiSempu);
                }

                if (dataSnapshot.child("alternatif-akses-2").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatif2AksesPantaiSempu = dataSnapshot.child("alternatif-akses-2").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritas2AksesPantaiSempu = bobotPrioritasD * nilaiAlternatif2AksesPantaiSempu;
                    nilaiTotal2PantaiSempu = nilaiTotal2PantaiSempu + bobotPrioritas2AksesPantaiSempu;

                    mDatabase.child("recommendation").child("pantai-sempu").child("value-2").setValue(nilaiTotal2PantaiSempu);
                }

                if (dataSnapshot.child("alternatif-akses-3").child("pantai-sempu").getValue() != null) {
                    nilaiAlternatif3AksesPantaiSempu = dataSnapshot.child("alternatif-akses-3").child("pantai-sempu").getValue(Double.class);
                    bobotPrioritas3AksesPantaiSempu = bobotPrioritasD * nilaiAlternatif3AksesPantaiSempu;
                    nilaiTotal3PantaiSempu = nilaiTotal3PantaiSempu + bobotPrioritas3AksesPantaiSempu;

                    nilaiMainPantaiSempu = (nilaiTotalPantaiSempu + nilaiTotal2PantaiSempu + nilaiTotal3PantaiSempu) / 3;

                    mDatabase.child("recommendation").child("pantai-sempu").child("value-3").setValue(nilaiTotal3PantaiSempu);
                    mDatabase.child("recommendation").child("pantai-sempu").child("value-main").setValue(nilaiMainPantaiSempu);
                }

                // Pantai Sendiki

                if (dataSnapshot.child("alternatif-akses-1").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatifAksesPantaiSendiki = dataSnapshot.child("alternatif-akses-1").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritasAksesPantaiSendiki = bobotPrioritasD * nilaiAlternatifAksesPantaiSendiki;
                    nilaiTotalPantaiSendiki = nilaiTotalPantaiSendiki + bobotPrioritasAksesPantaiSendiki;

                    mDatabase.child("recommendation").child("pantai-sendiki").child("value-1").setValue(nilaiTotalPantaiSendiki);
                }

                if (dataSnapshot.child("alternatif-akses-2").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatif2AksesPantaiSendiki = dataSnapshot.child("alternatif-akses-2").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritas2AksesPantaiSendiki = bobotPrioritasD * nilaiAlternatif2AksesPantaiSendiki;
                    nilaiTotal2PantaiSendiki = nilaiTotal2PantaiSendiki + bobotPrioritas2AksesPantaiSendiki;

                    mDatabase.child("recommendation").child("pantai-sendiki").child("value-2").setValue(nilaiTotal2PantaiSendiki);
                }

                if (dataSnapshot.child("alternatif-akses-3").child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatif3AksesPantaiSendiki = dataSnapshot.child("alternatif-akses-3").child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritas3AksesPantaiSendiki = bobotPrioritasD * nilaiAlternatif3AksesPantaiSendiki;
                    nilaiTotal3PantaiSendiki = nilaiTotal3PantaiSendiki + bobotPrioritas3AksesPantaiSendiki;

                    nilaiMainPantaiSendiki = (nilaiTotalPantaiSendiki + nilaiTotal2PantaiSendiki + nilaiTotal3PantaiSendiki) / 3;

                    mDatabase.child("recommendation").child("pantai-sendiki").child("value-3").setValue(nilaiTotal3PantaiSendiki);
                    mDatabase.child("recommendation").child("pantai-sendiki").child("value-main").setValue(nilaiMainPantaiSendiki);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRecycler = findViewById(R.id.list_recommendation);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Recommendation>()
                .setQuery(query, Recommendation.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Recommendation, RecommendationViewHolder>(options) {
            @NonNull
            @Override
            public RecommendationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new RecommendationViewHolder(inflater.inflate(R.layout.item_recommendation, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull RecommendationViewHolder holder, int position, @NonNull final Recommendation model) {
                holder.setDisplayImage(model.getImage_url(), ResultActivity.this);

                holder.cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), DetailRecommendationActivity.class);
                        i.putExtra("name", model.getName());
                        i.putExtra("image-url", model.getImage_url());
                        i.putExtra("location", model.getLocation_title());
                        i.putExtra("description", model.getDescription());
                        startActivity(i);
                    }
                });

                holder.bindToRecommendation(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        };

        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
    }

    public void onClickRecommendation(View view) {
        Intent intent = new Intent(this, SurveyRecommendationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase) {
        return mDatabase.child("recommendation").orderByChild("value-main");
    }
}
