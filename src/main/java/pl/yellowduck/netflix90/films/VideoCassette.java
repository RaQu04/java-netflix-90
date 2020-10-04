package pl.yellowduck.netflix90.films;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "cassette")
public class VideoCassette {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Transient
    private String idCassette;

 //   @Column(name = "price", scale = 5, precision = 2)
    private BigDecimal price;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_d")
    private Director director;

    @Transient
    @ManyToOne
    private CategoryHibernate categoryHibernate;


    //@ManyToOne(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Actor> actors;

    public VideoCassette() {
    }

    @JsonCreator
    public VideoCassette(@JsonProperty("id") String id,
                         @JsonProperty("price") BigDecimal price,
                         @JsonProperty("title") String title,
                         @JsonProperty("director") Director director,
                         @JsonProperty("category") Category category,
                         @JsonProperty("actors") Set<Actor> actors) {
        this.idCassette = id;
        this.price = price;
        this.title = title;
        this.director = director;
        this.category = category;
        this.actors = actors;
    }

    public void printOut() {
        // print casette data with below pattern
        //    | ID | Title | Category | Price | Director | Actors |
        StringBuilder builder = new StringBuilder();
        builder.append("|")
                .append(this.idCassette)
                .append("|")
                .append(this.title)
                .append("|")
                .append(this.category)
                .append("|")
                .append(this.price)
                .append("|")
                .append(this.director.toString())
                .append("|")
                .append(this.actors)
                .append("|");
        System.out.println(builder.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoCassette that = (VideoCassette) o;
        return Objects.equals(idCassette, that.idCassette);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCassette);
    }
}
