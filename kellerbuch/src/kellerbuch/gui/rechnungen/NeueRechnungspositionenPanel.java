package kellerbuch.gui.rechnungen;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import java.beans.PropertyChangeEvent;

public class NeueRechnungspositionenPanel extends JPanel
{	
	private Winzerbetrieb betrieb;
	
	private JComboBox<Weine> cbWein;
	private JTextField txtMenge;
	private JLabel lblEinzelpreis;
	
	public NeueRechnungspositionenPanel(Winzerbetrieb wb, int i)
	{		
		this.betrieb = wb;
		initPanel(i);
		if(i != 0)
			setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
	}
	
	public void initPanel(int i)
	{
		setLayout(new GridLayout(0, 3, 0, 0));
		
		//Combobox-Wein
		cbWein = new JComboBox<Weine>(new Vector<Weine>(betrieb.getWeinliste()));
		add(cbWein);
		cbWein.setSelectedItem(null);
		cbWein.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				doCbWeinItemStateChanged(e);
			}
		});

		//Weinmenge
		txtMenge = new JTextField("1");
		txtMenge.setHorizontalAlignment(SwingConstants.CENTER);
		add(txtMenge);
		
		//Weinpreis
		lblEinzelpreis = new JLabel("Preis");
		lblEinzelpreis.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblEinzelpreis);
	}
	
	protected void doCbWeinItemStateChanged(ItemEvent arg0)
	{
		Weine w = (Weine) cbWein.getSelectedItem();
		lblEinzelpreis.setText(String.valueOf(w.getPreis()));
	}
	
	public Weine getCbWein()
	{
		return (Weine)cbWein.getSelectedItem();
	}
	
	public int getMenge()
	{
		return Integer.valueOf(txtMenge.getText());
	}
}
