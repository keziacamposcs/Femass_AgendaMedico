package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.PlanoDao;

import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Plano;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PlanoController implements Initializable {

    @FXML
    private TextField TxtId;

    @FXML
    private TextField TxtPlano;

    @FXML
    private ListView<Plano> listaPlano;

    private PlanoDao planoDao = new PlanoDao();


    @FXML 
    private void listaPlano_keyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML 
    private void listaPlano_mouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void exibirDados() {
        Plano plano = listaPlano.getSelectionModel().getSelectedItem();
        if (plano==null) return;

        TxtPlano.setText(plano.getPlano());
        TxtId.setText(plano.getId().toString());
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Plano plano = listaPlano.getSelectionModel().getSelectedItem();
        if (plano==null) return;

        try {
            if (planoDao.excluir(plano)==false) {
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
            Plano plano = new Plano(TxtPlano.getText());
            TxtId.setText(plano.getId().toString());

            if (planoDao.gravar(plano)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível gravar o especialidade");
                return;
            }

            TxtId.setText("");
            TxtPlano.setText("");

            exibirPlano();   
        } catch (Exception e) {
            DiversosJavaFx.exibirMensagem(e.getMessage());
        }

    }

    public void exibirPlano() {
        try {
        ObservableList<Plano> data = FXCollections.observableArrayList(
            planoDao.buscarAtivos()
        );
        listaPlano.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirPlano();
    }

}
