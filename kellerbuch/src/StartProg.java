import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kellerbuch.fachlogik.Winzerbetrieb;
import kellerbuch.gui.Hauptfenster;
import kellerbuch.persistence.WinzerbetriebDB;

public class StartProg
{	
	public static void main(String[] args)
	{
		try
		{
			Winzerbetrieb wb = new WinzerbetriebDB();
			Hauptfenster hf = new Hauptfenster(wb);
			hf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			hf.setVisible(true);
			
//			SimpleDateFormat sdf = new SimpleDateFormat("d.M.y");
//			Date d1 = sdf.parse("1.1.2015");
//			Date d2 = sdf.parse("25.2.2015");
//			Weine w1 = new Weine("GV Alte Rebe", 2014, 12, true, "Kremstal", 0.75, 1000, 6.5);
//			wb.weineAnlegen(w1);
//			Weine w2 = new Weine("Riesling Urgestein", 2013, 13, true, "Kamptal", 0.75, 2000, 6);
//			wb.weineAnlegen(w2);
//			Kunde k1 = new Kunde(null, "Huber", "Franz", "Im Gei�eck", "4", 3552, "Dross", "�sterreich");
//			wb.kundenAnlegen(k1);
//			Kunde k2 = new Kunde(null, "Mayer", "Susanne", "Ringstra�e", "345", 1010, "Wien", "�sterreich");
//			Rechnungspositionen pos1 = new Rechnungspositionen(w1, 2);
//			Rechnungspositionen pos2 = new Rechnungspositionen(w2, 5);
//			Rechnungspositionen pos3 = new Rechnungspositionen(w2, 1);
//			Rechnungspositionen pos21 = new Rechnungspositionen(w1, 2);
//			Rechnungspositionen pos22 = new Rechnungspositionen(w2, 5);
//			Rechnungspositionen pos32 = new Rechnungspositionen(w2, 1);
//			Rechnungspositionen pos13 = new Rechnungspositionen(w1, 2);
//			Rechnungspositionen pos24 = new Rechnungspositionen(w2, 5);
//			Rechnungspositionen pos35 = new Rechnungspositionen(w2, 1);
//			wb.kundenAnlegen(k2);
//			Rechnung r1 = new Rechnung(1, d1, k1);
//			r1.neueRechungsposition(pos1);
//			r1.neueRechungsposition(pos2);
//			r1.neueRechungsposition(pos21);
//			r1.neueRechungsposition(pos22);
//			r1.neueRechungsposition(pos32);
//			r1.neueRechungsposition(pos13);
//			r1.neueRechungsposition(pos24);
//			r1.neueRechungsposition(pos35);
//			wb.rechnungAnlegen(r1);
//			Rechnung r2 = new Rechnung(2, d2, k2);
//			r2.neueRechungsposition(pos3);
//			wb.rechnungAnlegen(r2);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
