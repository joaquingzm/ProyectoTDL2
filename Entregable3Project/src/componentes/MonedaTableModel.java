package componentes;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelos.MonedaFiduciaria;

/*Ver q onda si copa hacerlo así, supuestamente se mantendría actualizado
sin necesidad de que tocaramos nada, aun asi capaz la idea es solamente
bajar los datos cuadno se abren la app, trabajar con eso sin actualizar
y dsp volver a guardar todo y listo 
*/
public class MonedaTableModel extends AbstractTableModel {
    private List<MonedaFiduciaria> monedas;
    private String[] columnas = {"Nombre", "Valor", "Símbolo"};

    public MonedaTableModel(List<MonedaFiduciaria> monedas) {
        this.monedas = monedas;
    }

    @Override
    public int getRowCount() {
        return monedas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MonedaFiduciaria moneda = monedas.get(rowIndex);
        switch (columnIndex) {
            case 0: return moneda.getNombre();
            case 1: return moneda.getPrecioEnDolar();
            case 2: return moneda.getSigla();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
