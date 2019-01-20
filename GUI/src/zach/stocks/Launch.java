package zach.stocks;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class Launch implements Initializable {

    //Sim stuff
    @FXML
    private TableView<SimStock> stockSimView;

    @FXML
    private TableColumn<SimStock, Double> price1Sim, price2Sim, profitSim, simulatorSim;

    @FXML
    private TableColumn<SimStock, String> symbolSim, companySim;

    @FXML
    DatePicker stockdateSim, stockdateSim1;


    //Other stuff
    @FXML
    private TableView<Stock> stockTableView, stockTableView1, stockTableView2;

    @FXML
    private TableColumn<Stock, Double> pricetable, pricetable1, pricetable2;

    @FXML
    private TableColumn<Stock, String> companytable, companytable1, companytable2;

    @FXML
    private TableColumn<Stock, String> symboltable,symboltable1, symboltable2;

    @FXML
    private TableColumn<Stock, Double> changetable, changetable1, changetable2;

    @FXML
    private DatePicker stockdate, stockdate1, stockdate2;


    public ObservableList<Stock> getStocks(LocalDate local) {
        ObservableList<Stock> stocks = FXCollections.observableArrayList();
        try {
            File stockfile = new File("");
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Instant instant = Instant.from(local.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            String dateform = dateFormat.format(date);
            for (File file : Launcher.files) {
                if (file.getName().contains(dateform)) {
                    stockfile = file;
                    break;
                }
            }

            BufferedReader reader = new BufferedReader(new FileReader(stockfile));
            String temp = "";
            while ((temp = reader.readLine()) != null) {
                String[] args = temp.split(",");
                Stock stock;
                try {
                    if (args.length == 4) {
                        stock = new Stock(args[0], Double.valueOf(args[2]), Double.valueOf(args[1]), args[3]);
                    } else {
                        stock = new Stock(args[0], Double.valueOf(args[2]), Double.valueOf(args[1]), args[3] + args[4]);
                    }
                } catch (Exception e) {
                    continue;
                }
                stocks.add(stock);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return stocks;
    }

    @FXML
    public void setDataCompare1() {
        ObservableList<Stock> stocks = getStocks(stockdate1.getValue());
        stockTableView1.setItems(stocks);
    }

    @FXML
    public void setDataCompare2() {
        ObservableList<Stock> stocks = getStocks(stockdate2.getValue());
        stockTableView2.setItems(stocks);
    }

    @FXML
    public void setData() {
        ObservableList<Stock> stocks = getStocks(stockdate.getValue());
        stockTableView.setItems(stocks);
    }

    @FXML
    public void simulate() {
        ObservableList<SimStock> stocks = FXCollections.observableArrayList();
        ObservableList<Stock> stock1 = getStocks(stockdateSim.getValue());
        ObservableList<Stock> stock2 = getStocks(stockdateSim1.getValue());
        for(Stock stock : stock1) {
            for(Stock compstock : stock2) {
                if(compstock.getCompany().equalsIgnoreCase(stock.getCompany())) {
                    stocks.add(new SimStock(stock, compstock));
                    stock2.remove(compstock);
                    break;
                }
            }
        }
        stockSimView.setItems(stocks);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Sim stuff
        companySim.setCellValueFactory(new PropertyValueFactory<>("company"));
        symbolSim.setCellValueFactory(new PropertyValueFactory<>("symbol"));
        price1Sim.setCellValueFactory(new PropertyValueFactory<>("price1"));
        price2Sim.setCellValueFactory(new PropertyValueFactory<>("price2"));
        profitSim.setCellValueFactory(new PropertyValueFactory<>("profit"));
        simulatorSim.setCellValueFactory(new PropertyValueFactory<>("simulated"));

        //other stuffs
        symboltable.setCellValueFactory(new PropertyValueFactory<>("symbol"));
        pricetable.setCellValueFactory(new PropertyValueFactory<>("price"));
        changetable.setCellValueFactory(new PropertyValueFactory<>("percentchange"));
        companytable.setCellValueFactory(new PropertyValueFactory<>("company"));
        symboltable1.setCellValueFactory(new PropertyValueFactory<>("symbol"));
        pricetable1.setCellValueFactory(new PropertyValueFactory<>("price"));
        changetable1.setCellValueFactory(new PropertyValueFactory<>("percentchange"));
        companytable1.setCellValueFactory(new PropertyValueFactory<>("company"));
        symboltable2.setCellValueFactory(new PropertyValueFactory<>("symbol"));
        pricetable2.setCellValueFactory(new PropertyValueFactory<>("price"));
        changetable2.setCellValueFactory(new PropertyValueFactory<>("percentchange"));
        companytable2.setCellValueFactory(new PropertyValueFactory<>("company"));
    }
}
