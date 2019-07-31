$(document).ready(function () {
    var rutaRelativa = window.location.href;
    $.get(rutaRelativa+"/compras",{},function (response) {
        response.forEach(function (compra) {
            $("#compras").append('<div class="ml-3 mt-2 p-3" style="background:rgb(250, 248, 248); border-radius: 20px; max-height: 400px; overflow: scroll; width: 20rem">\n' +
                '            <table class="table" id="T' + compra.idCompra + '">\n' +
                '                <tr>\n' +
                '                    <td><h5>Fecha:</h5></td>\n' +
                '                    <td><h5>' + compra.fecha + '</h5></td>\n' +
                '                </tr>\n' +
                '                <tr>\n' +
                '                    <td><h5>Id Compra:</h5></td>\n' +
                '                    <td><h5>' + compra.idCompra + '</h5></td>\n' +
                '                </tr>\n' +
                '                <tr>\n' +
                '                    <td>Medio de pago:</td>\n' +
                '                    <td>' + compra.medioPago + '</td>\n' +
                '                </tr>\n' +
                '                <tr><td><h3>Productos:</h3></td></tr>\n' +
                '                <tr><td><strong>Producto</strong></td><td><strong>Cantidad</strong></td></tr>\n' +
                '            </table>\n' +
                '        </div>')


            var strJson = compra.productos;
            var productos = JSON.parse(strJson)
            console.log(strJson)
            console.log(productos)
            var total = 0;
            productos.forEach(function (producto) {
                $("#T" + compra.idCompra).append('<tr><td>'+producto.nombreProducto+'</td><td>'+producto.cantidad+'</td></tr>')
                total+=producto.valorProducto;
            })
            $("#T" + compra.idCompra).append('<tr>\n' +
                '                    <td><h5>Total:</h5></td>\n' +
                '                    <td><h5>$'+total+'</h5></td>\n' +
                '                </tr>')

        })
    })
})