package com.example.g2s21_ddim_practica1_armm;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> datos = new ArrayList<>();
    almacen Almacen;
    ArrayList<String> infogrid = new ArrayList<>();
    ArrayAdapter<String> adapter;
    EditText txtnombre, txtedad, txtcorreo;
    Button btngrabar, btnsalir, btnborrar;
    GridView gvdatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar los elementos
        txtnombre = findViewById(R.id.txtnombre);
        txtedad = findViewById(R.id.txtedad);
        txtcorreo = findViewById(R.id.txtcorreo);
        btngrabar = findViewById(R.id.btngrabar);
        btnsalir = findViewById(R.id.btnsalir);
        btnborrar = findViewById(R.id.btnborrar);
        gvdatos = findViewById(R.id.gvdatos);
        cargardatos();

        btngrabar.setOnClickListener(v -> {
            String nuevoDato = txtnombre.getText().toString() + "," +
                    Integer.parseInt(txtedad.getText().toString()) + "," +
                    txtcorreo.getText().toString();
            datos.add(nuevoDato);

            if (Almacen.grabarlist(datos, this)) {
                Toast.makeText(this, "Datos grabados correctamente", Toast.LENGTH_SHORT).show();
                cargardatos();
                vaciar();
                infogrid.clear();
                for (String cadena : datos) {
                    String[] separacion = cadena.split(",");
                    if (separacion.length == 3) {
                        infogrid.add(separacion[0]);
                        infogrid.add(separacion[1]);
                        infogrid.add(separacion[2]);
                    }
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Error al grabar los datos", Toast.LENGTH_SHORT).show();
            }
        });

        btnborrar.setOnClickListener(v -> {
            if (Almacen.borrarDatos(this)) {
                Toast.makeText(this, "Datos borrados correctamente", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(this, "Error al borrar los datos", Toast.LENGTH_SHORT).show();
            }
        });

        btnsalir.setOnClickListener(v -> finish());
    }
    private void cargardatos(){
        infogrid.clear();
        Almacen = new almacen();
        if (Almacen.leer(this)) {
            datos = Almacen.getDatos();
            infogrid.clear();

            for (String cadena : datos) {
                String[] separacion = cadena.split(",");
                if (separacion.length == 3) {
                    infogrid.add(separacion[0]);
                    infogrid.add(separacion[1]);
                    infogrid.add(separacion[2]);
                }
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, infogrid);
            gvdatos.setAdapter(adapter);
        }
    }
    private void vaciar(){
        txtnombre.setText("");
        txtedad.setText("");
        txtcorreo.setText("");
    }
}
