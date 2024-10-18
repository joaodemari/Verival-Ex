package com.vev.exemplo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CadastroProdutoTest {

    private ProdutoRepository mockRepository;
    private CadastroProduto cadastroProduto;

    @BeforeEach
    public void setUp() throws IOException {
        mockRepository = mock(ProdutoRepository.class);

        List<Produto> produtosMockados = Arrays.asList(
            new Produto(1, "Produto A", 10.0f, "BRL"),
            new Produto(2, "Produto B", 20.0f, "USD"),
            new Produto(3, "Produto C", 5.0f, "BRL")
        );

        when(mockRepository.carregaProdutos()).thenReturn(produtosMockados);

        cadastroProduto = new CadastroProduto(mockRepository);
    }

    @Test
    public void testRecuperaPorCodigo() {
        Produto p = cadastroProduto.recuperaPorCodigo(1);
        assertNotNull(p);
        assertEquals("Produto A", p.getDescricao());
    }

    @Test
    public void testProdutosVendidosEm() {
        List<Produto> produtosBRL = cadastroProduto.produtosVendidosEm("BRL");
        assertEquals(2, produtosBRL.size());
    }

    @Test
    public void testProdutosMaisBaratosQue() {
        List<Produto> produtosBaratos = cadastroProduto.produtosMaisBaratosQue(15.0f);
        assertEquals(2, produtosBaratos.size());
    }

    @Test
    public void testTodosProdutos() {
        List<Produto> todos = cadastroProduto.todos();
        assertEquals(3, todos.size());
    }
}