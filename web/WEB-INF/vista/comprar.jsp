<%--
  Created by IntelliJ IDEA.
  User: egutierrez
  Date: 30/07/2019
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="modelo.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: egutierrez
  Date: 29/07/2019
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario user = null;
    try{
        user = (Usuario) session.getAttribute("credenciales");
    }catch(Exception e){
        e.printStackTrace();
    }
    String jsonCarrito = (String) session.getAttribute("carrito");

    session.setAttribute("ruta",request.getContextPath()+"/iniciarCompra");
    String url = (String) request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Dashboard</title>
    <script  src="https://code.jquery.com/jquery-3.3.1.min.js"  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="  crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <%if (jsonCarrito!=null){%>
    <script>
        var carrito = <%=jsonCarrito%>;
        var ruta = "<%=url%>";
    </script>
    <%}%>
</head>
<body>
<div class="bg-primary p-3 text-light" style="height: 70px">
    <div class="d-flex justify-content-between"><h3>Usuario: <span><%=(user!=null)?user.getUsuario():"null" %></span></h3><a href="<%=request.getContextPath()%>" class="btn btn-primary">Regresar</a></div>
</div>
<div class="container-fluid">
    <div class="d-flex justify-content-center">
        <h1>Carrito</h1>
    </div>
    <div class="row mt-3">
        <div class="col-2"></div>
        <div class="col-4" id="divCarrito">

            <div style="overflow: scroll; max-height: 480px;">
                <table class="table table-striped">
                    <tbody id="carrito">
                    </tbody>
                </table>
            </div>

        </div>
        <div class="col-3">
            <h3 id="total" class="alert alert-warning"></h3>
            <div class="form-group">
                <form action="<%=request.getContextPath()%>/comprar" method="post">
                <select name="pago" class="form-control" id="pago">
                    <option>PayPal</option>
                    <option>Tarjeta de Credito</option>
                    <option>PSE</option>
                </select>
                <div class="fixed-bottom p-4 d-flex justify-content-end"><button type="submit" class="btn btn-success p-2 mr-5"><h3>Comprar</h3></button></div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/recursos/comprar.js"></script>

</body>
</html>
