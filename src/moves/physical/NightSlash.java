package moves.physical;

import ru.ifmo.se.pokemon.*;

public class NightSlash extends PhysicalMove {
    public NightSlash(){
        super(Type.DARK,70,100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect.paralyze(pokemon);
        Status effectedPokemon = pokemon.getCondition();
    }

}
