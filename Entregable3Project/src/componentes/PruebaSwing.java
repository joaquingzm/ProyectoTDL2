package componentes;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PruebaSwing extends JFrame {
	
	public PruebaSwing() {
		super("PruebaSwing");
		JLabel label = new JLabel("Hola");
		this.setSize(200,100);
		this.add(label);
	}
	
}