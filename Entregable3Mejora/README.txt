-- Visuales --

- Se mejoraron los colores en general de las componentes de varios menús así. También
se agregaron títulos a cada menú.
- Al menú de cotizaciones se le realizó un cambió visual y lógico, haciendo que
la tabla de cotizaciones que simulaba los botones de swap y compra utilizando las
dos últimas columnas de la tabla pasen a renderizar botones en cada celda. Con esto se
consiguió utilizar funciones graficas de los botones como cambio de color y animación
de presionado (La animación de posar el mouse encima del botón no fue posible de 
realizar debido a cómo funciona el renderizador y editor del botón en la tabla). Por
otro lado éste cambio en la parte gráfica permitió también agregarle un listener a
cada columna de botones. 
- En el menú de activos se agregó el label que muestra el balance en USD del usuario.
- Se agregó el label de comisión en el menú de compra así cómo su respectiva lógica
del lado del controlador.

-- Controlador --

- Los controladores asociados a los botones de swap y compra son una instancia sola
por columna de botones. Por lo que la solución que hallamos para que cada botón 
pudiera diferenciarse del resto y que el controlador supiera sin accceder a la vista
qué moneda se está queriendo comprar fue poner como actionCommand del botón la sigla
de la moneda a la que hace referencia.
