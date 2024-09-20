<html>
<body>
    <% 
        String dato = (String) request.getAttribute("dato");
        if (dato == null) {
            out.println("El atributo 'dato' es nulo");
        } else {
            out.println("El atributo 'dato' es: " + dato);
        }
    %>
    <h2>Hello World! '<%= dato %>' </h2>
</body>
</html>
