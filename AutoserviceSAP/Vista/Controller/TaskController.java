package org.banxico.dds.proyectoweb.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.banxico.dds.proyectoweb.dao.TaskDao;
import org.banxico.dds.proyectoweb.entity.Empleado;
import org.banxico.dds.proyectoweb.entity.Task;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.inject.Inject;

@WebServlet("/Task")
public class TaskController extends HttpServlet {
	
	@Inject
	TaskDao taskDao;
	
	@Override
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String foliosPermisos = request.getParameter("foliosPermisos");
		
		if (foliosPermisos != null) {  
			gestionarTareas(request, response);
		}else {
			asignarPermisos(request, response, foliosPermisos);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String[] taskIds = request.getParameterValues("taskId");
		
		if (taskIds != null) { 
		    for (String Id : taskIds) {
		        Integer taskId = Integer.parseInt(Id);
		        String actualOwner = request.getParameter("actualOwner_" + taskId);  //Ejemplo B19908
		        Integer status = Integer.parseInt(request.getParameter("taskStatus_" + taskId));
	
		        actualizarTarea(request, response, taskId, actualOwner, status);
		    }
		}
		
		gestionarTareas(request, response);
		
	}
	
	 private void gestionarTareas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String processInstanceUuId = request.getParameter("processInstanceUuId");
	        String cve_empleado = request.getParameter("cve");

	        String errorMessage = validarCredenciales(processInstanceUuId, cve_empleado); 
	        
	        if (errorMessage == null) {
	            Empleado empleado = obtenerSID(request, response, cve_empleado);
	            if (empleado != null && empleado.getSID() != null && !empleado.getSID().isEmpty()) {
	                seleccionarTareas(request, response, processInstanceUuId);
	            } else {
	                request.setAttribute("errorMessage", "No se encontró clave del cliente.");
	            }
	        } else {
	            request.setAttribute("errorMessage", errorMessage);
	        }
	        
	        enviarVista(request, response, "ActualizarTask.jsp");
	    }
	 
	 private void asignarPermisos(HttpServletRequest request, HttpServletResponse response, String foliosPermisos) throws ServletException, IOException {
		 
		 String[] folios = separarFolios(foliosPermisos);
		 String cadenaFolios = prepararCadena(folios);
		 String errorMessage = "";
		 String sid = "";
				 
		 if (cadenaFolios != null) {
			 String taskIds = taskDao.obtenerTaskIds(cadenaFolios);
			 String idsDescartar = taskDao.verificarPermisos(taskIds, sid);
			 List<String> Ids = taskDao.verificarInserts(taskIds, sid, idsDescartar);
			 
			 for (String Id : Ids) {
				    taskDao.asignarPermisos(Id, sid);
				}
		 }else {
			 errorMessage = "No se encontraron folios";
			  request.setAttribute("errorMessage", errorMessage);
		 }
		 enviarVista(request, response, "ActualizarTask.jsp");
		 
	 }
	
	 private String validarCredenciales(String processInstanceUuId, String cve_empleado) {
	        if (processInstanceUuId == null || processInstanceUuId.isEmpty()) {
	            return "Falta el ID del proceso.";
	        } 
	        if (cve_empleado == null || cve_empleado.isEmpty()) {
	            return "Falta clave del cliente.";
	        }
	        return null;
	    }

	 
	private void enviarVista(HttpServletRequest request, HttpServletResponse response, String vista) throws ServletException, IOException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher(vista);
	    dispatcher.forward(request, response);
	}

	private void seleccionarTareas(HttpServletRequest request, //revisado funcionamiento correcto
			HttpServletResponse response, String processInstanceUuId) throws ServletException, IOException {
		
		List<Task> tareas = taskDao.getLike(processInstanceUuId);
		request.setAttribute("taskList", tareas);
		enviarVista(request, response, "ActualizarTask.jsp");

	}

	private Empleado obtenerSID(HttpServletRequest request, //revisado funcionamiento correcto
			HttpServletResponse response, String cve_empleado) {
		
		Empleado empleado =  taskDao.obtenerSID(cve_empleado);
		request.setAttribute("empleado", empleado);
		return empleado;
		
	}
	
	private void actualizarTarea(HttpServletRequest request, //revisado funcionamiento correcto
			HttpServletResponse response, Integer taskId, String actualOwner, Integer status) {
		
		Task tarea = new Task();
		
		String sid = taskDao.obtenerSID(actualOwner).getSID();
		
		tarea.setTaskId(taskId);
		tarea.setActualOwner(sid);
		tarea.setTaskStatus(status);

		taskDao.actualizarTarea(tarea);
	}
	
	private String[] separarFolios(String folios) {
		
		String[] arregloFolios = null;
		
		arregloFolios = folios.split(",");
		
		for (int i = 0; i < arregloFolios.length; i++) {
			arregloFolios[i] = arregloFolios[i].trim();
        }
		
		return arregloFolios;
	}
	
	private String prepararCadena(String[] arreglo) { 
		
		String cadenaPreparada = "";
		
		for (int i = 0; i < arreglo.length; i++){
			cadenaPreparada = cadenaPreparada.concat("'").concat(arreglo[i]).concat("'");
			if (i < arreglo.length - 1) {
				cadenaPreparada= cadenaPreparada.concat(", ");
	        }
		}
		return cadenaPreparada;
	}
}