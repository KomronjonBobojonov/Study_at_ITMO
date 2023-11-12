package moves.physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class NightSlash extends PhysicalMove {
    public NightSlash(){
        super(Type.DARK,70,100);
    }
    @Override
    protected void applyOppDamage(Pokemon pokemon, double damage){
        pokemon.setMod(Stat.HP, (int) Math.round(damage));
    }
}
