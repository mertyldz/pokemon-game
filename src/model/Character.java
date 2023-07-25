package model;

import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name;
    private SpecialPower specialPower;
    private List<Pokemon> pokemonList;
    private Pokemon selectedPokemon;

    public Character(String name, SpecialPower specialPower, List<Pokemon> pokemonList) {
        this.name = name;
        this.specialPower = specialPower;
        this.pokemonList = pokemonList;
    }

    public Character(String name, SpecialPower specialPower) {
        this.name = name;
        this.specialPower = specialPower;
        this.pokemonList = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpecialPower getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(SpecialPower specialPower) {
        this.specialPower = specialPower;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public Pokemon getSelectedPokemon() {
        return selectedPokemon;
    }

    public void setSelectedPokemon(Pokemon selectedPokemon) {
        this.selectedPokemon = selectedPokemon;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", specialPower=" + specialPower +
                ", pokemonList=" + pokemonList +
                ", selectedPokemon=" + selectedPokemon +
                '}';
    }
}
