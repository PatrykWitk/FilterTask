package com.example.zadanko3;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void destroy() {
    }
}