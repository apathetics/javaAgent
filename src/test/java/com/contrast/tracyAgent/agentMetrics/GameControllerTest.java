package com.contrast.tracyAgent.agentMetrics;

import com.contrast.tracyAgent.agentMetrics.Game.Game;
import com.contrast.tracyAgent.agentMetrics.Game.GameController;
import com.contrast.tracyAgent.agentMetrics.Game.GameService;
import com.contrast.tracyAgent.agentMetrics.Metric.MetricDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MetricDao metricDao;

    @MockBean
    private GameService gameService;

    @Test
    public void givenGames_whenGetGames_thenReturnJsonArray() throws Exception {

        Game botw = new Game("BOTW", "Breath of the Wild", "A young boy wakes up from a very long slumber to murder bokoblins.");
        Game smo = new Game("SMO", "Super Mario Odyssey", "Everyone's favorite Italian plumber does a lot of hat tricks to impress a princess.");
        Game rs = new Game("RS", "Runescape", "A child's first foray into learning basic economics and how to deal with scammers.");

        List<Game> allGames = Arrays.asList(botw, smo, rs);

        given(gameService.getAllGames()).willReturn(allGames);

        mvc.perform(get("/api/games")
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(3)))
           .andExpect(jsonPath("$[0].name", is(botw.getName())))
           .andExpect(jsonPath("$[1].id", is(smo.getId())))
           .andExpect(jsonPath("$[2].description", is(rs.getDescription())));
    }

    @Test
    public void givenGameId_whenGetGameId_thenReturnJson() throws Exception {
        Game botw = new Game("BOTW", "Breath of the Wild", "A young boy wakes up from a very long slumber to murder bokoblins.");
        Game smo = new Game("SMO", "Super Mario Odyssey", "Everyone's favorite Italian plumber does a lot of hat tricks to impress a princess.");
        Game rs = new Game("RS", "Runescape", "A child's first foray into learning basic economics and how to deal with scammers.");

        List<Game> allGames = Arrays.asList(botw, smo, rs);

        given(gameService.getGame("BOTW")).willReturn(botw);
        given(gameService.getGame("SMO")).willReturn(smo);
        given(gameService.getGame("RS")).willReturn(rs);

        mvc.perform(get("/api/games/SMO")
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id", is(smo.getId())))
           .andExpect(jsonPath("$.name", is(smo.getName())))
           .andExpect(jsonPath("$.description", is(smo.getDescription())));
    }
}
