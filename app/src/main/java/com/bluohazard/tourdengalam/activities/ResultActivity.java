package com.bluohazard.tourdengalam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.menu.SurveyRecommendationActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        // Perhitungan AHP

        double[][] matrix = new double[4][4];
        double[][] matrixC = new double[4][4];

        double barisA, barisB, barisC, barisD, jumlahBaris;
        double hasilBagiA, hasilBagiB, hasilBagiC, hasilBagiD;

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

        for(int a=0;a<4;a++)
        {
            for(int b=0;b<4;b++)
            {
                System.out.print("[" + matrix[a][b] + "]");
            }
        }

        // Step 2 ( Dikuadratkan )

        for (int i=0; i<=3;i++)
        {
            for (int j=0; j<=3;j++)
            {
                for(int k=0;k<=3;k++)
                {
                    matrixC[i][j]=matrixC[i][j]+matrix[i][k]*matrix[k][j];
                }
            }
        }

        // Step 3 ( dijumlahkan tiap baris )

        barisA = matrixC[0][0] + matrixC[0][1] + matrixC[0][2] + matrixC[0][3];
        barisB = matrixC[1][0] + matrixC[1][1] + matrixC[1][2] + matrixC[1][3];
        barisC = matrixC[2][0] + matrixC[2][1] + matrixC[2][2] + matrixC[2][3];
        barisD = matrixC[3][0] + matrixC[3][1] + matrixC[3][2] + matrixC[3][3];

        jumlahBaris = barisA + barisB + barisC + barisD;

        // Step 4 ( dibagi dengan jumlah )

        hasilBagiA = barisA / jumlahBaris;
        hasilBagiB = barisB / jumlahBaris;
        hasilBagiC = barisC / jumlahBaris;
        hasilBagiD = barisD / jumlahBaris;

        double total = hasilBagiA + hasilBagiB + hasilBagiC + hasilBagiD;

        hasil1.setText(Double.toString(hasilBagiA));
        hasil2.setText(Double.toString(hasilBagiB));
        hasil3.setText(Double.toString(hasilBagiC));
        hasil4.setText(Double.toString(hasilBagiD));
        totalHasil.setText(Double.toString(total));

        // Memasukkan data hasilbagi ke dalam database

        mDatabase = FirebaseDatabase.getInstance().getReference("rank");

        mDatabase.child("jarak").setValue(hasilBagiA);
        mDatabase.child("harga").setValue(hasilBagiB);
        mDatabase.child("fasilitas").setValue(hasilBagiC);
        mDatabase.child("akses").setValue(hasilBagiD);

    }


    public void onClickRecommendation(View view) {
        Intent intent = new Intent(this, SurveyRecommendationActivity.class);
        startActivity(intent);
    }
}
