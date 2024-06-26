package com.engsoft2.cache_service;

import java.math.BigDecimal;
import java.util.Date;

public class HistoryDTO {
    private Long codAss;
    private BigDecimal valorPago;
    private Date data;

    public HistoryDTO(Long codAss, BigDecimal valorPago, Date data) {
        this.codAss = codAss;
        this.valorPago = valorPago;
        this.data = data;
    }

    public HistoryDTO() {
    }

    public Long getCodAss() {
        return codAss;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public Date getData() {
        return this.data;
    }

    public void setCodAss(Long codAss) {
        this.codAss = codAss;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HistoryDTO [codAss=" + codAss + ", valorPago=" + valorPago + "]";
    }
}
