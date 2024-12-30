package com.example.registrationpage;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RegistrationViewModelFactory implements ViewModelProvider.Factory {

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new RegistrationViewModel();
    }
}
