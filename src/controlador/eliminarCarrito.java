package controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelo.Producto;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "eliminarCarrito", urlPatterns = {"/eliminarCarrito"})
public class eliminarCarrito extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int idProducto = Integer.parseInt(request.getParameter("id"));
        String ruta = (String) session.getAttribute("ruta");
        session.setAttribute("ruta","");

        String json = (String) session.getAttribute("carrito");
        Gson gson = new Gson();
        ArrayList<Producto> carrito = null;
        if (json!=null){
            Type listType = new TypeToken<ArrayList<Producto>>(){}.getType();
            carrito = gson.fromJson(json, listType);
            int pos = verificarCarrito(idProducto, carrito);
            if (pos !=-1 ){
                carrito.remove(pos);
            }
            String carritoJson = gson.toJson(carrito);
            session.setAttribute("carrito", carritoJson);
            session.setAttribute("mensaje", "Se elimino del carrito");
        }else{
            session.setAttribute("mensaje", "Ese producto no esta en el carrito");
        }
        ServletContext contexto=request.getServletContext();

        response.sendRedirect(ruta);

    }

    public int verificarCarrito(int idProducto, List<Producto> productos){
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getIdProducto() == idProducto){
                return i;
            }
        }
        return -1;
    }
}
