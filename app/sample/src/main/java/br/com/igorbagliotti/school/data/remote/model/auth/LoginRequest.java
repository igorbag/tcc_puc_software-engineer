package br.com.igorbagliotti.school.data.remote.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(analyze = {LoginRequest.class})
public class LoginRequest {

    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("senha")
    @Expose
    public String senha;

    public LoginRequest makeAuth(String email, String senha) {
        this.email = email;
        this.senha = senha;
        return this;
    }




}