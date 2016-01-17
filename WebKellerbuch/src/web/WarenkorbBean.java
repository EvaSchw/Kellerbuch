package web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import kellerbuch.fachlogik.Rechnungspositionen;
import kellerbuch.fachlogik.Weine;

@ManagedBean(name="warenkorb")
@SessionScoped
public class WarenkorbBean
{
	private List<Rechnungspositionen> warenListe;
	

	public void neu(Rechnungspositionen neu) throws Exception{
		if(neu != null){
			if(!warenListe.contains(neu)){
				if(!warenListe.add(neu)){
					throw new Exception("Ein unbekannter Fehler ist aufgetreten als die "
							+ "Rechnungsposition zum Warenkorb hinzugefügt wurde!");
				}
			} else {
				throw new Exception("Rechnungsposition wurde bereits hinzugefügt");
			}
		} else {
			throw new Exception("Rechnungsposition darf nicht leer sein");
		}
	}
	
	public void neu(Weine wein, int anzahl) throws Exception{
		if(wein != null){
			Rechnungspositionen r = new Rechnungspositionen(wein, anzahl);
			if(!warenListe.contains(r)){
				if(!warenListe.add(r)){
					throw new Exception("Ein unbekannter Fehler ist aufgetreten als die "
							+ "Rechnungsposition zum Warenkorb hinzugefügt wurde!");
				}
			} else {
				throw new Exception("Rechnungsposition wurde bereits hinzugefügt");
			}
		} else {
			throw new Exception("Rechnungsposition darf nicht leer sein");
		}
		System.out.println("--- START WARENKORB ---");
		System.out.println("Warenkorb: ");
		for(Rechnungspositionen pos : warenListe)
			System.out.println("Wein: "+pos.getWein()+ " - Menge: " +pos.getMenge());
		System.out.println("--- ENDE WARENKORB ---");
	}
	
	
	public WarenkorbBean()
	{
		warenListe = new ArrayList<Rechnungspositionen>();
	}

}
