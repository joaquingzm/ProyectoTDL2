package controlador.menuCotizacionesListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwapListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {

		
		String sigla = arg0.getActionCommand();
		/*
		int idCripto;
		
		try {
			idCripto = FactoryDAO.getCriptomonedaDAO().buscarCriptomonedaId(sigla);
			
		} catch (SQLException e) {
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
			return;
		}
		*/
		System.out.println("Se accionó swap, "+sigla);
	}
}
