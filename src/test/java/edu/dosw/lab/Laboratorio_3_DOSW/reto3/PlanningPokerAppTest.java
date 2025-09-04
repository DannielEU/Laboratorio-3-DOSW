package edu.dosw.lab.Laboratorio_3_DOSW.reto3;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de caja blanca sobre PlanningPokerApp para elevar cobertura.
 * Se usan reflexiones para invocar métodos privados y simular entradas de usuario.
 */
public class PlanningPokerAppTest {

    @Test
    void testCargarHistorias() throws Exception {
        Method cargar = PlanningPokerApp.class.getDeclaredMethod("cargarHistorias");
        cargar.setAccessible(true);
        @SuppressWarnings("unchecked")
        List<String> historias = (List<String>) cargar.invoke(null);
        assertFalse(historias.isEmpty());
    }

    @Test
    void testFlujoConsensoUnaHistoria() throws Exception {
        // Simula: integrantes=2 luego votos 3 y 3 para una historia (se detiene tras consenso para cada historia)
        StringBuilder sb = new StringBuilder();
        sb.append("2\n"); // número de integrantes
        // Para cada historia del archivo: aportar dos votos iguales (usar 3)
        Method cargar = PlanningPokerApp.class.getDeclaredMethod("cargarHistorias");
        cargar.setAccessible(true);
        @SuppressWarnings("unchecked")
        List<String> historias = (List<String>) cargar.invoke(null);
        for (int i = 0; i < historias.size(); i++) {
            sb.append("3\n3\n");
        }
        byte[] inputData = sb.toString().getBytes(StandardCharsets.UTF_8);
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayInputStream testIn = new ByteArrayInputStream(inputData);
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        try {
            System.setIn(testIn);
            System.setOut(new PrintStream(testOut));
            PlanningPokerApp.main(new String[]{});
            String output = testOut.toString(StandardCharsets.UTF_8);
            assertTrue(output.contains("Resumen"));
            assertTrue(output.contains("Consenso alcanzado"));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    @Test
    void testMetodosValidacionEntrada() throws Exception {
        // Prueba lectura de entero positivo con entradas inválidas primero
        ByteArrayInputStream testIn = new ByteArrayInputStream("0\n-1\nabc\n5\n".getBytes(StandardCharsets.UTF_8));
        BufferedReader br = new BufferedReader(new InputStreamReader(testIn));

        Method leerPos = PlanningPokerApp.class.getDeclaredMethod("leerEnteroPositivo", BufferedReader.class);
        leerPos.setAccessible(true);
        int val = (int) leerPos.invoke(null, br);
        assertEquals(5, val);
    }
}
