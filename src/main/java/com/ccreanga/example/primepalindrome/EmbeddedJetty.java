package com.ccreanga.example.primepalindrome;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class EmbeddedJetty {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.setContextPath("/hello");

        server.setHandler(context);


        context.addServlet(new ServletHolder(new PalindromeEchoServlet()), "/*");
        server.start();

    }

}
