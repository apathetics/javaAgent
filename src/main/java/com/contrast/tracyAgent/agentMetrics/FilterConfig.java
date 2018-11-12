package com.contrast.tracyAgent.agentMetrics;

import com.contrast.tracyAgent.agentMetrics.filters.RequestResponseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    //If we want to match URL patterns, then we must annotate this with @Bean and remove the @Component on the filter.
    public FilterRegistrationBean<RequestResponseFilter> loggingFilter() {
        FilterRegistrationBean<RequestResponseFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestResponseFilter());
        registrationBean.addUrlPatterns("/games/*");
        return registrationBean;
    }
}
