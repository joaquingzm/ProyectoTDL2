package funcionalidades_sistema;

import java.sql.SQLException;
import java.sql.Statement;

import singletones.MyStatement;

public class MetodosDelSistema {
	
	//ES PRIVADO ESTE METODO!!!!!!
	private static void creaciónDeTablasEnBD() {
		
		try {
			Statement stmt = MyStatement.getStmt();
			
			String sql = "CREATE TABLE MONEDA " 
					+ "(" 
					+ " TIPO       VARCHAR(1)    NOT NULL, "
					+ " NOMBRE       VARCHAR(50)    NOT NULL, " 
					+ " NOMENCLATURA VARCHAR(10)  PRIMARY KEY   NOT NULL, "
					+ " VALOR_DOLAR	REAL     NOT NULL, " 
					+ " VOLATILIDAD	REAL     NULL, "
					+ " STOCK	REAL     NULL "  + ")";
			
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE ACTIVO_CRIPTO" 
					+ "(" 
					+ " NOMENCLATURA VARCHAR(10)  PRIMARY KEY     NOT NULL, "
					+ " CANTIDAD	REAL    NOT NULL " 
					//En nuestro modelo tenemos un campo que es dirección, lo añadimos?
					+ " DIRECCION VARCHAR(20) NULL" + ")";
			
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE ACTIVO_FIAT" 
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
					+ " ID INTEGER PRIMARY KEY"
					+ " CANTIDAD INTEGER NOT NULL"
					+ " NOMENCLATURA_CRIPTO VARCHAR(10) NOT NULL "
					+ " FOREIGN KEY (NOMRECLATURA_CRIPTO) REFERENCES MONEDA(NOMENCLATURA)" + ")";
			
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}

	}

}
