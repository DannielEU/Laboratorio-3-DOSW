import java.util.*;

class PlanningPokerContext {
    private VotingStrategy strategy;
    private List<String> historias;
    private List<Integer> fibonacci = Arrays.asList(1, 2, 3, 5, 8, 13);

    public PlanningPokerContext(VotingStrategy strategy, List<String> historias) {
        this.strategy = strategy;
        this.historias = historias;
    }
    public void ejecutar(int numIntegrantes) {
        Map<String, Integer> estimaciones = new LinkedHashMap<>();

        for (String historia : historias) {
            System.out.println("\nHistoria: " + historia);
            int puntaje = procesarHistoria(historia, numIntegrantes);
            estimaciones.put(historia, puntaje);
        }

        mostrarResumen(estimaciones);
    }
    private int procesarHistoria(String historia, int numIntegrantes) {
        boolean consenso = false;
        int puntaje = -1;

        while (!consenso) {
            List<Integer> votos = recolectarVotos(numIntegrantes);

            if (strategy.hasConsensus(votos)) {
                puntaje = strategy.getConsensusValue(votos);
                System.out.println("Consenso alcanzado. Puntaje asignado: " + puntaje);
                consenso = true;
            } else {
                System.out.println("\n Votos divergentes – Discutan y vuelvan a votar.");
            }
        }
        return puntaje;
    }
    private List<Integer> recolectarVotos(int numIntegrantes) {
        Scanner sc = new Scanner(System.in);
        List<Integer> votos = new ArrayList<>();

        for (int i = 0; i < numIntegrantes; i++) {
            votos.add(pedirVoto(sc, i + 1));
        }
        return votos;
    }
    private int pedirVoto(Scanner sc, int integrante) {
        int voto = -1;
        while (!fibonacci.contains(voto)) {
            System.out.print("Integrante " + integrante + ", ingrese su voto " + fibonacci + ": ");
            try {
                voto = Integer.parseInt(sc.nextLine());
                if (!fibonacci.contains(voto)) {
                    System.out.println("\nEl valor no es válido.");
                } else {
                    System.out.println("\nValor agregado.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        return voto;
    }
    private void mostrarResumen(Map<String, Integer> estimaciones) {
        System.out.println("\n=== Resumen ===");
        estimaciones.forEach((historia, puntaje) ->
                System.out.println(historia + ": " + puntaje));
    }

}