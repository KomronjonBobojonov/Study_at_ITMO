package pokemons.Chimchar;

import moves.physical.AerialAce;
import moves.status.DoubleTeam;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Chimchar extends Pokemon {
    public Chimchar(String name, int level){
        super(name, level);

        super.setType(Type.FIRE);
        super.setStats(44, 58, 44, 58,44,61);


        super.setMove(new AerialAce(), new DoubleTeam());
    }
}
