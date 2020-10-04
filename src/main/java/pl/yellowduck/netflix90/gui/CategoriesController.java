package pl.yellowduck.netflix90.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.yellowduck.netflix90.films.Category;
import pl.yellowduck.netflix90.films.CategoryHibernate;

import javax.persistence.TypedQuery;
import java.util.List;

public class CategoriesController {

    @FXML
    TableView<CategoryHibernate> categoriesTable;

    public void loadData(ActionEvent event) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(CategoryHibernate.class)
                .buildSessionFactory();
        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            TypedQuery<CategoryHibernate> query = session.createQuery("SELECT c from CategoryHibernate c", CategoryHibernate.class);
            categoriesTable.getItems().clear();
            List<CategoryHibernate> reultList = query.getResultList();
            categoriesTable.getItems().addAll(reultList);
            tx.commit();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill in all fielnds");
            alert.show();

        }
    }
}
