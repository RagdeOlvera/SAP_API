package org.banxico.dds.proyectoweb;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/clase-vista")
public class ClaseVista extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establece el atributo "dato" en el requestScope
        request.setAttribute("dato",  new ClaseNegocio().ejecuta());
        // Redirige a la JSP
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}