package com.example.libros.Adapters;

import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libros.DetalleLibroActivity;
import com.example.libros.Models.libros;
import com.example.libros.R;

public class libroAdapter extends RecyclerView.Adapter<libroAdapter.LibroViewHolder> {

    private List<libros> listaLibros;
    private Context context;

    public libroAdapter(List<libros> listaLibros, Context context) {
        this.listaLibros = listaLibros;
        this.context = context;
    }

    public static class LibroViewHolder extends RecyclerView.ViewHolder {
        TextView txtTituloLibro;
        TextView txtAutorLibro;
        TextView txtGeneroLibro;
        TextView txtAnoLibro;

        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTituloLibro = itemView.findViewById(R.id.txtTituloLibro);
            txtAutorLibro = itemView.findViewById(R.id.txtAutorLibro);
            txtGeneroLibro = itemView.findViewById(R.id.txtGeneroLibro);
            txtAnoLibro = itemView.findViewById(R.id.txtAnoLibro);
        }
    }

    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
        return new LibroViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {
        libros libroActual = listaLibros.get(position);

        holder.txtTituloLibro.setText(libroActual.getTitulo());
        holder.txtAutorLibro.setText("Autor: " + libroActual.getAutor());
        holder.txtGeneroLibro.setText("Género: " + libroActual.getGenero());
        holder.txtAnoLibro.setText("Año: " + libroActual.getAno());

        // Configurar el click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalleLibroActivity.class);
                intent.putExtra("libro", libroActual); // Ya no necesita casting porque implementa Serializable
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }
}