package service;

import model.Character;
import model.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class CharacterService {

    public void printCharacters(ArrayList<Character> characterArrayList) {
        System.out.println("#### Available Characters ####");
        for (int i = 0; i < characterArrayList.size(); i++) {
            System.out.println((i + 1) + " - " + "Character Name: " + characterArrayList.get(i).getName() +
                    ", Special Power: " + characterArrayList.get(i).getSpecialPower().getName());
        }
    }

    public Character getCharacterById(ArrayList<Character> characterList, int id) {
        Character character = characterList.get(id);
        characterList.remove(id);
        return character;
    }

    public void updateSelectedPokemonForCharacter(Character character, int id) {
        if (character.getPokemonList() != null) {
            character.setSelectedPokemon(character.getPokemonList().get(id));
        }
    }

    public void changeSelectedPokemon(Player player) {
        Scanner scanner = new Scanner(System.in);
        if (player.getCharacter().getPokemonList().size() > 1) {
            System.out.println("Pokemons that you can select: ");
            for (int i = 0; i < player.getCharacter().getPokemonList().size(); i++) {
                System.out.println(i + 1 + " - " + player.getCharacter().getPokemonList().get(i));
            }
            System.out.print("Choice: ");
            int selectedPokemonId = scanner.nextInt() - 1;
            player.getCharacter().setSelectedPokemon(player.getCharacter().getPokemonList().get(selectedPokemonId));
            System.out.println("New pokemon for " + player.getName() + " is " + player.getCharacter().getSelectedPokemon());
        } else {
            player.getCharacter().setSelectedPokemon(player.getCharacter().getPokemonList().get(0));
        }
    }

}
