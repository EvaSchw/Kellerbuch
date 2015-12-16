package kellerbuch.gui.weine;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;

import java.awt.Font;

public class Weinfenster extends JDialog
{
	private Winzerbetrieb betrieb;
	private DefaultListModel<Weine> weinModel = new DefaultListModel<Weine>();
	private JList<Weine> weinlist;
	private Weinpanel weinpanel;
	
	
	public Weinfenster(Winzerbetrieb betrieb)
	{
		this.betrieb = betrieb;

		this.setTitle("Weine");
		this.setSize(800, 500);
		this.setResizable(false);
		
		updateList();
		
		initFenster();
	}
	
	public void initFenster()
	{
		//SplitPane
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		//Weinpanel
		weinpanel = new Weinpanel(this, betrieb);
		splitPane.setRightComponent(weinpanel);
		
		//Weinliste
		weinlist = new JList<Weine>(weinModel);
		weinlist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		weinlist.setBorder(new EmptyBorder(0, 0, 0, 100));
		scrollPane.setViewportView(weinlist);
		weinlist.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				doWeineValueChanged(arg0);
				
			}
		});	
		
		//Label Header
		JLabel lblHeader = new JLabel("alle Weine");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		scrollPane.setColumnHeaderView(lblHeader);
	}
	
	public void updateList()
	{
		try
		{
			weinModel.clear();
			for(Weine w: betrieb.getWeinliste())
				weinModel.addElement(w);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	protected void doWeineValueChanged(ListSelectionEvent arg0)
	{
		Weine auswahl = weinlist.getSelectedValue();
		if(auswahl != null)
			weinpanel.setWein(auswahl);
	}
}
