package pl.yellowduck.netflix90.clients;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pl.yellowduck.netflix90.common.Gender;
import pl.yellowduck.netflix90.common.Person;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "client")
public class Client extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonCreator
    public Client(@JsonProperty("firstname") String firstname,
                  @JsonProperty("lastname") String lastname,
                  @JsonProperty("gender") Gender gender) {
        super(firstname, lastname, gender);
    }

    public Client() {
    }

    @Override
    public void introduce() {

    }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }

    public static final class ClientBuilder {
        protected Gender gender;
        private String firstname;
        private String lastname;

        private ClientBuilder() {
        }

        public ClientBuilder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public ClientBuilder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public ClientBuilder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Client build() {
            return new Client(firstname, lastname, gender);
        }
    }
}
