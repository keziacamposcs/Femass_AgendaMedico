package br.edu.femass.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlanoTest {

    private Plano plano;

    @BeforeEach
    void setUp() {
        plano = new Plano("Plano Teste");
    }

    @Test
    void testGetPlano() {
        String expectedPlano = "Plano Teste";
        assertEquals(expectedPlano, plano.getPlano());
    }

    @Test
    void testToString() {
        String expectedString = "Plano Teste";
        assertEquals(expectedString, plano.toString());
    }
}
