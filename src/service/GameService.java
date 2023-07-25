package service;

import model.Player;
import model.Pokemon;
import model.WeatherType;

import java.util.ArrayList;
import java.util.Scanner;

public class GameService {

    public void attack(Player attacker, Player defender, boolean isPokeSpecialAttack, boolean isCharacterSpecialAttack) {
        int weatherNumber = (int) (Math.random() * 6);
        WeatherType activeWeather = null;
        boolean specialAttack = false;

        // Change the weather before every attack.
        for (WeatherType weatherType : WeatherType.values()) {
            if (weatherType.getWeatherNumber() == weatherNumber) {
                activeWeather = weatherType;
            }
        }
        System.out.println("#### Weather is " + activeWeather + " ####");

        if (isPokeSpecialAttack && isCharacterSpecialAttack) {
            specialAttack = attacker.getCharacter().getSelectedPokemon().getSpecialPower().getRemainRight() > 0 &&
                    attacker.getCharacter().getSpecialPower().getRemainRight() > 0;
        } else if (isPokeSpecialAttack) {
            specialAttack = attacker.getCharacter().getSelectedPokemon().getSpecialPower().getRemainRight() > 0;
        } else if (isCharacterSpecialAttack) {
            specialAttack = attacker.getCharacter().getSpecialPower().getRemainRight() > 0;
        }

        int damage = 0;
        if (specialAttack) {
            if (isPokeSpecialAttack && isCharacterSpecialAttack) {
                damage += attacker.getCharacter().getSelectedPokemon().specialAttack();
                damage += attacker.getCharacter().getSpecialPower().getDamage();
                attacker.getCharacter().getSpecialPower().setRemainRight(attacker.getCharacter().getSpecialPower().
                        getRemainRight() - 1);
            } else if (isPokeSpecialAttack) {
                damage += attacker.getCharacter().getSelectedPokemon().specialAttack();
            } else {
                damage = attacker.getCharacter().getSelectedPokemon().getDamage() + attacker.getCharacter().getSpecialPower().getDamage();
                attacker.getCharacter().getSpecialPower().setRemainRight(attacker.getCharacter().getSpecialPower().
                        getRemainRight() - 1);
            }
        } else {
            if (isPokeSpecialAttack || isCharacterSpecialAttack) {
                damage = 0;
            } else {
                damage = attacker.getCharacter().getSelectedPokemon().getDamage();
            }
        }

        if (activeWeather != null) {
            if (activeWeather.equals(attacker.getCharacter().getSelectedPokemon().getWeakWeather())) {
                damage -= (int) (damage * 20 / 100);
            }
        }

        defender.getCharacter().getSelectedPokemon().setHealth((
                defender.getCharacter().getSelectedPokemon().getHealth()) - damage);

    }

    public boolean healthCheck(Player defender) {
        Pokemon defenderPokemon = defender.getCharacter().getSelectedPokemon();

        if (defenderPokemon.getHealth() >= 0) {
            System.out.println("Health of " + defenderPokemon.getName() + " belongs to " + defender.getName() +
                    " is " + defenderPokemon.getHealth());
            System.out.println("Game continues...\n");
            return true;
        } else {
            System.out.println("Health of " + defenderPokemon.getName() + " belongs to " + defender.getName() +
                    " is dead!");
            System.out.println(defender.getName() + " has lost the round!\n");
            return false;
        }
    }

    public void startFight(Player player1, Player player2) {
        CharacterService characterService = new CharacterService();
        PlayerService playerService = new PlayerService();

        // Use first pokemon at the start
        characterService.updateSelectedPokemonForCharacter(player1.getCharacter(), 0);
        characterService.updateSelectedPokemonForCharacter(player2.getCharacter(), 0);

        Scanner scanner = new Scanner(System.in);
        GameService gameService = new GameService();
        int whoStarts = (int) (Math.random() * 100);
        // whoStarts will generate a number between 0-99. 0-49 for player1 start. 50-99 for player2 start.
        if (whoStarts > 49) {
            Player tempPlayer = player1;
            player1 = player2;
            player2 = tempPlayer;

            // In order to make GarbageCollector destroy this.
            tempPlayer = null;
        }
        System.out.println("-----Round 1-----");
        System.out.println(player1.getName() + " will be the first attacker!");
        fightUntilDie(player1, player2);
        obtainPokemon(player1, player2);
        System.out.println("-----Round 2-----");
        characterService.changeSelectedPokemon(player1);
        characterService.changeSelectedPokemon(player2);
        fightUntilDie(player1, player2);

        playerService.checkWinner(player1);
        playerService.checkWinner(player2);
    }

    public void fightUntilDie(Player player1, Player player2) {
        boolean checkFirstPlayer = healthCheck(player1);
        boolean checkSecondPlayer = healthCheck(player2);

        while (checkFirstPlayer && checkSecondPlayer) {

            System.out.println("--- " + player1.getName() + "'s turn to attack ---");
            boolean isFirstPokemonSpecial = isPokemonSpecialAttack();
            boolean isFirstCharacterSpecial = isCharacterSpecialAttack();
            attack(player1, player2, isFirstPokemonSpecial, isFirstCharacterSpecial);
            checkSecondPlayer = healthCheck(player2);

            if (!checkSecondPlayer) {
                break;
            }

            System.out.println("--- " + player2.getName() + "'s turn to attack ---");
            boolean isSecondPokemonSpecial = isPokemonSpecialAttack();
            boolean isSecondCharacterSpecial = isCharacterSpecialAttack();
            attack(player2, player1, isSecondPokemonSpecial, isSecondCharacterSpecial);
            checkFirstPlayer = healthCheck(player1);

            if (!checkFirstPlayer) {
                break;
            }
        }
    }

    public void obtainPokemon(Player player1, Player player2) {
        // Obtain loser's pokemon and and weakest pokemon.
        PlayerService playerService = new PlayerService();
        PokemonService pokemonService = new PokemonService();
        LoadService loadService = new LoadService();
        ArrayList<Pokemon> pokemons = loadService.loadPokemon();
        pokemonService.removeFromListByPokemon(pokemons, player1.getCharacter().getPokemonList().get(0));
        pokemonService.removeFromListByPokemon(pokemons, player2.getCharacter().getPokemonList().get(0));
        if (player1.getCharacter().getPokemonList().get(0).getHealth() <= 0) {
            player1.getCharacter().getPokemonList().get(0).setHealth(100);
            player2.getCharacter().getPokemonList().add(player1.getCharacter().getPokemonList().get(0));
            player1.getCharacter().getPokemonList().remove(0);
            playerService.addWeakestPokemonToPlayer(player1, pokemons);
        } else {
            player2.getCharacter().getPokemonList().get(0).setHealth(100);
            player1.getCharacter().getPokemonList().add(player2.getCharacter().getPokemonList().get(0));
            player2.getCharacter().getPokemonList().remove(0);
            playerService.addWeakestPokemonToPlayer(player2, pokemons);
        }
    }

    public boolean isPokemonSpecialAttack() {
        Scanner scanner = new Scanner(System.in);
        boolean pokemonSpecialAttack;
        System.out.print("Do you want to use special attack for Pokemon?(y/n)");
        String firstPlayerPokemonSpecialAttack = scanner.nextLine().toLowerCase();

        pokemonSpecialAttack = firstPlayerPokemonSpecialAttack.equals("y");
        return pokemonSpecialAttack;
    }

    public boolean isCharacterSpecialAttack() {
        Scanner scanner = new Scanner(System.in);
        boolean characterSpecialAttack;
        System.out.print("Do you want to use special attack for Character?(y/n)");
        String firstPlayerCharacterSpecialAttack = scanner.nextLine().toLowerCase();

        characterSpecialAttack = firstPlayerCharacterSpecialAttack.equals("y");
        return characterSpecialAttack;
    }

}
