<%@page import="org.Banxico.Proyecto1.entity.Actor"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Actores</title>
</head>
	<body>
		<div> <%-- Seccion Metodo Save (Create/Insert) --%>
			
			<h3>Alta de Actores</h3>
			
			<%
			Actor loadedActor = (Actor)request.getAttribute("actor");
			String url = "actores?tipoOperacion=3";
			
			if (loadedActor == null){
				url = "actores?tipoOperacion=4";
				loadedActor = new Actor();
				loadedActor.setFirstName("");
				loadedActor.setLastName("");
				loadedActor.setActorId(0);
				}
			
			%>
			
			<form action="<%=url%>" method="post"  >
				<table border="1">
					<tr>
						<td>Nombre:</td>
						<td>
							<input 
							type="hidden"
							name= "id"
						 	value="<%= loadedActor.getActorId()%>" />
							
							<input 
							type="text"
							name= "firstName"
							id= "firstName"
						 	value="<%= loadedActor.getFirstName()%>" 
							required />
						</td>
					</tr>
					<tr>
						<td>Apellido:</td>
						<td>
							<input 
							type="text"
							name= "lastName"
							id= "lastName"
							value="<%= loadedActor.getLastName()%>"
							required />
						</td>
					</tr>
					<tr>
						<td>
							<button type="submit">Guardar</button>
						</td>
					</tr>
				</table>
			</form>
		</div>	
	
		<div> <%-- Seccion Metodo ListAll (Select *) --%>
			
			<h3>Relacion de clientes</h3>
			<table border="1">	
				<thead>
				<tr> 
					<th>Id</th>
					<th>Nombre</th>
					<th>Apellido</th>
					<th colspan ="2">Acciones </th>
				</tr>
				</thead>
				<tbody>
			
				<% //Codigo Java 'Scriptlets'
				List<Actor> actores = (List<Actor>)request.getAttribute("actorsList");
				if (actores != null ){
					for (Actor actor : actores) {
				%>

					<tr> 
						<td><%= actor.getActorId() %></td>
						<td><%= actor.getFirstName() %></td>
						<td><%= actor.getLastName() %></td>
						<td>
							<a href="actores?tipoOperacion=1&id=<%=actor.getActorId()%>">
								Eliminar
							</a>
						</td>
						<td>
							<a href="actores?tipoOperacion=2&id=<%=actor.getActorId()%>">
								Actualizar
							</a>
						</td>
					</tr>
				<%
						}
					} else { %>
						<h3>No hay registros que mostrar </h3>
				<% } %>
				</tbody>
			</table>
		</div>
		
	</body>
</html>