package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.AgendaDao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.dao.Dao;

import br.edu.femass.diversos.DiversosJavaFx;

import br.edu.femass.model.Agenda;
import br.edu.femass.model.Medico;
import br.edu.femass.model.Paciente;

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

public class AgendaController implements Initializable {

    @FXML
    private TextField TxtData;

    @FXML
    private ComboBox<Medico> CboMedico;

    @FXML
    private ComboBox<Paciente> CboPaciente;

    @FXML
    private ListView<Agenda> listaAgenda;

    private Dao<Paciente> pacienteDao = new PacienteDao();
    private Dao<Medico> medicoDao = new MedicoDao();
    private Dao<Agenda> agendaDao = new AgendaDao();


    @FXML 
    private void listaAgenda_keyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML 
    private void listaAgenda_mouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void exibirDados() {
        Agenda agenda = listaAgenda.getSelectionModel().getSelectedItem();
        if (agenda==null) return;

        TxtData.setText(agenda.getData());

        CboPaciente.getSelectionModel().select(agenda.getPaciente());
        CboMedico.getSelectionModel().select(agenda.getMedico());
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Agenda agenda = listaAgenda.getSelectionModel().getSelectedItem();
        if (agenda==null) return;

        try {
            if (agendaDao.excluir(agenda)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível excluir a conta selecionado");
            }
            exibirAgendas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
            Agenda agenda = new Agenda(
                    TxtData.getText(),
                    CboMedico.getSelectionModel().getSelectedItem(),
                    CboPaciente.getSelectionModel().getSelectedItem());

            if (agendaDao.gravar(agenda)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível gravar a conta corrente");
                return;
            }

            TxtData.setText("null");
            CboMedico.getSelectionModel().select(null);
            CboPaciente.getSelectionModel().select(null);

            exibirAgendas();   
        } catch (Exception e) {
            DiversosJavaFx.exibirMensagem(e.getMessage());
        }

    }

    public void exibirAgendas() {
        try {
        ObservableList<Agenda> data = FXCollections.observableArrayList(
            agendaDao.buscarAtivos()
        );
        listaAgenda.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    public void exibirMedicos() {
        try {
            ObservableList<Medico> data = FXCollections.observableArrayList(
                medicoDao.buscarAtivos()
            );
            CboMedico.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }        
    }

    public void exibirPacientes() {
        try {
            ObservableList<Paciente> data = FXCollections.observableArrayList(
                pacienteDao.buscarAtivos()
            );
            CboPaciente.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirAgendas();
        exibirMedicos();
        exibirPacientes();
    }

}
