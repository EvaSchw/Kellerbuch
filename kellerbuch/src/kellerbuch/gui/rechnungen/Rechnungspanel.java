package kellerbuch.gui.rechnungen;

import java.awt.*;

import javax.swing.*;

import kellerbuch.fachlogik.Kunde;
import kellerbuch.fachlogik.Rechnung;
import kellerbuch.fachlogik.Rechnungspositionen;
import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class Rechnungspanel extends JPanel
{
	private Rechnung rechnung;
	private JPanel pnlRechungspositionen;
	private Rechnungsfenster rechnungsfenster;
	private Winzerbetrieb betrieb;
	private JLabel lblRechnungsinformationen;
	private JTextField txtName;
	private JTextField txtAdresse;
	private JTextField txtRechnungsnummer;
	private JTextField txtRechnungsdatum;
	private JTextField txtOrt;
	private JLabel lblRechnungspreis;
	
	public Rechnungspanel(Rechnungsfenster rf, Winzerbetrieb wb)
	{
		this.rechnungsfenster = rf;
		this.betrieb = wb;
		this.setSize(520, 400);
		this.setLayout(new BorderLayout(0, 0));

		initPanel();
	}

	private void initPanel()
	{
		//Überschrift
		lblRechnungsinformationen = new JLabel("Rechungsinformationen");
		lblRechnungsinformationen.setFont(new Font("Dialog", Font.BOLD, 18));
		add(lblRechnungsinformationen, BorderLayout.NORTH);
		
		//Hauptpanel
		JPanel pnlContent = new JPanel();
		add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.PAGE_AXIS));
		
		//Kundenpanel
		JPanel pnlKunde = new JPanel();
		pnlKunde.setBorder(new EmptyBorder(15, 0, 0, 0));
		pnlContent.add(pnlKunde);
		pnlKunde.setLayout(new GridLayout(5, 2, 0, 0));
		
		//Kundenname
		JLabel lblName = new JLabel("Name");
		pnlKunde.add(lblName);
		txtName = new JTextField();
		txtName.setEditable(false);
		pnlKunde.add(txtName);
		txtName.setColumns(10);
		
		//Kundenadresse
		JLabel lblAdresse = new JLabel("Adresse");
		pnlKunde.add(lblAdresse);
		txtAdresse = new JTextField();
		txtAdresse.setEditable(false);
		pnlKunde.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		//Kundenort
		JLabel lblOrt = new JLabel("Ortschaft");
		pnlKunde.add(lblOrt);
		txtOrt = new JTextField();
		txtOrt.setEditable(false);
		pnlKunde.add(txtOrt);
		txtOrt.setColumns(10);
		
		//Rechnungsnummer
		JLabel lblRechnungsnummer = new JLabel("Rechnungsnummer");
		pnlKunde.add(lblRechnungsnummer);
		txtRechnungsnummer = new JTextField();
		txtRechnungsnummer.setEditable(false);
		pnlKunde.add(txtRechnungsnummer);
		txtRechnungsnummer.setColumns(10);
		
		//Rechnungsdatum
		JLabel lblRechnungsdatum = new JLabel("Rechnungsdatum");
		pnlKunde.add(lblRechnungsdatum);
		txtRechnungsdatum = new JTextField();
		txtRechnungsdatum.setEditable(false);
		pnlKunde.add(txtRechnungsdatum);
		txtRechnungsdatum.setColumns(10);
		
		//Beschriftungspanel
		JPanel pnlBeschriftung = new JPanel();
		pnlBeschriftung.setLayout(new GridLayout(1, 3, 0, 0));
		pnlBeschriftung.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnlBeschriftung.setBackground(new Color(102, 205, 170));
		pnlContent.add(pnlBeschriftung);
		JLabel lblWein = new JLabel("Weine");
		lblWein.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlBeschriftung.add(lblWein);
		JLabel lblMenge = new JLabel("Menge");
		lblMenge.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMenge.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBeschriftung.add(lblMenge);
		JLabel lblPreis = new JLabel("Gesamtpreis pro Wein");
		lblPreis.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPreis.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlBeschriftung.add(lblPreis);
		
		//Rechnungspositionspanel
		JScrollPane scrollPane = new JScrollPane();
		pnlContent.add(scrollPane);
		pnlRechungspositionen = new JPanel();
		scrollPane.setViewportView(pnlRechungspositionen);
		pnlRechungspositionen.setLayout(new BoxLayout(pnlRechungspositionen, BoxLayout.PAGE_AXIS));
		
		//Gesamtpreispanel
		JPanel pnlGesamtpreis = new JPanel();
		pnlContent.add(pnlGesamtpreis);
		pnlGesamtpreis.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblSumme = new JLabel("Gesamtpreis");
		lblSumme.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlGesamtpreis.add(lblSumme);
		lblRechnungspreis = new JLabel("Preis");
		lblRechnungspreis.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRechnungspreis.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlGesamtpreis.add(lblRechnungspreis);
		
		//Buttonpanel
		JPanel pnlButtons = new JPanel();
		pnlContent.add(pnlButtons);
		JButton btnNeueRechnung = new JButton("Neue Rechnung");
		pnlButtons.add(btnNeueRechnung);
		btnNeueRechnung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnNeueRechnungActionPerformed(e);
			}
		});
	}

	public Rechnung getRechnung()
	{
		return this.rechnung;
	}

	public void setRechnung(Rechnung r)
	{
		this.rechnung = r;
		updatePanel();
	}
	
	public void updatePanel()
	{
		txtName.setText(rechnung.getKunde().toString());
		txtAdresse.setText(rechnung.getKunde().getStrasse()+" "+rechnung.getKunde().getHausnummer());
		txtOrt.setText(String.valueOf(rechnung.getKunde().getPlz())+ " "+rechnung.getKunde().getOrt());
		txtRechnungsnummer.setText(String.valueOf(rechnung.getRechnungsnr()));
		txtRechnungsdatum.setText(String.valueOf(rechnung.getRechnungsdatum()));
		setRechnungsPositionen(rechnung.getRechnungspositionen());
		lblRechnungspreis.setText(String.valueOf(getGesamtpreis(rechnung.getRechnungspositionen())));
		revalidate();
		repaint();
	}
	
	public void setRechnungsPositionen(List<Rechnungspositionen> positionen)
	{
		pnlRechungspositionen.removeAll();
		int i = 0;
		for(Rechnungspositionen pos : positionen)
		{
			pnlRechungspositionen.add(new RechnungspositionenPanel(pos, i));
			i++;
		}
	}
	
	public double getGesamtpreis(List<Rechnungspositionen> positionen)
	{
		double preis = 0.0;
		for(Rechnungspositionen pos : positionen)
		{
			preis += (pos.getWein().getPreis() * pos.getMenge());
		}
		return preis;
	}
	
	protected void doBtnNeueRechnungActionPerformed(ActionEvent e)
	{
		try
		{
			JDialog neueRechnung = new NeueRechnungFenster(betrieb, rechnungsfenster);
			neueRechnung.setVisible(true);
			neueRechnung.setAlwaysOnTop(true);
			neueRechnung.setLocationRelativeTo(this);
		}
		catch (SecurityException e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
	}
}
