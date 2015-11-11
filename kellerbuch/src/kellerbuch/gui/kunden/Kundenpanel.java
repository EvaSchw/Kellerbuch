package kellerbuch.gui.kunden;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.*;

import kellerbuch.fachlogik.Kunde;
import kellerbuch.fachlogik.Winzerbetrieb;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Kundenpanel extends JPanel
{
	private Kunde kunde;
	private Kundenfenster kundenfenster;
	private Winzerbetrieb betrieb;
	
	private JButton btnSpeichern;
	private JButton btnNeu;
	private JButton btnLoeschen;
	private JTextField txtFirma;
	private JTextField txtVorname;
	private JTextField txtNachname;
	private JTextField txtStrasse;
	private JTextField txtHausnr;
	private JTextField txtPlz;
	private JTextField txtOrt;
	private JTextField txtLand;
	
	public Kundenpanel(Kundenfenster kf, Winzerbetrieb betrieb)
	{
		this.kundenfenster = kf;
		this.betrieb = betrieb;
		
		this.setLayout(null);
		this.setSize(520, 400);
			
		initPanel();
	}

	private void initPanel()
	{
		//�berschrift
		JLabel lblKundendaten = new JLabel("Kundendaten");
		lblKundendaten.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKundendaten.setHorizontalAlignment(SwingConstants.CENTER);
		lblKundendaten.setBounds(0, 13, 519, 39);
		add(lblKundendaten);
		
		//Firma
		txtFirma = new JTextField();
		txtFirma.setBackground(SystemColor.control);
		txtFirma.setText("Firma");
		txtFirma.setBounds(36, 85, 176, 22);
		add(txtFirma);
		txtFirma.setColumns(10);
		
		//Vorname
		txtVorname = new JTextField();
		txtVorname.setBackground(SystemColor.control);
		txtVorname.setText("Vorname");
		txtVorname.setBounds(36, 129, 182, 22);
		add(txtVorname);
		txtVorname.setColumns(10);
		
		//Nachname
		txtNachname = new JTextField();
		txtNachname.setBackground(SystemColor.control);
		txtNachname.setText("Nachname");
		txtNachname.setBounds(253, 129, 213, 22);
		add(txtNachname);
		txtNachname.setColumns(10);
		
		//Strasse
		txtStrasse = new JTextField();
		txtStrasse.setBackground(SystemColor.control);
		txtStrasse.setText("Strasse");
		txtStrasse.setBounds(36, 174, 295, 22);
		add(txtStrasse);
		txtStrasse.setColumns(10);
		
		//Hausnr
		txtHausnr = new JTextField();
		txtHausnr.setBackground(SystemColor.control);
		txtHausnr.setText("Hausnr");
		txtHausnr.setBounds(350, 174, 116, 22);
		add(txtHausnr);
		txtHausnr.setColumns(10);
		
		//PLZ
		txtPlz = new JTextField();
		txtPlz.setBackground(SystemColor.control);
		txtPlz.setText("PLZ");
		txtPlz.setBounds(36, 219, 116, 22);
		add(txtPlz);
		txtPlz.setColumns(10);
		
		//Ort
		txtOrt = new JTextField();
		txtOrt.setBackground(SystemColor.control);
		txtOrt.setText("Ort");
		txtOrt.setBounds(192, 219, 274, 22);
		add(txtOrt);
		txtOrt.setColumns(10);
		
		//Land
		txtLand = new JTextField();
		txtLand.setBackground(SystemColor.control);
		txtLand.setText("Land");
		txtLand.setBounds(36, 266, 181, 22);
		add(txtLand);
		txtLand.setColumns(10);

		//Button Speichern
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBackground(new Color(100, 149, 237));
		btnSpeichern.setBounds(23, 360, 97, 25);
		add(btnSpeichern);
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateData();
				kundenfenster.updateList();
			}
		});
		
		//Button Neu
		btnNeu = new JButton("Neuer Kunde");
		btnNeu.setBackground(new Color(60, 179, 113));
		btnNeu.setBounds(172, 360, 176, 25);
		add(btnNeu);
		btnNeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doNeuActionPerformed(arg0);
			}
		});
		
		//Button L�schen
		btnLoeschen = new JButton("L\u00F6schen");
		btnLoeschen.setBackground(new Color(255, 69, 0));
		btnLoeschen.setBounds(400, 360, 97, 25);
		add(btnLoeschen);
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doLoeschenActionPerformed(arg0);
			}
		});
	}
	
	public Kunde getKunde()
	{
		return kunde;
	}
	
	public void setKunde(Kunde kunde)
	{
		this.kunde = kunde;
		updatePanel();
	}
	
	public void updatePanel()
	{
		txtFirma.setText(kunde.getFirma());
		txtVorname.setText(kunde.getVorname());
		txtNachname.setText(kunde.getNachname());
		txtStrasse.setText(kunde.getStrasse());
		txtHausnr.setText(kunde.getHausnummer());
		txtPlz.setText(String.valueOf(kunde.getPlz()));
		txtOrt.setText(kunde.getOrt());
		txtLand.setText(kunde.getLand());
	}
	
	public void updateData()
	{
		try
		{
			kunde.setFirma(txtFirma.getText());
			kunde.setVorname(txtVorname.getText());
			kunde.setNachname(txtNachname.getText());
			kunde.setStrasse(txtStrasse.getText());
			kunde.setHausnummer(txtHausnr.getText());
			kunde.setPlz(Integer.valueOf(txtPlz.getText()));
			kunde.setOrt(txtOrt.getText());
			kunde.setLand(txtLand.getText());
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
			betrieb.kundenloeschen(getKunde());
			kundenfenster.updateList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	protected void doNeuActionPerformed(ActionEvent arg0)
	{
		NeuerKundeFenster nk = new NeuerKundeFenster(betrieb, kundenfenster);
		nk.setAlwaysOnTop(true);
		nk.setVisible(true);
		nk.setMinimumSize(new Dimension(400, 350));
	}
}
