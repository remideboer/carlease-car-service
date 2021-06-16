package com.monolithical.carservice.api.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;

public class LeaseContractData {
  private int mileage;
  private Period duration;
  private BigDecimal interestRatePercent;
  private Car car;

  public LeaseContractData(Car car) {
    this.car = car;
  }

  public void setMileage(int mileage) {
    this.mileage = mileage;
  }

  public void setDuration(Period duration) {
    this.duration = duration;
  }

  /**
   * Calculates lease rate using formulae: ((( mileage / 12) * duration) // nett price ) + (((
   * interest rate / 100 ) * nett price) / 12) Using BigDecimals, but does not yield significant
   * inaccuracies The calculation is split up over a few methods for readability
   *
   * @return
   */
  public BigDecimal leaseRate() {
    // onderstaande berekening geeft een afwijkend antwoord met gegeven test waarden => 239.82
    var result = monthlyMileageNettPriceRatio().add(carInterestPerMonth());
    return result.setScale(2, RoundingMode.HALF_UP); // to deliver 2 decimal places
  }

  private BigDecimal monthlyMileage() {
    return BigDecimal.valueOf(mileage).divide(BigDecimal.valueOf(12), 5, RoundingMode.HALF_UP);
  }

  private BigDecimal monthlyMileageNettPriceRatio() {
    return mileageOverDuration().divide(carNettPriceEuro(), 5, RoundingMode.HALF_UP);
  }

  private BigDecimal mileageOverDuration() {
    return monthlyMileage().multiply(BigDecimal.valueOf(duration.getMonths()));
  }

  private BigDecimal carInterest() {
    return interestRatePercent.multiply(carNettPriceEuro());
  }

  private BigDecimal carInterestPerMonth() {
    return carInterest().divide(BigDecimal.valueOf(12), 5, RoundingMode.HALF_UP);
  }

  /**
   * Converts price from cents to euro
   *
   * @return
   */
  private BigDecimal carNettPriceEuro() {
    return BigDecimal.valueOf(car.getNettPrice()).scaleByPowerOfTen(-2);
  }

  /**
   * Converst supplied interest rate to percentage
   *
   * @param interestRate
   */
  public void setInterestRate(BigDecimal interestRate) {
    this.interestRatePercent = interestRate.scaleByPowerOfTen(-2);
  }
}
