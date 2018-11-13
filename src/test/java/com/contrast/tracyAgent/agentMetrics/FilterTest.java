package com.contrast.tracyAgent.agentMetrics;

import com.contrast.tracyAgent.agentMetrics.filters.RequestResponseFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterTest {

    @Autowired
    RequestResponseFilter filter = new RequestResponseFilter();

    @Test
    public void testFilter() throws ServletException, IOException {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);

        FilterChain mockFilterChain = mock(FilterChain.class);
        FilterConfig mockFilterConfig = mock(FilterConfig.class);

        when(mockRequest.getRequestURI()).thenReturn("/");
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockResponse.getHeader("Content-Length")).thenReturn("150");

        filter.init(mockFilterConfig);
        filter.doFilter(mockRequest, mockResponse, mockFilterChain);
        filter.destroy();

        verify(mockRequest, times(1)).getRequestURI();
        verify(mockRequest, times(1)).getMethod();
        verify(mockResponse, times(2)).getHeader("Content-Length");
    }
}
