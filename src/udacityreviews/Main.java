/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udacityreviews;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.collections.FXCollections;

/**
 *
 * @author josel
 */
public class Main extends Application {

    Group root;
    TextArea textFieldAPIKey;
    VBox verticalBoxAPIKey;

    @Override
    public void start(Stage primaryStage) {
        Label labelAPIKey = new Label("Enter your API Key ");
        textFieldAPIKey = new TextArea();
        textFieldAPIKey.setWrapText(true);
        textFieldAPIKey.setPrefColumnCount(10);
        
        Button buttonAPIKey = new Button();
        // Set up the API button
        buttonAPIKey.setText("Submit");
        buttonAPIKey.setOnAction((ActionEvent event) -> {
            loadCertifications();
        });
        // Set up the components for the initial scene
        root = new Group();
        verticalBoxAPIKey = new VBox();
        verticalBoxAPIKey.setPadding(new Insets(10, 50, 50, 50));
        verticalBoxAPIKey.setSpacing(10);
        verticalBoxAPIKey.getChildren().add(labelAPIKey);
        verticalBoxAPIKey.getChildren().add(textFieldAPIKey);
        verticalBoxAPIKey.getChildren().add(buttonAPIKey);
        root.getChildren().add(verticalBoxAPIKey);
        // Create the initial scene
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Udacity Reviews");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadCertifications() {
        String APIKey = textFieldAPIKey.getText();
        String results = API.getCertifications(APIKey);
        ArrayList<Certification> certifications = JSONParser.parseCertifications(results);
        System.out.println(results);
        System.out.println(certifications);

        ObservableList<Certification> data = FXCollections.observableArrayList(
                certifications
        );

        TableView tableCertifications = new TableView();
        TableColumn columnProject = new TableColumn("Project");
        TableColumn columnStatus = new TableColumn("Status");
        TableColumn columnPrice = new TableColumn("Price");

        columnProject.setCellValueFactory(
                new PropertyValueFactory<>("project_name")
        );
        columnStatus.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
        columnPrice.setCellValueFactory(
                new PropertyValueFactory<>("project_price")
        );
        tableCertifications.setItems(data);
        tableCertifications.getColumns().addAll(columnProject, columnStatus, columnPrice);
     
        root.getChildren().remove(verticalBoxAPIKey);
        root.getChildren().add(tableCertifications);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
