# 2020
Master JAVA 2020
Covidstats
Tomás José Hombreiro Noriega

Explicación del proyecto
De acuerdo con las especificaciones indicadas se ha realizado una aplicación MVC en la que trabajamos con Servlets y JSP, además de con APIS de acceso a OpenStreetMap -OpenLayers- y librerías gráficas de Javascript -Chart.js-.
La estructura de la aplicación hace descansar la interfaz de comunicación , la vista, en las paginas JSP -index.jsp, donde se loguea el usuario, bienvenida.jsp, donde se ve la información para el usuario de rol empleado y bienvenidaadministrador.jsp donde se visualiza la información del rol administrador-, la lógica se articula en los servlets -holausuarioservlet.java, donde se deriva al usuario a la página jsp correspondiente después de validarlo frente a la base de datos, cargarexcelservlet.java, donde se abre el archivo Excel que está almacenado en la dirección c:\users\tomas\desktop\test\ + el nombre del archivo, el cual se introduce por teclado, empleando las bibliotecas XSSFWorkbook y otras incluidas en poi-4.1-2.jar, y poi-ooxml-4-1-2.jar, y cargando los datos en una tabla de la base de datos, crearusuarioservlet.java que crea nuevos usuarios con los perfiles empleado y administrador, borrarusuarioservlet.java, que los borra, conexión.java que crea el perfil básico de conexión a la base de datos y devuelvecasosservlet que devuelve a la interfaz de empleado el numero de casos y el numero de muertes-.

 
En la página de administración se podrá ver un mensaje de ejecución de cada tarea -carga de archivos, creación de usuarios, borrado de usuarios- cuando se realice.

 
Se ha empleado Openlayers para acceder a la API de OpenStreetMapas, y se han empleado mapas de cartoDB para representar los casos de covid en cada país. Se puede ver en la pagina un menú de selección para elegir país, y un botón para ver el numero de casos que se muestra en la pagina -que es llamada de nuevo-, así como el país correspondiente. Se puede ver también una grafica de charts.js con el porcentaje de muertos y casos.
En cuanto a los usuarios, hay varios creados. Un usuario con permisos de empleado llamado “usuario” y con clave “usuario” y otro llamado “empleado” con clave “empleado” y permisos de administrador. Hay mas usuarios de prueba.
 
 
 
En cuanto al control de excepciones, por premura de tiempo no se ha creado una clase especifica de excepción sino que el control se ha realizado a través de los throw y los catch. Asimismo se creo una pagina de error error.jsp a la que redirigen los mensajes de excepción donde se muestran los errores.
Se ha empleado HttpSesssion para gestionar la sesión, como se puede comprobar en holaUsuarioServlet.java.

