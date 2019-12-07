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
import com.bluohazard.tourdengalam.activities.detail.DetailPlaygroundActivity;
import com.bluohazard.tourdengalam.activities.menu.ListVacationActivity;
import com.bluohazard.tourdengalam.models.Playground;
import com.bluohazard.tourdengalam.viewholders.PlaygroundViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PlaygroundActivity extends AppCompatActivity {

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Playground, PlaygroundViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = findViewById(R.id.list_playground);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Playground>()
                .setQuery(query, Playground.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Playground, PlaygroundViewHolder>(options) {
            @Override
            public PlaygroundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new PlaygroundViewHolder(inflater.inflate(R.layout.item_playground, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull PlaygroundViewHolder holder, int position, @NonNull final Playground model) {
                holder.setDisplayImage(model.getImage_url(), PlaygroundActivity.this);

                holder.cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), DetailPlaygroundActivity.class);
                        i.putExtra("name", model.getName());
                        i.putExtra("image-url", model.getImage_url());
                        i.putExtra("location", model.getLocation_title());
                        i.putExtra("description", model.getDescription());
                        startActivity(i);
                    }
                });

                holder.bindToPlayground(model, new View.OnClickListener() {
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
        Query query = mDatabase.child("list-vacation").child("playground").orderByChild("name");
        return query;
    }


    public void onClickListVacation(View view) {
        Intent intent = new Intent(this, ListVacationActivity.class);
        startActivity(intent);
    }
}
