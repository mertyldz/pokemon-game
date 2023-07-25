package model;

import java.util.List;

public class Brock extends Character{
    public Brock(String name, SpecialPower specialPower, List<Pokemon> pokemonList) {
        super(name, specialPower, pokemonList);
    }

    public Brock(String name, SpecialPower specialPower) {
        super(name, specialPower);
    }
}
