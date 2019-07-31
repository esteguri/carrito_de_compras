

$(document).ready(function(){

    var rutaRelativa = window.location.href;

    $.post(rutaRelativa+"productos",{}, function(response){
        response.forEach(function (producto) {
            $("#cards").append('<div class="card ml-3 mt-2 float-left" style="width: 18rem;">\n' +
                '        <div class="card-body">\n' +
                '          <h5 class="card-title">'+producto.nombreProducto+'</h5>\n' +
                '        </div>\n' +
                '        <ul class="list-group list-group-flush">\n' +
                '          <li class="list-group-item">Precio: $ '+producto.valorProducto+'</li>\n' +
                '        </ul>\n' +
                '        <div class="card-body">\n' +
                '          <form action="'+rutaRelativa+'adicionar" method="get"><input type="text" name="id" value="'+producto.idProducto+'" style="display: none;"><input type="submit" class="btn btn-primary card-link" value="Añadir al carrito">\n' +
                '          <input type="number" name="cantidad" min="1" style="width: 40px;" value="1"></form>\n' +
                '        </div>\n' +
                '      </div>')
        })
    })



    if (carrito != undefined){
        let total = 0;
        for (let i = 0; i < carrito.length; i++) {
            $("#carrito").append('<tr><td><h5>'+carrito[i].nombreProducto+'</h5><p>Precio: $'+carrito[i].valorProducto+'</p><p>Cantidad: '+carrito[i].cantidad+'</p></td><td><a href="'+rutaRelativa+'eliminarCarrito?id='+carrito[i].idProducto+'" class="btn btn-danger">Eliminar</a></td></tr>')
            total += carrito[i].valorProducto * carrito[i].cantidad;
        }

        $("#total").append("Total: $"+total)
    }

    $('#shoopingCart').click(function () {
        $("#divCarrito").toggle('normal');
        if ($(this).attr("operation") == "ocultar") {
            $(this).html("Mostrar Carrito");
            $(this).removeAttr("operation");
            $(this).attr("operation", "mostrar")
        }else{
            $(this).html("Ocultar Carrito");
            $(this).removeAttr("operation");
            $(this).attr("operation", "ocultar");
        }
    })

    $("#myBtn").click(function(){
        $("#myModal").modal();
    });





});