package componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardLayoutExample {
    public static void main(String[] args) {
        // Crear el JFrame principal
        JFrame frame = new JFrame("CardLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el CardLayout y el JPanel principal que lo contendrá
        CardLayout cardLayout = new CardLayout();
        JPanel panel = new JPanel(cardLayout);

        // Crear dos tarjetas (JPanels)
        JPanel card1 = new JPanel();
        card1.add(new JLabel("Esta es la tarjeta 1"));

        JPanel card2 = new JPanel();
        card2.add(new JLabel("Esta es la tarjeta 2"));

        // Agregar las tarjetas al JPanel con el CardLayout
        panel.add(card1, "Tarjeta 1");
        panel.add(card2, "Tarjeta 2");

        // Crear botones con lambdas para los ActionListener
        JButton nextButton = new JButton("Siguiente");
        nextButton.addActionListener(e -> cardLayout.next(panel)); // Cambia a la siguiente tarjeta

        JButton prevButton = new JButton("Anterior");
        prevButton.addActionListener(e -> cardLayout.previous(panel)); // Cambia a la tarjeta anterior

        // Crear un panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);
        buttonPanel.add(prevButton);

        // Agregar el panel de tarjetas y los botones al JFrame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Configurar y mostrar el JFrame
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
