package main.java.br.com.psouza.domain.dao;

import main.java.br.com.psouza.domain.Curso;

public class CursoDAO extends GenericDAO<Curso, Long> {
    public CursoDAO() {
        super(Curso.class);
    }
}
