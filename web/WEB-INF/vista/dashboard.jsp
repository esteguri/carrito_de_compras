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
</head>
<body>
<div class="bg-primary p-3 text-light" style="height: 70px">
    <div class="float-left"><h3>Usuario: <span><%=(user!=null)?user.getUsuario():"null" %></span></h3></div>
    <div class="mr-1 float-right"><a href="<%=request.getContextPath()%>/close" class="btn btn-outline-light">Cerrar Sesion</a></div>
</div>
<div class="container-fluid">
    <h1 class="d-flex justify-content-center">Compras</h1>
    <div class="row mt-4" id="compras">

    </div>
</div>
<script src="<%=request.getContextPath()%>/recursos/dashboard.js"></script>
<div class="fixed-bottom p-2 d-flex justify-content-end"><a href="<%=request.getContextPath()%>" class="btn btn-primary">Regresar</a></div>
</body>
</html>
