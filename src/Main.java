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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import javafx.event.ActionEvent;

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

        anchorPaneCertifications = FXMLLoader.load(getClass().getResource("/views/certifications.fxml"));
        VBox vBoxCertifications = (VBox) anchorPaneCertifications.lookup("#vbox_certifications");
        int i = 0;
        int count = certifications.size();
        while (i < count) {
            Certification certification = certifications.get(i);
            if (certification.getStatus().equals("certified")) {
                System.out.println(certifications.get(i).getProject_name());
                HBox hb = new HBox();
                CheckBox cb = new CheckBox();
                cb.setSelected(true);
                cb.setId(Integer.toString(certification.getProject_id()));
                   System.out.println(cb.getId());
                Label label = new Label(certification.getProject_name());
                hb.getChildren().add(cb);
                hb.getChildren().add(label);
                vBoxCertifications.getChildren().add(hb);
            }
            i++;
        }
        anchorPaneMain.getChildren().remove(anchorPaneAPIKey);
        anchorPaneMain.getChildren().add(anchorPaneCertifications);
        Button buttonCreateRequest = (Button) anchorPaneCertifications.lookup("#create_request_button");
        buttonCreateRequest.setOnAction((ActionEvent event) -> {
            try {
                createRequest(certifications);
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    public void createRequest(ArrayList<Certification> certifications) throws Exception {
        int i = 0;
        int count = certifications.size();
        while (i < count) {
            Certification certification = certifications.get(i);
                if (certification.getStatus().equals("certified")) {
            CheckBox cb = (CheckBox) anchorPaneCertifications.lookup("#" + certification.getProject_id());
            System.out.println(cb.isSelected());
                }
            i++;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
