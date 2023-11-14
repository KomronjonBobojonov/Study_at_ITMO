package moves.status;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class DoubleTeam extends StatusMove {
    public DoubleTeam(){
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPECIAL_ATTACK, +6);
        pokemon.setMod(Stat.HP, +6);
        pokemon.setMod(Stat.DEFENSE, +6);
        pokemon.setMod(Stat.SPEED, +6);
        pokemon.setMod(Stat.SPECIAL_ATTACK, +6);
        pokemon.setMod(Stat.SPECIAL_DEFENSE, +6);
        pokemon.setMod(Stat.ACCURACY, +6);
        pokemon.setMod(Stat.EVASION, +6);
    }
}
