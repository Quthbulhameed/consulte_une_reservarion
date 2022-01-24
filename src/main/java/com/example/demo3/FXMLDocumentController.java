package com.example.demo3;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;


public class FXMLDocumentController implements Initializable {


    @FXML
    private TableView<place> table_place;

    @FXML
    private TableColumn<place, Integer> col_id;

    @FXML
    private TableColumn<place, Integer> col_etage;

    @FXML
    private TableColumn<place, Integer> col_numParking;

    @FXML
    private TableColumn<place, String> col_disponibilite;

    @FXML
    private TableColumn<place, Integer> col_reservation;

    @FXML
    private TableColumn<place, Integer> col_categorie;

    @FXML
    private TableColumn<place, Integer> col_typevoiture;




    @FXML
    private TextField txt_etage;

    @FXML
    private TextField txt_numParking;

    @FXML
    private TextField txt_disponibilite;

    @FXML
    private TextField txt_reservation;

    @FXML
    private TextField txt_categorie;

    @FXML
    private TextField txt_typevoiture;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField filterField;




    ObservableList<place> listM;
    ObservableList<place> dataList;



    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;





    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
        index = table_place.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_etage.setText(col_etage.getCellData(index).toString());
        txt_numParking.setText(col_numParking.getCellData(index).toString());
        txt_disponibilite.setText(col_disponibilite.getCellData(index).toString());
        txt_reservation.setText(col_reservation.getCellData(index).toString());
        txt_categorie.setText(col_categorie.getCellData(index).toString());
        txt_typevoiture.setText(col_typevoiture.getCellData(index).toString());


    }

    public void Edit (){
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_etage.getText();
            String value3 = txt_numParking.getText();
            String value4 = txt_disponibilite.getText();
            String value5 = txt_categorie.getText();
            String value6 = txt_typevoiture.getText();

            String sql = "update place set id= '"+value1+"',etage= '"+value2+"',numParking= '"+
                    value3+"',disponibilite= '"+value4+"',categorie= '"+value5+"',typevoiture= '"+value6+"' where user_id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Delete(){
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from place where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<place,Integer>("id"));
        col_etage.setCellValueFactory(new PropertyValueFactory<place,Integer>("etage"));
        col_numParking.setCellValueFactory(new PropertyValueFactory<place,Integer>("numParking"));
        col_disponibilite.setCellValueFactory(new PropertyValueFactory<place,String>("disponibilite"));
        col_reservation.setCellValueFactory(new PropertyValueFactory<place,Integer>("reservation"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<place,Integer>("categorie"));
        col_typevoiture.setCellValueFactory(new PropertyValueFactory<place,Integer>("typevoiture"));



        listM = mysqlconnect.getDatausers();
        table_place.setItems(listM);
    }




    @FXML
    void search_user() {
        col_id.setCellValueFactory(new PropertyValueFactory<place,Integer>("id"));
        col_etage.setCellValueFactory(new PropertyValueFactory<place,Integer>("etage"));
        col_numParking.setCellValueFactory(new PropertyValueFactory<place,Integer>("numParking"));
        col_disponibilite.setCellValueFactory(new PropertyValueFactory<place,String>("disponibilite"));
        col_reservation.setCellValueFactory(new PropertyValueFactory<place,Integer>("reservation"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<place,Integer>("categorie"));
        col_typevoiture.setCellValueFactory(new PropertyValueFactory<place,Integer>("typevoiture"));



        dataList = mysqlconnect.getDatausers();
        table_place.setItems(dataList);
        FilteredList<place> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getEtage().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (person.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<place> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_place.comparatorProperty());
        table_place.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_user();
        // Code Source in description
    }
}
