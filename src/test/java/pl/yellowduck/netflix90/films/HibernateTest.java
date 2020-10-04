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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.fail;


public class HibernateTest {
    public static final Client CLIENT = new Client("Lukasz", "Rakowiecki", Gender.MALE);
    SessionFactory sessionFactory;
    public static final Actor JIM_CARRY = new Actor("Jim", "Carry", Gender.MALE);
    public static final Actor PENELOPE = new Actor("Penelope", "Cruse", Gender.FEMALE);
    //    public static final EntityManager ENTITY_MANAGER = Persistence.createEntityManagerFactory("NETFLIX")
//            .createEntityManager();
    public static final Director DIRECTOR = new Director("Jim", "Carry", Gender.MALE);
    public static final Set<Actor> ACTOR_LIST = new HashSet<>();
    public static final VideoCassette VIDEO_CASSETTE = new VideoCassette();


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
    public void shouldAddActors() {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ActorHibernate.class)
                .buildSessionFactory();

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.save(PENELOPE);

            tx.commit();

        } catch (HibernateException ex) {
            fail("Nie powinno dojść do wyjątku");
            if (tx != null) {
                tx.rollback();
            }
        }

    }

//    @Test
//    public void saveTest() {
//        ENTITY_MANAGER.getTransaction().begin();
//
//        ENTITY_MANAGER.persist(JIM_CARRY);
//
//        ENTITY_MANAGER.persist(DIRECTOR);
//
//
//        ENTITY_MANAGER.getTransaction().commit();
//    }

    @Test
    public void addClientToBase() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Client.class)
                .buildSessionFactory();

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
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

        } catch (HibernateException ex) {
            fail("Nie powinno dojść do wyjątku");
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Test
    public void shouldAddCassetteTab() {


        ACTOR_LIST.add(JIM_CARRY);
        ACTOR_LIST.add(PENELOPE);


        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(VideoCassette.class)
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Actor.class)
                .buildSessionFactory();

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(DIRECTOR);
            session.persist(JIM_CARRY);
            session.persist(PENELOPE);


            VIDEO_CASSETTE.setTitle("Park Jurajski");
            VIDEO_CASSETTE.setPrice(BigDecimal.valueOf(10));
            VIDEO_CASSETTE.setActors(Set.of(JIM_CARRY, PENELOPE));
            VIDEO_CASSETTE.setCategory(Category.ACTION);
            //videoCassette.setCategoryHibernate(categoryHibernate);
            VIDEO_CASSETTE.setDirector(DIRECTOR);


            session.persist(VIDEO_CASSETTE);


            tx.commit();
        } catch (HibernateException ex) {
            fail("Nie powinno dojść do wyjątku");
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Test
    public void shouldCreateRentalBase() {

        ACTOR_LIST.add(JIM_CARRY);
        ACTOR_LIST.add(PENELOPE);
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
        try (Session session = sessionFactory.openSession()) {

            tx = session.beginTransaction();
            session.persist(CLIENT);
            session.persist(JIM_CARRY);
            session.persist(PENELOPE);
            session.persist(DIRECTOR);

            VIDEO_CASSETTE.setTitle("Park Jurajski");
            VIDEO_CASSETTE.setPrice(BigDecimal.valueOf(10));
            VIDEO_CASSETTE.setActors(Set.of(JIM_CARRY, PENELOPE));
            VIDEO_CASSETTE.setCategory(Category.ACTION);
            //videoCassette.setCategoryHibernate(categoryHibernate);
            VIDEO_CASSETTE.setDirector(DIRECTOR);

            session.persist(VIDEO_CASSETTE);


            Rental rental = Rental.builder()
                    .withClientId(CLIENT)
                    .withRentDate(LocalDate.now())
                    .withCassetteId(VIDEO_CASSETTE)
                    .withRentDays(5)
                    .withRentCost(BigDecimal.valueOf(10))
                    .build();

            session.persist(rental);


            tx.commit();

        } catch (HibernateException ex) {
            fail("Nie powinno dojść do wyjątku");
            if (tx != null) {
                tx.rollback();
            }
        }
    }


}