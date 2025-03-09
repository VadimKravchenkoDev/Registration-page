package com.example.registrationpage;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.registrationpage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private RegistrationViewModel registrationViewModel;
    ParentFragment parentFragment = new ParentFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainConstraint), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFieldsAndToggleCheckmark();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        // Назначаем TextWatcher всем трём полям
        binding.editTextName.addTextChangedListener(textWatcher);
        binding.editTextSername.addTextChangedListener(textWatcher);
        binding.editTextPassword.addTextChangedListener(textWatcher);


        RegistrationViewModelFactory factory = new RegistrationViewModelFactory(getApplicationContext());
        registrationViewModel = new ViewModelProvider(this, factory).get(RegistrationViewModel.class);
        RegistrationData registrationData = new RegistrationData(registrationViewModel);

        registrationViewModel.getIsImageVisible().observe(this, isVisible -> {
            binding.imageCloseEye.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            binding.imageOpenEye.setVisibility(isVisible ? View.GONE : View.VISIBLE);
            binding.editTextPassword.setInputType(isVisible ?
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                    InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            binding.editTextPassword.setTypeface(ResourcesCompat.getFont(this, R.font.roboto));
        });
        binding.imageCloseEye.setOnClickListener(v -> registrationData.onImageClick());
        binding.imageOpenEye.setOnClickListener(v -> registrationData.onImageClick());

        binding.editTextName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.editTextName.setTextColor(ContextCompat.getColor(this, R.color.black));
                if(binding.editTextName.getText().toString().equals("name")){binding.editTextName.setText("");}
            }
        });

        binding.editTextSername.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.editTextSername.setTextColor(ContextCompat.getColor(this, R.color.black));
                if(binding.editTextSername.getText().toString().equals("surname")){binding.editTextSername.setText("");}
            }
        });

        binding.editTextPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.editTextPassword.setTextColor(ContextCompat.getColor(this, R.color.black));
                if(binding.editTextPassword.getText().toString().equals("password")){binding.editTextPassword.setText("");}
            }
        });

        binding.buttonContinue.setOnClickListener(clickContinue -> {
            String name = binding.editTextName.getText().toString();
            String surname = binding.editTextSername.getText().toString();
            String password = binding.editTextPassword.getText().toString();
            if (!RegistrationValidator.areFieldsValid(name, surname, password)) {
                String errorMessage = RegistrationValidator.getErrorMessage(MainActivity.this, name, surname, password);

                if (name.isEmpty()) {
                    binding.editTextName.setText("name");
                    binding.editTextName.setTextColor(ContextCompat.getColor(this, R.color.red));
                }

                if (surname.isEmpty()) {
                    binding.editTextSername.setText("surname");
                    binding.editTextSername.setTextColor(ContextCompat.getColor(this, R.color.red));
                }

                if (password.isEmpty()) {
                    binding.editTextPassword.setText("password");
                    binding.editTextPassword.setTextColor(ContextCompat.getColor(this, R.color.red));
                    binding.editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }

                Toast toast = new Toast(this);
                View toastView = getLayoutInflater().inflate(R.layout.custom_toast, null);
                TextView textView = toastView.findViewById(R.id.custom_toast_message);
                textView.setText(errorMessage);
                toast.setView(toastView);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();

            } else {
                registrationViewModel.setRegistrationDate(name, surname, password);
                Log.d("MainActivity", "add data");
                setNewFragment(parentFragment);
            }
        });
    }
    private void checkFieldsAndToggleCheckmark() {
        String name = binding.editTextName.getText().toString().trim();
        String surname = binding.editTextSername.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString().trim();
        
        if (!name.isEmpty()) {
            binding.imNameChecked.setVisibility(View.VISIBLE);
        } else {
            binding.imNameChecked.setVisibility(View.GONE);
        }

        if (!surname.isEmpty()) {
            binding.imSernameChecked.setVisibility(View.VISIBLE);
        } else {
            binding.imSernameChecked.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void setNewFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Log.d("MainActivity", "Replacing fragment...");
        ft.replace(R.id.frameLayout, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}