import java.util.*;
import java.util.concurrent.*;

class Tournament {
    private final ExecutorService executor = Executors.newFixedThreadPool(8);

    public Player runTournament(List<Player> players) throws InterruptedException, ExecutionException {
        while (players.size() > 1) {
            printRoundTitle(players.size());
            players = playRound(players);
        }

        executor.shutdown();
        System.out.println("🏆 ¡Campeón del torneo: " + players.get(0) + "!");
        return players.get(0);
    }

    private List<Player> playRound(List<Player> players) throws InterruptedException, ExecutionException {
        List<Match> matches = new ArrayList<>();
        List<Future<Player>> futures = new ArrayList<>();

        for (int i = 0; i < players.size(); i += 2) {
            Match match = new Match(players.get(i), players.get(i + 1));
            matches.add(match);
            futures.add(executor.submit(match));
        }

        List<Player> winners = new ArrayList<>();

        for (int i = 0; i < futures.size(); i++) {
            Player winner = futures.get(i).get(); // espera a que termine
            winners.add(winner);
        }

        // Ahora imprimimos los resultados en el orden original
        for (Match match : matches) {
            System.out.println(match.getResultado());
        }

        return winners;
    }

    private void printRoundTitle(int numPlayers) {
        switch (numPlayers) {
            case 16 -> System.out.println("===== OCTAVOS DE FINAL =====");
            case 8 -> System.out.println("===== CUARTOS DE FINAL =====");
            case 4 -> System.out.println("===== SEMIFINAL =====");
            case 2 -> System.out.println("===== FINAL =====");
        }
    }
}
