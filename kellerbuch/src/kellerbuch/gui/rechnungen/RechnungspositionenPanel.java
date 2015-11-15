package kellerbuch.gui.rechnungen;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.*;

import kellerbuch.fachlogik.Rechnungspositionen;
import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;
import javax.swing.border.MatteBorder;

public class RechnungspositionenPanel extends JPanel
{	
	private JLabel lblWein;
	private JLabel lblMenge;
	private JLabel lblGesamtpreis;

	/**
	 * @wbp.parser.constructor
	 */
	public RechnungspositionenPanel()
	{
		initPanel();
	}

	public RechnungspositionenPanel(Rechnungspositionen repos, int i)
	{
		initPanel();
		lblWein.setText(repos.getWein().getBezeichnung());
		lblMenge.setText(String.valueOf(repos.getMenge()));
		setGesamtpreis(repos);
		if(i != 0)
			setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
	}
	
	private void initPanel()
	{
		setLayout(new GridLayout(0, 3, 0, 0));
		lblWein = new JLabel();
		add(lblWein);
		lblMenge = new JLabel();
		lblMenge.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMenge);
		lblGesamtpreis = new JLabel();
		lblGesamtpreis.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblGesamtpreis);
	}

	private void setGesamtpreis(Rechnungspositionen repos)
	{
		double preis = repos.getWein().getPreis();
		int anzahl = repos.getMenge();
		lblGesamtpreis.setText(Double.toString(preis * anzahl));
	}
}
