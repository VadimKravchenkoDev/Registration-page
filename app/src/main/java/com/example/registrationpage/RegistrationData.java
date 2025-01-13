package com.example.registrationpage;

public class RegistrationData {
    private final RegistrationViewModel viewModel;
    private String name;
    private String surname;
    private String password;
    private boolean isOver18;
    private boolean isPasswordVisible = false;


    public RegistrationData(RegistrationViewModel viewModel){
        this.viewModel = viewModel;
    }

    public void onImageClick(){
        viewModel.toggleImageVisibility();
    }
    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOver18() {
        return isOver18;
    }

    public void setOver18(boolean over18) {
        isOver18 = over18;
    }

    public boolean isPasswordVisible() {
        if(isPasswordVisible){
            isPasswordVisible = false;
        } else {
            isPasswordVisible = true;
        }
        return isPasswordVisible;
    }

    public void setPasswordVisible(boolean passwordVisible) {
        isPasswordVisible = passwordVisible;
    }
}
