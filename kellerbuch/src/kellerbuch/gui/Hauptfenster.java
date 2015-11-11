package kellerbuch.gui;

import javax.swing.*;

import kellerbuch.fachlogik.Winzerbetrieb;
import kellerbuch.gui.kunden.Kundenfenster;
import kellerbuch.gui.rechnung.Rechnungsfenster;
import kellerbuch.gui.weine.Weinfenster;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Hauptfenster extends JFrame
{
	private Winzerbetrieb betrieb;
	
	public Hauptfenster(Winzerbetrieb betrieb)
	{
		this.betrieb = betrieb;
		
		//Hauptfenster
		this.setAlwaysOnTop(true);
		this.setTitle("Kellerbuch");
		this.setMinimumSize(new Dimension(500, 400));
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initfenster();
	}
	
	public void initfenster()
	{
		//Menu-Bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Menus
		JMenu mMenue = new JMenu("Men\u00FC");
		mMenue.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mMenue);
		JMenu mWeine = new JMenu("Weine");
		mWeine.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mWeine);
		JMenu mKunden = new JMenu("Kunden");
		mKunden.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mKunden);
		JMenu mRechnung = new JMenu("Rechnung");
		mRechnung.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mRechnung);
		
		//Menu-Items
		JMenuItem mISchliessen = new JMenuItem("Schlie\u00DFen");
		mISchliessen.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mISchliessen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mMenue.add(mISchliessen);
		
		JMenuItem mIWeineBearbeiten = new JMenuItem("Weine bearbeiten");
		mIWeineBearbeiten.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mIWeineBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doWeineBearbeitenActionPerformed(e);
			}
		});
		mWeine.add(mIWeineBearbeiten);
		
		JMenuItem mIKundenBearbeiten = new JMenuItem("Kunden bearbeiten");
		mIKundenBearbeiten.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mIKundenBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doKundenBearbeitenActionPerformed(arg0);
			}
		});
		mKunden.add(mIKundenBearbeiten);
		
		JMenuItem mIRechnungErstellen = new JMenuItem("Rechnung erstellen");
		mIRechnungErstellen.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mIRechnungErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doRechnungErstellenActionPerformed(e);
			}
		});
		mRechnung.add(mIRechnungErstellen);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		//JPanel Center
		JPanel pCenter = new JPanel();
		getContentPane().add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new GridLayout(2, 1, 0, 0));
		
		//JLabel Firmenname
		JLabel lblFirma = new JLabel("Winzerhof Drozze");
		lblFirma.setForeground(new Color(0, 128, 0));
		lblFirma.setFont(new Font("SansSerif", Font.BOLD, 46));
		lblFirma.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFirma.setHorizontalAlignment(SwingConstants.CENTER);
		pCenter.add(lblFirma);
		
		JLabel lblName = new JLabel("Familie Schwanzelberger");
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblName.setVerticalAlignment(SwingConstants.TOP);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		pCenter.add(lblName);
	}
	
	protected void doWeineBearbeitenActionPerformed(ActionEvent e)
	{
		JDialog weineBearbeiten = new Weinfenster(betrieb);
		weineBearbeiten.setAlwaysOnTop(true);
		weineBearbeiten.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		weineBearbeiten.setVisible(true);
	}
	
	protected void doKundenBearbeitenActionPerformed(ActionEvent arg0)
	{
		JDialog kundenBearbeiten = new Kundenfenster(betrieb);
		kundenBearbeiten.setAlwaysOnTop(true);
		kundenBearbeiten.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		kundenBearbeiten.setVisible(true);
	}
	
	protected void doRechnungErstellenActionPerformed(ActionEvent e)
	{
		JDialog rechnungErstellen = new Rechnungsfenster(betrieb);
		rechnungErstellen.setAlwaysOnTop(true);
		rechnungErstellen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		rechnungErstellen.setVisible(true);
	}
}
