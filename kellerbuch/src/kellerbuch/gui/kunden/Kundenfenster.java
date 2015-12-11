package kellerbuch.gui.kunden;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kellerbuch.fachlogik.Kunde;
import kellerbuch.fachlogik.Winzerbetrieb;

public class Kundenfenster extends JDialog
{
	private Winzerbetrieb betrieb;
	private DefaultListModel<Kunde> kundenModel = new DefaultListModel<Kunde>();
	private JList<Kunde> kundenlist;
	private Kundenpanel kundenpanel;
	
	
	public Kundenfenster(Winzerbetrieb betrieb)
	{
		this.betrieb = betrieb;
	
		this.setTitle("Kunden");
		this.setSize(750, 500);
		this.setResizable(false);
		
		updateList();
		
		initFenster();
	}
	
	public void initFenster()
	{
		//Splitpane	
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		//Kundenpanel
		kundenpanel = new Kundenpanel(this, betrieb);
		splitPane.setRightComponent(kundenpanel);
		
		//Kundenliste
		kundenlist = new JList<Kunde>(kundenModel);
		kundenlist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		kundenlist.setBorder(new EmptyBorder(0, 0, 0, 100));
		scrollPane.setViewportView(kundenlist);
		kundenlist.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				doKundenValueChanged(arg0);
			}
		});
		
		//Label Header
		JLabel lblHeader = new JLabel("alle Kunden");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		scrollPane.setColumnHeaderView(lblHeader);	
	}
	
	public void updateList()
	{
		kundenModel.clear();
		for(Kunde k: betrieb.getKundenliste())
			kundenModel.addElement(k);
	}
	
	public void doKundenValueChanged(ListSelectionEvent arg0)
	{
		Kunde auswahl = kundenlist.getSelectedValue();
		if(auswahl != null)
			kundenpanel.setKunde(auswahl);
	}
}
