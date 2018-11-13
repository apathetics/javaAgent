package com.contrast.tracyAgent.agentMetrics.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/games")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @RequestMapping("/games/{id}")
    public Game getGame(@PathVariable String id) {
        return gameService.getGame(id);
    }
}
