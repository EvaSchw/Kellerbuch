package kellerbuch.gui.rechnung;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import kellerbuch.fachlogik.Rechnungspositionen;
import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;

public class RechnungspositionsPanel extends JPanel {

	/**
	 * Create the panel.
	 * 
	 */
	
	Winzerbetrieb wb;
	JSpinner spinner;
	JComboBox<Weine> comboBox;
	
	public RechnungspositionsPanel(Winzerbetrieb wb) {
		this.wb = wb;
		initPanel();
	}

	public RechnungspositionsPanel(Winzerbetrieb wb, Rechnungspositionen repos) {
		this.wb = wb;
		initPanel();
		comboBox.setSelectedItem(repos.getWein());
		spinner.setValue(new Integer(repos.getMenge()));
	}
	
	private void initPanel() {
		setLayout(new GridLayout(0, 2, 0, 0));	
		comboBox = new JComboBox<Weine>(new Vector<Weine>(wb.getWeinliste()));
		add(comboBox);	
		spinner = new JSpinner();
		add(spinner);	
	}
}
