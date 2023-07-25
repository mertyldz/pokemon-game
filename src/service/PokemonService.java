package service;

import model.Player;
import model.Pokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class PokemonService {

    public void printPokemons(ArrayList<Pokemon> pokemonList){
        System.out.println("---Pokemons---");
        for (int i = 0; i<pokemonList.size(); i++) {
            System.out.println(i+1 + " - " + pokemonList.get(i));
        }
    }

    public Pokemon getPokemonById(ArrayList<Pokemon> pokemonList, int id){
        Pokemon pokemon = pokemonList.get(id);
        pokemonList.remove(id);
        return pokemon;
    }

    public void addPokemonToPlayer(Player player, ArrayList<Pokemon> pokemonList){
        PokemonService pokemonService = new PokemonService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hey " + player.getName() + " pick a pokemon to play with!\n");
        System.out.println("#### Available Pokemons ####");
        pokemonService.printPokemons(pokemonList);
        System.out.print("Choice: ");
        int firstPokemonChoice = scanner.nextInt()-1;
        Pokemon pokemon = pokemonService.getPokemonById(pokemonList,firstPokemonChoice);
        System.out.println(player.getName() + " picked " + pokemon);
        player.getCharacter().getPokemonList().add(pokemon);
    }

    public void removeFromListByPokemon(ArrayList<Pokemon> pokemonList, Pokemon pokemon){
        for(int i = 0; i<pokemonList.size(); i++){
            if(pokemonList.get(i).getName().equals(pokemon.getName())){
                pokemonList.remove(i);
            }
        }
    }


}
