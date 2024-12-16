<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>SAP Banxico</title>


</head>
<body onload="" style="text-align: center;">
		<a href="http://www.banxico.org.mx" title="www.banxico.org.mx"> <img
			style="-webkit-user-select: none;" border="0"
			src="http://www.banxico.org.mx/apps_header.png" width="1024"
			height="74"></a>
			
		<form id="mainForm" action="" method="post">
			<font face="Arial">
				<h4>Sistema de Auto Servicio SAP</h4>
				<div id="tabs">
					<ul>
						<li><a href="#tabs-1">PROCESOS</a></li>
					</ul>
					<div class="demo" id="#tabs-1">
						<br>
						<table border="1"style="margin: 0 auto;" class="">

							<tr bgcolor="A2B8D2">
								<td width="200px" align="center"><input type="button"
									class="ui-state-active"
									onclick="window.location.href='ActualizarProcess.jsp';"
									value="Concluir/Cancelar Proceso" /></td>
							</tr>

							<tr bgcolor="A2B8D2">
								<td width="200px" align="center"><input type="button"
									class="ui-state-active" onclick="window.location.href='';"
									value="Relanzar Proceso" /></td>
							</tr>

							<tr bgcolor="A2B8D2">
								<td width="200px" align="center"><input type="button"
									class="ui-state-active"
									onclick="window.location.href='AsignarPermisos.jsp';"
									value="Asignar Permisos" /></td>
							</tr>
							<tr bgcolor="A2B8D2">
								<td width="200px" align="center"><input type="button"
									class="ui-state-active"
									onclick="window.location.href='ActualizarTask.jsp';"
									value="Actualizar Tareas" /></td>
							</tr>
							<tr bgcolor="A2B8D2">
								<td width="200px" align="center"><input type="button"
									class="ui-state-active" onclick="window.location.href='';"
									value="Relanzar Tarea" /></td>
							</tr>

							<tr bgcolor="A2B8D2">
								<td width="200px" align="center"><input type="button"
									class="ui-state-active" onclick="window.location.href='';"
									value="Recuperar Tarea" /></td>
							</tr>
						</table>
						<br> <br>
					</div>
				</div>
		</form>
</body>
</html>