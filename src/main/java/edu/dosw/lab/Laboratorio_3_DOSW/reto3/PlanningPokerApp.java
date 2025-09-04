package edu.dosw.lab.Laboratorio_3_DOSW.reto3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Aplicación de consola que simula Planning Poker para estimar historias.
 * Historias se leen del recurso de classpath: reto3/stories.txt
 */
public class PlanningPokerApp {

    private static final Set<Integer> VALORES = Set.of(1,2,3,5,8,13);
    private static final String STORIES_RESOURCE = "reto3/stories.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("=== Planning Poker - Reto 3 ===");
        System.out.print("Ingrese número de integrantes que votan: ");
        int integrantes = leerEnteroPositivo(br);

        List<String> historias = cargarHistorias();
        if (historias.isEmpty()) {
            System.out.println("No se encontraron historias (recurso: " + STORIES_RESOURCE + ")");
            return;
        }

        Map<String,Integer> resultado = new LinkedHashMap<>();
        for (String historia : historias) {
            System.out.println("\nHistoria: " + historia);
            Integer consenso = obtenerConsenso(integrantes, br);
            resultado.put(historia, consenso);
            System.out.println("Consenso alcanzado: " + consenso);
        }

        System.out.println("\n=== Resumen de estimaciones ===\n");
        resultado.forEach((h,p) -> System.out.println("- " + h + ": " + p));
        System.out.println("\nFin.");
    }

    private static Integer obtenerConsenso(int integrantes, BufferedReader br) throws IOException {
        while (true) {
            List<Integer> ronda = new ArrayList<>();
            for (int i = 1; i <= integrantes; i++) {
                Integer voto = solicitarVoto(i, br);
                ronda.add(voto);
            }
            boolean todosIguales = ronda.stream().distinct().count() == 1;
            if (todosIguales) {
                return ronda.get(0);
            }
            System.out.println("Votos divergentes – Discutan y vuelvan a votar");
        }
    }

    private static Integer solicitarVoto(int miembro, BufferedReader br) throws IOException {
        while (true) {
            System.out.print("Voto miembro " + miembro + " (1,2,3,5,8,13): ");
            String linea = br.readLine();
            try {
                int val = Integer.parseInt(linea.trim());
                if (VALORES.contains(val)) {
                    return val;
                }
            } catch (NumberFormatException ignored) { }
            System.out.println("Valor inválido. Intente de nuevo.");
        }
    }

    private static int leerEnteroPositivo(BufferedReader br) throws IOException {
        while (true) {
            String linea = br.readLine();
            try {
                int n = Integer.parseInt(linea.trim());
                if (n > 0) return n;
            } catch (NumberFormatException ignored) { }
            System.out.print("Número inválido. Ingrese nuevamente: ");
        }
    }

    private static List<String> cargarHistorias() throws IOException {
        InputStream in = PlanningPokerApp.class.getClassLoader().getResourceAsStream(STORIES_RESOURCE);
        if (in == null) return List.of();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            List<String> historias = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String t = line.trim();
                if (t.isEmpty() || t.startsWith("#")) continue;
                historias.add(t);
            }
            return historias;
        }
    }
}
