package com.contrast.tracyAgent.agentMetrics;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MetricDao {

    private ConcurrentHashMap<UUID, Metric> metricMemoryMap = new ConcurrentHashMap<>();

    public void addMetric(Metric metric) {
        metricMemoryMap.put(metric.getId(), metric);
    }

    public ConcurrentHashMap<UUID, Metric> getMetricsMap() {
        return metricMemoryMap;
    }
}
