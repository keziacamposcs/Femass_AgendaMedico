package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.PlanoDeSaudeDao;

import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.PlanoDeSaude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PlanoDeSaudeController implements Initializable {

    @FXML
    private TextField TxtId;

    @FXML
    private TextField TxtPlanoDeSaude;

    @FXML
    private ListView<PlanoDeSaude> listaPlanoDeSaude;

    private PlanoDeSaudeDao planoDeSaudeDao = new PlanoDeSaudeDao();


    @FXML 
    private void listaPlanoDeSaude_keyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML 
    private void listaPlanoDeSaude_mouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void exibirDados() {
        PlanoDeSaude plano = listaPlanoDeSaude.getSelectionModel().getSelectedItem();
        if (plano==null) return;

        TxtPlanoDeSaude.setText(plano.getPlanoDeSaude());
        TxtId.setText(plano.getId().toString());
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        PlanoDeSaude plano = listaPlanoDeSaude.getSelectionModel().getSelectedItem();
        if (plano==null) return;

        try {
            if (planoDeSaudeDao.excluir(plano)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível excluir a especialidade selecionado");
            }
            exibirPlano();
        } catch (Exception e) {
            e.printStackTrace();
        }
       

    }

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
            PlanoDeSaude plano = new PlanoDeSaude(TxtPlanoDeSaude.getText());
            TxtId.setText(plano.getId().toString());

            if (planoDeSaudeDao.gravar(plano)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível gravar o especialidade");
                return;
            }

            TxtId.setText("");
            TxtPlanoDeSaude.setText("");


            exibirPlano();   
        } catch (Exception e) {
            DiversosJavaFx.exibirMensagem(e.getMessage());
        }

    }

    public void exibirPlano() {
        try {
        ObservableList<PlanoDeSaude> data = FXCollections.observableArrayList(
            planoDeSaudeDao.buscarAtivos()
        );
        listaPlanoDeSaude.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirPlano();
    }

}
