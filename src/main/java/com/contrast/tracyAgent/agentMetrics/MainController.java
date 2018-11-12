package com.contrast.tracyAgent.agentMetrics;

import com.contrast.tracyAgent.agentMetrics.Metric.MetricDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

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

    @RequestMapping("/games")
    public String Games(Map<String, Object> model) {
        model.put("metrics", metricDao);
        return "Games";
    }
}
