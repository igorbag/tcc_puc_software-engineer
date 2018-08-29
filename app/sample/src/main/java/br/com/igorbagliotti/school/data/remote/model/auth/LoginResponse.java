package br.com.igorbagliotti.school.data.remote.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.parceler.Parcel;

@Entity(nameInDb = "loginResponse")
@Parcel(analyze = {LoginResponse.class})
public class LoginResponse {

    @Id(autoincrement = true)
    public Long pk;

    @SerializedName("_id")
    @Expose
    public String id;
    @Property(nameInDb = "cpf")
    @SerializedName("cpf")
    @Expose
    public String cpf;
    @Property(nameInDb = "nome")
    @SerializedName("nome")
    @Expose
    public String nome;
    @Property(nameInDb = "endereco")
    @SerializedName("endereco")
    @Expose
    public String endereco;
    @Property(nameInDb = "estado")
    @SerializedName("estado")
    @Expose
    public String estado;
    @Property(nameInDb = "municipio")
    @SerializedName("municipio")
    @Expose
    public String municipio;
    @Property(nameInDb = "telefone")
    @SerializedName("telefone")
    @Expose
    public String telefone;
    @Property(nameInDb = "email")
    @SerializedName("email")
    @Expose
    public String email;
    @Property(nameInDb = "senha")
    @SerializedName("senha")
    @Expose
    public String senha;
    @Property(nameInDb = "ativo")
    @SerializedName("ativo")
    @Expose
    public Boolean ativo;
    @Property(nameInDb = "data_cadastro")
    @SerializedName("data_cadastro")
    @Expose
    public String dataCadastro;

    @Generated(hash = 1783640055)
    public LoginResponse(Long pk, String id, String cpf, String nome,
                         String endereco, String estado, String municipio, String telefone,
                         String email, String senha, Boolean ativo, String dataCadastro) {
        this.pk = pk;
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

    @Generated(hash = 1521785954)
    public LoginResponse() {
    }

    public Long getPk() {
        return this.pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
