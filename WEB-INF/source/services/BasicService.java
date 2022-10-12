package services;

/**
 * Web Service por defecto de una aplicación construida con Dinámica,
 * puede añadir sus propios métodos públicos para ampliar las operaciones
 * soportadas por este servicio. Ya viene preconfigurado para ser publicado.
 */

import java.util.Date;
import javax.jws.WebService;

@WebService
public class BasicService {

	/**
	 * Mensaje de bienvenida para probar la comunicación con el web service
	 * @return Mensaje
	 */
	public String ping() {
		return "Hello World con JAX-WS.";
	}
	
	/**
	 * Obtener la fecha y hora del servidor
	 * @return Fecha y Hora del servidor
	 */
	public Date getServerDate() {
		return new Date();
	}
	
}
