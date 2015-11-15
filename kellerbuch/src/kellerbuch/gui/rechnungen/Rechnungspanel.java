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
	
	public Rechnungspanel(Rechnungsfenster rf, Winzerbetrieb wb)
	{
		this.rechnungsfenster = rf;
		this.betrieb = wb;
		setSize(520, 400);

		initPanel();
	}

	private void initPanel()
	{
		setLayout(new BorderLayout(0, 0));
		
		lblRechnungsinformationen = new JLabel("Rechungsinformationen");
		lblRechnungsinformationen.setFont(new Font("Dialog", Font.BOLD, 18));
		add(lblRechnungsinformationen, BorderLayout.NORTH);
		
		JPanel pnlContent = new JPanel();
		add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.PAGE_AXIS));
		
		JPanel pnlKunde = new JPanel();
		pnlKunde.setBorder(new EmptyBorder(15, 0, 0, 0));
		pnlContent.add(pnlKunde);
		pnlKunde.setLayout(new GridLayout(5, 2, 0, 0));
		
		JLabel lblName = new JLabel("Name");
		pnlKunde.add(lblName);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		pnlKunde.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse");
		pnlKunde.add(lblAdresse);
		
		txtAdresse = new JTextField();
		txtAdresse.setEditable(false);
		pnlKunde.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		JLabel lblOrt = new JLabel("Ortschaft");
		pnlKunde.add(lblOrt);
		
		txtOrt = new JTextField();
		txtOrt.setEditable(false);
		pnlKunde.add(txtOrt);
		txtOrt.setColumns(10);
		
		JLabel lblRechnungsnummer = new JLabel("Rechnungsnummer");
		pnlKunde.add(lblRechnungsnummer);
		
		txtRechnungsnummer = new JTextField();
		txtRechnungsnummer.setEditable(false);
		pnlKunde.add(txtRechnungsnummer);
		txtRechnungsnummer.setColumns(10);
		
		JLabel lblRechnungsdatum = new JLabel("Rechnungsdatum");
		pnlKunde.add(lblRechnungsdatum);
		
		txtRechnungsdatum = new JTextField();
		txtRechnungsdatum.setEditable(false);
		pnlKunde.add(txtRechnungsdatum);
		txtRechnungsdatum.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		pnlContent.add(scrollPane);
		
		pnlRechungspositionen = new JPanel();
		scrollPane.setViewportView(pnlRechungspositionen);
		pnlRechungspositionen.setLayout(new BoxLayout(pnlRechungspositionen, BoxLayout.PAGE_AXIS));
		
		JPanel pnlButtons = new JPanel();
		pnlContent.add(pnlButtons);
		
		JButton btnNeueRechnung = new JButton("Neue Rechnung");
		btnNeueRechnung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnNeueRechnungActionPerformed(e);
			}
		});
		pnlButtons.add(btnNeueRechnung);
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
		revalidate();
		repaint();
	}
	
	public void setRechnungsPositionen(List<Rechnungspositionen> positionen)
	{
		pnlRechungspositionen.removeAll();
		int i = 0;
		for(Rechnungspositionen pos : positionen){
			pnlRechungspositionen.add(new RechnungspositionenPanel(pos, i));
			i++;
		}
	}
	
	protected void doBtnNeueRechnungActionPerformed(ActionEvent e)
	{
		JDialog neueRechnung = new NeueRechnungFenster(betrieb, rechnungsfenster);
		neueRechnung.setVisible(true);
		neueRechnung.setAlwaysOnTop(true);
	}
}
