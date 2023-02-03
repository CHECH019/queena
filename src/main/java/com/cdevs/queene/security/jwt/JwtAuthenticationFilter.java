package com.cdevs.queene.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cdevs.queene.exceptions.NoTokenProvidedException;
import com.cdevs.queene.utils.global.Constants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                HttpServletResponse response, FilterChain filterChain)
                     throws ServletException, IOException {
        List<String> authenticationNoRequiredPatterns = Arrays.asList("/login","/signup");
        String requestURI = request.getServletPath();
        requestURI = requestURI.replaceAll(Constants.BASE_URL, "");
        if(authenticationNoRequiredPatterns.contains(requestURI)){
            filterChain.doFilter(request, response);
            return;
        }
        
        final String jwt = Optional.ofNullable(request.getHeader("Authorization"))
                                .filter(t-> t.startsWith("Bearer"))
                                .map(t -> t.substring(7))
                                .orElseThrow(()->
                                            new NoTokenProvidedException("Authorization header is missing or invalid"));
        final String userEmail = JwtService.extractUsername(jwt);

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                
                if(JwtService.isTokenValid(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(
                            userDetails, 
                            null, 
                            userDetails.getAuthorities()
                        );

                    authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
        }

        filterChain.doFilter(request, response);
    }
    
}