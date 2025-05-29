import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Player> players = new ArrayList<>();

        // Jugadores del 1 al 16
        List<Player> ascendente = new ArrayList<>();
        List<Player> descendente = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            ascendente.add(new Player(i));
        }
        for (int i = 16; i >= 9; i--) {
            descendente.add(new Player(i));
        }

        // Emparejar jugador 1 vs 16, 2 vs 15, ...
        for (int i = 0; i < 8; i++) {
            players.add(ascendente.get(i));
            players.add(descendente.get(i));
        }

        Tournament tournament = new Tournament();
        tournament.runTournament(players);
    }
}
