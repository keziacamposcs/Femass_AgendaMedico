package br.edu.femass.model;

public class Medico {
    private String crm;
    private String nome;
    private Especialidade especialidade;
    private Boolean ativo;
    
    public Medico() {
    }

    public Medico(String crm, String nome, Especialidade especialidade) {
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
        this.ativo = true;
    }

    public String getCrm() {
        return crm;
    }

    public String getNome() {
        return nome;
    }

    public Especialidade getEsp() {
        return especialidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "crm= " + crm + "nome="+ nome + ", especialidade= " + especialidade.getEspecialidade() ;
    }
    
}
