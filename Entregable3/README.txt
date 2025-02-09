Respecto a los DAOS:
-Se intento que todas las busquedas relacionadas con criptomonedas o monedas fiduciarias se hagan mediante el ID de la moneda correspondiente, el cual buscamos con metodos especificos que buscan el ID mediante la sigla.
-Se intento que las operaciones que involucren diferentes campos de un objeto, reciban dicho objeto en el metodo correspondiente para que ese metodo pueda generalizarse lo más posible. De todos modos, hicimos sobrecarga de algunos metodos para que dichas operaciones se hagan a partir de ciertos campos especificos porque hay contextos en los que se hace muy complejo, tendiendo a imposible, crear el objeto respectivo.

Respecto a MVC:
-Para respetar el patron de diseño, nosotros decidimos que el controlador sea distribuido a partir de los listeners y de otras clases que brindan información. En base a esto, decidimos que los listeners sean siempre clases por fuera de la vista y que accedan a la información de una manera que ellos tendran acceso total a cualquier parte de la vista según necesiten dado que una operación podría afectar paneles que no estan relacionados con el componente al que pertenecía el listener. De esta manera, lo que hicimos fue establecer una referencia estatica al JFrame (creado por nosotros) que contiene todos los paneles con los que trabajamos (utilizamos el CardLayout, con JPanels que creamos nosotros). Cabe aclarar que nuestro JFrame no es estático, sino que hicimos una referencia estática a él.

Respecto a la actualización de los precios:
-Implementamos dicha actualización a partir de un Timer y un TimerTask. Sin embargo, hicimos que la actualización se realice cada 1 minuto en vez de cada 5 segundos porque nos aparecian codigos de error asociados al acceso indiscriminado a la URL proporcionada.
-Dependiendo el acceso al panel de precios de criptos, se añadó un delay a la actualización de dichos precios porque, en caso contrario, tendrian lugar los mismos errores que se explicaron previamente. Esto ocurre debido a que multiples interacciones que buscan actualizar los precios tomarían lugar en una ventana muy breve de tiempo.	

Respecto a las excepciones:
-Nuestra forma de reportar cualquier tipo de problema en la App es mediante excepciones, las cuales mostramos en pantalla al usuario a traves de un Pop-Up. Para hacerlo optamos por establecer un cuerpo y un titulo a cada excepción desarrollada por nosotros para que cada Pop-Up sea detallado y amigable al usuario. Sin embargo, para seguir esa misma logica con las excepciones base de Java, elegimos por hacer un tipo Enumerativo el cual tenga un titulo y cuerpo predefinidos para cada tipo de excepción a la cual le creamos una instancia en el Enumerativo.

Respecto a las imagenes:
-Optamos por eliminar el campo de NOMBRE_ICONO de las tablas respectivas porque conideramos que no cumplía un rol importante para este trabajo ya que podiamos recuperar las imagenes mediante la sigla (nomenclatura) de cada moneda (llamamos a cada imagen de una moneda como su nomenclatura correspondiente).

Respecto a las comisiones:
-Basandonos en el primer entregable, decidimos agregar una comisión del 2% a la compra de criptomonedas (esta operación es la unica que nos correspondía desarrollar) aunque no se la especificaba en la consigna del tercer entregable.