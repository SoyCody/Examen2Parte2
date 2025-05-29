public class Player {
        private final int id;

        public Player(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Jugador " + id;
        }
    }

