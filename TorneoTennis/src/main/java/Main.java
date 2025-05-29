import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
        public static void main(String[] args) throws Exception {
            List<Player> players = new ArrayList<>();
            for (int i = 1; i <= 16; i++) {
                players.add(new Player(i));
            }

            Collections.shuffle(players);

            Tournament tournament = new Tournament();
            tournament.runTournament(players);
        }

}
