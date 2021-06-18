package com.monolithical.carservice.api.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Model to represent car data
 */
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String version;
    private int doorCount;
    private BigDecimal co2Emission;
    private Long grossPrice;
    private Long nettPrice;

    public Car(Builder builder) {
        this.id = builder.id;
        this.make = builder.make;
        this.model = builder.model;
        this.version = builder.version;
        this.doorCount = builder.doorCount;
        this.co2Emission = builder.co2Emission;
        this.grossPrice = builder.grossPrice;
        this.nettPrice = builder.nettPrice;
    }

    /**
     * Used by JPA/Hibernate and Spring JSON marshalling
     */
    public Car() {

    }

    public static class Builder {
        private Long id;
        private String make;
        private String model;
        private String version;
        private int doorCount;
        private BigDecimal co2Emission;
        private Long grossPrice; // in cents
        private Long nettPrice; // in cents

        public Car build() {
            return new Car(this);
        }

        public Builder make(String make) {
            this.make = make;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder doorCount(int count) {
            this.doorCount = count;
            return this;
        }

        public Builder co2Emission(BigDecimal emission) {
            this.co2Emission = emission;
            return this;
        }

        public Builder grossPriceInCents(Long price) {
            this.grossPrice = price;
            return this;
        }

        public Builder nettPriceInCents(Long price) {
            this.nettPrice = price;
            return this;
        }
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVersion() {
        return version;
    }

    public int getDoorCount() {
        return doorCount;
    }

    public BigDecimal getCo2Emission() {
        return co2Emission;
    }

    public Long getGrossPrice() {
        return grossPrice;
    }

    public Long getNettPrice() {
        return nettPrice;
    }

    /**
     * convenience function to encapsulate self updates
     * does not update id
     *
     * @param car this car
     */
    public Car update(Car car) {
        this.make = car.make;
        this.model = car.model;
        this.version = car.version;
        this.doorCount = car.doorCount;
        this.co2Emission = car.co2Emission;
        this.grossPrice = car.grossPrice;
        this.nettPrice = car.nettPrice;
        return this;
    }
}
