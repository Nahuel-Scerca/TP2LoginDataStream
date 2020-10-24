package com.example.tp2Logindatastream.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2Logindatastream.model.Usuario;
import com.example.tp2Logindatastream.registro.RegistroActivity;
import com.example.tp2Logindatastream.request.ApiClient;

public class LoginActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> error;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }

    public LiveData<String> getError() {
        if(error == null){
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void autenticacion(String u, String c){

        ApiClient api= new ApiClient();



        if ( api.login(context,u,c)) {

            Intent intent = new Intent(context, RegistroActivity.class);
            intent.putExtra("logueado",true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            error.setValue("Usuario y/o o contraseña incorrectos");
        }
    }

}
