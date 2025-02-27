package controlador.menuMisActivosListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import excepciones.InformacionExcepciones;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;
import vista.FramePrincipal;

public class ExportarCSVListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {

		int idUsuario = GestorDeDatosDelControlador.getIdUsuario();
		
		//Con las 3 lineas siguientes se puede obtener la dirección de ejecución actual (comunmente será la del JAR)
		Path rutaActual = Paths.get("").toAbsolutePath();
		String nombreArchivoCSV = "MisActivos#" + idUsuario + ".csv";
		Path rutaArchivoCSV = rutaActual.resolve(nombreArchivoCSV);
		
		String estructuraCSV = "Nombre, Sigla, Tipo, PrecioDolar, Cantidad\n";
		
		
		List<ActivoCripto> misActivosCripto;
		List<ActivoMonedaFiduciaria> misActivosFiat;
		
		try {
			
			misActivosCripto = FactoryDAO.getActivoCriptoDAO().listarActivosCripto(idUsuario);
			misActivosFiat = FactoryDAO.getActivoMonedaFiduciariaDAO().listarActivosFiduciarios(idUsuario);
			
		} catch (SQLException exc) {
			
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), "Ha ocurrido una excepción correspondiente a la base de datos en la creación del archivo CSV del usuario de ID " + idUsuario + ".");
			return;
		}
		

		BufferedWriter salida = null;
		
		try {
			
			salida = new BufferedWriter (new FileWriter(rutaArchivoCSV.toFile()));
			salida.write(estructuraCSV);
			
			for (ActivoCripto activoCripto : misActivosCripto) {
				Criptomoneda c = activoCripto.getCriptomoneda();
				String linea = c.getNombre() + ", " + c.getSigla() + ", " + "Cripto, " + c.getPrecioEnDolar() + ", " + activoCripto.getCantidad() + "\n";
				salida.write(linea);
			}
			
			for (ActivoMonedaFiduciaria activoMonedaFiduciaria : misActivosFiat) {
				MonedaFiduciaria mf = activoMonedaFiduciaria.getMonedaFIAT();
				String linea = mf.getNombre() + ", " + mf.getSigla() + ", " + "Fiat, " + mf.getPrecioEnDolar() + ", " + activoMonedaFiduciaria.getCantidad() + "\n";
				salida.write(linea);
			}
			
			salida.close();
			
			FramePrincipal.mostrarAviso("EXITO", "Se ha creado el archivo CSV correspondiente a los activos del usuario de ID " + idUsuario + ".");
			
		} catch (IOException exc) {
			
			FramePrincipal.mostrarAviso(InformacionExcepciones.IyO.getTitulo(), "Ha ocurrido una excepción de E/S en la creación del archivo CSV del usuario de ID " + idUsuario + ".");
			return;
		}

		
	}

}
