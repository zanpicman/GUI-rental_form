package com.example.naloga4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    public Button saveButton;
    public TextArea povzetek;
    public TextField ime;
    public TextField naslov;
    public TextField mail;
    public TextField priimek;
    public TextField telefon;
    public CheckBox zavarovanje;
    public TextField kartica;
    public TextField cvv;
    public Tab povzetekTab;
    public TabPane tabPane;
    public Tab podatki;
    private long dni;

    private int cena1;

    @FXML


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 9, 1);
        izposojaOd.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactory1 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 9, 1);
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
        if (zavarovanje.isSelected())skupnaCena.setText(String.valueOf(dni*cena1+dni*2) + "€");
        else skupnaCena.setText(String.valueOf(dni*cena1) + "€");

    }

    public void shraniCB(ActionEvent actionEvent) {
        if (ime.getText().equals("") || priimek.getText().equals("") ||
                ime.getText().equals("") || priimek.getText().equals("") || naslov.getText().equals("") ||
                naslov.getText().equals("") ||  mail.getText().equals("") || telefon.getText().equals("")) {
            System.out.println(" prazna polja");
            status.setText("Prazna polja");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opozorilo");
            alert.setHeaderText("Izpolnite vsa polja");
            alert.setContentText("Nekatera polja so prazna, prosim izpolnite vse!");
            alert.showAndWait();

        } else {
        povzetek.setText("Ime: " + ime.getText() + "\nPriimek: " + priimek.getText() +"\nNaslov: "+ naslov.getText()
                    + "\nTelefon: " + telefon.getText() + "\nE-mail: " + mail.getText() + "\nStarost: "
        + starost.getValue() + "\nLeta (izpit): " + leta.getValue() + "\nDodatno zavarovanje: "+ zavarovanje.isSelected()
        + "\nŠtevilka kartice: " + "xxxx-xxxx-xxxx-1111" + "\nIzposoja od: "+ datumOd.getValue() + " "+ izposojaOd.getValue() + ".00"
        + "\nIzposoja do: "+  datumDo.getValue() + " "+  izposojaDo.getValue()+".00"
        +"\nAvto: " + avtomobil.getValue() + "\nMenjalnik: Samodejni" + "\nGorivo: Bencin" + "\nPrevzemno mesto: "
        + prevzemno.getValue() + "\nOddajno mesto: " + oddajno.getValue() + "\nSkupna cena: " + (dni*cena1 + dni * 2) );
        povzetekTab.setDisable(false);
        tabPane.getSelectionModel().select(povzetekTab);
        status.setText("Uspešno shranjeno");


    }
}

    public void ponastaviCB(ActionEvent actionEvent) {
        ime.setText("");
        priimek.setText("");
        naslov.setText("");
        mail.setText("");
        kartica.setText("");
        cvv.setText("");
        telefon.setText("");
        status.setText("Ponastavljen obrazec");
        povzetekTab.setDisable(true);
        tabPane.getSelectionModel().select(podatki);

    }
}