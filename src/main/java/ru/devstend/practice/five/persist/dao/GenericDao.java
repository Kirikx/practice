package ru.devstend.practice.five.persist.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.devstend.practice.five.persist.entity.Student;

public class GenericDao<E, T extends Serializable> {

  private final Class<E> type;

  private Session currentSession;

  private Transaction currentTransaction;

  public GenericDao(Class<E> type) {
    this.type = type;
  }

  public Session openCurrentSession() {
    currentSession = getSessionFactory(type).openSession();
    return currentSession;
  }

  public Session openCurrentSessionWithTransaction() {
    currentSession = getSessionFactory(type).openSession();
    currentTransaction = currentSession.beginTransaction();
    return currentSession;
  }

  public void closeCurrentSession() {
    currentSession.close();
  }

  public void closeCurrentSessionWithTransaction() {
    currentTransaction.commit();
    currentSession.close();
  }

  private static SessionFactory getSessionFactory(Class type) {
    Configuration configuration = new Configuration().configure().addAnnotatedClass(type);
    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
        .applySettings(configuration.getProperties());
    return configuration.buildSessionFactory(builder.build());
  }

  public Session getCurrentSession() {
    return currentSession;
  }

  public void setCurrentSession(Session currentSession) {
    this.currentSession = currentSession;
  }

  public Transaction getCurrentTransaction() {
    return currentTransaction;
  }

  public void setCurrentTransaction(Transaction currentTransaction) {
    this.currentTransaction = currentTransaction;
  }

  public UUID persist(E entity) {
    return (UUID) getCurrentSession().save(entity);
  }

  public void update(E entity) {
    getCurrentSession().update(entity);
  }

  public Student findById(T id) {
    return (Student) getCurrentSession().get(type, id);
  }

  public void delete(E entity) {
    getCurrentSession().delete(entity);
  }

  @SuppressWarnings("unchecked")
  public List<E> findAll() {
    return (List<E>) getCurrentSession().createQuery("from " + type.getSimpleName()).list();
  }

  public void deleteAll() {
    List<E> entityList = findAll();
    for (E entity : entityList) {
      delete(entity);
    }
  }

}
