package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Especialidade;
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

public class EspecialidadeController implements Initializable {

    @FXML
    private TextField TxtId;

    @FXML
    private TextField TxtEspecialidade;

    @FXML
    private ListView<Especialidade> listaEspecialidade;

    private EspecialidadeDao especialidadeDao = new EspecialidadeDao();

    @FXML
    private TableView<Especialidade> TableEspecialidade;

    @FXML
    private TableColumn<Especialidade, Integer> col_id;

    @FXML
    private TableColumn<Especialidade, String> col_especialidade;

    @FXML 
    private void On_Key_Pressed_TableEspecialidade(KeyEvent event) {
        exibirDados();
    }

    @FXML 
    private void On_Mouse_Clicked_TableEspecialidade(MouseEvent event) {
        exibirDados();
    }

    private void exibirDados() {
        Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade==null) return;

        TxtEspecialidade.setText(especialidade.getEspecialidade());
        TxtId.setText(especialidade.getId().toString());
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Especialidade especialidade = TableEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade==null) return;

        try {
            if (especialidadeDao.excluir(especialidade)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível excluir a especialidade selecionado");
            }
            exibirEspecialidade();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
            Especialidade especialidade = new Especialidade(TxtEspecialidade.getText());
            TxtId.setText(especialidade.getId().toString());

            if (especialidadeDao.gravar(especialidade)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível gravar o especialidade");
                return;
            }

            TxtId.setText("");
            TxtEspecialidade.setText("");


            exibirEspecialidade();   
        } catch (Exception e) {
            DiversosJavaFx.exibirMensagem(e.getMessage());
        }

    }
/*
    public void exibirEspecialidade() {
        try {
        ObservableList<Especialidade> data = FXCollections.observableArrayList(
            especialidadeDao.buscarAtivos()
        );
        listaEspecialidade.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
*/

    public void exibirEspecialidade() {
        try{
            ObservableList<Especialidade> data = FXCollections.observableArrayList(
                especialidadeDao.buscarAtivos()
            );
            TableEspecialidade.setItems(data);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirEspecialidade();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_especialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
    }

}
