# appWeather
Aplicación dedicada para la consulta de información de los climas de las ciudades y estadisticas.

#Base de datos
La base de datos se encuentra en postgresql en su versión 6.7
La creación de las tablas y relaciones se realiza al correr la aplicación.
La configuración de la base de datos se encuentra en el archivo application.properties (Verificar solo usuario y contraseña).


#Aplicación
La aplicación se inicia desde la clase AppWeatherApplication como JavaApplication ya que corre con el servidor embebido de Tomcat.

#EndPoints

1. Para consulta del clima de una ciudad
http://localhost:8080/weathers/getWeather?nameCity=?
donde nameCity es el nombre de la ciudad a consultar.

2. Para consulta de estadisticas:
http://localhost:8080/weathers/getHistory

Con esta ruta obtenemos las ultimas 10 ciudades consultadas y con el número de consultas realizadas.

