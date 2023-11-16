package moves.status;

import ru.ifmo.se.pokemon.*;

public class FoulPlay extends StatusMove {
    public FoulPlay(){
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPECIAL_ATTACK, -4);
    }

    @Override
    protected String describe() {
        return "use Foul Play";
    }
}
