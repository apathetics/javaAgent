package com.contrast.tracyAgent.agentMetrics;

import com.contrast.tracyAgent.agentMetrics.Metric.Metric;
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

    @RequestMapping("/")
    public String Home(Map<String, Object> model) {
        model.put("minRequestTime", metricDao.getMinRequestTime());
        model.put("minResponseSize", metricDao.getMinResponseSize());
        model.put("maxRequestTime", metricDao.getMaxRequestTime());
        model.put("maxResponseSize", metricDao.getMaxResponseSize());
        model.put("averageRequestTime", metricDao.getAverageRequestTime());
        model.put("averageResponseSize", metricDao.getAverageResponseSize());
        return "Home";
    }

    @RequestMapping("/search")
    public String Search() {
        return "Search";
    }

    @RequestMapping("/search/result/{id}")
    public String SearchResult(Map<String, Object> model, @PathVariable String id) {
        Metric selectedMetric = metricDao.getMetricsMap().get(UUID.fromString(id));
        model.put("id", selectedMetric.getId());
        model.put("requestTime", selectedMetric.getRequestTime());
        model.put("responseSize", selectedMetric.getResponseSize());
        return "SearchResult";
    }
}
