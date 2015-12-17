package kellerbuch.gui.weine;

import java.awt.*;

import javax.swing.*;

import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Weinpanel extends JPanel
{
	private Weine wein;
	private Weinfenster weinfenster;
	private Winzerbetrieb betrieb;
	
	private JTextField txtBezeichnung;
	private JLabel lblJahrgang;
	private JLabel lblLage;
	private JLabel lblAlkohol;
	private JLabel lblQualitaetswein;
	private JLabel lblLiter;
	private JTextField txtLagerstand;
	private JTextField txtPreis;
	private JButton btnSpeichern;
	private JButton btnNeu;
	private JButton btnLoeschen;
	
	public Weinpanel(Weinfenster wf, Winzerbetrieb wb)
	{
		this.weinfenster = wf;
		this.betrieb = wb;
		
		this.setLayout(null);
		this.setSize(520, 400);
		
		initPanel();
	}

	private void initPanel()
	{
		//�berschrift
		JLabel lblWeindaten = new JLabel("Weindaten");
		lblWeindaten.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWeindaten.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeindaten.setBounds(0, 13, 519, 39);
		add(lblWeindaten);
		
		//Bezeichnung
		txtBezeichnung = new JTextField();
		txtBezeichnung.setBackground(SystemColor.control);
		txtBezeichnung.setHorizontalAlignment(SwingConstants.CENTER);
		txtBezeichnung.setText("Bezeichnung");
		txtBezeichnung.setBounds(142, 78, 231, 22);
		add(txtBezeichnung);
		txtBezeichnung.setColumns(10);
		
		//Jahrgang
		lblJahrgang = new JLabel("Jahrgang");
		lblJahrgang.setHorizontalAlignment(SwingConstants.CENTER);
		lblJahrgang.setBounds(234, 123, 56, 16);
		add(lblJahrgang);
		
		//Lage
		lblLage = new JLabel("Lage");
		lblLage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLage.setBounds(205, 154, 122, 16);
		add(lblLage);
		
		//Alkohol
		lblAlkohol = new JLabel("Alkohol");
		lblAlkohol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlkohol.setBounds(23, 218, 50, 16);
		add(lblAlkohol);
		JLabel lblAlk = new JLabel("% Alk.");
		lblAlk.setBounds(77, 218, 56, 16);
		add(lblAlk);
		
		//Qualit�tswein
		lblQualitaetswein = new JLabel("Qualit\u00E4tswein");
		lblQualitaetswein.setHorizontalAlignment(SwingConstants.CENTER);
		lblQualitaetswein.setBounds(217, 218, 98, 16);
		add(lblQualitaetswein);
		
		//Liter
		lblLiter = new JLabel("Liter");
		lblLiter.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLiter.setBounds(396, 218, 56, 16);
		add(lblLiter);
		JLabel lblL = new JLabel("l");
		lblL.setBounds(464, 218, 19, 16);
		add(lblL);

		//Lagerstand
		txtLagerstand = new JTextField();
		txtLagerstand.setBackground(SystemColor.control);
		txtLagerstand.setHorizontalAlignment(SwingConstants.CENTER);
		txtLagerstand.setText("Lagerstand");
		txtLagerstand.setBounds(142, 283, 74, 22);
		add(txtLagerstand);
		txtLagerstand.setColumns(10);
		JLabel lblLagerstand = new JLabel("Lagerstand:");
		lblLagerstand.setBounds(64, 286, 82, 16);
		add(lblLagerstand);
		
		//Preis
		txtPreis = new JTextField();
		txtPreis.setBackground(SystemColor.control);
		txtPreis.setHorizontalAlignment(SwingConstants.CENTER);
		txtPreis.setText("Preis");
		txtPreis.setBounds(374, 283, 45, 22);
		add(txtPreis);
		txtPreis.setColumns(10);
		JLabel lblEuro = new JLabel("Euro:");
		lblEuro.setBounds(328, 286, 45, 16);
		add(lblEuro);
		
		//Button Neu
		btnNeu = new JButton("Neuer Wein");
		btnNeu.setBackground(new Color(60, 179, 113));
		btnNeu.setBounds(172, 360, 176, 25);
		add(btnNeu);
		btnNeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doNeuActionPerformed(arg0);
			}
		});
		
		//Button Speichern
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBackground(new Color(100, 149, 237));
		btnSpeichern.setBounds(23, 360, 97, 25);
		add(btnSpeichern);
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateData();
				weinfenster.updateList();
			}
		});
		
		//Button L�schen
		btnLoeschen = new JButton("L\u00F6schen");
		btnLoeschen.setBackground(new Color(255, 69, 0));
		btnLoeschen.setBounds(400, 360, 97, 25);
		add(btnLoeschen);
		btnLoeschen.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				doLoeschenActionPerformed(arg0);
			}
		});
	}

	public Weine getWein()
	{
		return wein;
	}

	public void setWein(Weine wein)
	{
		this.wein = wein;
		updatePanel();
	}
	
	public void updatePanel()
	{
		txtBezeichnung.setText(wein.getBezeichnung());
		lblJahrgang.setText(String.valueOf(wein.getJahrgang()));
		lblLage.setText(wein.getLage());
		lblQualitaetswein.setText(String.valueOf(wein.isQualitaetswein()));
		lblAlkohol.setText(String.valueOf(wein.getAlkohol()));
		lblLiter.setText(String.valueOf(wein.getLiter()));
		txtLagerstand.setText(String.valueOf(wein.getLagerstand()));
		txtPreis.setText(String.valueOf(wein.getPreis()));
	}
	
	public void updateData()
	{
		try
		{
			wein.setBezeichnung(txtBezeichnung.getText());
			wein.setLagerstand(Integer.parseInt(txtLagerstand.getText()));
			wein.setPreis(Double.parseDouble(txtPreis.getText()));
			JOptionPane.showMessageDialog(this, "Wein gespeichert!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	protected void doLoeschenActionPerformed(ActionEvent arg0)
	{
		try
		{
			betrieb.weineloeschen(getWein());
			weinfenster.updateList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	protected void doNeuActionPerformed(ActionEvent arg0)
	{
		NeuerWeinFenster nw = new NeuerWeinFenster(betrieb, weinfenster);
		nw.setLocationRelativeTo(this);
		nw.setAlwaysOnTop(true);
		nw.setVisible(true);
	}
}
