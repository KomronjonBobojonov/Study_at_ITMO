import pokemons.Chimchar.Chimchar;
import pokemons.Infernape.Infernape;
import pokemons.Monferno.Monferno;
import pokemons.Sharpedo.Sharpedo;
import pokemons.carvanha.Carvanha;
import pokemons.klefki.Klefki;
import ru.ifmo.se.pokemon.Battle;

public class Main {

    public static void main(String[] args) {
        Battle battle = new Battle();

        battle.addAlly(new Klefki("Klefki", 8));
        battle.addAlly(new Carvanha("Carvanha", 4));
        battle.addAlly(new Sharpedo("Sharpedo", 9));

        battle.addFoe(new Chimchar("Chimchar", 3));
        battle.addFoe(new Monferno("Monferno", 7));
        battle.addFoe(new Infernape("Infernape", 14));

        battle.go();
    }
}