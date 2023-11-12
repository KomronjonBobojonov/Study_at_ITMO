package moves.special;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

import javax.swing.plaf.PanelUI;

public class MirrorShot extends SpecialMove {
    public MirrorShot(){
        super(Type.STEEL, 65, 85);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPECIAL_ATTACK, -6);
    }
}
