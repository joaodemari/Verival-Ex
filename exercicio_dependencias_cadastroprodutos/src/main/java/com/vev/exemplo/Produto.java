package com.vev.exemplo;

public class Produto{
    private int codigo;
    private String descricao;
    private float precoUnitario;
    private String moeda;

    public Produto(int codigo,String descricao,float precoUnitario,String moeda){
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.moeda = moeda;
    }

    public int getCodigo(){
        return this.codigo;
    }

    public String getMoeda(){
        return moeda;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public float getPrecoUnitario(){
        return this.precoUnitario;
    }

    public void setPrecoUnitario(float preco){
        this.precoUnitario = preco;
    }

    @Override
    public String toString() {
        return "ProdutoDTO [codigo=" + codigo + ", descricao=" + descricao + ", moeda=" + moeda + ", precoUnitario="
                + precoUnitario + "]";
    }
}