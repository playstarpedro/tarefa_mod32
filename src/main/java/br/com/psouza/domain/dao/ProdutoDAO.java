package main.java.br.com.psouza.domain.dao;

import main.java.br.com.psouza.domain.Produto;

public class ProdutoDAO extends GenericDAO<Produto, Long> {
    public ProdutoDAO() {
        super(Produto.class);
    }
}
