package test.java.br.com.psouza;

import main.java.br.com.psouza.domain.Matricula;
import main.java.br.com.psouza.domain.dao.GenericDAO;
import main.java.br.com.psouza.domain.dao.MatriculaDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.List;

public class MatriculaTest {
    private GenericDAO matriculaDAO;

    public MatriculaTest() {
        matriculaDAO = new MatriculaDAO();
    }

    @Test
    public void cadastrar() {
        Matricula mat = criarMatricula();
        Matricula matriculaDB = (Matricula) matriculaDAO.cadastrar(mat);

        Assert.assertNotNull(matriculaDB);
        Assert.assertEquals(matriculaDB.getCodigo(), mat.getCodigo());

        matriculaDAO.excluir(mat.getId());
    }

    @Test
    public void excluir() {
        Matricula mat = criarMatricula();
        matriculaDAO.cadastrar(mat);

        matriculaDAO.excluir(mat.getId());
        Object matDB = matriculaDAO.consultar(mat.getId());

        Assert.assertNull(matDB);
    }

    @Test
    public void consultrar() {
        Matricula mat = criarMatricula();
        matriculaDAO.cadastrar(mat);

        Matricula matDB = (Matricula) matriculaDAO.consultar(mat.getId());

        Assert.assertNotNull(matDB);
        Assert.assertEquals(matDB.getCodigo(), mat.getCodigo());


        matriculaDAO.excluir(mat.getId());
    }

    @Test
    public void alterar() {
        Matricula mat = criarMatricula();
        matriculaDAO.cadastrar(mat);

        mat.setCodigo("A20");
        mat.setStatus("INATIVA");
        mat.setValor(1_000d);

        matriculaDAO.alterar(mat);
        Matricula matDB = (Matricula) matriculaDAO.consultar(mat.getId());

        Assert.assertNotNull(matDB);
        Assert.assertEquals(matDB.getCodigo(), "A20");


        matriculaDAO.excluir(mat.getId());
    }

    @Test
    public void buscarTodos() {
        Matricula mat = criarMatricula();
        matriculaDAO.cadastrar(mat);

        Matricula mat2 = new Matricula();
        mat2.setCodigo("A24");
        mat2.setDataMatricula(Instant.now());
        mat2.setStatus("ATIVA");
        mat2.setValor(2_000d);
        matriculaDAO.cadastrar(mat2);

        List<Matricula> matriculasDB = matriculaDAO.buscarTodos();

        Assert.assertNotNull(matriculasDB);
        Assert.assertEquals(mat.getCodigo(), matriculasDB.get(0).getCodigo());
        Assert.assertEquals(mat2.getCodigo(), matriculasDB.get(1).getCodigo());


        matriculaDAO.excluir(mat.getId());
        matriculaDAO.excluir(mat2.getId());
    }

    public Matricula criarMatricula() {
        Matricula mat = new Matricula();
        mat.setCodigo("A22");
        mat.setDataMatricula(Instant.now());
        mat.setStatus("ATIVA");
        mat.setValor(2_000d);
        return mat;
    }
}
