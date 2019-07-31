$(document).ready(function(){
    if (carrito != undefined){
        let total = 0;
        for (let i = 0; i < carrito.length; i++) {
            $("#carrito").append('<tr><td><h5>'+carrito[i].nombreProducto+'</h5><p>Precio: $'+carrito[i].valorProducto+'</p><p>Cantidad: '+carrito[i].cantidad+'</p></td><td><a href="'+ruta+'/eliminarCarrito?id='+carrito[i].idProducto+'" class="btn btn-danger">Eliminar</a></td></tr>')
            total += carrito[i].valorProducto * carrito[i].cantidad;
        }

        $("#total").append("Total: $"+total)
    }
});
