package com.contrast.tracyAgent.agentMetrics;

import com.contrast.tracyAgent.agentMetrics.Metric.Metric;
import com.contrast.tracyAgent.agentMetrics.Metric.MetricDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MetricDao metricDao;

    @Test
    public void defaultShouldDisplayHomeMetrics() throws Exception {

        // Sample 2 metrics that exist in this dao for user reading.
        // Metric metric1 = new Metric(UUID.randomUUID(), 10, 200);
        // Metric metric2 = new Metric(UUID.randomUUID(), 20, 100);

        when(metricDao.getMinRequestTime()).thenReturn(10.0);
        when(metricDao.getMinResponseSize()).thenReturn(100.0);
        when(metricDao.getMaxRequestTime()).thenReturn(20.0);
        when(metricDao.getMaxResponseSize()).thenReturn(200.0);
        when(metricDao.getAverageRequestTime()).thenReturn(55.0);
        when(metricDao.getAverageResponseSize()).thenReturn(150.0);

         mockMvc.perform(get("/"))
                 .andExpect(status().isOk())
                 .andExpect(view().name("Home"))
                 .andExpect(forwardedUrl("/WEB-INF/jsp/Home.jsp"))
                 .andExpect(model().attribute("minRequestTime", 10.0))
                 .andExpect(model().attribute("minResponseSize", 100.0))
                 .andExpect(model().attribute("maxRequestTime", 20.0))
                 .andExpect(model().attribute("maxResponseSize", 200.0))
                 .andExpect(model().attribute("averageRequestTime", 55.0))
                 .andExpect(model().attribute("averageResponseSize", 150.0));

         verify(metricDao, times(1)).getMinRequestTime();
         verify(metricDao, times(1)).getMinResponseSize();
         verify(metricDao, times(1)).getMaxRequestTime();
         verify(metricDao, times(1)).getMaxResponseSize();
         verify(metricDao, times(1)).getAverageRequestTime();
         verify(metricDao, times(1)).getAverageResponseSize();
    }

    @Test
    public void searchShouldDisplay() throws Exception {
         mockMvc.perform(get("/search"))
                 .andExpect(status().isOk())
                 .andExpect(view().name("Search"))
                 .andExpect(forwardedUrl("/WEB-INF/jsp/Search.jsp"));
    }

    @Test
    public void searchResultsShouldDisplayMetric() throws Exception {
         Metric metric1 = new Metric(UUID.randomUUID(), 10, 200);
         Metric metric2 = new Metric(UUID.randomUUID(), 20, 100);

         ConcurrentHashMap<UUID, Metric> mockMap = new ConcurrentHashMap<>();
         mockMap.put(metric1.getId(), metric1);
         mockMap.put(metric2.getId(), metric2);

         when(metricDao.getMetricsMap()).thenReturn(mockMap);

         UUID searchId = metric1.getId();

         mockMvc.perform(get("/search/result/" + searchId.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("SearchResult"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/SearchResult.jsp"))
                .andExpect(model().attribute("id", metric1.getId()))
                .andExpect(model().attribute("requestTime", (long) 10))
                .andExpect(model().attribute("responseSize", (long) 200));

         verify(metricDao, times(1)).getMetricsMap();
    }



}
