# appWeather
Aplicación dedicada para la consulta de información de los climas de las ciudades y estadisticas.

# Base de datos: 
La base de datos se encuentra en postgresql en su versión 6.7
La creación de las tablas y relaciones se realiza al correr la aplicación.
La configuración de la base de datos se encuentra en el archivo application.properties (Verificar solo usuario y contraseña).


# API OpenWeather:
La llave la contiene el archivo application.properties

# Aplicación: 
La aplicación se inicia desde la clase AppWeatherApplication como JavaApplication ya que corre con el servidor embebido de Tomcat.

# EndPoints

1. Para consulta del clima de una ciudad: 
http://localhost:8080/weathers/getWeather?nameCity=?
donde nameCity es el nombre de la ciudad a consultar.

Se tiene configurado para este endPoint diferentes tipos de respuesta como son:
500 ---> En caso de no estar disponible el servidor o no mandar un dato de la ciudad
200 ---> Devolviendo la información del clima de la ciudad correctamente de la API.
206 ---> Devolviendo información de cache del clima de la ciudad consultada.
404 ---> En caso de no encontrar información del clima de la ciudad en la API ni en la cache.
406 ---> En caso de no encontrar información de la ciudad en la Base de datos.


2. Para consulta de estadisticas: 
http://localhost:8080/weathers/getHistory

Con esta ruta obtenemos las ultimas 10 ciudades consultadas y con el número de consultas realizadas.
Para este manejamos lo siguientes códigos de error: 

200 -----> Respuesta correcta 
404 -----> En caso de que no encontrar ciudades registradas.
500 ----- Servidor no disponible
