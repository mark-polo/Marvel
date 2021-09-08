package com.mrm.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static RecyclerView recyclerView;
    FilmAdapter filmAdapter;
    static List<FilmModel> filmModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filmModelList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView = findViewById(R.id.marvelFilms);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);

        filmAdapter = new FilmAdapter(filmModelList, this);

        recyclerView.setAdapter(filmAdapter);

        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {
        DatabaseReference reference = FirebaseDatabase.getInstance("https://marvel-c9592-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Marvel");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                filmModelList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    FilmModel model = dataSnapshot1.getValue(FilmModel.class);
                    filmModelList.add(model);
                    filmAdapter = new FilmAdapter(filmModelList, MainActivity.this);
                    recyclerView.setAdapter(filmAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

/*
Marvel
1
Name:
"Iron man 2008"
Poster:
"https://wp-media.patheos.com/blogs/sites/747/20..."
2
Name:
"Iron man 2010"
Poster:
"https://kritikanstvo.ru/movies/i/ironman2/poste..."
3
Name:
"Thor 2011"
Poster:
"https://cdn.substack.com/image/fetch/f_auto,q_a..."
4
Name:
"Capitan America : First Avenger"
Poster:
"http://ic.pics.livejournal.com/iskander_zombie/..."
5
Name:
"Avengers 2012"
Poster:
"http://ccmoviereviews.com/wp-content/uploads/20..."
6
Name:
"Capitan America : The Winter Soldier"
Poster:
"https://i0.wp.com/2.bp.blogspot.com/-S48A9-GLSW..."

 */