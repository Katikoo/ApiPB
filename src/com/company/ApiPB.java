package com.company;

import java.util.List;

class ApiPB {

    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;
    private List<Currency> exchangeRate;


    public List<Currency> getExchangeRate() {
        return exchangeRate;
    }

    public String getDate() {
        return date;
    }
}