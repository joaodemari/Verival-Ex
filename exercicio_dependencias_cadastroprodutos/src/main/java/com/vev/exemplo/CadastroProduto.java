package com.vev.exemplo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CadastroProduto {
    private List<Produto> produtos;

    public CadastroProduto(ProdutoRepository repository) throws IOException {
        this.produtos = repository.carregaProdutos();
    }

    public Produto recuperaPorCodigo(int codigo) {
        return produtos.stream().filter(prod -> prod.getCodigo() == codigo).findAny().orElse(null);
    }

    public List<Produto> todos() {
        return new ArrayList<>(produtos);
    }

    public List<Produto> produtosVendidosEm(String moeda) {
        return produtos.stream().filter(prod -> prod.getMoeda().equals(moeda)).collect(Collectors.toList());
    }

    public List<Produto> produtosMaisBaratosQue(float valor) {
        return produtos.stream().filter(prod -> prod.getPrecoUnitario() < valor).collect(Collectors.toList());
    }
}