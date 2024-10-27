package dao_implementado_jdbc;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import dao_interfaces.StockDAO;
import modelos.Stock;

public class StockDAOjdbc implements StockDAO{
	
	@Override
	public void insertarStock() throws SQLException {
		/* -DEBERIA RECIBIR ALGO O GENERA CANTIDADES ALEATORIAS
		 * PARA TODOS LOS ELEMENTOS Y LISTO??
		 * -QUE PASA CUANDO LLAMAN A ESTE METODO DESPUES DE HABERLO LLAMADO INICIALMENTE??
		 * DEBERIA ACTUALIZAR TODO O NO DEBERIA EJECUTARSE?? CONTROLARIAMOS ESO
		 * DESDE EL MAIN???*/
		
	}
	
	@Override
	public List<Stock> listarStock(Comparator<Stock> c) throws SQLException{
		
		return null;
	}

}
