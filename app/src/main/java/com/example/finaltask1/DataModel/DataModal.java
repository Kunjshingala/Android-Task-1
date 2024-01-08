package com.example.finaltask1.DataModel;

public class DataModal {

    public String email;

    public String name;
    public String contact;
    public String gender;
    public String city;
    public String country;
    public String language;
    public String secondaryEmail;
    public String favoriteCity;

    public DataModal(String email, String name, String contact, String gender, String city, String country, String language, String secondaryEmail, String favoriteCity) {
        this.email = email;
        this.name = name;
        this.contact = contact;
        this.gender = gender;
        this.city = city;
        this.country = country;
        this.language = language;
        this.secondaryEmail = secondaryEmail;
        this.favoriteCity = favoriteCity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getFavoriteCity() {
        return favoriteCity;
    }

    public void setFavoriteCity(String favoriteCity) {
        this.favoriteCity = favoriteCity;
    }
}
