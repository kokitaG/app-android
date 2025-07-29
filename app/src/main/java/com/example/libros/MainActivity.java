package com.example.libros;
import com.example.libros.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.libros.Adapters.libroAdapter;
import com.example.libros.Models.libros;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewLibros = findViewById(R.id.recyclerViewLibros);
        recyclerViewLibros.setLayoutManager(new LinearLayoutManager(this));

        List<libros> listaLibros = new ArrayList<>();

        // Añadir datos de ejemplo con sinopsis
        listaLibros.add(new libros("Cien años de soledad", "Gabriel García Márquez", "Realismo Mágico", 1967, "Una obra maestra que narra la historia de la familia Buendía en el pueblo ficticio de Macondo."));
        listaLibros.add(new libros("Don Quijote de la Mancha", "Miguel de Cervantes", "Novela Picaresca", 1605, "Las aventuras de un hidalgo que enloquece leyendo libros de caballerías y decide convertirse en caballero andante."));
        listaLibros.add(new libros("1984", "George Orwell", "Distopía", 1949, "Una novela distópica sobre un futuro totalitario donde el Gran Hermano todo lo ve."));
        listaLibros.add(new libros("Orgullo y Prejuicio", "Jane Austen", "Romance", 1813, "La historia de las hermanas Bennet y sus relaciones amorosas en la Inglaterra del siglo XIX."));
        listaLibros.add(new libros("El Principito", "Antoine de Saint-Exupéry", "Fábula", 1943, "Un cuento poético sobre un pequeño príncipe que viaja por el universo y descubre la extraña forma en que los adultos ven la vida."));


        libroAdapter adapter = new libroAdapter(listaLibros, this); // <--- PASAR 'this' (el contexto)
        recyclerViewLibros.setAdapter(adapter);
    }
}