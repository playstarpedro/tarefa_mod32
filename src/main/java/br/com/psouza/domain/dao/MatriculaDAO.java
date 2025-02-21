package main.java.br.com.psouza.domain.dao;

import main.java.br.com.psouza.domain.Matricula;

public class MatriculaDAO extends GenericDAO<Matricula, Long> {
    public MatriculaDAO() {
        super(Matricula.class);
    }
}
