package funcionalidades_sistema;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MetodosDelSistema {
	private static void creaciónDeTablasEnBD(Connection connection) throws 
	SQLException {
		Statement stmt;
		stmt = connection.createStatement();
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
				+ " CANTIDAD	REAL    NOT NULL " + ")";
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
		stmt.close();
	}

}
