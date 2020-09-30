package pl.yellowduck.netflix90.films;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pl.yellowduck.netflix90.common.Gender;
import pl.yellowduck.netflix90.common.Person;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "actors")
public class ActorHibernate {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int id;


    private String firstname;
    private String lastname;

    @Enumerated(EnumType.STRING)
    protected Gender gender;

    public ActorHibernate() {
    }

    @JsonCreator
    public ActorHibernate(@JsonProperty("firstname") String firstname,
                          @JsonProperty("lastname") String lastname,
                          @JsonProperty("gender") Gender gender) {

    }

    public void introduce() {
        // like this
//    if(Gender.MALE.equals(gender)) {
//      System.out.println("My name is " + firstname + " " + lastname + ". I am actor.");
//    } else {
//      System.out.println("My name is " + firstname + " " + lastname + ". I am actress.");
//    }


    }
}