package com.stockApp.detailed.Authentication;

import com.stockApp.detailed.service.CacheService;
import com.stockApp.detailed.service.Impl.RedisCacheService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private CacheService cacheService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        String userId = request.getHeader("userId");

        // Basic validation (Replace this with actual token verification logic)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return false;  // Stop the request from reaching the controller
        }

        // Extract and validate token (Implement actual authentication logic here)
        String token = authHeader.substring(7);
        if (!isValidToken(userId, token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
            return false;
        }

        return true;  // Proceed to the controller
    }

    private boolean isValidToken(String userId, String token) {
        // Dummy validation (Replace with real JWT/Session validation)
        return cacheService.getToken(userId)!= null && cacheService.getToken(userId).equals(token);
    }
}