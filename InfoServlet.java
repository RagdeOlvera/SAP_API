package org.banxico.dds.proyectoweb;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
import java.io.IOException;
import java.io.PrintWriter;
 
@WebServlet("/infoServlet")
public class InfoServlet extends HttpServlet {
 
    @Inject
    DatabaseUtil dbInfo;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/plain;charset=UTF-8");
        printWriter.print(dbInfo.getVersion());
        printWriter.print(dbInfo.updateName());
    }
 
}