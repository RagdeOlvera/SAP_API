package org.banxico.dds.proyectoweb.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.banxico.dds.proyectoweb.dao.ProcessDao;
import org.banxico.dds.proyectoweb.entity.Empleado;
import org.banxico.dds.proyectoweb.entity.Process;
import org.banxico.dds.proyectoweb.entity.Process_History;

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
	
	public static int concluirProceso = 1;
	public static int cancelarProceso = 2;
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String processInstanceUuId = request.getParameter("processInstanceUuId");
		String cve_empleado = request.getParameter("cve");
				
		if (processInstanceUuId != null && !processInstanceUuId.isEmpty()) {
			if (cve_empleado != null && !cve_empleado.isEmpty()) {
				
				obtenerSID(request, response, cve_empleado);
				Empleado empleado = (Empleado) request.getAttribute("empleado");
				
				if(empleado != null && empleado.getSID() != null && !empleado.getSID().isEmpty()) {
					seleccionarProcesos(request, response, processInstanceUuId);
				}else {
					request.setAttribute("errorMessage", "No se encontro clave del cliente.");
				}
			}else {
				// Manejo de error: Cve no proporcionada
				request.setAttribute("errorMessage", "Falta clave del cliente.");
			}
		}else {
			// Manejo de error: ID no proporcionado
			request.setAttribute("errorMessage", "Falta el ID del proceso.");
		}
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("ActualizarProcess.jsp");
		dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Integer tipoOperacion = 0;
	
		if (request.getParameter("tipoOperacion") != null) { //verifica si se envia el parametro
			tipoOperacion = Integer.parseInt(request.getParameter("tipoOperacion")); // Toma el tipo de operacion
			
			if (request.getParameter("SID") != null) {
				actualizarProceso(request, response, tipoOperacion);
				registrarCambios(request, response, tipoOperacion); //Registra cambios en process_instance_history
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("ActualizarProcess.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void seleccionarProcesos(HttpServletRequest request, //revisado funcionamiento correcto
			HttpServletResponse response, String processInstanceUuId) {
		
		List<Process> procesos = processDao.getLike(processInstanceUuId);
		request.setAttribute("processList", procesos);

	}
	
	private void actualizarProceso(HttpServletRequest request, //revisado funcionamiento correcto
			HttpServletResponse response, Integer tipoOperacion) {
		
		Process proceso = new Process();
		proceso.setProcessInstanceId(Integer.parseInt(request.getParameter("processInstanceId")));
		proceso.setEndTime(LocalDateTime.now());
		
		if (tipoOperacion == concluirProceso) {
			proceso.setStatus(2);	//Concluir
		}else if (tipoOperacion == cancelarProceso) {
			proceso.setStatus(4);	//Cancelar
		}
		
		processDao.actualizarEstado(proceso);
	}
	
	private void registrarCambios(HttpServletRequest request, //revisado funcionamiento correcto
			HttpServletResponse response, Integer tipoOperacion) {
		
		Process_History history = new Process_History();
		history.setProcessInstanceId(Integer.parseInt(request.getParameter("processInstanceId")));
		
		if (tipoOperacion == concluirProceso) {
			history.setEventType("se concluye proceso a peticion del usuario");	//Concluir
		}else if (tipoOperacion == cancelarProceso) {
			history.setEventType("se cancela proceso a peticion del usuario");	//Cancelar
		}
		
		history.setEventTime(LocalDateTime.now());
		history.setUser(request.getParameter("SID"));  //cambiar
		history.setData("Proceso " + history.getProcessInstanceId() + " concluido");
		
		processDao.registrarCambio(history);

	}
	
	private void obtenerSID(HttpServletRequest request, //revisado funcionamiento correcto
			HttpServletResponse response, String cve_empleado) {
		
		Empleado empleado = processDao.obtenerSID(cve_empleado);
		request.setAttribute("empleado", empleado);
	}
}