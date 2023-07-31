package se2203.laljalla_assignment1;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class SortingHubController implements Initializable {

    private int[] intArray;
    private MergeSort mergeSort;
    private SelectionSort selectionSort;

    @FXML
    private Button sortButton;
    @FXML
    private Button resetButton;
    @FXML
    private Slider slider;
    @FXML
    private Label arraySize;
    @FXML
    private ComboBox<String> comboBox = new ComboBox<>();
    @FXML
    private Rectangle rectangle;
    @FXML
    private AnchorPane innerPane;


    @FXML
    void displaySort() {
        setSortingStrategy();
        displayRec();
    }

    @FXML
    void displayReset() {
        comboBox.setValue("Merge Sort");
        innerPane.getChildren().clear();
        intArray = new int[64];
        slider.setValue(Math.round(intArray.length));
        arraySize.setText(String.valueOf(Math.round(intArray.length)));
        updateArr(intArray);
        updateGraph(intArray);
        displayRec();
    }

    @FXML
    void displaySlider() {
        arraySize.setText(String.valueOf(Math.round(slider.getValue())));
        intArray = new int[(int) slider.getValue()];
        innerPane.getChildren().clear();
        updateArr(intArray);
        updateGraph(intArray);
    }

    @FXML
    void displayComboBox() {
        comboBox.getSelectionModel().getSelectedItem();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        intArray = new int[64];

        slider.setValue(Math.round(intArray.length));
        arraySize.setText(String.valueOf(Math.round(intArray.length)));
        ArrayList<String> namesArrList = new ArrayList<>();
        namesArrList.add("Merge Sort");
        namesArrList.add("Selection Sort");
        ObservableList<String> strList = FXCollections.observableArrayList(namesArrList);
        comboBox.getItems().setAll(strList);
        comboBox.setValue("Merge Sort");

        updateArr(intArray);
        displayRec();
    }

    public void updateGraph(int[] data) {
        Platform.runLater(() -> {
            innerPane.getChildren().clear();
            displayRec();
        });
    }

    public int [] updateArr (int[] data) {

        intArray = new int[data.length];

        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }

        ArrayList<Integer> intArrayList = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            intArrayList.add(data[i]);
        }

        Collections.shuffle(intArrayList);
        for (int i = 0; i < data.length; i++) {
            intArray[i]=intArrayList.get(i);
        }
        return intArray;
    }

    public void displayRec (){
        for (int i = 0; i < intArray.length; i++) {
            int recHeight = (int) (intArray[i] * (innerPane.getPrefHeight() / intArray.length));
            int recWidth = (int) (innerPane.getPrefWidth() / intArray.length) - 1;
            int recX = (int) (i * innerPane.getPrefWidth() / intArray.length);
            int recY = (int) (innerPane.getPrefHeight() - recHeight);
            rectangle = new Rectangle(recX, recY, recWidth, recHeight);
            rectangle.setFill(Color.RED);
            innerPane.getChildren().add(rectangle);
        }


    }

    public void setSortingStrategy (){
        if (comboBox.getValue() == "Merge Sort") {
            mergeSort = new MergeSort(intArray,this);
            Thread thread = new Thread(mergeSort);
            thread.start();
        }

        else if(comboBox.getValue() == "Selection Sort"){
            selectionSort = new SelectionSort(intArray,this);
            Thread thread = new Thread(selectionSort);
            thread.start();

        }
    }
}