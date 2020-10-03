package pl.yellowduck.netflix90.rentals;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rental")
@Getter
//@RequiredArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final int identity;

    private final String clientId;
    private final String cassetteId;
    private final LocalDate rentDate;
    private final int rentDays;
    private final BigDecimal rentCost;
    private LocalDate returnDate;

    private Rental(RentalBuilder builder) {
        this.identity = builder.identity;
        this.clientId = builder.clientId;
        this.cassetteId = builder.casseteId;
        this.rentDate = builder.rentDate;
        this.rentDays = builder.rentDays;
        this.rentCost = builder.rentCost;
    }

    public static RentalBuilder builder() {
        return new RentalBuilder();
    }

    public static final class RentalBuilder {
        private int identity;
        private String clientId;
        private String casseteId;
        private LocalDate rentDate;
        private int rentDays;
        private BigDecimal rentCost;
        private LocalDate returnDate;

        private RentalBuilder() {
        }

        public RentalBuilder withIdentity(int identity) {
            this.identity = identity;
            return this;
        }

        public RentalBuilder withClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public RentalBuilder withCasseteId(String casseteId) {
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
