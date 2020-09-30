package pl.yellowduck.netflix90.films;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import pl.yellowduck.netflix90.common.Gender;
import pl.yellowduck.netflix90.common.Person;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Table;


public class HibernateTest {
    SessionFactory sessionFactory;


    @Test
    public void shouldConnectToDb() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(CategoryHibernate.class)
                .buildSessionFactory();

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            System.out.println("Connect to database");

            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }

    }

    @Test
    public void saveCategory() {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(CategoryHibernate.class)
                .buildSessionFactory();

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            CategoryHibernate category = new CategoryHibernate();
            category.setName("Horror");
            category.setDescription("Scary movie");
            session.save(category);


            category = new CategoryHibernate();
            category.setName("Comedy");
            category.setDescription("Funny movie");
            session.save(category);

            category = new CategoryHibernate();
            category.setName("Action");
            category.setDescription("Film with a lot of guns and fast cars");
            session.save(category);

            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }

    }

    @Test
    public void shouldAddActors(){

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ActorHibernate.class)
                .buildSessionFactory();

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");

        Transaction tx = null;
        try(Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();

            ActorHibernate actor = new ActorHibernate();
            actor.setFirstname("Penelope");
            actor.setLastname("Cruse");
            actor.setGender(Gender.FEMALE);
            session.save(actor);


            tx.commit();
        }catch (HibernateException ex){
            if(tx != null){
                tx.rollback();
            }
        }

    }


}