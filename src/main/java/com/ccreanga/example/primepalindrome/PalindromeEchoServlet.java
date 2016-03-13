package com.ccreanga.example.primepalindrome;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ccreanga.example.primepalindrome.arithm.primes.PrimeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PalindromeEchoServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(PalindromeEchoServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String limit = request.getParameter("limit");
        response.setContentType("text/plain; charset=UTF-8");
        long maxNo;
        try {
            maxNo = limit != null ? Long.parseLong(limit) : Long.MAX_VALUE;
        }catch(NumberFormatException e){
            error(response,401);
            return;
        }

    }

    private void error(HttpServletResponse response,int error){
        try{
            response.sendError(error);
        }catch (IOException e){
            LOG.error(e.getMessage());
        }
    }


    private void writeMessage(HttpServletResponse response, String text){
        try{
            response.getWriter().println(text);
        }catch (IOException e){
            LOG.error(e.getMessage());
        }
    }
}
