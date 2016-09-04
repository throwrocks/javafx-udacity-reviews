/*
 * The MIT License
 *
 * Copyright 2016 Jose Lopez.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import data.API;
import data.APIResponse;
import data.JSONParser;
import data.Certification;
import util.Utilities;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Main The application class Contains the start up method and handles user
 * input
 *
 * @author Jose Lopez
 */
public class Main extends Application {

    AnchorPane anchorPaneMain;
    AnchorPane anchorPaneAPIKey;
    TextArea textFieldAPIKey;
    AnchorPane anchorPaneCertifications;
    VBox verticalBoxCertifications;

    @Override
    public void start(Stage primaryStage) throws Exception {
        anchorPaneMain = FXMLLoader.load(getClass().getResource("/views/main.fxml"));
        anchorPaneAPIKey = FXMLLoader.load(getClass().getResource("/views/load_api_key.fxml"));
        Button buttonAPIKey = (Button) anchorPaneAPIKey.lookup("#api_submit_button");
        textFieldAPIKey = (TextArea) anchorPaneAPIKey.lookup("#api_key");
        buttonAPIKey.setOnAction((ActionEvent event) -> {
            try {
                loadCertifications();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        anchorPaneMain.getChildren().add(anchorPaneAPIKey);
        Scene scene = new Scene(anchorPaneMain, 800, 400);
        primaryStage.setTitle("Udacity Reviews");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadCertifications() throws Exception {
        String APIKey = textFieldAPIKey.getText();
        APIResponse apiResponse = API.getCertifications(APIKey);
        int responseCode = apiResponse.getResponseCode();
        String responseText = apiResponse.getResponseText();
        // Evaluate response
        switch (responseCode) {
            case 401:
                Utilities.alert("error", "Error " + responseCode, "Not Authorized", "Make sure your API Key is up to date.");
                return;
        }

        ArrayList<Certification> certifications = JSONParser.parseCertifications(responseText);
        System.out.println(responseText);
        System.out.println(certifications);
        ObservableList<Certification> data = FXCollections.observableArrayList(
                certifications
        );
        anchorPaneCertifications = FXMLLoader.load(getClass().getResource("/views/certifications.fxml"));
        verticalBoxCertifications = (VBox) anchorPaneCertifications.lookup("#vbox_certifications");
        TableView certificationsTable = (TableView) anchorPaneCertifications.lookup("#table_certifications");
        TableColumn columnProject = (TableColumn) certificationsTable.getColumns().get(0);
        TableColumn columnStatus = (TableColumn) certificationsTable.getColumns().get(1);
        TableColumn columnPrice = (TableColumn) certificationsTable.getColumns().get(2);
        TableColumn columnSelect = (TableColumn) certificationsTable.getColumns().get(3);
        columnProject.setCellValueFactory(
                new PropertyValueFactory<>("project_name")
        );
        columnStatus.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
        columnPrice.setCellValueFactory(
                new PropertyValueFactory<>("project_price")
        );
        columnSelect.setCellFactory(CheckBoxTableCell.forTableColumn(columnSelect));
        

        columnSelect.setEditable(true);
        certificationsTable.setItems(data);
        certificationsTable.setPrefWidth(400);
        //certificationsTable.getColumns().addAll(columnProject, columnStatus, columnPrice);
        anchorPaneMain.getChildren().remove(anchorPaneAPIKey);
        anchorPaneMain.getChildren().add(anchorPaneCertifications);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
