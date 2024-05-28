package pokemons.Infernape;

import moves.physical.AerialAce;
import moves.status.DoubleTeam;
import moves.status.SlackOff;
import pokemons.Monferno.Monferno;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Infernape extends Monferno {
    public Infernape(String name, int level){
        super(name, level);

        super.setType(Type.WATER, Type.DARK);

        super.setStats(76, 104, 71, 104,71,108);

        super.addMove(new AerialAce());
    }
}
