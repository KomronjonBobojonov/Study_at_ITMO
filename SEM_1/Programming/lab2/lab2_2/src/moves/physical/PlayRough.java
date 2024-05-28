package moves.physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class PlayRough extends PhysicalMove {

    public PlayRough() {
        super(Type.NORMAL, 90, 90);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPECIAL_ATTACK, -6);
    }

    @Override
    protected String describe() {
        return "use Play Rough";
    }
}
