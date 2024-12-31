package com.example.registrationpage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RegistrationViewModel extends androidx.lifecycle.ViewModel {

    private final MutableLiveData<RegistrationData> registrationDate = new MutableLiveData<>(new RegistrationData(this));

    private final MutableLiveData<Boolean> isImageVisible = new MutableLiveData<>(true);

    public LiveData<RegistrationData> getRegistrationData() {
        return registrationDate;
    }

    public LiveData<Boolean> getIsImageVisible(){
        return isImageVisible;
    }

    public void toggleImageVisibility(){
        Boolean currentState = isImageVisible.getValue();
        isImageVisible.setValue(currentState == null || !currentState);
    }

    public void setRegistrationDate(String name, String surname, String password) {
        RegistrationData data = registrationDate.getValue();
        data.setName(name);
        data.setSurname(surname);
        data.setPassword(password);
        registrationDate.setValue(data);
    }
}
