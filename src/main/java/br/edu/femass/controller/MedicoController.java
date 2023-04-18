package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.dao.Dao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MedicoController implements Initializable {

    @FXML
    private TextField TxtCrm;

    @FXML
    private TextField TxtNome;

    @FXML
    private ComboBox<Especialidade> CboEspecialidade;

    @FXML
    private ListView<Medico> listaMedico;

    private Dao<Especialidade> especialidadeDao = new EspecialidadeDao();
    private Dao<Medico> medicoDao = new MedicoDao();


    @FXML 
    private void listaMedico_keyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML 
    private void listaMedico_mouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void exibirDados() {
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        if (medico==null) return;

        TxtCrm.setText(medico.getCrm());
        TxtNome.setText(medico.getNome());

        CboEspecialidade.getSelectionModel().select(medico.getEsp());
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        if (medico==null) return;

        try {
            if (medicoDao.excluir(medico)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível excluir a conta selecionado");
            }
        exibirMedicos();
        } catch (Exception e) {
            e.printStackTrace();
        }
       

    }

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
            Medico medico = new Medico(
                    TxtCrm.getText(),
                    TxtNome.getText(),

                    CboEspecialidade.getSelectionModel().getSelectedItem());

            if (medicoDao.gravar(medico)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível gravar a conta corrente");
                return;
            }

            TxtCrm.setText("null");
            TxtNome.setText("null");

            CboEspecialidade.getSelectionModel().select(null);


            exibirMedicos();   
        } catch (Exception e) {
            DiversosJavaFx.exibirMensagem(e.getMessage());
        }

    }

    public void exibirMedicos() {
        try {
        ObservableList<Medico> data = FXCollections.observableArrayList(
            medicoDao.buscarAtivos()
        );
        listaMedico.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void exibirEspecialidades() {
        try {
            ObservableList<Especialidade> data = FXCollections.observableArrayList(
                especialidadeDao.buscarAtivos()
            );
            CboEspecialidade.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirMedicos();
        exibirEspecialidades();
    }

}
