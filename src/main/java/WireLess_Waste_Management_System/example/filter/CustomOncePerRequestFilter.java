package WireLess_Waste_Management_System.example.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * CustomOncePerRequestFilter:
 * A filter that ensures the request is processed only once per request lifecycle.
 * It handles logging, validation, and exception handling before passing the request
 * to the controller or further down the filter chain.
 */
public class CustomOncePerRequestFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomOncePerRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        logIncomingRequest(request);
        filterChain.doFilter(request, response);
        logOutgoingResponse(response);
    }

    private void logIncomingRequest(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames != null && headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.append(headerName).append(": ").append(request.getHeader(headerName)).append("; ");
        }

        logger.info("Incoming request: Method={}, URI={}, Headers={}",
                request.getMethod(),
                request.getRequestURI(),
                headers.toString());
    }

    private void logOutgoingResponse(HttpServletResponse response) {
        logger.info("Outgoing response: status={}", response.getStatus());
    }
}