package lk.udarabattery.newproj.dao;

import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class CrudDAOImpl<T,ID> implements CrudDAO<T,ID>{

    protected Session session;
    private Class<T> entity;

    public CrudDAOImpl(){
        entity = (Class<T>)(((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void save(T entity) throws Exception {
        session.persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        session.persist(entity);
    }

    @Override
    public void delete(ID id) throws Exception {
        T t = session.find(entity, id);
        session.remove(t);
    }

    @Override
    public List<T> loadAll() throws Exception {
        return session.createQuery("FROM "+ entity.getName()).list();
    }

    @Override
    public T findID(ID id) throws Exception {
        return session.find(entity, id);
    }
}
