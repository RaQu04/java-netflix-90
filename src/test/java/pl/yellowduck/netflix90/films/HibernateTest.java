package pl.yellowduck.netflix90.films;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import pl.yellowduck.netflix90.clients.Client;
import pl.yellowduck.netflix90.common.Gender;
import pl.yellowduck.netflix90.rentals.Rental;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.Assert.fail;


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

            actor = new ActorHibernate();
            actor.setFirstname("Jan");
            actor.setLastname("Kowalski");
            actor.setGender(Gender.MALE);
            session.save(actor);


            tx.commit();

        }catch (HibernateException ex){
            fail("Nie powinno dojść do wyjątku");
            if(tx != null){
                tx.rollback();
            }
        }

    }

    @Test
    public void saveTest(){
        EntityManager entityManager = Persistence.createEntityManagerFactory("NETFLIX")
                .createEntityManager();
        entityManager.getTransaction().begin();

        Actor jimCarry = new Actor("Jim", "Carry", Gender.MALE);
        entityManager.persist(jimCarry);

        Director director = new Director("Jim", "Carry", Gender.MALE);
        entityManager.persist(director);



        entityManager.getTransaction().commit();
    }

    @Test
    public void addClientToBase(){
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Client.class)
                .buildSessionFactory();

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");

        Transaction tx = null;
        try(Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();

            Client client = new Client();
            client.setFirstname("Tomasz");
            client.setLastname("Kijowski");
            client.setGender(Gender.MALE);
            session.save(client);

            client = new Client();
            client.setFirstname("Jacek");
            client.setLastname("Jackowski");
            client.setGender(Gender.MALE);
            session.save(client);


            tx.commit();

        }catch (HibernateException ex){
            fail("Nie powinno dojść do wyjątku");
            if(tx != null){
                tx.rollback();
            }
        }
    }
    @Test
    public void shouldAddCassetteTab(){

        Director director1 = new Director("Lukasz", "Nowak", Gender.MALE);
        CategoryHibernate categoryHibernate = new CategoryHibernate("Drama", "Dramat");

        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        Set<Actor> actorList = new HashSet<>();
        actorList.add(actor1);
        actorList.add(actor2);


        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(VideoCassette.class)
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Actor.class)
                .buildSessionFactory();

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");

        Transaction tx = null;
        try(Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            session.persist(director1);
            session.persist(actor1);
            session.persist(actor2);


            VideoCassette videoCassette = new VideoCassette();
            videoCassette.setTitle("Park Jurajski");
            videoCassette.setPrice(BigDecimal.valueOf(10));
            videoCassette.setActors(Set.of(actor1, actor2));
            videoCassette.setCategory(Category.ACTION);
            //videoCassette.setCategoryHibernate(categoryHibernate);
            videoCassette.setDirector(director1);


            session.persist(videoCassette);


            tx.commit();
        }catch (HibernateException ex){
            fail("Nie powinno dojść do wyjątku");
            if(tx != null){
                tx.rollback();
            }
        }
    }
    @Test
    public void shouldAddRental(){

        LocalDate date1 = LocalDate.now() ;
        LocalDate date2 = LocalDate.of(2020,01,01);
        int dateTime = (int) ChronoUnit.DAYS.between(date1, date2);


        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(VideoCassette.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Actor.class)
                .buildSessionFactory();

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");

        Transaction tx = null;
        try(Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();

            Client client = new Client();
            client.setFirstname("Tomasz");
            client.setLastname("Kijowski");
            client.setGender(Gender.MALE);
            session.save(client);
            Client CLIENT = new Client("Lukasz", "Rakowiecki", Gender.MALE);
            session.save(CLIENT);

            Actor JIM_CARRY = new Actor("Jim", "Carry", Gender.MALE);
            session.save(JIM_CARRY);
            Actor PENELOPE = new Actor("Penelope", "Cruse", Gender.FEMALE);
            session.save(PENELOPE);

            Director DIRECTOR = new Director("Jim", "Carry", Gender.MALE);
            session.save(DIRECTOR);
//            Set<Actor> ACTOR_LIST = new HashSet<>();

            VideoCassette cassette1 = new VideoCassette();
            cassette1.setTitle("Park Jurajski");
            cassette1.setPrice(BigDecimal.valueOf(10));
            cassette1.setActors(Set.of(JIM_CARRY, PENELOPE));
            cassette1.setCategory(Category.ACTION);
            cassette1.setDirector(DIRECTOR);
            session.persist(cassette1);


            Rental rental1 = Rental.builder()
                    .withClientId(client)
                    .withCasseteId(cassette1)
                    .withRentDate(date1)
                    .withRentCost(BigDecimal.valueOf(5))
                    .withRentDays(5)
                    .withReturnDate(date2)
                    .build();

            session.persist(rental1);
            tx.commit();
        }catch (HibernateException ex){
            fail("Nie powinno dojść do wyjątku");
            if(tx != null){
                tx.rollback();
            }
        }
    }


}