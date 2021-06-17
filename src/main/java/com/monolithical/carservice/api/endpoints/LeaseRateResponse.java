package com.monolithical.carservice.api.endpoints;

import com.monolithical.carservice.api.domain.LeaseContractData;

import java.math.BigDecimal;

public class LeaseRateResponse {
    private LeaseContractData contractData;
    private BigDecimal leaseRate;

    public LeaseRateResponse(LeaseContractData data) {
        this.leaseRate = data.leaseRate();
        this.contractData = data;
    }

    public LeaseContractData getContractData() {
        return contractData;
    }

    public BigDecimal getLeaseRate() {
        return leaseRate;
    }
}
