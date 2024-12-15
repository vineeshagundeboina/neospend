package com.neomaxer.neospend.pojos;

import java.math.BigDecimal;

public class ChartData {

    private String label;

    private BigDecimal value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public ChartData() {
        super();
    }

    public ChartData(String label, BigDecimal value) {
        super();
        this.label = label;
        this.value = value;
    }

}