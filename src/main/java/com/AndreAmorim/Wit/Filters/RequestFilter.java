package com.AndreAmorim.Wit.Filters;

import jakarta.servlet.Filter;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestFilter implements Filter {
    private static final String REQUEST_ID_HEADER = "RequestID"; //

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Generate a request ID
        String requestId = UUID.randomUUID().toString();

        // Store the request ID in MDC
        MDC.put("requestId", requestId);

        // Add the ID to the response headers
        httpResponse.setHeader(REQUEST_ID_HEADER, requestId);

        chain.doFilter(request, response);

        // Clean up the MDC
        MDC.remove("requestId");
    }

}
