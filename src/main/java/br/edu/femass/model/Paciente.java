package br.edu.femass.model;
import br.edu.femass.diversos.Validador;

public class Paciente {
    private String cpf;
    private String nome;
    private PlanoDeSaude plano;
    private Boolean ativo;
    
    public Paciente() {
    }

    public Paciente(String cpf, String nome, PlanoDeSaude plano) {
        if (Validador.validarCPF(cpf)==false) throw new IllegalArgumentException("CPF Inválido");
        this.cpf = cpf;
        this.nome = nome;
        this.plano = plano;
        this.ativo = true;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public PlanoDeSaude getPlano() {
        return plano;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "cpf= " + cpf + "nome="+ nome + ", plano= " + plano.getPlanoDeSaude() ;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Paciente other = (Paciente) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }
    
}
