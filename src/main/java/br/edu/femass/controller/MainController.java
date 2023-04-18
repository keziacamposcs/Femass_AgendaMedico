package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Cliente.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Cliente");
        stage.setScene(scene);
        stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }    
    
    @FXML
    private void handleBtnContaAction(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ContaCorrente.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Conta Corrente");
        stage.setScene(scene);
        stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void especialidade_onAction(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Especialidade.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Especialidade");
        stage.setScene(scene);
        stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void plano_onAction(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Plano.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Plano de Saúde");
        stage.setScene(scene);
        stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void medico_onAction(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Medico.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Médico");
        stage.setScene(scene);
        stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void paciente_onAction(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Paciente.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Paciente");
        stage.setScene(scene);
        stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
