package br.edu.femass.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ComboBox<Especialidade> CboEspecialidade1;

    @FXML
    private TableView<Medico> TableMedico;

    @FXML
    private TableColumn<Medico, Integer> col_crm;

    @FXML
    private TableColumn<Medico, String> col_nome;

    @FXML
    private TableColumn<Medico, String> col_esp;

    @FXML
    private ListView<Medico> listaMedico;

    private Dao<Especialidade> especialidadeDao = new EspecialidadeDao();
    private Dao<Medico> medicoDao = new MedicoDao();


    @FXML 
    private void On_Key_Pressed_TableMedico(KeyEvent event) {
        exibirDados();
    }

    @FXML 
    private void On_Mouse_Clicked_TableMedico(MouseEvent event) {
        exibirDados();
    }

    private void exibirDados() {
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        if (medico == null) return;
    
        TxtCrm.setText(medico.getCrm());
        TxtNome.setText(medico.getNome());
    
        ObservableList<Especialidade> especialidades = FXCollections.observableArrayList(medico.getEspecialidades());
        CboEspecialidade.setItems(especialidades);
        CboEspecialidade1.setItems(especialidades);
    }
    

    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Medico medico = TableMedico.getSelectionModel().getSelectedItem();
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
        List<Especialidade> especialidades = new ArrayList<>();
        
        // Adiciona a primeira especialidade selecionada
        Especialidade especialidadeSelecionada = CboEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidadeSelecionada != null) {
            especialidades.add(especialidadeSelecionada);
        }
        // Adiciona a segunda especialidade selecionada
        Especialidade especialidadeSelecionada2 = CboEspecialidade1.getSelectionModel().getSelectedItem();
        if (especialidadeSelecionada2 != null) {
            especialidades.add(especialidadeSelecionada2);
        }

        Medico medico = new Medico(
                TxtCrm.getText(),
                TxtNome.getText(),
                especialidades);

        if (medicoDao.gravar(medico) == false) {
            DiversosJavaFx.exibirMensagem("Não foi possível gravar a conta corrente");
            return;
        }
        TxtCrm.setText("null");
        TxtNome.setText("null");
        CboEspecialidade.getSelectionModel().select(null);
        CboEspecialidade1.getSelectionModel().select(null);
        exibirMedicos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*
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
*/
    public void exibirMedicos() {
        try {
        ObservableList<Medico> data = FXCollections.observableArrayList(
            medicoDao.buscarAtivos()
        );
        TableMedico.setItems(data);
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

    public void exibirEspecialidades1() {
        try {
            ObservableList<Especialidade> data = FXCollections.observableArrayList(
                especialidadeDao.buscarAtivos()
            );
            CboEspecialidade1.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }        
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirMedicos();
        exibirEspecialidades();
        exibirEspecialidades1();

        col_crm.setCellValueFactory(new PropertyValueFactory<>("crm"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_esp.setCellValueFactory(new PropertyValueFactory<>("especialidades"));

    }

}
