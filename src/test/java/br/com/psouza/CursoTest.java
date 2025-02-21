package test.java.br.com.psouza;

import main.java.br.com.psouza.domain.Curso;
import main.java.br.com.psouza.domain.dao.CursoDAO;
import main.java.br.com.psouza.domain.dao.GenericDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.List;

public class CursoTest {
    private GenericDAO cursoDAO;

    public CursoTest() {
        cursoDAO = new CursoDAO();
    }

    @Test
    public void cadastrar() {
        Curso curso = criarCurso();
        Curso cursoDB = (Curso) cursoDAO.cadastrar(curso);

        Assert.assertNotNull(cursoDB);
        Assert.assertEquals(cursoDB, curso);

        cursoDAO.excluir(curso.getId());
    }

    @Test
    public void excluir() {
        Curso curso = criarCurso();
        cursoDAO.cadastrar(curso);

        cursoDAO.excluir(curso.getId());
        Object matDB = cursoDAO.consultar(curso.getId());

        Assert.assertNull(matDB);
    }

    @Test
    public void consultrar() {
        Curso mat = criarCurso();
        cursoDAO.cadastrar(mat);

        Curso matDB = (Curso) cursoDAO.consultar(mat.getId());

        Assert.assertNotNull(matDB);
        Assert.assertEquals(matDB.getCodigo(), mat.getCodigo());

        cursoDAO.excluir(mat.getId());
    }

    @Test
    public void alterar() {
        Curso curso = criarCurso();
        cursoDAO.cadastrar(curso);

        curso.setCodigo("A22");
        curso.setDescricao("CURSO ALTERADO");
        curso.setNome("Curso de Frontend");

        cursoDAO.alterar(curso);
        Curso matDB = (Curso) cursoDAO.consultar(curso.getId());

        Assert.assertNotNull(matDB);
        Assert.assertEquals(matDB.getCodigo(), "A22");


        cursoDAO.excluir(curso.getId());
    }

    @Test
    public void buscarTodos() {
        Curso curso = criarCurso();
        cursoDAO.cadastrar(curso);

        Curso curso2 = new Curso();
        curso2.setCodigo("A2");
        curso2.setDescricao("CURSO TEST 2");
        curso2.setNome("Curso de Python Backkend");
        cursoDAO.cadastrar(curso2);

        List<Curso> cursos = cursoDAO.buscarTodos();

        Assert.assertNotNull(cursos);
        Assert.assertEquals(curso.getCodigo(), cursos.get(0).getCodigo());
        Assert.assertEquals(curso2.getCodigo(), cursos.get(1).getCodigo());


        cursoDAO.excluir(curso.getId());
        cursoDAO.excluir(curso2.getId());
    }

    public Curso criarCurso() {
        Curso curso = new Curso();
        curso.setCodigo("A1");
        curso.setDescricao("CURSO TEST");
        curso.setNome("Curso de Java Backkend");
        return curso;
    }
}
