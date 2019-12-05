package com.bluohazard.tourdengalam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.menu.SurveyRecommendationActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultActivity extends AppCompatActivity {

    TextView tvValueJarakToHarga, tvValueHargaToJarak;
    TextView tvValueJarakToFasilitas, tvValueFasilitasToJarak;
    TextView tvValueJarakToAkses, tvValueAksesToJarak;
    TextView tvValueHargaToFasilitas, tvValueFasilitasToHarga;
    TextView tvValueHargaToAkses, tvValueAksesToHarga;
    TextView tvValueFasilitasToAkses, tvValueAksesToFasilitas;

    TextView hasil1, hasil2, hasil3, hasil4, totalHasil;

    String strValueHargaToJarak, strValueFasilitasToJarak, strValueAksesToJarak, strValueFasilitasToHarga, strAksesToHarga, strAksesToFasilitas;
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

    double nilaiTotalGunungBromo = 0, nilaiTotalGunungSemeru, nilaiTotalJatimPark3, nilaiTotalMuseumAngkut, nilaiTotalPantaiSempu, nilaiTotalPantaiSendiki;

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        hasil1 = findViewById(R.id.hasil1);
        hasil2 = findViewById(R.id.hasil2);
        hasil3 = findViewById(R.id.hasil3);
        hasil4 = findViewById(R.id.hasil4);
        totalHasil = findViewById(R.id.total);

        // inisialisasi dari textview
        tvValueJarakToHarga = findViewById(R.id.valueJarakToHarga);
        tvValueHargaToJarak = findViewById(R.id.valueHargaToJarak);

        tvValueJarakToFasilitas = findViewById(R.id.valueJarakToFasilitas);
        tvValueFasilitasToJarak = findViewById(R.id.valueFasilitasToJarak);

        tvValueJarakToAkses = findViewById(R.id.valueJarakToAkses);
        tvValueAksesToJarak = findViewById(R.id.valueAksesToJarak);

        tvValueHargaToFasilitas = findViewById(R.id.valueHargaToFasilitas);
        tvValueFasilitasToHarga = findViewById(R.id.valueFasilitasToHarga);

        tvValueHargaToAkses = findViewById(R.id.valueHargaToAkses);
        tvValueAksesToHarga = findViewById(R.id.valueAksesToHarga);

        tvValueFasilitasToAkses = findViewById(R.id.valueFasilitasToAkses);
        tvValueAksesToFasilitas = findViewById(R.id.valueAksesToFasilitas);

        // Rangebar Jarak ke Harga
        tvValueJarakToHarga.setText(getIntent().getStringExtra("value-jarak-harga"));

        valueHargaToJarak = 1 / Double.valueOf("" + getIntent().getStringExtra("value-jarak-harga"));
        strValueHargaToJarak = Double.toString(valueHargaToJarak);
        tvValueHargaToJarak.setText(strValueHargaToJarak);

        // Rangebar Jarak ke Fasilitas
        tvValueJarakToFasilitas.setText(getIntent().getStringExtra("value-jarak-fasilitas"));

        valueFasilitasToJarak = 1 / Double.valueOf("" + getIntent().getStringExtra("value-jarak-fasilitas"));
        strValueFasilitasToJarak = Double.toString(valueFasilitasToJarak);
        tvValueFasilitasToJarak.setText(strValueFasilitasToJarak);

        // Rangebar Jarak ke Akses
        tvValueJarakToAkses.setText(getIntent().getStringExtra("value-jarak-akses"));

        valueAksesToJarak = 1 / Double.valueOf("" + getIntent().getStringExtra("value-jarak-akses"));
        strValueAksesToJarak = Double.toString(valueAksesToJarak);
        tvValueAksesToJarak.setText(strValueAksesToJarak);

        // Rangebar Harga ke Fasilitas
        tvValueHargaToFasilitas.setText(getIntent().getStringExtra("value-harga-fasilitas"));

        valueFasilitasToHarga = 1 / Double.valueOf("" + getIntent().getStringExtra("value-harga-fasilitas"));
        strValueFasilitasToHarga = Double.toString(valueFasilitasToHarga);
        tvValueFasilitasToHarga.setText(strValueFasilitasToHarga);

        // Rangebar Harga ke Akses
        tvValueHargaToAkses.setText(getIntent().getStringExtra("value-harga-akses"));

        valueAksesToHarga = 1 / Double.valueOf("" + getIntent().getStringExtra("value-harga-akses"));
        strAksesToHarga = Double.toString(valueAksesToHarga);
        tvValueAksesToHarga.setText(strAksesToHarga);

        // Rangebar Fasilitas ke Akses
        tvValueFasilitasToAkses.setText(getIntent().getStringExtra("value-fasilitas-akses"));

        valueAksesToFasilitas = 1 / Double.valueOf("" + getIntent().getStringExtra("value-fasilitas-akses"));
        strAksesToFasilitas = Double.toString(valueAksesToFasilitas);
        tvValueAksesToFasilitas.setText(strAksesToFasilitas);

        // Perhitungan AHP Untuk Kriteria

        matrix[0][0] = 1;
        matrix[0][1] = Double.parseDouble(getIntent().getStringExtra("value-jarak-harga"));
        matrix[0][2] = Double.parseDouble(getIntent().getStringExtra("value-jarak-fasilitas"));
        matrix[0][3] = Double.parseDouble(getIntent().getStringExtra("value-jarak-akses"));

        matrix[1][0] = valueHargaToJarak;
        matrix[1][1] = 1;
        matrix[1][2] = Double.parseDouble(getIntent().getStringExtra("value-harga-fasilitas"));
        matrix[1][3] = Double.parseDouble(getIntent().getStringExtra("value-harga-akses"));

        matrix[2][0] = valueFasilitasToJarak;
        matrix[2][1] = valueFasilitasToHarga;
        matrix[2][2] = 1;
        matrix[2][3] = Double.parseDouble(getIntent().getStringExtra("value-fasilitas-akses"));

        matrix[3][0] = valueAksesToJarak;
        matrix[3][1] = valueAksesToHarga;
        matrix[3][2] = valueAksesToFasilitas;
        matrix[3][3] = 1;

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

//        hasil1.setText(Double.toString(bobotPrioritasA));
//        hasil2.setText(Double.toString(bobotPrioritasB));
//        hasil3.setText(Double.toString(bobotPrioritasC));
//        hasil4.setText(Double.toString(bobotPrioritasD));

        // Memasukkan data hasilbagi ke dalam database

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("kriteria").child("jarak").setValue(bobotPrioritasA);
        mDatabase.child("kriteria").child("harga").setValue(bobotPrioritasB);
        mDatabase.child("kriteria").child("fasilitas").setValue(bobotPrioritasC);
        mDatabase.child("kriteria").child("akses").setValue(bobotPrioritasD);

        mDatabase.child("alternatif").child("alternatif-jarak").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("gunung-bromo").getValue() != null) {
                    nilaiAlternatifJarakGunungBromo = dataSnapshot.child("gunung-bromo").getValue(Double.class);
                    bobotPrioritasJarakGunungBromo = bobotPrioritasA * nilaiAlternatifJarakGunungBromo;
                    nilaiTotalGunungBromo = nilaiTotalGunungBromo + bobotPrioritasJarakGunungBromo;

                    mDatabase.child("total").child("gunung-bromo").setValue(nilaiTotalGunungBromo);
//                    hasil1.setText(Double.toString(bobotPrioritasJarakGunungBromo));
                }

                if (dataSnapshot.child("gunung-semeru").getValue() != null) {
                    nilaiAlternatifJarakGunungSemeru = dataSnapshot.child("gunung-semeru").getValue(Double.class);
                    bobotPrioritasJarakGunungSemeru = bobotPrioritasA * nilaiAlternatifJarakGunungSemeru;
                    nilaiTotalGunungSemeru = nilaiTotalGunungSemeru + bobotPrioritasJarakGunungSemeru;
                }

                if (dataSnapshot.child("jatim-park-3").getValue() != null) {
                    nilaiAlternatifJarakJatimPark3 = dataSnapshot.child("jatim-park-3").getValue(Double.class);
                    bobotPrioritasJarakJatimPark3 = bobotPrioritasA * nilaiAlternatifJarakJatimPark3;
                    nilaiTotalJatimPark3 = nilaiTotalJatimPark3 + bobotPrioritasJarakJatimPark3;
                }

                if (dataSnapshot.child("museum-angkut").getValue() != null) {
                    nilaiAlternatifJarakMuseumAngkut = dataSnapshot.child("museum-angkut").getValue(Double.class);
                    bobotPrioritasJarakMuseumAngkut = bobotPrioritasA * nilaiAlternatifJarakMuseumAngkut;
                    nilaiTotalMuseumAngkut = nilaiTotalMuseumAngkut + bobotPrioritasJarakMuseumAngkut;
                }

                if (dataSnapshot.child("pantai-sempu").getValue() != null) {
                    nilaiAlternatifJarakPantaiSempu = dataSnapshot.child("pantai-sempu").getValue(Double.class);
                    bobotPrioritasJarakPantaiSempu = bobotPrioritasA * nilaiAlternatifJarakPantaiSempu;
                    nilaiTotalPantaiSempu = nilaiTotalPantaiSempu + bobotPrioritasJarakPantaiSempu;
                }

                if (dataSnapshot.child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatifJarakPantaiSendiki = dataSnapshot.child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritasJarakPantaiSendiki = bobotPrioritasA * nilaiAlternatifJarakPantaiSendiki;
                    nilaiTotalPantaiSendiki = nilaiTotalPantaiSendiki + bobotPrioritasJarakPantaiSendiki;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child("alternatif").child("alternatif-harga").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("gunung-bromo").getValue() != null) {
                    nilaiAlternatifHargaGunungBromo = dataSnapshot.child("gunung-bromo").getValue(Double.class);
                    bobotPrioritasHargaGunungBromo = bobotPrioritasB * nilaiAlternatifHargaGunungBromo;
                    nilaiTotalGunungBromo = nilaiTotalGunungBromo + bobotPrioritasHargaGunungBromo;

                    hasil2.setText(Double.toString(bobotPrioritasHargaGunungBromo));
                }

                if (dataSnapshot.child("gunung-semeru").getValue() != null) {
                    nilaiAlternatifHargaGunungSemeru = dataSnapshot.child("gunung-semeru").getValue(Double.class);
                    bobotPrioritasHargaGunungSemeru = bobotPrioritasB * nilaiAlternatifHargaGunungSemeru;
                    nilaiTotalGunungSemeru = nilaiTotalGunungSemeru + bobotPrioritasHargaGunungSemeru;
                }

                if (dataSnapshot.child("jatim-park-3").getValue() != null) {
                    nilaiAlternatifHargaJatimPark3 = dataSnapshot.child("jatim-park-3").getValue(Double.class);
                    bobotPrioritasHargaJatimPark3 = bobotPrioritasB * nilaiAlternatifHargaJatimPark3;
                    nilaiTotalJatimPark3 = nilaiTotalJatimPark3 + bobotPrioritasHargaJatimPark3;
                }

                if (dataSnapshot.child("museum-angkut").getValue() != null) {
                    nilaiAlternatifHargaMuseumAngkut = dataSnapshot.child("museum-angkut").getValue(Double.class);
                    bobotPrioritasHargaMuseumAngkut = bobotPrioritasB * nilaiAlternatifHargaMuseumAngkut;
                    nilaiTotalMuseumAngkut = nilaiTotalMuseumAngkut + bobotPrioritasHargaMuseumAngkut;
                }

                if (dataSnapshot.child("pantai-sempu").getValue() != null) {
                    nilaiAlternatifHargaPantaiSempu = dataSnapshot.child("pantai-sempu").getValue(Double.class);
                    bobotPrioritasHargaPantaiSempu = bobotPrioritasB * nilaiAlternatifHargaPantaiSempu;
                    nilaiTotalPantaiSempu = nilaiTotalPantaiSempu + bobotPrioritasHargaPantaiSempu;
                }

                if (dataSnapshot.child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatifHargaPantaiSendiki = dataSnapshot.child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritasHargaPantaiSendiki = bobotPrioritasB * nilaiAlternatifHargaPantaiSendiki;
                    nilaiTotalPantaiSendiki = nilaiTotalPantaiSendiki + bobotPrioritasHargaPantaiSendiki;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child("alternatif").child("alternatif-fasilitas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("gunung-bromo").getValue() != null) {
                    nilaiAlternatifFasilitasGunungBromo = dataSnapshot.child("gunung-bromo").getValue(Double.class);
                    bobotPrioritasFasilitasGunungBromo = bobotPrioritasC * nilaiAlternatifFasilitasGunungBromo;
                    nilaiTotalGunungBromo = nilaiTotalGunungBromo + bobotPrioritasFasilitasGunungBromo;

                    hasil3.setText(Double.toString(bobotPrioritasFasilitasGunungBromo));
                }

                if (dataSnapshot.child("gunung-semeru").getValue() != null) {
                    nilaiAlternatifFasilitasGunungSemeru = dataSnapshot.child("gunung-semeru").getValue(Double.class);
                    bobotPrioritasFasilitasGunungSemeru = bobotPrioritasC * nilaiAlternatifFasilitasGunungSemeru;

                    nilaiTotalGunungSemeru = nilaiTotalGunungSemeru + bobotPrioritasFasilitasGunungSemeru;
                }

                if (dataSnapshot.child("jatim-park-3").getValue() != null) {
                    nilaiAlternatifFasilitasJatimPark3 = dataSnapshot.child("jatim-park-3").getValue(Double.class);
                    bobotPrioritasFasilitasJatimPark3 = bobotPrioritasC * nilaiAlternatifFasilitasJatimPark3;

                    nilaiTotalJatimPark3 = nilaiTotalJatimPark3 + bobotPrioritasFasilitasJatimPark3;
                }

                if (dataSnapshot.child("museum-angkut").getValue() != null) {
                    nilaiAlternatifFasilitasMuseumAngkut = dataSnapshot.child("museum-angkut").getValue(Double.class);
                    bobotPrioritasFasilitasMuseumAngkut = bobotPrioritasC * nilaiAlternatifFasilitasMuseumAngkut;

                    nilaiTotalMuseumAngkut = nilaiTotalMuseumAngkut + bobotPrioritasFasilitasMuseumAngkut;
                }

                if (dataSnapshot.child("pantai-sempu").getValue() != null) {
                    nilaiAlternatifFasilitasPantaiSempu = dataSnapshot.child("pantai-sempu").getValue(Double.class);
                    bobotPrioritasFasilitasPantaiSempu = bobotPrioritasC * nilaiAlternatifFasilitasPantaiSempu;

                    nilaiTotalPantaiSempu = nilaiTotalPantaiSempu + bobotPrioritasFasilitasPantaiSempu;
                }

                if (dataSnapshot.child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatifFasilitasPantaiSendiki = dataSnapshot.child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritasFasilitasPantaiSendiki = bobotPrioritasC * nilaiAlternatifFasilitasPantaiSendiki;

                    nilaiTotalPantaiSendiki = nilaiTotalPantaiSendiki + bobotPrioritasFasilitasPantaiSendiki;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child("alternatif").child("alternatif-akses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("gunung-bromo").getValue() != null) {
                    nilaiAlternatifAksesGunungBromo = dataSnapshot.child("gunung-bromo").getValue(Double.class);
                    bobotPrioritasAksesGunungBromo = bobotPrioritasD * nilaiAlternatifAksesGunungBromo;
                    nilaiTotalGunungBromo = nilaiTotalGunungBromo + bobotPrioritasAksesGunungBromo;

                    hasil4.setText(Double.toString(bobotPrioritasAksesGunungBromo));
                    totalHasil.setText(Double.toString(nilaiTotalGunungBromo));
                }

                if (dataSnapshot.child("gunung-semeru").getValue() != null) {
                    nilaiAlternatifAksesGunungSemeru = dataSnapshot.child("gunung-semeru").getValue(Double.class);
                    bobotPrioritasAksesGunungSemeru = bobotPrioritasD * nilaiAlternatifAksesGunungSemeru;

                    nilaiTotalGunungSemeru = nilaiTotalGunungSemeru + bobotPrioritasAksesGunungSemeru;
                }

                if (dataSnapshot.child("jatim-park-3").getValue() != null) {
                    nilaiAlternatifAksesJatimPark3 = dataSnapshot.child("jatim-park-3").getValue(Double.class);
                    bobotPrioritasAksesJatimPark3 = bobotPrioritasD * nilaiAlternatifAksesJatimPark3;

                    nilaiTotalJatimPark3 = nilaiTotalJatimPark3 + bobotPrioritasAksesJatimPark3;
                }

                if (dataSnapshot.child("museum-angkut").getValue() != null) {
                    nilaiAlternatifAksesMuseumAngkut = dataSnapshot.child("museum-angkut").getValue(Double.class);
                    bobotPrioritasAksesMuseumAngkut = bobotPrioritasD * nilaiAlternatifAksesMuseumAngkut;

                    nilaiTotalMuseumAngkut = nilaiTotalMuseumAngkut + bobotPrioritasAksesMuseumAngkut;
                }

                if (dataSnapshot.child("pantai-sempu").getValue() != null) {
                    nilaiAlternatifAksesPantaiSempu = dataSnapshot.child("pantai-sempu").getValue(Double.class);
                    bobotPrioritasAksesPantaiSempu = bobotPrioritasD * nilaiAlternatifAksesPantaiSempu;

                    nilaiTotalPantaiSempu = nilaiTotalPantaiSempu + bobotPrioritasAksesPantaiSempu;
                }

                if (dataSnapshot.child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatifAksesPantaiSendiki = dataSnapshot.child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritasAksesPantaiSendiki = bobotPrioritasD * nilaiAlternatifAksesPantaiSendiki;

                    nilaiTotalPantaiSendiki = nilaiTotalPantaiSendiki + bobotPrioritasAksesPantaiSendiki;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        nilaiTotalGunungBromo = bobotPrioritasJarakGunungBromo + bobotPrioritasHargaGunungBromo + bobotPrioritasFasilitasGunungBromo + bobotPrioritasAksesGunungBromo;
//        nilaiTotalGunungSemeru = bobotPrioritasJarakGunungSemeru + bobotPrioritasHargaGunungSemeru + bobotPrioritasFasilitasGunungSemeru + bobotPrioritasAksesGunungSemeru;
//        nilaiTotalJatimPark3 = bobotPrioritasJarakJatimPark3 + bobotPrioritasHargaJatimPark3 + bobotPrioritasFasilitasJatimPark3 + bobotPrioritasAksesJatimPark3;
//        nilaiTotalMuseumAngkut = bobotPrioritasJarakMuseumAngkut + bobotPrioritasHargaMuseumAngkut + bobotPrioritasFasilitasMuseumAngkut + bobotPrioritasAksesMuseumAngkut;
//        nilaiTotalPantaiSempu = bobotPrioritasJarakPantaiSempu + bobotPrioritasHargaPantaiSempu + bobotPrioritasFasilitasPantaiSempu + bobotPrioritasAksesPantaiSempu;
//        nilaiTotalPantaiSendiki = bobotPrioritasJarakPantaiSendiki + bobotPrioritasHargaPantaiSendiki + bobotPrioritasFasilitasPantaiSendiki + bobotPrioritasAksesPantaiSendiki;

//        totalHasil.setText(Double.toString(nilaiTotalGunungSemeru));
    }

    public void onClickRecommendation(View view) {
        Intent intent = new Intent(this, SurveyRecommendationActivity.class);
        startActivity(intent);
    }
}
