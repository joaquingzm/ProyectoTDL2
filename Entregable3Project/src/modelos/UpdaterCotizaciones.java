package modelos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.json.JSONObject;

import daos.CriptomonedaDAO;
import daos.FactoryDAO;
import vista.FramePrincipal;

public class UpdaterCotizaciones extends TimerTask {

	private static final String URL_API = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";
	private static final HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(URL_API)).GET().build();
	private static final HttpClient cliente = HttpClient.newHttpClient();
	
	@Override
	public void run() {
		
		Map<String, Double> preciosCriptomonedas;
		
	    try {
	    	
	    	HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
	    	
	    	if (respuesta.statusCode() == 200) {
	    		
	    		preciosCriptomonedas = parsear(respuesta.body()); //Fiarme de cambiarlo si hacemos la lista estatica de criptomonedas
	    		
	    	} else {
	    		
	    		System.out.println("Error: " + respuesta.statusCode());
	    	}
	    	
	    } catch (IOException | InterruptedException e) {
	    	
	    	e.printStackTrace();
	    	return;
	    }
	    
		FramePrincipal framePrincipal = GestorDeDatosGlobales.getFramePrincipal();
		
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		
		for (String llave : preciosCriptomonedas.keySet()) {
			cDAO.actualizarPrecioEnDolar(llave, preciosCriptomonedas.get(llave));
		}
		
		framePrincipal.getMenuCotizaciones().getCotizacionesTableModel().actualizarPrecios(preciosCriptomonedas);;

	}
	
	   private static Map<String, Double> parsear(String cuerpoRespuesta) {
		   
		   Map<String, Double> preciosCriptomonedas = new HashMap<String, Double>();
	       JSONObject json = new JSONObject(cuerpoRespuesta);
	       List<Criptomoneda> listaCriptos = GestorDeDatosGlobales.getListaCriptos();
	       
	       for (Criptomoneda criptomoneda : listaCriptos) {
	    	   String nombre = criptomoneda.getNombre();
	    	   preciosCriptomonedas.put(nombre.toUpperCase(), json.getJSONObject(nombre.toLowerCase()).getDouble("usd"));
	       }

	       return preciosCriptomonedas;
	   }

}