/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.API;
import data.JSONParser;
import data.Certification;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import javafx.collections.FXCollections;

/**
 *
 * @author josel
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
        String results = API.getCertifications(APIKey);
        ArrayList<Certification> certifications = JSONParser.parseCertifications(results);
        System.out.println(results);
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
        columnProject.setCellValueFactory(
                new PropertyValueFactory<>("project_name")
        );
        columnStatus.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
        columnPrice.setCellValueFactory(
                new PropertyValueFactory<>("project_price")
        );
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
