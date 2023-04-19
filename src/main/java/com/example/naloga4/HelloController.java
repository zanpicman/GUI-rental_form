package com.example.naloga4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Spinner izposojaOd;
    public Spinner izposojaDo;
    public ComboBox prevzemno;
    public ComboBox oddajno;
    public ComboBox velikost;
    public ComboBox avtomobil;
    public ToggleGroup menjalnik;
    public ToggleGroup gorivo;
    public Label cena;
    public Spinner starost;
    public Spinner leta;
    public ComboBox placilo;
    public DatePicker datumOd;
    public DatePicker datumDo;
    public Label status;
    public Label skupnaCena;
    private long dni;

    private int cena1;

    @FXML


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Double> valueFactory =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 23.00, 9.00, 1.00);
        izposojaOd.setValueFactory(valueFactory);
        SpinnerValueFactory<Double> valueFactory1 =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 23.00, 9.00, 1.00);
        izposojaDo.setValueFactory(valueFactory1);
        prevzemno.getItems().addAll("Ljubljana", "Maribor"," Celje", "Kranj", "Velenje", "Koper", "Novo Mesto", "Murska Sobota", "Jesenice", "Portorož", "letališče Brnik", "letališče Maribor");
        prevzemno.setValue("Ljubljana");
        oddajno.getItems().addAll("Ljubljana", "Maribor"," Celje", "Kranj", "Velenje", "Koper", "Novo Mesto", "Murska Sobota", "Jesenice", "Portorož", "letališče Brnik", "letališče Maribor");
        oddajno.setValue("Ljubljana");
        velikost.getItems().addAll("Velik", "Srednji", "Majhen");
        velikost.setValue("Srednji");
        avtomobil.getItems().addAll("MB C180", "Audi A5", "Toyota Avensis", "Honda Accord", "Škoda Octavia");
        avtomobil.setValue("Audi A5");
        cena.setText("Cena: 100€/dan");
        cena1 = 100;
        velikost.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Srednji")) {
                avtomobil.getItems().clear();
                avtomobil.getItems().addAll("MB C180", "Audi A5", "Toyota Avensis", "Honda Accord", "Škoda Octavia");
                avtomobil.setValue("Audi A5");
                cena.setText("Cena: 100€/dan");
                cena1 = 100;
            } else if (newValue.equals("Velik")) {
                avtomobil.getItems().clear();
                avtomobil.getItems().addAll("VW Arteon", "Audi Q8", "Ford Raptor", "Dodge Challenger", "Ford Mustang");
                avtomobil.setValue("VW Arteon");
                cena.setText("Cena: 130€/dan");
                cena1 = 130;
            }
             else if (newValue.equals("Majhen")) {
                avtomobil.getItems().clear();
                avtomobil.getItems().addAll("Fiat Panda","Hyundai Getz", "Mini Cooper", "Citroen C3", "Renault Clio");
                avtomobil.setValue("Fiat Panda");
                cena.setText("Cena: 80€/dan");
                cena1 = 80;

            }
        });
        SpinnerValueFactory<Integer> valueFactory2 =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(18, 99, 20, 1);
        starost.setValueFactory(valueFactory2);
        SpinnerValueFactory<Integer> valueFactory3 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 5, 1);
        leta.setValueFactory(valueFactory3);
        placilo.getItems().addAll("Kartica", "Gotovina");
        placilo.setValue("Gotovina");

        datumOd.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateLabel(datumOd.getValue(), datumDo.getValue());
        });

        datumDo.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateLabel(datumOd.getValue(), datumDo.getValue());
        });
    }


    private void updateLabel(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null) {
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
            dni = daysBetween;
    }}

    public void izracunCB(ActionEvent actionEvent) {
        skupnaCena.setText(String.valueOf(dni*cena1 + dni * 2) + "€");

    }
}