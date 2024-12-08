package controlador;

import java.sql.SQLException;
import java.sql.Statement;

import singletones.MyConnection;

public class MetodosDelSistema {

	protected static void creaciónDeTablasEnBD() throws SQLException {

		Statement stmt = MyConnection.getCon().createStatement();

		String sql = "CREATE TABLE IF NOT EXISTS CRIPTOMONEDA" 
				+ "(" 
				+ " ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
				+ " NOMBRE VARCHAR(50) NOT NULL, " 
				+ " SIGLA VARCHAR(10) NOT NULL, "
				+ " PRECIO_EN_DOLAR	REAL NOT NULL, " 
				+ " VOLATILIDAD	REAL NULL " 
				+ ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE IF NOT EXISTS MONEDA_FIDUCIARIA" 
				+ "(" 
				+ " ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
				+ " NOMBRE VARCHAR(50) NOT NULL, " 
				+ " SIGLA VARCHAR(10) NOT NULL, "
				+ " PRECIO_EN_DOLAR	REAL NOT NULL, " 
				+ " PAIS_EMISOR VARCHAR(50) NOT NULL "
				+ ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE IF NOT EXISTS ACTIVO_CRIPTO" 
				+ "(" 
				+ " ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
				+ " ID_CRIPTO INTEGER NOT NULL, "
				+ " ID_USUARIO INTEGER NOT NULL, "
				+ " CANTIDAD REAL NOT NULL, " 
				+ " DIRECCION VARCHAR(20) NOT NULL, "
				+ " FOREIGN KEY(ID_CRIPTO) REFERENCES CRIPTOMONEDA(ID), "
				+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID) "
				+ ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE IF NOT EXISTS ACTIVO_MONEDA_FIDUCIARIA" 
				+ "(" 
				+ " ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
				+ " ID_FIAT INTEGER NOT NULL, "
				+ " ID_USUARIO INTEGER NOT NULL, "
				+ " CANTIDAD REAL NOT NULL, "
				+ " FOREIGN KEY(ID_FIAT) REFERENCES MONEDA_FIDUCIARIA(ID), "
				+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID) "
				 + ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE IF NOT EXISTS TRANSACCION" 
				+ "(" 
				+ " ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
				+ " RESUMEN VARCHAR(1000) NOT NULL, "
				+ " FECHA_HORA DATETIME NOT NULL, "
				+ " ID_USUARIO INTEGER NOT NULL, "
				+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID) "
				+ ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE IF NOT EXISTS STOCK"
				+ "("
				+ " ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
				+ " ID_CRIPTO INTEGER NOT NULL, "
				+ " CANTIDAD REAL NOT NULL, "
				+ " FOREIGN KEY(ID_CRIPTO) REFERENCES CRIPTOMONEDA(ID) "
				+ ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE IF NOT EXISTS PERSONA"
				+ "("
				+ " ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
				+ " NOMBRE VARCHAR(30) NOT NULL, "
				+ " APELLIDO VARCHAR(30) NOT NULL"
				+ ")";
		stmt.executeUpdate(sql);
		
		sql = "CREATE TABLE IF NOT EXISTS USUARIO"
				+ "("
				+ " ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
				+ " ID_PERSONA INTEGER NOT NULL, "
				+ " EMAIL VARCHAR(20) NOT NULL, "
				+ " PASSWORD VARCHAR(20) NOT NULL, "
				+ " ACEPTA_TERMINOS BOOLEAN NOT NULL, "
				+ " FOREIGN KEY(ID_PERSONA) REFERENCES PERSONA(ID)"
				+ ")";
		stmt.executeUpdate(sql);
		
		stmt.close();
	}

}
