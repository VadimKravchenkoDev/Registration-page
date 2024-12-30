package com.example.registrationpage;

public class RegistrationData {
    private String name;
    private String surname;
    private String password;
    private boolean isOver18;
    private boolean isPasswordVisible;

    //Getters and Setters
    public String getName(){
        return name;
    }

    public void setName(String name){
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }


}
