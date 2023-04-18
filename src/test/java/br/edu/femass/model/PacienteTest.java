package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PacienteTest {

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        Plano plano = new Plano("Plano Teste");
        paciente = new Paciente("02792655062", "Teste", plano);
    }

    @Test
    void testGetCpf() {
        assertEquals("02792655062", paciente.getCpf());
    }

    @Test
    void testGetNome() {
        assertEquals("Teste", paciente.getNome());
    }

    @Test
    void testGetPlano() {
        assertEquals("Plano Teste", paciente.getPlano().getPlano());
    }


    @Test
    void testToString() {
        String expected = "cpf= 02792655062 , nome= Teste , plano= Plano Teste";
        assertEquals(expected, paciente.toString());
    }
}

