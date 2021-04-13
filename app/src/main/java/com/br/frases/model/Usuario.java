package com.br.frases.model;

import com.br.frases.helpers.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Usuario {
    private String idUsuario;
    private String urlImagem;
    private String nome;
    private String email;
    private String telefone;
    private String senha;



    public Usuario() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public void salvar() {
        DatabaseReference firebaseREf = ConfiguracaoFirebase.getReferenciaFirebase();
        DatabaseReference usuarioRef = firebaseREf.child("usuarios").child(getIdUsuario());
        usuarioRef.setValue(this);
    }
}
