package pl.yellowduck.netflix90.films;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class MainTestowy {
    public static void main(String[] args) {


        SessionFactory sessionFactory = new Configuration()
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
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }


    }
}

