package com.contrast.tracyAgent.agentMetrics;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MetricDao {

    private ConcurrentHashMap<UUID, Metric> metricMemoryMap = new ConcurrentHashMap<>();

    private double minRequestTime;
    private double minResponseSize;
    private double maxRequestTime;
    private double maxResponseSize;
    private double averageRequestTime;
    private double averageResponseSize;

    public void addMetric(Metric metric) {
        metricMemoryMap.put(metric.getId(), metric);
        calculateMetrics();
    }

    // We can optimize this further by implementing a min-max heap structure for O(1) min/max access instead of O(n log n).
    private void calculateMetrics() {
        minRequestTime = Collections.min(metricMemoryMap.values(), Comparator.comparing(Metric::getRequestTime)).getRequestTime();
        maxRequestTime = Collections.max(metricMemoryMap.values(), Comparator.comparing(Metric::getRequestTime)).getRequestTime();

        minResponseSize = Collections.min(metricMemoryMap.values(), Comparator.comparing(Metric::getResponseSize)).getResponseSize();
        maxResponseSize = Collections.max(metricMemoryMap.values(), Comparator.comparing(Metric::getResponseSize)).getResponseSize();

        averageRequestTime = metricMemoryMap.values().stream().map(Metric::getRequestTime).reduce(0L, Long::sum).doubleValue() / metricMemoryMap.size();
        averageResponseSize = metricMemoryMap.values().stream().map(Metric::getResponseSize).reduce(0L, Long::sum).doubleValue() / metricMemoryMap.size();
    }

    public ConcurrentHashMap<UUID, Metric> getMetricsMap() {
        return metricMemoryMap;
    }

    public double getMinRequestTime() {
        return minRequestTime;
    }

    public double getMinResponseSize() {
        return minResponseSize;
    }

    public double getMaxRequestTime() {
        return maxRequestTime;
    }

    public double getMaxResponseSize() {
        return maxResponseSize;
    }

    public double getAverageRequestTime() {
        return averageRequestTime;
    }

    public double getAverageResponseSize() {
        return averageResponseSize;
    }
}
