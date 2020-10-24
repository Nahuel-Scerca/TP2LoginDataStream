package com.example.tp2Logindatastream.registro;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2Logindatastream.model.Usuario;
import com.example.tp2Logindatastream.request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Usuario> user;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUser() {
        if(user == null){
            user= new MutableLiveData<>();
        }
        return user;
    }

    public void registrar(Long dni, String mail, String apellido, String nombre, String password){

        Usuario usuario = new Usuario(dni,mail,apellido,nombre,password);

        ApiClient api= new ApiClient();

        api.guardar(context,usuario);

        Log.d("salida",usuario.getMail()+" "+usuario.getPassword());

    }

    public  void logeado(Boolean bool){
        //consultar al usuario logeado

        if(bool == true){
            ApiClient api= new ApiClient();
            Usuario usuario = new Usuario();
            usuario = api.leer(context);

            user.setValue(usuario);
        }

    }

}
