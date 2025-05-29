import java.util.Random;
import java.util.concurrent.Callable;

class Match implements Callable<Player> {
    private final Player player1;
    private final Player player2;
    private final Random random = new Random();
    private final StringBuilder resultado = new StringBuilder();

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public Player call() throws Exception {
        int setsPlayer1 = 0, setsPlayer2 = 0;
        int setNumber = 1;

        resultado.append(player1).append(" vs ").append(player2).append("\n");

        while (setsPlayer1 < 2 && setsPlayer2 < 2) {
            boolean ganaP1 = random.nextBoolean();
            if (ganaP1) {
                setsPlayer1++;
                resultado.append("Set ").append(setNumber).append(": ").append(player1).append("\n");
            } else {
                setsPlayer2++;
                resultado.append("Set ").append(setNumber).append(": ").append(player2).append("\n");
            }
            setNumber++;
        }

        Player winner = setsPlayer1 == 2 ? player1 : player2;
        resultado.append("Ganador del partido: ").append(winner).append("\n");

        double duration = 1.5 + (0.5 * random.nextDouble());
        Thread.sleep((long) (duration * 1000));

        return winner;
    }

    public String getResultado() {
        return resultado.toString();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
