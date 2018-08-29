package br.com.igorbagliotti.school.data.remote.model.student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import br.com.igorbagliotti.school.data.remote.model.auth.LoginResponse;

/**
 * Created by Igor Rotondo Bagliot on 27/08/2018.
 */

@Parcel(analyze = {Student.class})
public class Student {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("cpf")
    @Expose
    public String cpf;
    @SerializedName("nome")
    @Expose
    public String nome;
    @SerializedName("endereco")
    @Expose
    public String endereco;
    @SerializedName("estado")
    @Expose
    public String estado;
    @SerializedName("municipio")
    @Expose
    public String municipio;
    @SerializedName("telefone")
    @Expose
    public String telefone;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("senha")
    @Expose
    public String senha;
    @SerializedName("ativo")
    @Expose
    public Boolean ativo;
    @SerializedName("data_cadastro")
    @Expose
    public String dataCadastro;

    public Student() {

    }

    public Student(String cpf, String nome, String endereco, String estado, String municipio, String telefone, String email, String senha, Boolean ativo, String dataCadastro) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.estado = estado;
        this.municipio = municipio;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
    }

    public Student(String id, String cpf, String nome, String endereco, String estado, String municipio, String telefone, String email, String senha, Boolean ativo, String dataCadastro) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.estado = estado;
        this.municipio = municipio;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
    }

}
