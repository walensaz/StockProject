package zach.stocks;

public class Stock {

    public String symbol;
    public Double percentchange;
    public Double price;
    public String company;


    public Stock(String symbol, Double percentchange, Double price, String company) {
        this.symbol = symbol;
        this.percentchange = percentchange;
        this.price = price;
        this.company = company;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPercentchange() {
        return percentchange;
    }

    public void setPercentchange(Double percentchange) {
        this.percentchange = percentchange;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
