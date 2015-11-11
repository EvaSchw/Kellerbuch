package kellerbuch.gui.rechnungen;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import kellerbuch.fachlogik.Rechnungspositionen;
import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RechnungspositionenPanel extends JPanel {

	/**
	 * Create the panel.
	 * 
	 */

	Winzerbetrieb wb;
	JSpinner spinner;
	JComboBox<Weine> comboBox;
	private JTextField txtGesamtpreis;

	/**
	 * @wbp.parser.constructor
	 */
	public RechnungspositionenPanel(Winzerbetrieb wb) {
		this.wb = wb;
		initPanel();
	}

	public RechnungspositionenPanel(Winzerbetrieb wb, Rechnungspositionen repos) {
		this.wb = wb;
		initPanel();
		comboBox.setSelectedItem(repos.getWein());
		spinner.setValue(new Integer(repos.getMenge()));
		setGesamtpreis(comboBox, spinner);
	}

	private void setGesamtpreis(JComboBox<Weine> comboBox, JSpinner spinner) {
		Weine w = (Weine) comboBox.getSelectedItem();
		int anzahl = new Integer(spinner.getValue().toString());
		double gesamtpreis = w.getPreis() * anzahl;
		txtGesamtpreis.setText(Double.toString(gesamtpreis));
	}

	private void initPanel() {
		setLayout(new GridLayout(0, 3, 0, 0));
		comboBox = new JComboBox<Weine>(new Vector<Weine>(wb.getWeinliste()));
		add(comboBox);
		spinner = new JSpinner();
		add(spinner);

		txtGesamtpreis = new JTextField();
		txtGesamtpreis.setHorizontalAlignment(SwingConstants.CENTER);
		txtGesamtpreis.setEditable(false);
		add(txtGesamtpreis);
		txtGesamtpreis.setColumns(10);
	}
}
