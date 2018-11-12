package com.contrast.tracyAgent.agentMetrics.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/api/games")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @RequestMapping("/api/games/{id}")
    public Game getGame(@PathVariable String id) {
        return gameService.getGame(id);
    }
}
