package com.contrast.tracyAgent.agentMetrics.Metric;

import java.util.UUID;

public class Metric {

    private UUID id;
    private long requestTime;
    private long responseSize;

    public Metric() {

    }

    public Metric(UUID id, long requestTime, long responseSize) {
        super();
        this.id = id;
        this.requestTime = requestTime;
        this.responseSize = responseSize;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(long responseSize) {
        this.responseSize = responseSize;
    }
}
