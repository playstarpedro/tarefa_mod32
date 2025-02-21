package test.java.br.com.psouza;

import main.java.br.com.psouza.domain.Produto;
import main.java.br.com.psouza.domain.dao.ProdutoDAO;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.List;

public class ProdutoTest {
    private ProdutoDAO produtoDAO;

    public ProdutoTest() {
        produtoDAO = new ProdutoDAO();
    }

    @Test
    public void cadastrar() {
        Produto produto = criarProduto();
        Produto produtoDB = (Produto) produtoDAO.cadastrar(produto);

        Assert.assertNotNull(produtoDB);
        Assert.assertEquals(produtoDB.getCodigo(), produto.getCodigo());

        produtoDAO.excluir(produto.getId());
    }

    @Test
    public void excluir() {
        Produto produto = criarProduto();
        produtoDAO.cadastrar(produto);

        produtoDAO.excluir(produto.getId());
        Object produtoDB = produtoDAO.consultar(produto.getId());

        Assert.assertNull(produtoDB);
    }

    @Test
    public void consultrar() {
        Produto produto = criarProduto();
        produtoDAO.cadastrar(produto);

        Produto produtoDB = (Produto) produtoDAO.consultar(produto.getId());

        Assert.assertNotNull(produtoDB);
        Assert.assertEquals(produtoDB.getCodigo(), produto.getCodigo());


        produtoDAO.excluir(produto.getId());
    }

    @Test
    public void alterar() {
        Produto produto = criarProduto();
        produtoDAO.cadastrar(produto);

        produto.setCodigo("B420");
        produto.setName("Produto test alterado");
        produto.setPreco(35.50);

        produtoDAO.alterar(produto);
        Produto produtoDB = (Produto) produtoDAO.consultar(produto.getId());

        Assert.assertNotNull(produtoDB);
        Assert.assertEquals(produtoDB.getCodigo(), "B420");

        produtoDAO.excluir(produto.getId());
    }

    @Test
    public void buscarTodos() {
        Produto produto = criarProduto();
        produtoDAO.cadastrar(produto);

        Produto produto2 = new Produto();
        produto2.setCodigo("M234");
        produto2.setName("Produto test 2");
        produto2.setPreco(22.50);
        produtoDAO.cadastrar(produto2);

        List<Produto> matriculasDB = produtoDAO.buscarTodos();

        Assert.assertNotNull(matriculasDB);
        Assert.assertEquals(produto.getCodigo(), matriculasDB.get(0).getCodigo());
        Assert.assertEquals(produto2.getCodigo(), matriculasDB.get(1).getCodigo());


        produtoDAO.excluir(produto.getId());
        produtoDAO.excluir(produto2.getId());
    }

    public Produto criarProduto() {
        Produto produto = new Produto();
        produto.setCodigo("C42");
        produto.setName("Produto test 1");
        produto.setPreco(25.99);
        return produto;
    }
}
