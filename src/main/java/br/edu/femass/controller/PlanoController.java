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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Plano> TablePlano;

    @FXML
    private TableColumn<Plano, Integer> col_id;

    @FXML
    private TableColumn<Plano, String> col_plano;

    @FXML 
    private void On_Key_Pressed_TablePlano(KeyEvent event) {
        exibirDados();
    }

    @FXML 
    private void On_Mouse_Clicked_TablePlano(MouseEvent event) {
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
        Plano plano = TablePlano.getSelectionModel().getSelectedItem();
        if (plano==null) return;

        try {
            if (planoDao.excluir(plano)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível excluir o plano selecionado");
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
                DiversosJavaFx.exibirMensagem("Não foi possível gravar o plano");
                return;
            }

            TxtId.setText("");
            TxtPlano.setText("");

            exibirPlano();   
        } catch (Exception e) {
            DiversosJavaFx.exibirMensagem(e.getMessage());
        }

    }
/* 
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
*/

    public void exibirPlano() {
        try{
            ObservableList<Plano> data = FXCollections.observableArrayList(
                planoDao.buscarAtivos()
            );
            TablePlano.setItems(data);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirPlano();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_plano.setCellValueFactory(new PropertyValueFactory<>("plano"));
    }

}
