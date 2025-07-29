package com.example.libros;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.example.libros.Models.libros;

public class DetalleLibroActivity extends AppCompatActivity {

    private EditText etTitulo, etAutor, etGenero, etAno, etSinopsis;
    private Button btnGuardar, btnEliminar;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_libro);

        // Inicializar vistas
        initializeViews();

        // Configurar toolbar
        setupToolbar();

        // Recuperar y mostrar datos del libro
        loadBookData();

        // Configurar listeners de botones
        setupButtonListeners();
    }

    private void initializeViews() {
        etTitulo = findViewById(R.id.etTituloDetalle);
        etAutor = findViewById(R.id.etAutorDetalle);
        etGenero = findViewById(R.id.etGeneroDetalle);
        etAno = findViewById(R.id.etAnoDetalle);
        etSinopsis = findViewById(R.id.etSinopsisDetalle);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnEliminar = findViewById(R.id.btnEliminar);
        toolbar = findViewById(R.id.topAppBarDetalle);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detalle del Libro");
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadBookData() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("libro")) {
            libros libro = (libros) intent.getSerializableExtra("libro");

            if (libro != null) {
                etTitulo.setText(libro.getTitulo());
                etAutor.setText(libro.getAutor());
                etGenero.setText(libro.getGenero());
                etAno.setText(String.valueOf(libro.getAno()));
                etSinopsis.setText(libro.getSinopsis());
            }
        }
    }

    private void setupButtonListeners() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener valores actuales
                String titulo = etTitulo.getText().toString().trim();
                String autor = etAutor.getText().toString().trim();
                String genero = etGenero.getText().toString().trim();
                String anoStr = etAno.getText().toString().trim();
                String sinopsis = etSinopsis.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || anoStr.isEmpty()) {
                    Toast.makeText(DetalleLibroActivity.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int ano = Integer.parseInt(anoStr);
                    // Aquí puedes implementar la lógica para guardar los cambios
                    Toast.makeText(DetalleLibroActivity.this, "Cambios guardados correctamente", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(DetalleLibroActivity.this, "Por favor ingrese un año válido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes implementar la lógica para eliminar el libro
                Toast.makeText(DetalleLibroActivity.this, "Libro eliminado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}