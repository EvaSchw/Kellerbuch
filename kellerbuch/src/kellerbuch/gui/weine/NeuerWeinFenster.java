package kellerbuch.gui.weine;

import javax.swing.*;

import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NeuerWeinFenster extends JDialog
{
	private Winzerbetrieb wb;
	private Weinfenster wf;
	
	private JTextField txtBezeichnung;
	private JTextField txtJahrgang;
	private JTextField txtAlkohol;
	private JTextField txtLage;
	private JTextField txtLagerstand;
	private JTextField txtPreis;
	private JCheckBox ckbxQualitaetswein;
	private JComboBox comboBox;
	
	public NeuerWeinFenster(Winzerbetrieb betrieb, Weinfenster fenster)
	{
		wb = betrieb;
		wf = fenster;
		
		this.setTitle("Neuer Wein");
		this.getContentPane().setLayout(new GridLayout(0, 2, 10, 10));
		this.setMinimumSize(new Dimension(400, 350));
		
		initFenster();
	}
	
	public void initFenster()
	{
		//Bezeichnung
		JLabel lblBezeichnung = new JLabel("Bezeichnung");
		getContentPane().add(lblBezeichnung);
		txtBezeichnung = new JTextField();
		getContentPane().add(txtBezeichnung);
		txtBezeichnung.setColumns(10);
		
		//Jahrgang
		JLabel lblJahrgang = new JLabel("Jahrgang");
		getContentPane().add(lblJahrgang);
		txtJahrgang = new JTextField();
		getContentPane().add(txtJahrgang);
		txtJahrgang.setColumns(10);
		
		//Alkohol
		JLabel lblAlkohol = new JLabel("Alkohol (als Zahl eingeben)");
		getContentPane().add(lblAlkohol);
		txtAlkohol = new JTextField();
		getContentPane().add(txtAlkohol);
		txtAlkohol.setColumns(10);
		
		//Qualitï¿½tswein
		JLabel lblQualitaetswein = new JLabel("Qualit\u00E4tswein");
		getContentPane().add(lblQualitaetswein);
		ckbxQualitaetswein = new JCheckBox("JA");
		getContentPane().add(ckbxQualitaetswein);
		
		//Lage
		JLabel lblLage = new JLabel("Lage");
		getContentPane().add(lblLage);
		txtLage = new JTextField();
		getContentPane().add(txtLage);
		txtLage.setColumns(10);
		
		//Liter
		JLabel lblLiter = new JLabel("Liter");
		getContentPane().add(lblLiter);
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0.25", "0.75", "1.00", "2.00"}));
		comboBox.setSelectedItem("0.75");
		getContentPane().add(comboBox);
		
		//Lagerstand
		JLabel lblLagerstand = new JLabel("Lagerstand");
		getContentPane().add(lblLagerstand);
		txtLagerstand = new JTextField();
		getContentPane().add(txtLagerstand);
		txtLagerstand.setColumns(10);
		
		//Preis
		JLabel lblPreis = new JLabel("Preis (mit Punkt dazwischen)");
		getContentPane().add(lblPreis);
		txtPreis = new JTextField();
		getContentPane().add(txtPreis);
		txtPreis.setColumns(10);
		
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
		String liter = (String) comboBox.getSelectedItem();
		
			try
			{
				wb.weineAnlegen(new Weine(txtBezeichnung.getText(), Integer.parseInt(txtJahrgang.getText()),
						Integer.parseInt(txtAlkohol.getText()), ckbxQualitaetswein.isSelected(), txtLage.getText(),
						Double.parseDouble(liter), Integer.parseInt(txtLagerstand.getText()),
						Double.parseDouble(txtPreis.getText())));
				wf.updateList();
				this.dispose();
			} catch (NumberFormatException e1)
			{
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Bitte bei Jahrgang, Alkohol, Lagerstand und Preis überprüfen, "
						+ "ob richtig eingetragen wurde!");
			} catch (Exception e)
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
