package pl.yellowduck.netflix90.rentals;

import lombok.Getter;
import lombok.Setter;
import pl.yellowduck.netflix90.clients.Client;
import pl.yellowduck.netflix90.films.VideoCassette;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rental")
@Getter
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_cassette")
    private VideoCassette cassette;

    private LocalDate rentDate;
    private int rentDays;
    private BigDecimal rentCost;

//    @Setter
    private LocalDate returnDate;

    public int getId() {
        return id;
    }

    public void setId(int identity) {
        this.id = identity;
    }

    public Client getClient() {
        return client;
    }

//    public void setClientId(Client clientId) {
//        this.clientId = clientId;
//    }

    public VideoCassette getCassette() {
        return cassette;
    }

//    public void setCassetteId(VideoCassette cassetteId) {
//        this.cassetteId = cassetteId;
//    }

    public LocalDate getRentDate() {
        return rentDate;
    }

//    public void setRentDate(LocalDate rentDate) {
//        this.rentDate = rentDate;
//    }

    public int getRentDays() {
        return rentDays;
    }

//    public void setRentDays(int rentDays) {
//        this.rentDays = rentDays;
//    }

    public BigDecimal getRentCost() {
        return rentCost;
    }

//    public void setRentCost(BigDecimal rentCost) {
//        this.rentCost = rentCost;
//    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    private Rental(RentalBuilder builder) {
        this.client = builder.clientId;
        this.cassette = builder.casseteId;
        this.rentDate = builder.rentDate;
        this.rentDays = builder.rentDays;
        this.rentCost = builder.rentCost;
        this.returnDate = builder.returnDate;
    }

    public Rental() {

    }

    public static RentalBuilder builder() {
        return new RentalBuilder();
    }

    public static final class RentalBuilder {
        private Client clientId;
        private VideoCassette casseteId;
        private LocalDate rentDate;
        private int rentDays;
        private BigDecimal rentCost;
        private LocalDate returnDate;

        private RentalBuilder() {
        }

        public RentalBuilder withClientId(Client clientId) {
            this.clientId = clientId;
            return this;
        }

        public RentalBuilder withCasseteId(VideoCassette casseteId) {
            this.casseteId = casseteId;
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
