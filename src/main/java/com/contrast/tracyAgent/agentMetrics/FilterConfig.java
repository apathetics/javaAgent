package com.contrast.tracyAgent.agentMetrics;

import com.contrast.tracyAgent.agentMetrics.filters.RequestResponseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    public FilterRegistrationBean<RequestResponseFilter> loggingFilter() {
        FilterRegistrationBean<RequestResponseFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestResponseFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
