package sistema;

import java.sql.SQLException;
import java.sql.Statement;
import singletones.MyStatement;

public class MetodosDelSistema {

	//ES PRIVADO ESTE METODO!!!!!!
	protected static void creaciónDeTablasEnBD() throws SQLException {

		Statement stmt = MyStatement.getStmt();

		String sql = "CREATE TABLE IF NOT EXISTS CRIPTOMONEDA" 
				+ "(" 
				+ " NOMBRE VARCHAR(50) NOT NULL, " 
				+ " SIGLA VARCHAR(10) PRIMARY KEY   NOT NULL, "
				+ " PRECIO_EN_DOLAR	REAL NOT NULL, " 
				+ " VOLATILIDAD	REAL NULL " + ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE IF NOT EXISTS MONEDA_FIDUCIARIA" 
				+ "(" 
				+ " NOMBRE VARCHAR(50) NOT NULL, " 
				+ " SIGLA VARCHAR(10) PRIMARY KEY NOT NULL, "
				+ " PRECIO_EN_DOLAR	REAL NOT NULL, " 
				+ " PAIS_EMISOR VARCHAR(50) NOT NULL "
				+ ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE IF NOT EXISTS ACTIVO_CRIPTO" 
				+ "(" 
				+ " SIGLA VARCHAR(10)  PRIMARY KEY NOT NULL, "
				+ " CANTIDAD REAL NOT NULL, " 
				+ " DIRECCION VARCHAR(20) NOT NULL "
				+ ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE IF NOT EXISTS ACTIVO_MONEDA_FIDUCIARIA" 
				+ "(" 
				+ " SIGLA VARCHAR(10) PRIMARY KEY NOT NULL, "
				+ " CANTIDAD REAL NOT NULL " + ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE IF NOT EXISTS TRANSACCION" 
				+ "(" 
				+ " RESUMEN VARCHAR(1000) NOT NULL, "
				+ " FECHA_HORA DATETIME NOT NULL " + ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE IF NOT EXISTS STOCK"
				+ "("
				+ " CANTIDAD REAL NOT NULL, "
				+ " SIGLA VARCHAR(10) PRIMARY KEY NOT NULL "
				+ ")";
		stmt.executeUpdate(sql);


	}

}
