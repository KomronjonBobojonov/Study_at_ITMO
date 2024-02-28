package pokemons.Sharpedo;

import moves.physical.NightSlash;
import moves.status.Confide;
import moves.status.DoubleTeam;
import moves.status.ScaryFace;
import pokemons.carvanha.Carvanha;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Sharpedo extends Carvanha {
    public Sharpedo(String name, int level){
        super(name, level);

        super.setType(Type.WATER, Type.DARK);

        super.setStats(70, 120, 40, 95,40,95);

        super.addMove(new NightSlash());
    }
}
