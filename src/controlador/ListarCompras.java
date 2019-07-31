package controlador;

import com.google.gson.Gson;
import modelo.Compra;
import modelo.Producto;
import modelo.Usuario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "compras", urlPatterns = {"/dashboard/compras"})
public class ListarCompras extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Usuario user = null;
        try{
            user = (Usuario) session.getAttribute("credenciales");
        }catch(Exception e){
            e.printStackTrace();
        }

        if (user != null){
            List<Compra> compras = new Compra(user.getIdUsuario()).consultarAll();
            if(compras!= null){
                String json = "";
                if (compras.size() != 0){
                    json = new Gson().toJson(compras);
                    response.setContentType("application/json");
                }
                PrintWriter out = response.getWriter();
                out.println(json);

            }else{
                response.sendError(response.SC_INTERNAL_SERVER_ERROR,"Error al consultar");
            }
        }else{
            session.setAttribute("mensaje","No tiene permisos, inicie sesion");
            response.sendRedirect(request.getContextPath());
        }

    }


}
