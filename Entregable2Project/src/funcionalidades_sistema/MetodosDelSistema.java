package funcionalidades_sistema;

import java.sql.SQLException;
import java.sql.Statement;

import singletones.MyStatement;

public class MetodosDelSistema {

	//ES PRIVADO ESTE METODO!!!!!!
	private static void creaciónDeTablasEnBD() throws SQLException {

		Statement stmt = MyStatement.getStmt();

		String sql = "CREATE TABLE CRIPTOMONEDA" 
				+ "(" 
				+ " NOMBRE       VARCHAR(50)    NOT NULL, " 
				+ " SIGLA VARCHAR(10)  PRIMARY KEY   NOT NULL, "
				+ " PRECIO_EN_DOLAR	REAL     NOT NULL, " 
				+ " VOLATILIDAD	REAL     NULL " + ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE MONEDA_FIDUCIARIA" 
				+ "(" 
				+ " NOMBRE       VARCHAR(50)    NOT NULL, " 
				+ " SIGLA VARCHAR(10)  PRIMARY KEY   NOT NULL, "
				+ " PRECIO_EN_DOLAR	REAL     NOT NULL, " 
				+ " PAIS_EMISOR 	VARCHAR(50) NOT NULL " + ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE ACTIVO_CRIPTO" 
				+ "(" 
				+ " NOMENCLATURA VARCHAR(10)  PRIMARY KEY     NOT NULL, "
				+ " CANTIDAD	REAL    NOT NULL " 
				//En nuestro modelo tenemos un campo que es dirección, lo añadimos?
				+ " DIRECCION VARCHAR(20) NOT NULL " + ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE ACTIVO_MONEDA_FIDUCIARIA" 
				+ "(" 
				+ " NOMENCLATURA VARCHAR(10)  PRIMARY KEY     NOT NULL, "
				+ " CANTIDAD	REAL    NOT NULL " + ")";
		stmt.executeUpdate(sql);

		sql = "CREATE TABLE TRANSACCION" 
				+ "(" 
				+ " RESUMEN VARCHAR(1000)   NOT NULL, "
				+ " FECHA_HORA		DATETIME  NOT NULL " + ")";
		stmt.executeUpdate(sql);

		sql = "CREAT TABLE STOCK"
				+ "("
				+ " CANTIDAD REAL NOT NULL"
				+ " SIGLA VARCHAR(10) PRIMARY KEY NOT NULL "
				+ ")";
		stmt.executeUpdate(sql);


	}

}
