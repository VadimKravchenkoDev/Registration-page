package com.example.registrationpage;

public class RegistrationValidator {
    public static boolean areFieldsValid(String name, String surname, String password) {
        return !(name.isEmpty() || surname.isEmpty() || password.isEmpty());
    }

    public static String getErrorMessage(String name, String surname, String password) {
        if (name.isEmpty()) {
            return "The name is not filled";
        } else if (surname.isEmpty()) {
            return "The surname is not filled";
        } else if (password.isEmpty()) {
            return "The password is not filled";
        }
        return null;
    }
}
