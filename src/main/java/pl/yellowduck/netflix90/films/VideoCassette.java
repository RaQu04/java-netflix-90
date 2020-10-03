package pl.yellowduck.netflix90.films;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Getter
public class VideoCassette {

  private String id;
  private BigDecimal price;
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  private Director director;

  @ManyToOne(fetch = FetchType.LAZY)
  private Category category;

  @ManyToMany(fetch = FetchType.LAZY)
  private Set<Actor> actors;

  @JsonCreator
  public VideoCassette(@JsonProperty("id") String id,
                       @JsonProperty("price") BigDecimal price,
                       @JsonProperty("title") String title,
                       @JsonProperty("director") Director director,
                       @JsonProperty("category") Category category,
                       @JsonProperty("actors") Set<Actor> actors) {
    this.id = id;
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
        .append(this.id)
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
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
