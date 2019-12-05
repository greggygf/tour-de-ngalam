package com.bluohazard.tourdengalam.activities;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ResultActivity extends AppCompatActivity {

//    TextView hasil1, hasil2, hasil3, hasil4, totalHasil;

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

        // Rangebar Jarak ke Harga
        valueHargaToJarak = 1 / Double.valueOf("" + getIntent().getStringExtra("value-jarak-harga"));

        // Rangebar Jarak ke Fasilitas
        valueFasilitasToJarak = 1 / Double.valueOf("" + getIntent().getStringExtra("value-jarak-fasilitas"));

        // Rangebar Jarak ke Akses
        valueAksesToJarak = 1 / Double.valueOf("" + getIntent().getStringExtra("value-jarak-akses"));

        // Rangebar Harga ke Fasilitas
        valueFasilitasToHarga = 1 / Double.valueOf("" + getIntent().getStringExtra("value-harga-fasilitas"));

        // Rangebar Harga ke Akses
        valueAksesToHarga = 1 / Double.valueOf("" + getIntent().getStringExtra("value-harga-akses"));

        // Rangebar Fasilitas ke Akses
        valueAksesToFasilitas = 1 / Double.valueOf("" + getIntent().getStringExtra("value-fasilitas-akses"));

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

        // Memasukkan data hasilbagi ke dalam database

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

                    mDatabase.child("recommendation").child("gunung-bromo").child("value").setValue(nilaiTotalGunungBromo);
                }

                if (dataSnapshot.child("gunung-semeru").getValue() != null) {
                    nilaiAlternatifAksesGunungSemeru = dataSnapshot.child("gunung-semeru").getValue(Double.class);
                    bobotPrioritasAksesGunungSemeru = bobotPrioritasD * nilaiAlternatifAksesGunungSemeru;
                    nilaiTotalGunungSemeru = nilaiTotalGunungSemeru + bobotPrioritasAksesGunungSemeru;

                    mDatabase.child("recommendation").child("gunung-semeru").child("value").setValue(nilaiTotalGunungSemeru);
                }

                if (dataSnapshot.child("jatim-park-3").getValue() != null) {
                    nilaiAlternatifAksesJatimPark3 = dataSnapshot.child("jatim-park-3").getValue(Double.class);
                    bobotPrioritasAksesJatimPark3 = bobotPrioritasD * nilaiAlternatifAksesJatimPark3;
                    nilaiTotalJatimPark3 = nilaiTotalJatimPark3 + bobotPrioritasAksesJatimPark3;

                    mDatabase.child("recommendation").child("jatim-park-3").child("value").setValue(nilaiTotalJatimPark3);
                }

                if (dataSnapshot.child("museum-angkut").getValue() != null) {
                    nilaiAlternatifAksesMuseumAngkut = dataSnapshot.child("museum-angkut").getValue(Double.class);
                    bobotPrioritasAksesMuseumAngkut = bobotPrioritasD * nilaiAlternatifAksesMuseumAngkut;
                    nilaiTotalMuseumAngkut = nilaiTotalMuseumAngkut + bobotPrioritasAksesMuseumAngkut;

                    mDatabase.child("recommendation").child("museum-angkut").child("value").setValue(nilaiTotalMuseumAngkut);
                }

                if (dataSnapshot.child("pantai-sempu").getValue() != null) {
                    nilaiAlternatifAksesPantaiSempu = dataSnapshot.child("pantai-sempu").getValue(Double.class);
                    bobotPrioritasAksesPantaiSempu = bobotPrioritasD * nilaiAlternatifAksesPantaiSempu;
                    nilaiTotalPantaiSempu = nilaiTotalPantaiSempu + bobotPrioritasAksesPantaiSempu;

                    mDatabase.child("recommendation").child("pantai-sempu").child("value").setValue(nilaiTotalPantaiSempu);
                }

                if (dataSnapshot.child("pantai-sendiki").getValue() != null) {
                    nilaiAlternatifAksesPantaiSendiki = dataSnapshot.child("pantai-sendiki").getValue(Double.class);
                    bobotPrioritasAksesPantaiSendiki = bobotPrioritasD * nilaiAlternatifAksesPantaiSendiki;
                    nilaiTotalPantaiSendiki = nilaiTotalPantaiSendiki + bobotPrioritasAksesPantaiSendiki;

                    mDatabase.child("recommendation").child("pantai-sendiki").child("value").setValue(nilaiTotalPantaiSendiki);
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
        Query query = mDatabase.child("recommendation").orderByChild("value");
        return query;
    }
}
