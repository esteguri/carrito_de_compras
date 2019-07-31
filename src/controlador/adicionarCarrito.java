package controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelo.Producto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "controlador.adicionarCarrito", urlPatterns = {"/adicionar"})
public class adicionarCarrito extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int idProducto = Integer.parseInt(request.getParameter("id"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Producto producto = new Producto(idProducto,cantidad);
        if (producto.consultar()){
            String json = (String) session.getAttribute("carrito");
            Gson gson = new Gson();
            ArrayList<Producto> carrito = null;
            if (json!=null){
                Type listType = new TypeToken<ArrayList<Producto>>(){}.getType();
                carrito = gson.fromJson(json, listType);
                int pos = verificarCarrito(producto, carrito);
                if (pos !=-1 ){
                    carrito.get(pos).aumentarCantidad(cantidad);
                }else{
                    carrito.add(producto);
                }
            }else{
                carrito = new ArrayList<Producto>();
                carrito.add(producto);
            }
            
            String carritoJson = gson.toJson(carrito);
            session.setAttribute("carrito", carritoJson);
            session.setAttribute("mensaje", "Se agrego al carrito");
        }else{
            session.setAttribute("mensaje", "No existe dicho producto");
        }
        response.sendRedirect(request.getContextPath());
    }

    public int verificarCarrito(Producto producto, List<Producto> productos){
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getIdProducto() == producto.getIdProducto()){
                return i;
            }
        }
        return -1;
    }

}
