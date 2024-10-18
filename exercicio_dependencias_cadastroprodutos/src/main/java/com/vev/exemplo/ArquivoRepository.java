package com.vev.exemplo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArquivoRepository implements ProdutoRepository {
    private static final String FNAME = "produtos.dat";

    @Override
    public List<Produto> carregaProdutos() throws IOException {
        List<Produto> produtos = new ArrayList<>();
        String currDir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = currDir + "/" + FNAME;
        Path path = Paths.get(nameComplete);

        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {
            while (sc.hasNext()) {
                String linha = sc.nextLine();
                String[] dados = linha.split(",");
                int codigo = Integer.parseInt(dados[0]);
                String descricao = dados[1];
                float preco = Float.parseFloat(dados[2]);
                String moeda = dados[3];
                Produto p = new Produto(codigo, descricao, preco, moeda);
                produtos.add(p);
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
            throw e;
        }
        return produtos;
    }
}
