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

    public Especialidade getEspecialidade() {
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
        return "crm= " + crm + " , nome= " + nome +  " , especialidade= " + especialidade.getEspecialidade() ;
    }

    @Override
    public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Medico other = (Medico) obj;
    if (crm == null) {
        if (other.crm != null)
            return false;
    } else if (!crm.equals(other.crm))
        return false;
    if (especialidade == null) {
        if (other.especialidade != null)
            return false;
    } else if (!especialidade.equals(other.especialidade))
        return false;
    if (nome == null) {
        if (other.nome != null)
            return false;
    } else if (!nome.equals(other.nome))
        return false;
    return true;
}

}
