package vista.menuCotizaciones;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
class BotonRenderer extends JButton implements TableCellRenderer {
	
    private String label;

    public BotonRenderer(String label) {
        this.label = label;
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

    	if (value == null) {
            setText(""); 
            setOpaque(false);
        } else {
            setText(label); 
            setOpaque(true);
        }

        setBackground(isSelected ? Color.CYAN : Color.LIGHT_GRAY);
        return this;
    }
}
