package com.contrast.tracyAgent.agentMetrics.Game;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GameService {

    // Sample game API data, so users don't have to input data due to in-memory data storage
    private List<Game> gamesList = Arrays.asList(
            new Game("DS3", "Dark Souls 3", "A brief exercise in the unforgiving nature of life."),
            new Game("CS", "Counter-Strike 1.6", "A nostalgic remembrance of the good old days."),
            new Game( "LOL", "League of Legends", "A never-ending time sink full of toxicity and friendships.")
    );

    // Focusing on the metric collection, so only implemented the READs instead of all CRUD operations.
    public List<Game> getAllGames() {
        return gamesList;
    }

    public Game getGame(String id) {
        return gamesList.stream().filter(game -> game.getId().equals(id)).findFirst().get();
    }
}
