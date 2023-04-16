package com.example.zadanko3;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

@WebFilter(filterName = "countFilter", urlPatterns = {"/*"})
public class CountFilter implements Filter {

    private Map<String, Integer> counterMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        counterMap = new HashMap<>();
        counterMap.put("/Zadanko3_war_exploded/home", 0);
        counterMap.put("/Zadanko3_war_exploded/about", 0);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        if(!requestURI.equals("/Zadanko3_war_exploded/")){
            Integer cnt = counterMap.get(requestURI);
            if (cnt == null) {
                cnt = 1;
            } else {
                cnt++;
            }
            counterMap.put(requestURI, cnt);

            System.out.println("filter " + requestURI + " " + cnt);

            // Sortuj mapę na podstawie wartości cnt w kolejności malejącej
            counterMap = counterMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

            httpRequest.setAttribute("counterMap", counterMap);

            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            httpRequest.setAttribute("counterMap", counterMap);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


    @Override
    public void destroy() {
        counterMap = null;
    }

    public Map<String, Integer> getCounterMap() {
        return counterMap;
    }
}