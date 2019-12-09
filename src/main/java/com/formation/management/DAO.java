package com.formation.management;

import java.util.List;

public interface DAO<T> {

    public void add(T t);

    public void remove(T t);

    public T showbyId(int id);

    public List<T> showAll();

    public void edit(T t);

}
