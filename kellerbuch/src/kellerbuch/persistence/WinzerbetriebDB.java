package kellerbuch.persistence;

import javax.persistence.*;

import kellerbuch.fachlogik.Kunde;
import kellerbuch.fachlogik.Rechnung;
import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;

public class WinzerbetriebDB extends Winzerbetrieb
{
	private EntityManager em;
	
	public WinzerbetriebDB() throws Exception
	{
		try
		{
			em = Persistence.createEntityManagerFactory("kellerbuch").createEntityManager();
		}
		catch(Exception e)
		{
			throw new Exception("kann nicht zur Datenbank verbinden", e);
		}
	}
	
	@Override
	public void weineAnlegen(Weine w) throws Exception
	{
		try
		{
			em.getTransaction().begin();
			if(!em.contains(w))
				em.persist(w);
			else
				throw new Exception("Dieser Wein ist bereits vorhanden!");
			
			em.getTransaction().commit();
		}
		catch (Exception e)
		{
			em.getTransaction().rollback();
			throw new Exception("Fehler beim Speichern eines Weines in die Datenbank!", e);
		}
	}
	
	@Override
	public void kundenAnlegen(Kunde k) throws Exception
	{
		try
		{
			em.getTransaction().begin();
			if(!em.contains(k))
				em.persist(k);
			else
				throw new Exception("Dieser Kunde ist bereits vorhanden!");
			
			em.getTransaction().commit();
		}
		catch (Exception e)
		{
			em.getTransaction().rollback();
			throw new Exception("Fehler beim Speichern eines Kunden in die Datenbank!", e);
		}
	}
	
	@Override
	public void rechnungAnlegen(Rechnung r) throws Exception
	{
		try
		{
			em.getTransaction().begin();
			if(!em.contains(r))
				em.persist(r);
			else
				throw new Exception("Diese Rechnung ist bereits vorhanden!");
			
			em.getTransaction().commit();
		}
		catch (Exception e)
		{
			em.getTransaction().rollback();
			throw new Exception("Fehler beim Speichern einer Rechnung in die Datenbank!", e);
		}
	}
	
	@Override
	public void weineloeschen(Weine w) throws Exception
	{
		try
		{
			em.getTransaction().begin();
			em.remove(w);
			em.getTransaction().commit();
		}
		catch (Exception e)
		{
			em.getTransaction().rollback();
			throw new Exception("Fehler beim Löschen eines Weines", e);
		}
	}
}
