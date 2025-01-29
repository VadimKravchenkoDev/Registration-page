package com.example.registrationpage;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

        binding.buttonContinue.setOnClickListener(clickContinue -> {
            String name = binding.editTextName.getText().toString();
            String surname = binding.editTextSername.getText().toString();
            String password = binding.editTextPassword.getText().toString();
            if (!RegistrationValidator.areFieldsValid(name, surname, password)) {
                String errorMessage = RegistrationValidator.getErrorMessage(name, surname, password);
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
            } else {
                registrationViewModel.setRegistrationDate(name, surname, password);
                Log.d("MainActivity", "add data");
                setNewFragment(parentFragment);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void setNewFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Log.d("MainActivity", "Replacing fragment...");
        ft.replace(R.id.frameLayout,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}