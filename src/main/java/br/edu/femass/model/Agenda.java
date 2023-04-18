package br.edu.femass.model;

public class Agenda {
    private String data;
    private Medico medico;
    private Paciente paciente;

    private Boolean ativo;
    
    public Agenda() {
    }

    public Agenda(String data, Medico medico, Paciente paciente) {
        this.data = data;
        this.medico = medico;
        this.paciente = paciente;

        this.ativo = true;
    }

    public String getData() {
        return data;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "data= " + data + " , medico= " + medico.getNome() + " , paciente= " + paciente.getNome() ;
    }
    
    
}
