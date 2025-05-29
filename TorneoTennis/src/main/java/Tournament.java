import java.util.*;
import java.util.concurrent.*;

class Tournament {
    private final ExecutorService executor = Executors.newFixedThreadPool(8);

    public Player runTournament(List<Player> players) throws InterruptedException, ExecutionException {
        int round = 1;

        while (players.size() > 1) {
            printRoundTitle(players.size());
            players = playRound(players);
            round++;
        }

        executor.shutdown();
        System.out.println("🏆 ¡Campeón del torneo: " + players.get(0) + "!");
        return players.get(0);
    }

    private List<Player> playRound(List<Player> players) throws InterruptedException, ExecutionException {
        List<Future<Player>> futures = new ArrayList<>();

        for (int i = 0; i < players.size(); i += 2) {
            Match match = new Match(players.get(i), players.get(i + 1));
            futures.add(executor.submit(match));
        }

        List<Player> winners = new ArrayList<>();
        for (Future<Player> future : futures) {
            winners.add(future.get());
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
