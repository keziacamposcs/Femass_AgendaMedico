package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EspecialidadeTest {

    private Especialidade especialidade;

    @BeforeEach
    void setUp() {
        especialidade = new Especialidade("Cardiologia");
    }

    @Test
    void testGetEspecialidade() {
        String expectedEspecialidade = "Cardiologia";
        assertEquals(expectedEspecialidade, especialidade.getEspecialidade());
    }

    @Test
    void testToString() {
        String expectedString = "Cardiologia";
        assertEquals(expectedString, especialidade.toString());

    }
}
