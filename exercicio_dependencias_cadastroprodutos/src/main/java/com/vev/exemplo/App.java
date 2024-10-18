package com.vev.exemplo;

import java.io.IOException;

public class App {
    public static void main(String args[]) throws IOException{
        ProdutoRepository repository = new ArquivoRepository();
        CadastroProduto cadastroProduto = new CadastroProduto(repository);
        System.out.println("Todos os produtos:");
        cadastroProduto.todos().forEach(System.out::println);
        System.out.println("Produtos vendidos em BRL:");
        cadastroProduto.produtosVendidosEm("BRL").forEach(System.out::println);
        System.out.println("Produtos mais baratos que 15.0:");
        cadastroProduto.produtosMaisBaratosQue(15.0f).forEach(System.out::println);
    }
}
