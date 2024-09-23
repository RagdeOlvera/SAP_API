<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		        const tabla1 = document.getElementById('proceso');
		        const tabla2 = document.getElementById('operarProceso');
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
		</script>
</head>

<body style="text-align: center;">
	<div>
		<a href="http://www.banxico.org.mx" title="www.banxico.org.mx">
			<img style="-webkit-user-select: none;" border="0" src="http://www.banxico.org.mx/apps_header.png" width="1024" height="74">
		</a>

		<form id="getProcess" action="Process" method="get">
			<!-- CAMBIAR SERVLET POR EL NOMBRE DEL SERVLET -->
			<h4>Obtener Folio</h4>
			<br>
			<table id="proceso" style="margin: 0 auto;" border="0" class="">

				<tr bgcolor="A2B8D2">
					<td width="250px" align="right"><b>Folio del Proceso:</b></td>
					<td align="left"><input 
							type="text"
							name="processInstanceUuId"
							id="processInstanceUuId"
						 	value="" 
						 	required/>
					</td>			
				</tr>
				<tr>
				    <td colspan="2" align="center">
				        <button type="submit" onclick="ocultarTabla();">Enviar</button>
				    </td>
				</tr>
			</table>

			<table id="operarProceso" style="margin: 0 auto;" border="0"
				class="hidden">
				
				<c:if test="${not empty processList}">
				    <tr bgcolor="A2B8D2">
				        <td colspan="2" width="250px" align="right">
				            <p>Folios:</p>
				            <c:forEach var="proceso" items="${processList}">
				                <p>${proceso.processInstanceUuId} - ${proceso.processName} - ${proceso.status}</p>
				            </c:forEach>
				        </td>
				    </tr>
				</c:if>
				<c:if test="${empty processList}">
				    <tr>
				        <td colspan="2" align="center">
				            <p>No se encontraron procesos.</p>
				        </td>
				    </tr>
				</c:if>
								
				
				<tr>
				    <td colspan="2" align="center">
				        <button type="button" id="ocultar" class="ui-state-active"
				onClick="ocultarTabla();">Otro Folio</button>
				    </td>
				</tr>

			</table>
			<!--Aditional Parameters-->
			<br> <br>
		</form>
	</div>
</body>
</html>
