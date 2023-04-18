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
    
}
