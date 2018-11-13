package com.contrast.tracyAgent.agentMetrics;

import com.contrast.tracyAgent.agentMetrics.Metric.Metric;
import com.contrast.tracyAgent.agentMetrics.Metric.MetricDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@WebMvcTest(MetricDao.class)
public class MetricDaoTest {

    @Autowired
    private MetricDao metricDao;

    // Validation of the calculations for min/max/averages and any Metric logic here.
    @Test
    public void givenMetrics_whenAddMetrics_thenCalculateMetrics() {
        Metric metric1 = new Metric(UUID.randomUUID(), 1000, 5000);
        Metric metric2 = new Metric(UUID.randomUUID(), 3000, 10000);

        metricDao.addMetric(metric1);
        metricDao.addMetric(metric2);

        ConcurrentHashMap<UUID, Metric> metricMemoryMap = metricDao.getMetricsMap();
        Assert.assertThat(2, is(metricMemoryMap.size()));
        Assert.assertThat(1000.0, is(metricDao.getMinRequestTime()));
        Assert.assertThat(3000.0, is(metricDao.getMaxRequestTime()));
        Assert.assertThat(5000.0, is(metricDao.getMinResponseSize()));
        Assert.assertThat(10000.0, is(metricDao.getMaxResponseSize()));
        Assert.assertThat(2000.0, is(metricDao.getAverageRequestTime()));
        Assert.assertThat(7500.0, is(metricDao.getAverageResponseSize()));
    }
}
