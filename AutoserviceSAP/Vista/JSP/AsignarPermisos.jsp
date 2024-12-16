<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Actualizar Tareas</title>

    <style type="text/css">
        .hidden {
            display: none;
        }
    </style>

    <script>
    	
        function ocultarTabla() {
            const tabla1 = document.getElementById('proceso');
            const tabla2 = document.getElementById('operarTarea');
            const boton = document.getElementById('ocultar');

            // Toggle the visibility of the table
            if (tabla1.classList.contains('hidden')) {
                tabla1.classList.remove('hidden');
                tabla2.classList.add('hidden');
                boton.textContent = 'Enviar'; // Cambiar el texto del botón
            } else {
                tabla1.classList.add('hidden');
                tabla2.classList.remove('hidden');
                boton.textContent = 'Otro Folio'; // Cambiar el texto del botón
            }
        }
        
        function submitForm(tipoOperacion, processInstanceId) {
            document.getElementById('tipoOperacion').value = tipoOperacion;
            document.getElementById('processInstanceId').value = processInstanceId; // Asegúrate de que el ID esté correcto
            document.getElementById('actualizarProceso').submit();
        }

        
    
    </script>
</head>

<body onload="" style="text-align: center;">

<c:if test="${not empty errorMessage}">
    <script>
        alert('${errorMessage}');
    </script>
</c:if>

    <div>
        <a href="http://www.banxico.org.mx" title="www.banxico.org.mx">
            <img style="-webkit-user-select: none;" border="0" src="http://www.banxico.org.mx/apps_header.png" width="1024" height="74">
        </a>

        <form id="getProcess" action="Task" method="post">
            <h4>Actualizar Tareas</h4>
            <br>
            <table id="proceso" style="margin: 0 auto;" border="0" class="">

                <tr bgcolor="A2B8D2">
                    <td width="250px" align="right"><b>Folio de la Tarea:</b></td>
                    <td align="left"><input 
                            type="text"
                            name="processInstanceUuId"
                            id="processInstanceUuId"
                            value="" 
                            required/>
                    </td>            
                </tr>
                <tr bgcolor="A2B8D2">
                    <td width="250px" align="right"><b>Clave Cliente Solicitante:</b></td>
                    <td align="left"><input 
                            type="text"
                            name="cve"
                            id="cve"
                            value="" 
                            required/>
                    </td>            
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <button type="submit" >Enviar</button>
                    </td>
                </tr>
            </table>
		</form>
		<form id="operarTareaForm" action="Task" method="POST">
			<table id="operarTarea" style="margin: 0 auto;" border="0"
				class="hidden">
				<c:if test="${not empty taskList}">
					<script>
						ocultarTabla();
					</script>
					<tr bgcolor="A2B8D2">
						<td>Id</td>
						<td>Folio</td>
						<td>Nombre</td>
						<td>Asignado</td>
						<td>CompletionTime</td>
						<td>Status</td>

					</tr>
					<c:forEach var="tarea" items="${taskList}">
						<tr bgcolor="A2B8D2">

							<td>
								<input 
								type="text" 
								name="taskId" 
								value="${tarea.taskId}" 
								readonly>
							</td>
							<td>
								<input 
								type="text" 
								name="processInstanceUuId_${tarea.taskId}" 
								value="${tarea.processInstanceUuId}" 
								readonly>
							</td>
							<td>
								<input 
								type="text" 
								name="name_${tarea.taskId}" 
								value="${tarea.name}" 
								readonly>
							</td>
							<td>
								<input 
								type="text" 
								name="actualOwner_${tarea.taskId}" 
								value="${tarea.actualOwner != null && !tarea.actualOwner.isEmpty() ? tarea.actualOwner : 'sin asignar'}" 
								>
							</td>
							<td>
								<input 
								type="text" 
								name="completionTime_${tarea.taskId}" 
								value="${tarea.completionTime}" 
								readonly>
							</td>
							<td class="hidden">
								<input 
								type="text" 
								name="cve" 
								value="${empleado.cve_empleado}">
							</td>
							<td class="hidden">
								<input 
								type="text" 
								name="processInstanceUuId" 
								value="${tarea.processInstanceUuId}">
							</td>
							<td>
					            <select name="taskStatus_${tarea.taskId}">
					                <option value="2" ${tarea.taskStatus == 2 ? 'selected' : ''}>Lista</option>
					                <option value="3" ${tarea.taskStatus == 3 ? 'selected' : ''}>Reservada</option>
					                <option value="4" ${tarea.taskStatus == 4 ? 'selected' : ''}>En Curso</option>
					                <option value="12" ${tarea.taskStatus == 12 ? 'selected' : ''}>Obsoleta</option>
					                <option value="13" ${tarea.taskStatus == 13 ? 'selected' : ''}>Cerrada</option>
					            </select>
				       		</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty taskList}">
					<tr>
						<td colspan="2" align="center">
							<p>No se encontraron Tareas</p>
						</td>
					</tr>
				</c:if>

				<c:if test="${empty empleado}">
					<tr>
						<td colspan="2" align="center">
							<p>No se encontro el SID del cliente. Favor de verificar o
								introducir SAP Admin</p>
						</td>
					</tr>
				</c:if>
				 <tr>
		            <td colspan="3" align="right">
		                <!-- Botón para enviar el formulario con las modificaciones -->
		                <button type="submit" class="ui-state-active" style="margin-top: 20px">Actualizar Tareas</button>
		            </td>
					<td colspan="3" align="left">
						<button type="button" id="ocultar" class="ui-state-active"
							style="margin-top: 20px" onClick="location.href='ActualizarTask.jsp';">Otra
							Tarea</button>
					</td>
				</tr>
			</table>
        </form>
		<!--Aditional Parameters-->
            <br><br>
    </div>
</body>
</html>
