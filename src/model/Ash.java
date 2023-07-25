package model;

import java.util.List;

public class Ash extends Character{
    public Ash(String name, SpecialPower specialPower, List<Pokemon> pokemonList) {
        super(name, specialPower, pokemonList);
    }

    public Ash(String name, SpecialPower specialPower) {
        super(name, specialPower);
    }
}
