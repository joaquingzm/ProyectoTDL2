package controlador;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.json.JSONObject;

import daos.CriptomonedaDAO;
import daos.FactoryDAO;
import excepciones.InformacionExcepciones;
import modelos.Criptomoneda;
import vista.FramePrincipal;

public class UpdaterCotizaciones extends TimerTask {

	private static final String URL_API = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";
	private static final HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(URL_API)).GET().build();
	private static final HttpClient cliente = HttpClient.newHttpClient();
	
	@Override
	public void run() {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		Map<Integer, Double> preciosCriptomonedas = null;
		
	    try {
	    	
	    	HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
	    	
	    	if (respuesta.statusCode() == 200) {
	    		
	    		preciosCriptomonedas = parsear(respuesta.body()); 
	    		
	    	} else {
	    		
	    		System.out.println("Error: " + respuesta.statusCode());
	    	}
	    	
	    } catch (IOException e) {
	    	
	    	FramePrincipal.mostrarAviso(InformacionExcepciones.IyO.getTitulo(), InformacionExcepciones.IyO.getCuerpo());
	    	return;
	    	
	    } catch (InterruptedException e) {
	    	
	    	FramePrincipal.mostrarAviso(InformacionExcepciones.INTERRUPCION.getTitulo(), InformacionExcepciones.INTERRUPCION.getCuerpo());
	    	return;
	    }
	    
		
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		
		for (int llave : preciosCriptomonedas.keySet()) {
			
			try {
				cDAO.actualizarPrecioEnDolar(llave, preciosCriptomonedas.get(llave));
				
			} catch (SQLException e) {
				
				FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
				return;
			}
			
		}
		
		framePrincipal.getMenuCotizaciones().actualizarPrecios(preciosCriptomonedas);;

	}
	
	private static Map<Integer, Double> parsear(String cuerpoRespuesta) {

		Map<Integer, Double> preciosCriptomonedas = new LinkedHashMap<Integer, Double>();
		JSONObject json = new JSONObject(cuerpoRespuesta);
		List<Criptomoneda> listaCriptos = null;
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		
		try {
			
			listaCriptos = cDAO.listarCriptomonedas();
			
			for (Criptomoneda criptomoneda : listaCriptos) {
				String sigla = criptomoneda.getSigla();
				int idCripto;
				
				idCripto = cDAO.buscarCriptomonedaId(sigla);
				
				String nombre = criptomoneda.getNombre();
				preciosCriptomonedas.put(idCripto, json.getJSONObject(nombre.toLowerCase()).getDouble("usd"));
			}
			
		} catch (SQLException e) {
			
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
		}

		return preciosCriptomonedas;
	}

}
