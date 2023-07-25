package service;

import model.*;
import model.Character;

import java.util.ArrayList;
import java.util.Scanner;

public class LoadService {

    public void load() {
        Scanner scanner = new Scanner(System.in);

        PlayerService playerService = new PlayerService();
        GameService gameService = new GameService();
        PokemonService pokemonService = new PokemonService();

        // Import pokemon and character lists
        ArrayList<Character> characterList = loadCharacters();
        ArrayList<Pokemon> pokemonList = loadPokemon();


        Player firstPlayer = playerService.generatePlayer(characterList);
        Player secondPlayer = playerService.generatePlayer(characterList);

        System.out.println(firstPlayer);
        System.out.println(secondPlayer);

        pokemonService.addPokemonToPlayer(firstPlayer, pokemonList);
        pokemonService.addPokemonToPlayer(secondPlayer, pokemonList);

        System.out.println(firstPlayer);
        System.out.println(secondPlayer);

        System.out.println("1 - Start\n2 - Exit");
        int startOption = scanner.nextInt();
        if(startOption == 1){
            System.out.println("The war has begun!\n");
            gameService.startFight(firstPlayer, secondPlayer);
        } else {
            System.out.println("Please wait while game is closing...");
        }


    }

    public ArrayList<Character> loadCharacters() {

        SpecialPower strategy1 = new Strategy("Strategy", 7, 1);
        SpecialPower strategy2 = new Strategy("Strategy II", 4, 1);
        SpecialPower strategy3 = new Strategy("Strategy III", 6, 1);

        Character ash = new Ash("Ash", strategy1);
        Character brock = new Brock("Brock", strategy2);
        Character misty = new Misty("Misty", strategy3);

        ArrayList<Character> characterList = new ArrayList<>();
        characterList.add(ash);
        characterList.add(brock);
        characterList.add(misty);

        return characterList;
    }

    public ArrayList<Pokemon> loadPokemon() {
        ArrayList<SpecialPower> specialPowerList = loadSpecialPowers();

        Pokemon pikachu = new Pikachu("Pikachu", 100, 18,
                ElementType.ELECTRIC, specialPowerList.get(0), WeatherType.STORMY);

        Pokemon squirtle = new Squirtle("Squirtle", 100, 14,
                ElementType.WATER, specialPowerList.get(1), WeatherType.SUNNY);

        Pokemon charmender = new Charmender("Charmender", 100, 16,
                ElementType.FIRE, specialPowerList.get(2), WeatherType.RAINY);

        Pokemon balbazar = new Balbazar("Balbazar", 100, 20,
                ElementType.GRASS, specialPowerList.get(3), WeatherType.WINDY);

        ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();
        pokemonArrayList.add(pikachu);
        pokemonArrayList.add(squirtle);
        pokemonArrayList.add(charmender);
        pokemonArrayList.add(balbazar);

        return pokemonArrayList;
    }

    public ArrayList<SpecialPower> loadSpecialPowers() {
        SpecialPower electricity = new Electricity("Lightning Rod", 3, 1);
        SpecialPower water = new Water("Aqua Tail", 1, 1);
        SpecialPower fire = new Fire("Flamethrower", 2, 1);
        SpecialPower grass = new Grass("Chlorophyll", 4, 1);

        ArrayList<SpecialPower> specialPowerArrayList = new ArrayList<>();
        specialPowerArrayList.add(electricity);
        specialPowerArrayList.add(water);
        specialPowerArrayList.add(fire);
        specialPowerArrayList.add(grass);

        return specialPowerArrayList;
    }
}
