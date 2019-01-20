package zach.stocks;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SimStock {

    public String symbol;
    public double price1;
    public double price2;
    public double profit;
    private double stockamount;
    public double simulated;
    public String company;

    public SimStock(Stock stock, Stock stock2) {
        try {
            NumberFormat formatter = new DecimalFormat("#0.0000");
            this.symbol = stock.getSymbol();
            this.company = stock.getCompany();
            this.price1 = Double.valueOf(formatter.format(stock.getPrice()));
            this.price2 = Double.valueOf(formatter.format(stock2.getPrice()));
            this.profit = Double.valueOf(formatter.format((stock2.getPrice() - stock.getPrice())));
            this.stockamount = 10000 / stock.getPrice();
            double stocknegater = stockamount * 3;
            this.simulated = Double.valueOf(formatter.format(((stockamount * stock2.getPrice()) - stocknegater)));
        } catch(Exception e) {
            System.out.println(stock.company + " has failed");
            e.printStackTrace();
        }
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getStockamount() {
        return stockamount;
    }

    public void setStockamount(double stockamount) {
        this.stockamount = stockamount;
    }

    public double getSimulated() {
        return simulated;
    }

    public void setSimulated(double simulated) {
        this.simulated = simulated;
    }
}
