package com.example.g2s21_ddim_practica1_armm;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class almacen {

    List<String> datos = new ArrayList<>();
    String nomarc = "datos.txt";

    public boolean grabarlist(List<String> datos, Context context) {
        boolean estado = true;
        try {
            FileOutputStream archivo = context.openFileOutput(nomarc, Activity.MODE_PRIVATE);
            for (String informacion : datos) {
                archivo.write((informacion + "\n").getBytes());
            }
            archivo.flush();
            archivo.close();
        } catch (Exception ex) {
            estado = false;
        }
        return estado;
    }
    public boolean leer(Context context) {
        boolean estado = true;
        datos.clear();
        try {
            InputStreamReader archivo = new InputStreamReader(context.openFileInput(nomarc));
            BufferedReader br = new BufferedReader(archivo);
            String linea;
            while ((linea = br.readLine()) != null) {
                datos.add(linea);
            }
            br.close();
            archivo.close();
        } catch (IOException ex) {
            estado = false;
        }
        return estado;
    }
    public boolean borrarDatos(Context context) {
        boolean estado = true;
        File archivo = new File(context.getFilesDir(), nomarc);
        if (archivo.exists()) {
            estado = archivo.delete();
        }
        return estado;
    }
    public List<String> getDatos() {
        return datos;
    }
}
