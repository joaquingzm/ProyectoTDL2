Consideraciones a tener en cuenta sobre nuestro modelo de billetera:

Nuestro programa funciona de manera que si ya existe en la base de
datos una tabla esta no es sobreescrita, solamente se crea en caso
de que no exista.

Para los DAO solamente implementamos los métodos necesarios, si bien
conocemos el estándar CRUD, tomamos la decisión de aprovechar el
tiempo para desarrollar funcionalidades necesarias para el correcto
funcionamiento de la billetera.

La dirección por cada activo de criptomoneda fue generada de manera
aleatoria, siendo un string de un 10 carácteres que son dígitos 
decimales. Entendemos que esto es meramente una simplificación de lo
que son realmente estas direcciones de billeteras cripto en la vida 
real, y que para un desarrollo serio deberíamos tomar medidas de seguridad
de manera que no sea posible que existan 2 direcciones iguales.

Para compra de criptomoneda, decidimos que si la criptomoneda no está
en el sistema tira error, ya que en el enunciado del entregable no
se especificaba qué hacer en este caso.
