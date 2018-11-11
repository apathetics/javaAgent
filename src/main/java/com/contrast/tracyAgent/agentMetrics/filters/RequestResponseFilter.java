package com.contrast.tracyAgent.agentMetrics.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class RequestResponseFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger(RequestResponseFilter.class);

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        log.info("Initializing filter: {}", this);
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        log.info("Logging request: {} at {}", req.getMethod(), req.getRequestURI());
        long requestTime = System.nanoTime();

        // In case content-length is not set (for example - chunked encoding),
        // this will cache body content until the copyBodyToResponse method is called to set content-length.
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(res);

        chain.doFilter(req, responseWrapper);

        long responseTime = System.nanoTime();
        responseWrapper.copyBodyToResponse();


        log.info("Logging response body size: {} bytes", responseWrapper.getHeader("Content-Length"));
        log.info("Execution time: {} nanoseconds", responseTime - requestTime);
    }

    @Override
    public void destroy() {
        log.info("Destroying filter.");
    }
}
