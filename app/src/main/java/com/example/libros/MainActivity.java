package com.example.libros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.libros.Adapters.libroAdapter;
import com.example.libros.Models.libros;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import com.example.libros.R;

public class MainActivity extends AppCompatActivity {

    TextView wifiStatus;
    private RecyclerView recyclerViewLibros;
    private static final int REQUEST_STORAGE_PERMISSION = 101;
    private static final int PICK_IMAGE_REQUEST = 102;
    Button btnSeleccionar;
    ImageView imagenSeleccionada;

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

        libroAdapter adapter = new libroAdapter(listaLibros, this);
        recyclerViewLibros.setAdapter(adapter);

        wifiStatus = findViewById(R.id.wifiStatus);

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        boolean isWifiEnabled = wifiManager.isWifiEnabled();
        wifiStatus.setText("Wifi está " + (isWifiEnabled ? "Encendido" : "Apagado"));

        btnSeleccionar = findViewById(R.id.btnSeleccionarImagen);
        imagenSeleccionada = findViewById(R.id.imagenSeleccionada);

        // Opción 1: Usando expresión lambda (requiere el método)
        btnSeleccionar.setOnClickListener(view -> verificarPermisoYSeleccionarImagen());

        // Opción 2: Alternativa sin lambda (comentada)
        // btnSeleccionar.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         verificarPermisoYSeleccionarImagen();
        //     }
        // });
    }

    // Método que faltaba para verificar permisos y seleccionar imagen
    private void verificarPermisoYSeleccionarImagen() {
        // Verificar si tenemos permiso para leer almacenamiento externo
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Solicitar permiso si no lo tenemos
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_STORAGE_PERMISSION);
        } else {
            // Si ya tenemos permiso, abrir selector de imágenes
            seleccionarImagen();
        }
    }

    // Método para abrir el selector de imágenes
    private void seleccionarImagen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen"), PICK_IMAGE_REQUEST);
    }

    // Manejar la respuesta de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, abrir selector de imágenes
                seleccionarImagen();
            } else {
                // Permiso denegado
                Toast.makeText(this, "Permiso necesario para seleccionar imágenes", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Manejar el resultado de la selección de imagen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            imagenSeleccionada.setImageURI(imageUri);
            imagenSeleccionada.setVisibility(View.VISIBLE);
        }
    }
}