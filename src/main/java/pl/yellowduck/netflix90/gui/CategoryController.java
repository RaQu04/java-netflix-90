package pl.yellowduck.netflix90.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.yellowduck.netflix90.films.CategoryHibernate;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class CategoryController {


    @FXML
    TextField nameinput;

    @FXML
    TextField descriptioninput;

    public void save(ActionEvent actionEvent) {
        String name = nameinput.getText();
        String description = descriptioninput.getText();
        if (StringUtils.isNoneBlank(name) && StringUtils.isNoneBlank(description)){
            SessionFactory sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(CategoryHibernate.class)
                    .buildSessionFactory();
            System.out.println("\n\n--------------------->\n" +
                    "Hibernate Session Factory Created");
            Transaction tx = null;
            try (Session session = sessionFactory.openSession()) {
                tx = session.beginTransaction();
                CategoryHibernate category = new CategoryHibernate(name, description);
                session.save(category);
                tx.commit();
            } catch (HibernateException ex) {
                if (tx != null && !tx.getRollbackOnly()) {
                    tx.rollback();
                }
                throw ex;
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill in all fielnds");
            alert.show();
        }



    }
}
