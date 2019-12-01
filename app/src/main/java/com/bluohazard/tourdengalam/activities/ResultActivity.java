package com.bluohazard.tourdengalam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.menu.SurveyRecommendationActivity;

public class ResultActivity extends AppCompatActivity {

    TextView tvValueJarakToHarga, tvValueHargaToJarak;
    TextView tvValueJarakToFasilitas, tvValueFasilitasToJarak;
    TextView tvValueJarakToAkses, tvValueAksesToJarak;
    TextView tvValueHargaToFasilitas, tvValueFasilitasToHarga;
    TextView tvValueHargaToAkses, tvValueAksesToHarga;
    TextView tvValueFasilitasToAkses, tvValueAksesToFasilitas;

    String strValueHargaToJarak, strValueFasilitasToJarak, strValueAksesToJarak, strValueFasilitasToHarga, strAksesToHarga, strAksesToFasilitas;
    double valueHargaToJarak, valueFasilitasToJarak, valueAksesToJarak, valueFasilitasToHarga, valueAksesToHarga, valueAksesToFasilitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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
    }

    public void onClickRecommendation(View view) {
        Intent intent = new Intent(this, SurveyRecommendationActivity.class);
        startActivity(intent);
    }


}
