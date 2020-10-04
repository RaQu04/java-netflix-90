package pl.yellowduck.netflix90.rentals;

import lombok.Getter;
import lombok.Setter;
import pl.yellowduck.netflix90.clients.Client;
import pl.yellowduck.netflix90.films.VideoCassette;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Getter
//@RequiredArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_client") // w bazie id_client
    private Client client;
    //client_id

    @ManyToOne
    @JoinColumn(name = "id_cassette")
    private VideoCassette cassette;

    private LocalDate rentDate;
    private int rentDays;
    private BigDecimal rentCost;

    @Setter
    private LocalDate returnDate;

    public Rental() {
    }

    private Rental(RentalBuilder builder) {
        this.client = builder.client;
        this.cassette = builder.videoCassette;
        this.rentDate = builder.rentDate;
        this.rentDays = builder.rentDays;
        this.rentCost = builder.rentCost;
    }

    public static RentalBuilder builder() {
        return new RentalBuilder();
    }

    public static final class RentalBuilder {
        private Client client;
        private VideoCassette videoCassette;
        private LocalDate rentDate;
        private int rentDays;
        private BigDecimal rentCost;
        private LocalDate returnDate;

        private RentalBuilder() {
        }


        public RentalBuilder withClientId(Client client) {
            this.client = client;
            return this;
        }

        public RentalBuilder withCassetteId(VideoCassette videoCassette) {
            this.videoCassette = videoCassette;
            return this;
        }

        public RentalBuilder withRentDate(LocalDate rentDate) {
            this.rentDate = rentDate;
            return this;
        }

        public RentalBuilder withRentDays(int rentDays) {
            this.rentDays = rentDays;
            return this;
        }

        public RentalBuilder withRentCost(BigDecimal rentCost) {
            this.rentCost = rentCost;
            return this;
        }

        public RentalBuilder withReturnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Rental build() {
            return new Rental(this);
        }
    }
}
