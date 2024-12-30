package com.example.registrationpage;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.registrationpage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
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
        String nameEditText = binding.editTextName.toString();
        String surnameEditText = binding.editTextSername.toString();
        String passwordEditText = binding.editTextPassword.toString();

        if(!RegistrationValidator.areFieldsValid(nameEditText, surnameEditText, passwordEditText)){
            String errorMessage = RegistrationValidator.getErrorMessage(nameEditText, surnameEditText, passwordEditText);
            Toast.makeText(this, errorMessage,Toast.LENGTH_LONG).show();
        } else {
            //my logic
        }

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        binding = null;
    }
}