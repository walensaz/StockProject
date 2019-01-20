package me.zach;

import GoogleAPI.API;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Launcher {

    public static void main(String[] args) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
        //get current date time with Date()
        Date date = new Date();
        String daten = dateFormat.format(date);
        int index = 0;
        int i = 0;
        File sfile = new File("D:/Stocks/"+daten+".txt");
        PrintWriter writer = new PrintWriter(sfile);
        String[] symbols = getSymbols();
        for(String symbol : symbols) {
            index++;
            i++;
            if(i >= 100) {
                System.out.println(index);
                i = 0;
            }
            Stock stock;
            try {
                stock = YahooFinance.get(symbol);
            } catch(FileNotFoundException e) {
                System.out.println("!!!!!!ERROR: " + symbol);
                continue;
            } catch(NullPointerException e) {
                e.printStackTrace();
                continue;
            } catch(Exception e) {
                e.printStackTrace();
                continue;
            }
            writer.println(stock.getSymbol() + ", " + stock.getQuote().getPrice() + ", " + stock.getQuote().getChangeInPercent() + ", " + stock.getName());
            writer.flush();
        }
        JOptionPane.showMessageDialog(null, "Stocks updated!");
        System.out.print("DONE!!!!!!!!");
    }

    public static String getSymbolFromString(String str) {
        str.split(",");
        return str.split(",")[0].trim();
    }

    public static String[] getSymbols() {
        ArrayList<String> symbols = new ArrayList<>();
        int index = 0;
        try {
            InputStreamReader stream = new InputStreamReader(ClassLoader.getSystemResourceAsStream("stocklist.txt"));
            BufferedReader reader = new BufferedReader(stream);
            String temp;
            while ((temp = reader.readLine()) != null) {
                if(!temp.split(",")[0].equalsIgnoreCase("Symbol")) {
                    symbols.add(temp.split(",")[0].trim());
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        String[] finalsymbols = new String[symbols.size()];
        for(int i = 0;i < symbols.size(); i++) {
            if(symbols.get(i).length() > 1) {
                finalsymbols[i] = symbols.get(i);
            }
        }
        return finalsymbols;
    }




}