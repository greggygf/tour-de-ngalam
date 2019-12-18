package com.bluohazard.tourdengalam.activities.list_vacation;

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
import com.bluohazard.tourdengalam.activities.detail.DetailMountainActivity;
import com.bluohazard.tourdengalam.activities.menu.ListVacationActivity;
import com.bluohazard.tourdengalam.models.Mountain;
import com.bluohazard.tourdengalam.settings.SharedPref;
import com.bluohazard.tourdengalam.viewholders.MountainViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MountainActivity extends AppCompatActivity {

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Mountain, MountainViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);

        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.DarkAppTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = findViewById(R.id.list_mountain);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Mountain>()
                .setQuery(query, Mountain.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Mountain, MountainViewHolder>(options) {
            @Override
            public MountainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new MountainViewHolder(inflater.inflate(R.layout.item_mountain, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull MountainViewHolder holder, int position, @NonNull final Mountain model) {
                holder.setDisplayImage(model.getImage_url(), MountainActivity.this);

                holder.cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), DetailMountainActivity.class);
                        i.putExtra("name", model.getName());
                        i.putExtra("image-url", model.getImage_url());
                        i.putExtra("location", model.getLocation_title());
                        i.putExtra("description", model.getDescription());
                        startActivity(i);
                    }
                });

                holder.bindToMountain(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        };

        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
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
        Query query = mDatabase.child("list-vacation").child("mountain").orderByChild("nama");
        return query;
    }

    public void onClickListVacation(View view) {
        Intent intent = new Intent(this, ListVacationActivity.class);
        startActivity(intent);
    }
}
