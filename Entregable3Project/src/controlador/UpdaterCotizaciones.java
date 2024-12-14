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
import modelos.Criptomoneda;
import vista.FramePrincipal;

public class UpdaterCotizaciones extends TimerTask {

	private static final String URL_API = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";
	private static final HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(URL_API)).GET().build();
	private static final HttpClient cliente = HttpClient.newHttpClient();
	
	@Override
	public void run() {
		Map<Integer, Double> preciosCriptomonedas = null;
		
	    try {
	    	
	    	HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
	    	
	    	if (respuesta.statusCode() == 200) {
	    		
	    		preciosCriptomonedas = parsear(respuesta.body()); 
	    		
	    	} else {
	    		
	    		System.out.println("Error: " + respuesta.statusCode());
	    	}
	    	
	    } catch (IOException | InterruptedException e) {
	    	
	    	e.printStackTrace();
	    	return;
	    }
	    
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		
		for (int llave : preciosCriptomonedas.keySet()) {
			
			try {
				System.out.println("Actualizando");
				cDAO.actualizarPrecioEnDolar(llave, preciosCriptomonedas.get(llave));
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
			
		}
		System.out.println("");
		framePrincipal.getMenuCotizaciones().actualizarPrecios(preciosCriptomonedas);;

	}
	
	private static Map<Integer, Double> parsear(String cuerpoRespuesta) {

		Map<Integer, Double> preciosCriptomonedas = new LinkedHashMap<Integer, Double>();
		JSONObject json = new JSONObject(cuerpoRespuesta);
		List<Criptomoneda> listaCriptos = null;
		
		try {
			listaCriptos = FactoryDAO.getCriptomonedaDAO().listarCriptomonedas();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (Criptomoneda criptomoneda : listaCriptos) {
			String sigla = criptomoneda.getSigla();
			int idCripto = -1;
			try {
				idCripto = FactoryDAO.getCriptomonedaDAO().buscarCriptomonedaId(sigla);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String nombre = criptomoneda.getNombre();
			preciosCriptomonedas.put(idCripto, json.getJSONObject(nombre.toLowerCase()).getDouble("usd"));
		}

		return preciosCriptomonedas;
	}

}
