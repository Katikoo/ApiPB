package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        String api = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
        String date = getDate();
        String url = api + date;

        String result = HttpUtil.sendRequest(url, null, null);

        Gson gson = new GsonBuilder().create();

        ApiPB rate = gson.fromJson(result, ApiPB.class);
        if (rate.getExchangeRate().size() == 0) {
            System.out.println("Information on the exchange rate at that date was not found.");
        } else {
            for (int i = 0; i < rate.getExchangeRate().size(); i++) {
                if (rate.getExchangeRate().get(i).getCurrency().equals("USD")) {
                    System.out.print("The dollar exchange rate on " + rate.getDate());
                    System.out.print(" was " + rate.getExchangeRate().get(i).getSaleRateNB());

                }
            }
        }
    }

    private static String getDate() {
        System.out.println("Enter a date in format dd.MM.YYYY: ");
        String date = null;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                date = scanner.nextLine();
                if (!date.matches("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d")) {
                    System.out.println("Check the date format and try again: ");
                } else {
                    break;
                }
            }
        } catch (Exception ignored) {
        }
        return date;
    }
}