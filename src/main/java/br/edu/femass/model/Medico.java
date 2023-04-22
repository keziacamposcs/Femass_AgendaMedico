package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;

public class Medico {
    private String crm;
    private String nome;
    private List<Especialidade> especialidades;
    private Boolean ativo;
    
    public Medico() {
        especialidades = new ArrayList<>();
    }

    public Medico(String crm, String nome, List<Especialidade> especialidades) {
        this.crm = crm;
        this.nome = nome;
        this.especialidades = especialidades;
        this.ativo = true;
    }

    public String getCrm() {
        return crm;
    }

    public String getNome() {
        return nome;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void addEspecialidade(Especialidade especialidade) {
        this.especialidades.add(especialidade);
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("crm= ").append(crm).append(", nome= ").append(nome).append(", especialidades= ");
        for (Especialidade especialidade : especialidades) {
            sb.append(especialidade.getEspecialidade()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        return sb.toString();
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
        if (especialidades == null) {
            if (other.especialidades != null)
                return false;
        } else if (!especialidades.equals(other.especialidades))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }
}
