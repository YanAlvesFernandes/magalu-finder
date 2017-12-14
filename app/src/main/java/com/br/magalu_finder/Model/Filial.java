package com.br.magalu_finder.Model;

/**
 * Created by Yan Alves on 13/12/2017.
 * Criação do objeto Filial
 */

public class Filial {

    private Long id;
    private String desc_filial, cep, cidade, bairro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc_filial() {
        return desc_filial;
    }

    public void setDesc_filial(String desc_filial) {
        this.desc_filial = desc_filial;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
