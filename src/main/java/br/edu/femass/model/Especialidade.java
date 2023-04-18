package br.edu.femass.model;
import java.util.Set;

public class Especialidade {
    private Long id;
    private String especialidade;

    private Boolean ativo;

    private static Long ultimoCodigo = 0L;

    public Especialidade() {
    }
    
    public Especialidade( String especialidade) {
        this.especialidade = especialidade;
        this.ativo = true;
        
        this.id = ultimoCodigo+1;
        ultimoCodigo++;
    }

    public Long getId() {
        return id;
    }

    public String getEspecialidade() {
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
        return this.especialidade;
    }


    public static void atualizarUltimoId(Set<Especialidade> especialidades) {
        for (Especialidade especialidade: especialidades) {
            if (especialidade.getId().longValue()>ultimoCodigo) {
                ultimoCodigo = especialidade.getId();
            }
        }
    }
    
}
