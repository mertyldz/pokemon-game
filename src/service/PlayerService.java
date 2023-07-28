package service;

import model.Character;
import model.Player;
import model.Pokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerService {

    public ArrayList<Player> loadPlayers() {
        LoadService loadService = new LoadService();
        ArrayList<Character> characterList = loadService.loadCharacters();
        Player player1 = createPlayer("Player1", characterList.get(0));
        Player player2 = createPlayer("Player2", characterList.get(1));

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        return playerList;
    }

    public Player createPlayer(String name, Character character) {
        return new Player(name, character);
    }

    public Player generatePlayer(ArrayList<Character> characterList) {
        CharacterService characterService = new CharacterService();
        PlayerService playerService = new PlayerService();
        Scanner scanner = new Scanner(System.in);

        // Generate character for player

        System.out.print("Name of player: ");
        String playerName = scanner.nextLine();

        System.out.println("Hey " + playerName + " pick a character to play with!\n");
        characterService.printCharacters(characterList);
        System.out.print("Choice: ");
        int firstPlayerCharacterId = scanner.nextInt() - 1;

        // Add character to  player, remove selected character from character list
        Character firstPlayerCharacter = characterService.getCharacterById(characterList, firstPlayerCharacterId);
        System.out.println(playerName + " picked " + firstPlayerCharacter.getName());

        return playerService.createPlayer(playerName, firstPlayerCharacter);
    }
    public void checkWinner(Player player){
        if(player.getCharacter().getSelectedPokemon().getHealth() > 0){
            player.setWinner(true);
            System.out.println(player.getName() + " has won the game!");
        }
    }

    public void addWeakestPokemonToPlayer(Player player, ArrayList<Pokemon> pokemonList){
        Pokemon selectedPokemon = pokemonList.get(0);
        for(Pokemon pokemon:pokemonList){
            if(pokemon.getDamage() < selectedPokemon.getDamage()){
                selectedPokemon = pokemon;
            }
        }
        player.getCharacter().getPokemonList().add(selectedPokemon);
        player.getCharacter().setSelectedPokemon(selectedPokemon);
        System.out.println("New pokemon for " + player.getName() + " is " + selectedPokemon);
    }
}
