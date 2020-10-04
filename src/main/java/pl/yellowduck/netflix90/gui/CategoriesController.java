package pl.yellowduck.netflix90.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import pl.yellowduck.netflix90.films.Category;
import pl.yellowduck.netflix90.films.CategoryHibernate;

public class CategoriesController {

    @FXML
    TableView<CategoryHibernate> categoriesTable;

    public void loadData(ActionEvent event) {
        categoriesTable.getItems()
                .add(new CategoryHibernate("TEST", "TEST DESCRPTION"));
        categoriesTable.refresh();
    }
}
