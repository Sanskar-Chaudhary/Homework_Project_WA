package ee.mainor.game.web;

import ee.mainor.game.dto.GuessResult;
import ee.mainor.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public String createGame() {
        return gameService.createGame();
    }

    @GetMapping("/{gameId}/guess/{number}")
    public GuessResult guessNumber(@PathVariable String gameId, @PathVariable int number) {
        String result = gameService.guessNumber(gameId, number);
        return new GuessResult(result);
    }
}
