package com.example.registrationpage;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.registrationpage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
private RegistrationViewModel registrationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        registrationViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        binding.buttonContinue.setOnClickListener(v -> {
            String nameEditText = binding.editTextName.getText().toString();
            String surnameEditText = binding.editTextSername.getText().toString();
            String passwordEditText = binding.editTextPassword.getText().toString();
            if(!RegistrationValidator.areFieldsValid(nameEditText, surnameEditText, passwordEditText)){
                String errorMessage = RegistrationValidator.getErrorMessage(nameEditText, surnameEditText, passwordEditText);
                Toast.makeText(this, errorMessage,Toast.LENGTH_LONG).show();
            } else {
                registrationViewModel.setRegistrationDate(nameEditText, surnameEditText, passwordEditText);
            }
        });




    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        binding = null;
    }
}