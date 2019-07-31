package controlador;

import modelo.Producto;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.google.gson.*;

@WebServlet(name = "productos", urlPatterns = {"/productos"})
public class ListarProductos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        List<Producto> productos = new Producto().consultarAll();
        if (productos!= null && productos.size() != 0){
            String json = new Gson().toJson(productos);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(json);
        }else{
            response.sendError(response.SC_INTERNAL_SERVER_ERROR,"Error al consultar");
        }
    }

}
