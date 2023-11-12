package pokemons.Sharpedo;

import moves.physical.NightSlash;
import moves.status.Confide;
import moves.status.DoubleTeam;
import moves.status.ScaryFace;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Sharpedo extends Pokemon {
    public Sharpedo(String name, int level){
        super(name, level);

        super.setType(Type.WATER, Type.DARK);

        super.setStats(70, 120, 40, 95,40,95);

        super.setMove(new DoubleTeam(), new Confide(), new ScaryFace(), new NightSlash());
    }
}
