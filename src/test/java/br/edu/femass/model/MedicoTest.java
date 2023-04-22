package br.edu.femass.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class MedicoTest {

    private Medico medico;
    
    @BeforeEach
    public void setUp() {

        Especialidade especialidade = new Especialidade("Cardiologista");
        List<Especialidade> especialidades = new ArrayList<>();
        especialidades.add(especialidade);
        medico = new Medico("12345", "Teste", especialidades);
    }


    @Test
    void testAddEspecialidade() {
        Especialidade especialidade = new Especialidade("Cardiologista");
        medico.addEspecialidade(especialidade);
        assertEquals(2, medico.getEspecialidades().size());
    }

    @Test
    void testGetCrm() {
        assertEquals("12345", medico.getCrm());
    }

    @Test
    void testGetEspecialidades() {
        assertEquals(1, medico.getEspecialidades().size());
    }

    @Test
    void testGetNome() {
        assertEquals("Teste", medico.getNome());
    }
}
