package pokemons.klefki;

// https://pokemondb.net/pokedex/klefki

import moves.physical.Megahorn;
import moves.physical.PlayRough;
import moves.special.MirrorShot;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Klefki extends Pokemon {
    public Klefki(String name, int level) {
        super(name, level);
        // Stats
        super.setType(Type.STEEL, Type.FAIRY);

        super.setStats(57, 80, 91, 80, 87, 75);

        // Moves
        super.setMove(new PlayRough(), new MirrorShot(), new Megahorn());
    }
}
