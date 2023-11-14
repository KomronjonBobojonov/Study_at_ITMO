package pokemons.Monferno;

import moves.physical.AerialAce;
import moves.status.DoubleTeam;
import moves.status.SlackOff;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Monferno extends Pokemon{
    public Monferno(String name, int level){
        super(name, level);

        super.setType(Type.FIRE, Type.FIGHTING);

        super.setStats(64, 78, 52, 78,52,81);

        super.setMove(new AerialAce(), new DoubleTeam(), new SlackOff());
    }
}
