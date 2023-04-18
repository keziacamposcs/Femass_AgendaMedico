package br.edu.femass.model;
import java.util.Set;

public class Plano {
    private Long id;
    private String plano;

    private Boolean ativo;

    private static Long ultimoCodigo = 0L;

    public Plano() {
    }
    
    public Plano( String plano) {
        this.plano = plano;
        this.ativo = true;
        
        this.id = ultimoCodigo+1;
        ultimoCodigo++;
    }

    public Long getId() {
        return id;
    }

    public String getPlano() {
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
        return this.plano;
    }


    public static void atualizarUltimoId(Set<Plano> planos) {
        for (Plano plano: planos) {
            if (plano.getId().longValue()>ultimoCodigo) {
                ultimoCodigo = plano.getId();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) //verifica se é o mesmo objeto
            return true;
        if (obj == null) //verifica se é nulo
            return false;
        if (getClass() != obj.getClass()) //verifica se é do mesmo tipo
            return false;
        Plano other = (Plano) obj; //converte o objeto para Plano
        if (id == null) { //verifica se o id é nulo
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id)) //compara os ids
            return false;
        if (plano == null) { //verifica se o plano é nulo
            if (other.plano != null)
                return false;
        } else if (!plano.equals(other.plano)) //compara os planos
            return false;
        return true;
    }
    
    
}
