package com.vev.exemplo;

import java.io.IOException;
import java.util.List;

public interface ProdutoRepository {
    List<Produto> carregaProdutos() throws IOException;
}
