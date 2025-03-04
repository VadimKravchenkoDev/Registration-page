package com.example.registrationpage;

import android.content.Context;

public class RegistrationValidator {
    public static boolean areFieldsValid(String name, String surname, String password) {
        return !(name.isEmpty() || surname.isEmpty() || password.isEmpty());
    }


    public static String getErrorMessage(MainActivity context, String name, String surname, String password) {
        if (name.isEmpty()||surname.isEmpty()||password.isEmpty()) {
            return context.getString(R.string.error_message);
        }
        return null;
    }
}
