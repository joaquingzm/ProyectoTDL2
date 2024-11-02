package sistema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import daos.FactoryDAO;
import singletones.MyConnection;
import singletones.MyScanner;
import singletones.MyStatement;

public class Sistema {
	public static void main(String[] args) {
		try {
			MetodosDelSistema.creaciónDeTablasEnBD();
			//System.out.println(FactoryDAO.getActivoMonedaFiduciariaDAO().buscarActivoMonedaFiduciaria("p").toString());
			
			//Comentar si se "descomenta" lo de arriba"
			Menu.comenzar();
			MyStatement.cerrarStmt();
			MyConnection.cerrarCon();
			MyScanner.cerrarScan();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
