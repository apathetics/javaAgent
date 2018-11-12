package com.contrast.tracyAgent.agentMetrics;

import com.contrast.tracyAgent.agentMetrics.Metric.MetricDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    MetricDao metricDao;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @RequestMapping("/")
    public String Home(Map<String, Object> model) {
        model.put("metrics", metricDao);
        return "Home";
    }

    @RequestMapping("/search")
    public String Search(Map<String, Object> model) {
        model.put("metrics", metricDao.getMetricsMap());
        return "Search";
    }

    @RequestMapping("/search/result/{id}")
    public String SearchResult(Map<String, Object> model, @PathVariable String id) {
        model.put("metrics", metricDao.getMetricsMap().get(UUID.fromString(id)));
        return "SearchResult";
    }
}
