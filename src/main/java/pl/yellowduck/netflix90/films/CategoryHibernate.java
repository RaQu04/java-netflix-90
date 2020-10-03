package pl.yellowduck.netflix90.films;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryHibernate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    public CategoryHibernate() {
    }

    public CategoryHibernate(String name, String description) {
        this.name = name;
        this.description = description;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
