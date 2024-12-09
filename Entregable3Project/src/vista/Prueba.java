package vista;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prueba {
    public static void main(String[] args) {
            JFrame frame = new JFrame("Tabla con Botones");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Modelo de tabla personalizado
            String[] columnNames = {"Artículo", "Precio", "Acción"};
            Object[][] data = {
                    {"Artículo 1", "$10.00", "Comprar"},
                    {"Artículo 2", "$15.50", "Comprar"},
                    {"Artículo 3", "$20.00", "Comprar"}
            };

            JTable table = new JTable(new ModeloTabla(columnNames, data));
            table.setRowHeight(30);

            // Configurar el renderizador y editor para la columna de botones
            table.getColumn("Acción").setCellRenderer(new BotonRenderer());
            table.getColumn("Acción").setCellEditor(new BotonEditor(new JCheckBox()));

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.setSize(400, 200);
            frame.setVisible(true);
        ;
    }
}

// Modelo de tabla personalizado
class ModeloTabla extends AbstractTableModel {
    private String[] columnNames;
    private Object[][] data;

    public ModeloTabla(String[] columnNames, Object[][] data) {
        this.columnNames = columnNames;
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2; // Solo la columna "Acción" es editable
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}

// Renderizador para la columna de botones
class BotonRenderer extends JButton implements TableCellRenderer {
    public BotonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

// Editor para la columna de botones
class BotonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean clicked;
    private JTable table;

    public BotonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            int row = table.getSelectedRow();
            String articulo = table.getValueAt(row, 0).toString();
            JOptionPane.showMessageDialog(button, "Comprar " + articulo);
        }
        clicked = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }
}
