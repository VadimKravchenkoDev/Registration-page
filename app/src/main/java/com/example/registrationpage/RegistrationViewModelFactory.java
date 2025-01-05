package com.example.registrationpage;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RegistrationViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public RegistrationViewModelFactory(Context context){
        this.context = context.getApplicationContext();
    }
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new RegistrationViewModel();
    }
}
