package com.example.registrationpage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RegistrationViewModel extends androidx.lifecycle.ViewModel {
    public final MutableLiveData<RegistrationData> registrationDate = new MutableLiveData<>(new RegistrationData());

    public LiveData<RegistrationData> getRegistrationData() {
        return registrationDate;
    }

    public void setRegistrationDate(String name, String surname, String password) {
        RegistrationData data = registrationDate.getValue();
        data.setName(name);
        data.setSurname(surname);
        data.setPassword(password);
        registrationDate.setValue(data);
    }
}
