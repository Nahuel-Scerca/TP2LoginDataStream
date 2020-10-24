package com.example.tp2Logindatastream.request;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.tp2Logindatastream.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {

    private static File archivo;

    protected static File conectar(Context context){


        if(archivo == null) {
            archivo = new File(context.getFilesDir(), "/usuario.dat");
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error al crear el archivo", Toast.LENGTH_LONG).show();
            }
        }
        return archivo;
    }

    public static  void guardar(Context context , Usuario usuario){

        File archiv = conectar(context);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(archiv);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeObject(usuario);
            bufferedOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error guardar", Toast.LENGTH_SHORT).show();
        }


    }

    public static Usuario leer(Context context){
        File archiv = conectar(context);
        Usuario usuario = null;

        try {
            FileInputStream fileInputStream  = new FileInputStream(archiv);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            usuario = (Usuario) objectInputStream.readObject();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error ", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public static Boolean login(Context context, String email, String password){


        Usuario user= leer(context);
        String mailValido = user.getMail();
        String passValida = user.getPassword();


            if(email.equals(mailValido) && password.equals(passValida)){
                return true;
            }
            else{
                return false;
            }


    }
}
