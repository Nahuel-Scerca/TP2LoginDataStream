package com.example.tp2Logindatastream.registro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp2Logindatastream.R;
import com.example.tp2Logindatastream.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private EditText etDni,etApellido,etNombre,etMail,etPassword;
    private Button guardar;
    private RegistroActivityViewModel vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializar();
    }

    public void inicializar() {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        vm.getUser().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etDni.setText(usuario.getDni()+"");
                etApellido.setText(usuario.getApellido());
                etNombre.setText(usuario.getNombre());
                etMail.setText(usuario.getMail());
                 etPassword.setText(usuario.getPassword());
            }
        });

        etDni = findViewById(R.id.etDni);
        etApellido = findViewById(R.id.etApellido);
        etNombre = findViewById(R.id.etNombre);
        etMail = findViewById(R.id.etMail);
        etPassword = findViewById(R.id.etPassword);
        guardar = findViewById(R.id.btGuardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                Long Dni =Long.valueOf(etDni.getText().toString());
                String Apellido =etApellido.getText().toString();
                String Nombre =etNombre.getText().toString();
                String Mail =etMail.getText().toString();
                String Password =etPassword.getText().toString();
                vm.registrar(Dni,Apellido,Nombre,Mail,Password);
            }
        });

        vm.logeado(getIntent().getBooleanExtra("logueado",false));

    }

}
