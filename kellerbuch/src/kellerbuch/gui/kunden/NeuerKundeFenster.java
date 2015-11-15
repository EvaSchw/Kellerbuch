package kellerbuch.gui.kunden;

import javax.swing.*;

import kellerbuch.fachlogik.Kunde;
import kellerbuch.fachlogik.Winzerbetrieb;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.jar.JarOutputStream;
import java.awt.event.ActionEvent;

public class NeuerKundeFenster extends JDialog
{
	private Winzerbetrieb wb;
	private Kundenfenster kf;
	private JTextField txtFirma;
	private JTextField txtVorname;
	private JTextField txtNachname;
	private JTextField txtHausnr;
	private JTextField txtOrt;
	private JTextField txtLand;
	private JTextField txtStrasse;
	private JTextField txtPlz;
	
	public NeuerKundeFenster(Winzerbetrieb betrieb, Kundenfenster fenster)
	{
		wb = betrieb;
		kf = fenster;
		
		this.setTitle("Neuer Kunde");
		this.getContentPane().setLayout(new GridLayout(0, 2, 10, 10));
		this.setMinimumSize(new Dimension(400, 350));
		initFenster();
	}
	
	public void initFenster()
	{
		//Firma
		JLabel lblFirma = new JLabel("Firma");
		getContentPane().add(lblFirma);
		txtFirma = new JTextField();
		getContentPane().add(txtFirma);
		txtFirma.setColumns(10);
		
		//Vorname
		JLabel lblVorname = new JLabel("Vorname");
		getContentPane().add(lblVorname);
		txtVorname = new JTextField();
		getContentPane().add(txtVorname);
		txtVorname.setColumns(10);
		
		//Nachname
		JLabel lblNachname = new JLabel("Nachname");
		getContentPane().add(lblNachname);
		txtNachname = new JTextField();
		getContentPane().add(txtNachname);
		txtNachname.setColumns(10);
		
		//Strasse
		JLabel lblStrasse = new JLabel("Strasse");
		getContentPane().add(lblStrasse);
		txtStrasse = new JTextField();
		getContentPane().add(txtStrasse);
		txtStrasse.setColumns(10);
		
		//Hausnr
		JLabel lblHausnummer = new JLabel("Hausnummer");
		getContentPane().add(lblHausnummer);
		txtHausnr = new JTextField();
		getContentPane().add(txtHausnr);
		txtHausnr.setColumns(10);
		
		//PLZ
		JLabel lblPlz = new JLabel("PLZ");
		getContentPane().add(lblPlz);
		txtPlz = new JTextField();
		getContentPane().add(txtPlz);
		txtPlz.setColumns(10);
		
		//Ort
		JLabel lblOrt = new JLabel("Ort");
		getContentPane().add(lblOrt);
		txtOrt = new JTextField();
		getContentPane().add(txtOrt);
		txtOrt.setColumns(10);
		
		//Land
		JLabel lblLand = new JLabel("Land");
		getContentPane().add(lblLand);
		txtLand = new JTextField();
		getContentPane().add(txtLand);
		txtLand.setColumns(10);
		
		//Button Speichern
		JButton btnSpeichern = new JButton("Speichern");
		getContentPane().add(btnSpeichern);
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doSpeichernActionPerformed(arg0);
			}
		});
		
		//Button Abbruch
		JButton btnAbbruch = new JButton("Abbruch");
		getContentPane().add(btnAbbruch);
		btnAbbruch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doAbbruchActionPerformed(arg0);
			}
		});
	}
	
	protected void doSpeichernActionPerformed(ActionEvent arg0)
	{
		try
		{
			wb.kundenAnlegen(new Kunde(txtFirma.getText(), txtVorname.getText(), txtNachname.getText(),
					txtStrasse.getText(), txtHausnr.getText(), Integer.valueOf(txtPlz.getText()), txtOrt.getText(), txtLand.getText()));
			kf.updateList();
			this.dispose();
		}
		catch (NumberFormatException e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Bitte Eingabe beim Jahrgang überprüfen!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	protected void doAbbruchActionPerformed(ActionEvent arg0)
	{
		this.dispose();
	}
}
