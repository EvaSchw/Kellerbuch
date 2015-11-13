package kellerbuch.gui.rechnungen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kellerbuch.fachlogik.Rechnung;
import kellerbuch.fachlogik.Winzerbetrieb;

import java.awt.BorderLayout;
import java.awt.Font;

public class Rechnungsfenster extends JDialog
{
	private Winzerbetrieb wb;
	
	private JList<Rechnung> rechnungslist;
	private DefaultListModel<Rechnung> rechnungModel = new DefaultListModel<Rechnung>();
	private Rechnungspanel rechnungspanel;
	
	public Rechnungsfenster(Winzerbetrieb betrieb)
	{		
		this.wb = betrieb;
		
		this.setTitle("Alle Rechnungen");
		this.setSize(800, 500);
		
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
		
		//Rechnungspanel
		rechnungspanel = new Rechnungspanel(this, wb);
		splitPane.setRightComponent(rechnungspanel);
		
		//Rechnungsliste
		rechnungslist = new JList<Rechnung>(rechnungModel);
		rechnungslist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rechnungslist.setBorder(new EmptyBorder(0, 0, 0, 100));
		scrollPane.setViewportView(rechnungslist);
		rechnungslist.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				doRechnungValueChanged(arg0);
			}
		});

		//Label Header
		JLabel lblHeader = new JLabel("alle Rechnungen");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		scrollPane.setColumnHeaderView(lblHeader);
	}
	
	public void updateList()
	{
		rechnungModel.clear();
		for(Rechnung r: wb.getRechnungsliste())
			rechnungModel.addElement(r);
	}
	
	public void doRechnungValueChanged(ListSelectionEvent arg0)
	{
		Rechnung auswahl = rechnungslist.getSelectedValue();
		if(auswahl != null)
			rechnungspanel.setRechnung(auswahl);
	}
}
