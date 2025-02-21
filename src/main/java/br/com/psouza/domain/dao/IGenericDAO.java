package main.java.br.com.psouza.domain.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO <T, ID extends Serializable> {
    public T cadastrar(T t);

    public void excluir(ID id);

    public T consultar(ID id);

    public void alterar(T entity);

    public List<T> buscarTodos();
}
