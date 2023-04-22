package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.PlanoDao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.Dao;

import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Plano;
import br.edu.femass.model.Paciente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PacienteController implements Initializable {

    @FXML
    private TextField TxtCpf;

    @FXML
    private TextField TxtNome;

    @FXML
    private ComboBox<Plano> CboPlano;

    @FXML
    private ListView<Paciente> listaPaciente;

    private Dao<Plano> planoDao = new PlanoDao();
    private Dao<Paciente> pacienteDao = new PacienteDao();

    @FXML
    private TableView<Paciente> TablePaciente;

    @FXML
    private TableColumn<Paciente, Integer> col_cpf;

    @FXML
    private TableColumn<Paciente, String> col_nome;

    @FXML
    private TableColumn<Paciente, String> col_plano;


    @FXML 
    private void On_Key_Pressed_TablePaciente(KeyEvent event) {
        exibirDados();
    }

    @FXML 
    private void On_Mouse_Clicked_TablePaciente(MouseEvent event) {
        exibirDados();
    }

    private void exibirDados() {
        Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
        if (paciente==null) return;

        TxtCpf.setText(paciente.getCpf());
        TxtNome.setText(paciente.getNome());

        CboPlano.getSelectionModel().select(paciente.getPlano());
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Paciente paciente = TablePaciente.getSelectionModel().getSelectedItem();
        if (paciente==null) return;

        try {
            if (pacienteDao.excluir(paciente)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível excluir a conta selecionado");
            }
            exibirPacientes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
            Paciente paciente = new Paciente(
                    TxtCpf.getText(),
                    TxtNome.getText(),
                    CboPlano.getSelectionModel().getSelectedItem());

            if (pacienteDao.gravar(paciente)==false) {
                DiversosJavaFx.exibirMensagem("Não foi possível gravar a conta corrente");
                return;
            }
            TxtCpf.setText("null");
            TxtNome.setText("null");
            CboPlano.getSelectionModel().select(null);

            exibirPacientes();   
        } catch (Exception e) {
            DiversosJavaFx.exibirMensagem(e.getMessage());
        }
    }
/*
    public void exibirPacientes() {
        try {
        ObservableList<Paciente> data = FXCollections.observableArrayList(
            pacienteDao.buscarAtivos()
        );
        listaPaciente.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
*/
    public void exibirPacientes() {
        try{
            ObservableList<Paciente> data = FXCollections.observableArrayList(
                pacienteDao.buscarAtivos()
            );
            TablePaciente.setItems(data);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public void exibirPlano() {
        try {
            ObservableList<Plano> data = FXCollections.observableArrayList(
                planoDao.buscarAtivos()
            );
            CboPlano.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirPacientes();
        exibirPlano();

        col_cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_plano.setCellValueFactory(new PropertyValueFactory<>("plano"));
    }

}
