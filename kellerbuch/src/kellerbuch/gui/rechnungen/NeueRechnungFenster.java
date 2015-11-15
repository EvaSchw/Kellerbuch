package kellerbuch.gui.rechnungen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import kellerbuch.fachlogik.Kunde;
import kellerbuch.fachlogik.Rechnung;
import kellerbuch.fachlogik.Rechnungspositionen;
import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class NeueRechnungFenster extends JDialog
{
	private Winzerbetrieb betrieb;
	private Rechnungsfenster fenster;
	
	private JComboBox<Kunde> cBKunde;
	private JTextField txtRechnungsnr;
	private JTextField txtDatum;
	private JButton btnPlus;
	private JButton btnSpeichern;
	private JButton btnAbbruch;
	private JPanel rechnungspospanel;
	
	public NeueRechnungFenster(Winzerbetrieb wb, Rechnungsfenster rf)
	{
		betrieb = wb;
		fenster = rf;
		
		setTitle("Neue Rechnung");
		setSize(600, 400);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlContent = new JPanel();
		getContentPane().add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.PAGE_AXIS));
		
		JPanel pnlKunde = new JPanel();
		pnlKunde.setBorder(new EmptyBorder(15, 0, 0, 0));
		pnlContent.add(pnlKunde);
		pnlKunde.setLayout(new GridLayout(0, 2, 15, 15));
		
		JLabel lblKunde = new JLabel("Kunde");
		pnlKunde.add(lblKunde);
		
		cBKunde = new JComboBox<Kunde>(new Vector<Kunde>(betrieb.getKundenliste()));
		cBKunde.setSelectedItem(null);
		pnlKunde.add(cBKunde);
		
		JLabel lblRechnungsnr = new JLabel("Rechnungsnummer");
		pnlKunde.add(lblRechnungsnr);
		
		txtRechnungsnr = new JTextField();
		pnlKunde.add(txtRechnungsnr);
		txtRechnungsnr.setColumns(10);
		
		JLabel lblDatum = new JLabel("Rechnungsdatum (TT.MM.JJJJ)");
		pnlKunde.add(lblDatum);
		
		txtDatum = new JTextField();
		pnlKunde.add(txtDatum);
		txtDatum.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		pnlContent.add(scrollPane);
		
		rechnungspospanel = new JPanel();
		scrollPane.setViewportView(rechnungspospanel);
		rechnungspospanel.setLayout(new GridLayout(0, 2, 0, 0));
		rechnungspospanel.add(new NeueRechnungspositionenPanel(betrieb, 0));
		
		btnPlus = new ButtonRund("+");
		btnPlus.setForeground(new Color(255, 255, 255));
		btnPlus.setBackground(new Color(34, 139, 34));
		btnPlus.setFont(new Font("Tahoma", Font.BOLD, 18));
		rechnungspospanel.add(btnPlus);
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doBtnPlusActionPerformed(arg0);
			}
		});
		
		JPanel pnlButtons = new JPanel();
		getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		btnSpeichern = new JButton("Speichern");
		pnlButtons.add(btnSpeichern);
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doBtnSpeichernActionPerformed(arg0);
			}
		});
		
		btnAbbruch = new JButton("Abbruch");
		pnlButtons.add(btnAbbruch);
		btnAbbruch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doBtnAbbruchActionPerformed(arg0);
			}
		});		
	}
	
	protected void doBtnPlusActionPerformed(ActionEvent arg0)
	{
		rechnungspospanel.add(new JPanel());
		rechnungspospanel.add(new NeueRechnungspositionenPanel(betrieb, 1));
		rechnungspospanel.add(btnPlus);
		revalidate();
		repaint();
	}
	protected void doBtnSpeichernActionPerformed(ActionEvent arg0)
	{
		try
		{
			if(cBKunde.getSelectedItem() == null)
				throw new Exception("Kunde wurde nicht ausgew�hlt!");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			Rechnung r = new Rechnung(Integer.valueOf(txtRechnungsnr.getText()), sdf.parse(txtDatum.getText()), (Kunde) cBKunde.getSelectedItem());
			int i = rechnungspospanel.getComponentCount() -1;
			for(int j = 0; j <= i; j+=2)
			{
				NeueRechnungspositionenPanel nr = (NeueRechnungspositionenPanel) rechnungspospanel.getComponent(j);
				if(nr.getCbWein() == null)
					throw new Exception("Wein wurde nicht ausgew�hlt!");
				r.getRechnungspositionen().add(new Rechnungspositionen(nr.getCbWein(), nr.getMenge()));
			}
			betrieb.rechnungAnlegen(r);
			fenster.updateList();
			this.dispose();
		}
		catch (NumberFormatException e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Falsche Rechnungsnummerneingabe! Bitte eine Zahl eingeben!");
			
		}
		catch (ParseException e2)
		{
			e2.printStackTrace();
			JOptionPane.showMessageDialog(this, "Falsche Datumseingabe! Bitte im Format TT.MM.JJJJ");
		}
		catch (Exception e3)
		{
			e3.printStackTrace();
			JOptionPane.showMessageDialog(this, e3.getMessage());
		}
	}
	protected void doBtnAbbruchActionPerformed(ActionEvent arg0)
	{
		this.dispose();
	}
}