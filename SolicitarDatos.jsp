<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Formulario de Datos</title>
	
	<style type="text/css">
	.hidden {
		display: none;
	}
	</style>
	
		<script>
		    function ocultarTabla() {
		        const tabla_ = document.getElementById('proceso');
		        const  = document.getElementById('ocultar');
		
		        // Toggle the visibility of the table
		        if (table.classList.contains('hidden')) {
		            table.classList.remove('hidden');
		            button.textContent = 'Ocultar Tabla'; // Cambiar el texto del botón
		        } else {
		            table.classList.add('hidden');
		            button.textContent = 'Mostrar Tabla'; // Cambiar el texto del botón
		        }
		    }
		    function mostrarTabla() {
		        const table = document.getElementById('operarProceso');
		        const button = document.getElementById('ocultar');
		
		        // Toggle the visibility of the table
		        if (table.classList.contains('hidden')) {
		            table.classList.remove('hidden');
		            button.textContent = 'Ocultar Tabla'; // Cambiar el texto del botón
		        } else {
		            table.classList.add('hidden');
		            button.textContent = 'Mostrar Tabla'; // Cambiar el texto del botón
		        }
		    }
		</script>
</head>

<body style="text-align: center;">
	<div>
		<a href="http://www.banxico.org.mx" title="www.banxico.org.mx"> 
			<img style="-webkit-user-select: none;" border="0" src="http://www.banxico.org.mx/apps_header.png" width="1024" height="74">
		</a>
		
		<%
		String url = "";
		/*Agregar logica de eleccion por defecto o implementar directamente variable url*/
		%>
		
		<form id="getProceso" action="<%=url%>" method="get">
			<!-- CAMBIAR SERVLET POR EL NOMBRE DEL SERVLET -->
			<h4>Obtener Folio</h4>
			<br>
			<table id="proceso" style="margin: 0 auto;" border="0" class="">

				<tr bgcolor="A2B8D2">
					<td width="250px" align="right"><b>Folio del Proceso:</b></td>
					<td align="left"><input type="text" name="processInstanceUuId"
						id="odeInstanceId" size="50" class="required" value=""></td>
				</tr>

			</table>

			<table id="operarProceso" style="margin: 0 auto;" border="0"
				class="hidden">

				<tr bgcolor="A2B8D2">
					<td width="250px" align="right"><b>DATOS_Prueba</b></td>
					<td align="left"><input type="text" name="processInstanceUuId"
						id="odeInstanceId" size="50" class="required" value=""></td>
				</tr>

			</table>
			<!--Aditional Parameters-->
			<br> <br>
			<button type="button" id="ocultar" class="ui-state-active"
				onClick="ocultarTabla(); mostrarTabla();">Ocultar</button>
		</form>
	</div>
</body>
</html>
