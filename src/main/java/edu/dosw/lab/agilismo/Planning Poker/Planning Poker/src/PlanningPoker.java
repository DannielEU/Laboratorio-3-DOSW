import java.util.*;

public class PlanningPoker {
    public static void main(String[] args) {
        List<String> historias = Arrays.asList(
                "HU1 - Crear cuenta válida",
                "HU2 - Consultar saldo",
                "HU3 - Hacer depósito",
                "HU4 - Validar banco",
                "HU5 - Revisar historial"
        );

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el número de integrantes del equipo: ");
        int numIntegrantes = Integer.parseInt(sc.nextLine());

        VotingStrategy strategy = new ConsensusStrategy();
        PlanningPokerContext contexto = new PlanningPokerContext(strategy, historias);

        contexto.ejecutar(numIntegrantes);
    }
}
