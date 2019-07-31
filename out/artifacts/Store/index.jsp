<%@ page import="java.util.List" %>
<%@ page import="controlador.ListarProductos" %>
<%@ page import="modelo.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: egutierrez
  Date: 26/07/2019
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String mensaje = (String) session.getAttribute("mensaje");
Usuario user = null;
try{
  user = (Usuario) session.getAttribute("credenciales");
}catch(Exception e){
  e.printStackTrace();
}

if (mensaje!= null && mensaje.equals("si")){
  mensaje ="";
}
session.setAttribute("mensaje", "");
session.setAttribute("ruta",request.getContextPath());
String jsonCarrito = (String) session.getAttribute("carrito");

%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Store</title>
  <script  src="https://code.jquery.com/jquery-3.3.1.min.js"  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="  crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  <%if (jsonCarrito!=null){%>
  <script>
    var carrito = <%=jsonCarrito%>

  </script>
  <%}%>
</head>
<body>
<div class="container-fluid bg-dark">
  <nav class="navbar navbar-light bg-dark">
    <%if (user == null){%>
    <a class="navbar-brand text-white">Store</a>
    <button type="button" class="btn btn-outline-primary" id="myBtn">Iniciar Sesion</button>
    <%}else{%>
    <a class="navbar-brand text-white"><%=user.getUsuario()%></a>
    <a href="<%=request.getContextPath()%>/dashboard" class="btn btn-outline-primary">Configuracion</a>
    <%}%>
    <button type="button" class="btn btn-outline-primary" id="shoopingCart" operation="ocultar">Ocultar Carrito</button>
  </nav>
</div>
<div class="container-fluid mt-3">
  <div class="container-fluid">
    <div class="row">
      <div class="col">
        <h1>Productos</h1><h5 class="text-primary" id="mensaje"><%= (mensaje !=null)? mensaje: "" %></h5>
        <div class="row mt-3">
          <div class="col-12" id="cards">

          </div>

        </div>
      </div>
      <div class="col-3" id="divCarrito">
        <h2>Carrito</h2>
        <div style="overflow: scroll; max-height: 420px;">
          <table class="table table-striped">
            <tbody id="carrito">
            </tbody>
          </table>
        </div>
        <h3 id="total">

        </h3>
        <a href="<%=request.getContextPath()%>/iniciarCompra" class="btn btn-primary" id="comprar">Comprar</a>

      </div>
    </div>

  </div>
</div>



<div class="modal fade" id="myModal" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header" style="padding:35px 50px;">
        <h3>Iniciar Sesion</h3>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body" style="padding:40px 50px;">
        <form role="form" action="<%=request.getContextPath()%>/iniciarSesion" method="post">
          <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" name="usuario" id="username" placeholder="Ingrese el usuario" required>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password" id="password" placeholder="Ingrese el password" required>
          </div>
          <button type="submit" class="btn btn-primary btn-inline">Iniciar Sesion</button>
        </form>
      </div>
      <div class="modal-footer">
        <a href="#">Registrarse</a>
      </div>
    </div>

  </div>
</div>

<script src="<%=request.getContextPath()%>/recursos/main.js"></script>

</body>
</html>
