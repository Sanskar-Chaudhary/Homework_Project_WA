package ee.mainor.game.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class GameService {
    private final Map<String, Integer> games = new HashMap<>();
    private final Map<String, Integer> guessesMap = new HashMap<>();

    public String createGame() {
        String gameId = generateGameId();
        int generatedNumber = generateNumber();
        games.put(gameId, generatedNumber);
        guessesMap.put(gameId, 0); // Initialize guesses for the game
        return gameId;
    }

    public String guessNumber(String gameId, int number) {
        if (!games.containsKey(gameId)) {
            return "Game not found";
        }

        int generatedNumber = games.get(gameId);
        int guesses = guessesMap.get(gameId) + 1; // Increment guesses for the game
        guessesMap.put(gameId, guesses); // Update guesses for the game

        if (number < generatedNumber) {
            return "Nr is bigger";
        } else if (number > generatedNumber) {
            return "Nr is smaller";
        } else {
            games.remove(gameId);
            return "Correct, it took you " + guesses + " times";
        }
    }

    private String generateGameId() {
        return String.valueOf(new Random().nextInt(9000) + 1000);
    }

    private int generateNumber() {
        return new Random().nextInt(100) + 1;
    }
}
