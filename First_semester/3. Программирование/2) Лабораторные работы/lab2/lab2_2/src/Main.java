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

        Sharpedo Sharp = new Sharpedo("Sharp");


        battle.addAlly(new Klefki("11111", 8));
        battle.addAlly(new Carvanha("11111", 4));
        battle.addAlly(new Sharpedo("11111", 9));

        battle.addFoe(new Chimchar("22222", 3));
        battle.addFoe(new Monferno("22222", 7));
        battle.addFoe(new Infernape("22222", 14));

        battle.go();
    }
}