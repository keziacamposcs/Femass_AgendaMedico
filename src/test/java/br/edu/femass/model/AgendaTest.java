package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgendaTest {

    private Agenda agenda;

    @BeforeEach
    void setUp() {
        Medico medico = new Medico("CRM123", "Dr. Fulano", new Especialidade("Cardiologia"));
        Paciente paciente = new Paciente("02792655062", "Fulano de Tal", new Plano("Plano Teste"));
        agenda = new Agenda("2022-01-01", medico, paciente);
    }

    @Test
    void testGetData() {
        String expectedData = "2022-01-01";
        assertEquals(expectedData, agenda.getData());
    }

    @Test
    void testGetMedico() {
        Medico expectedMedico = new Medico("CRM123", "Dr. Fulano", new Especialidade("Cardiologia"));
        assertEquals(expectedMedico.toString(), agenda.getMedico().toString());
    }

    @Test
    void testGetPaciente() {
        Paciente expectedPaciente = new Paciente("02792655062", "Fulano de Tal", new Plano("Plano Teste"));
        assertEquals(expectedPaciente.toString(), agenda.getPaciente().toString());
    }

    @Test
    void testToString() {
        String expectedString = "data= 2022-01-01 , medico= Dr. Fulano , paciente= Fulano de Tal";
        assertEquals(expectedString, agenda.toString());
    }
}
