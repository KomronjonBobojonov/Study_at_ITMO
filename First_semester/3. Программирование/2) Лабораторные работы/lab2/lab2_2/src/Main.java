import pokemons.klefki.Klefki;
import ru.ifmo.se.pokemon.Battle;

public class Main {

    public static void main(String[] args) {
        Battle battle = new Battle();

        battle.addAlly(new Klefki("Ilhom", 12));

        battle.addFoe(new Klefki("Komron", 12));

        battle.go();
    }
}