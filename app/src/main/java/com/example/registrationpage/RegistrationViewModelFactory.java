package com.example.registrationpage;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RegistrationViewModelFactory implements ViewModelProvider.Factory {



    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if(modelClass.isAssignableFrom(ViewModel.class)){
            return
        }
    }
}
