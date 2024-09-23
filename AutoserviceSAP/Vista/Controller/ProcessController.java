package org.Banxico.Proyecto1.controller;

import java.io.IOException;
import java.util.List;


import org.Banxico.Proyecto1.dao.ProcessDao;
import org.Banxico.Proyecto1.entity.Process;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.inject.Inject;

@WebServlet("/Process")
public class ProcessController extends HttpServlet{
	
	@Inject
	ProcessDao processDao;

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

        String processInstanceUuId = request.getParameter("processInstanceUuId");

        if (processInstanceUuId != null && !processInstanceUuId.isEmpty()) {
            SeleccionarProcesos(request, response, processInstanceUuId);
        } else {
            // Manejo de error: ID no proporcionado
            request.setAttribute("errorMessage", "Falta el ID del proceso.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("SolicitarDatos.jsp");
        dispatcher.forward(request, response);

	}
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void SeleccionarProcesos(HttpServletRequest request, //revisado funcionamiento correcto
			HttpServletResponse response, String processInstanceUuId) {
		
		List<Process> procesos = processDao.getLike(processInstanceUuId);
		request.setAttribute("processList", procesos);

	}

}
