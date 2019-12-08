package com.bluohazard.tourdengalam.activities.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.appyvet.materialrangebar.RangeBar;
import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.MainMenuActivity;
import com.bluohazard.tourdengalam.activities.ResultActivity;

public class SurveyRecommendationActivity extends AppCompatActivity {

    int valueJarakHarga = 0, valueJarakFasilitas = 0, valueJarakAkses = 0, valueHargaFasilitas = 0, valueHargaAkses = 0, valueFasilitasAkses = 0;
    RangeBar rangebar1, rangebar2, rangebar3, rangebar4, rangebar5, rangebar6;

    private RadioButton rdJarakDaripadaHarga, rdHargaDaripadaJarak;
    private RadioButton rdJarakDaripadaFasilitas, rdFasilitasDaripadaJarak;
    private RadioButton rdJarakDaripadaAkses, rdAksesDaripadaJarak;
    private RadioButton rdHargaDaripadaFasilitas, rdFasilitasDaripadaHarga;
    private RadioButton rdHargaDaripadaAkses, rdAksesDaripadaHarga;
    private RadioButton rdFasilitasDaripadaAkses, rdAksesDaripadaFasilitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_recommendation);

        // define the radiobutton

        rdJarakDaripadaHarga = findViewById(R.id.pilihJarakDaripadaHarga);
        rdHargaDaripadaJarak = findViewById(R.id.pilihHargaDaripadaJarak);

        rdJarakDaripadaFasilitas = findViewById(R.id.pilihJarakDaripadaFasilitas);
        rdFasilitasDaripadaJarak = findViewById(R.id.pilihFasilitasDaripadaJarak);

        rdJarakDaripadaAkses = findViewById(R.id.pilihJarakDaripadaAkses);
        rdAksesDaripadaJarak = findViewById(R.id.pilihAksesDaripadaJarak);

        rdHargaDaripadaFasilitas = findViewById(R.id.pilihHargaDaripadaFasilitas);
        rdFasilitasDaripadaHarga = findViewById(R.id.pilihFasilitasDaripadaHarga);

        rdHargaDaripadaAkses = findViewById(R.id.pilihHargaDaripadaAkses);
        rdAksesDaripadaHarga = findViewById(R.id.pilihAksesDaripadaHarga);

        rdFasilitasDaripadaAkses = findViewById(R.id.pilihFasilitasDaripadaAkses);
        rdAksesDaripadaFasilitas = findViewById(R.id.pilihAksesDaripadaFasilitas);

        // Rangebar jarak terhadap harga
        rangebar1 = findViewById(R.id.rangebar1);

        rangebar1.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                valueJarakHarga = Integer.parseInt(rightPinValue);
            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }
        });

        // Rangebar jarak terhadap fasilitas
        rangebar2 = findViewById(R.id.rangebar2);

        rangebar2.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                valueJarakFasilitas = Integer.parseInt(rightPinValue);
            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }
        });

        // Rangebar jarak terhadap akses
        rangebar3 = findViewById(R.id.rangebar3);

        rangebar3.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                valueJarakAkses = Integer.parseInt(rightPinValue);
            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }
        });

        // Rangebar harga terhadap fasilitas
        rangebar4 = findViewById(R.id.rangebar4);

        rangebar4.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                valueHargaFasilitas = Integer.parseInt(rightPinValue);
            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }
        });

        // Rangebar harga terhadap akses
        rangebar5 = findViewById(R.id.rangebar5);

        rangebar5.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                valueHargaAkses = Integer.parseInt(rightPinValue);
            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }
        });

        // Rangebar fasilitas terhadap akses
        rangebar6 = findViewById(R.id.rangebar6);

        rangebar6.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                valueFasilitasAkses = Integer.parseInt(rightPinValue);
            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }
        });
    }

    public void onClickMainMenu(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void onClickResult(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("value-jarak-harga", "" + valueJarakHarga);
        intent.putExtra("value-jarak-fasilitas", "" + valueJarakFasilitas);
        intent.putExtra("value-jarak-akses", "" + valueJarakAkses);
        intent.putExtra("value-harga-fasilitas", "" + valueHargaFasilitas);
        intent.putExtra("value-harga-akses", "" + valueHargaAkses);
        intent.putExtra("value-fasilitas-akses", "" + valueFasilitasAkses);

        if (rdJarakDaripadaHarga.isChecked()) {
            intent.putExtra("jarak-harga", "0");
        } else if (rdHargaDaripadaJarak.isChecked()) {
            intent.putExtra("jarak-harga", "1");
        }

        if (rdJarakDaripadaFasilitas.isChecked()) {
            intent.putExtra("jarak-fasilitas", "0");
        } else if (rdFasilitasDaripadaJarak.isChecked()) {
            intent.putExtra("jarak-fasilitas", "1");
        }

        if (rdJarakDaripadaAkses.isChecked()) {
            intent.putExtra("jarak-akses", "0");
        } else if (rdAksesDaripadaJarak.isChecked()) {
            intent.putExtra("jarak-akses", "1");
        }

        if (rdHargaDaripadaFasilitas.isChecked()) {
            intent.putExtra("harga-fasilitas", "0");
        } else if (rdFasilitasDaripadaHarga.isChecked()) {
            intent.putExtra("harga-fasilitas", "1");
        }

        if (rdHargaDaripadaAkses.isChecked()) {
            intent.putExtra("harga-akses", "0");
        } else if (rdAksesDaripadaHarga.isChecked()) {
            intent.putExtra("harga-akses", "1");
        }

        if (rdFasilitasDaripadaAkses.isChecked()) {
            intent.putExtra("fasilitas-akses", "0");
        } else if (rdAksesDaripadaFasilitas.isChecked()) {
            intent.putExtra("fasilitas-akses", "1");
        }

        startActivity(intent);
    }
}
