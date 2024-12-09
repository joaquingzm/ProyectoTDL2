package vista;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class Prueba2 {
    public static void main(String[] args) {
        // Datos de la tabla
        String[] columnas = {"Artículo", "Precio", "Opciones"};
        Object[][] datos = {
            {"Artículo 1", "$10", ""},
            {"Artículo 2", "$20", ""},
            {"Artículo 3", "$30", ""}
        };

        JTable tabla = new JTable(new DefaultTableModel(datos, columnas));
        tabla.setRowHeight(30);
        
        // Renderizador para la columna de opciones
        tabla.getColumn("Opciones").setCellRenderer(new PanelRenderer());

        // Crear ventana
        JFrame frame = new JFrame("Tabla con Paneles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(tabla));
        frame.pack();
        frame.setVisible(true);
    }
}

// Renderizador para un JPanel en una celda
class PanelRenderer extends JPanel implements TableCellRenderer {
    public PanelRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.removeAll();

        // Añadir un botón por celda
        JButton boton = new JButton("Comprar");
        boton.addActionListener(e -> System.out.println("Comprando artículo en fila " + row));

        this.add(boton);
        return this;
    }
}

