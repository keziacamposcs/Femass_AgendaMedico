package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedicoTest {

    private Medico medico;

    @BeforeEach
    void setUp() {
        Especialidade esp = new Especialidade("Pediatria");
        medico = new Medico("1234", "João da Silva", esp);
    }

    @Test
    void testGetCrm() {
        String expectedCrm = "1234";
        assertEquals(expectedCrm, medico.getCrm());
    }

    @Test
    void testGetEspecialidade() {
        String expectedEspecialidade = "Pediatria";
        assertEquals(expectedEspecialidade, medico.getEspecialidade().getEspecialidade());
    }

    @Test
    void testGetNome() {
        String expectedNome = "João da Silva";
        assertEquals(expectedNome, medico.getNome());
    }

    @Test
    void testToString() {
        String expectedString = "crm= 1234 , nome= João da Silva , especialidade= Pediatria";
        assertEquals(expectedString, medico.toString());
    }
}
