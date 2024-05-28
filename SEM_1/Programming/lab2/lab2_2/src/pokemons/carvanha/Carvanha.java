package pokemons.carvanha;

import moves.status.Confide;
import moves.status.DoubleTeam;
import moves.status.ScaryFace;
import pokemons.klefki.Klefki;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Carvanha extends Pokemon {
    public Carvanha(String name, int level){
        super(name, level);

        super.setType(Type.WATER, Type.DARK);

        super.setStats(45, 90, 20, 65,20,65);

        super.setMove(new DoubleTeam(), new Confide(), new ScaryFace());
    }
}
