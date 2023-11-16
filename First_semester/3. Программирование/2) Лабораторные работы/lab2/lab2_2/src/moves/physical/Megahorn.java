package moves.physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class Megahorn extends PhysicalMove {
    public Megahorn(){
        super(Type.BUG,120,85);
    }
    @Override
    protected void applyOppDamage(Pokemon pokemon, double damage){
        pokemon.setMod(Stat.HP, (int) Math.round(damage));
    }
    @Override
    protected String describe() {
        return "use Dazzling Gleam";
    }
}
