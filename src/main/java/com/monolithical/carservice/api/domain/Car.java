package com.monolithical.carservice.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Model to represent car data
 */
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String make;
    private String model;
    private String version;
    private int doorCount;
    private BigDecimal co2Emission;
    private Long grossPrice; // in cents
    private Long nettPrice; // in cents

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
}
