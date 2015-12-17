package kellerbuch.persistence;

import java.util.List;

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
	public List<Weine> getWeinliste() throws Exception
	{
		try
		{
			TypedQuery<Weine> query = em.createNamedQuery("Weine.findAll", Weine.class);
			return query.getResultList();
		}
		catch (Exception e)
		{
			throw new Exception("Fehler beim Abrufen der Weine", e);
		}
	}
	
	@Override
	public List<Kunde> getKundenliste() throws Exception
	{
		try
		{
			TypedQuery<Kunde> query = em.createNamedQuery("Kunden.findAll", Kunde.class);
			return query.getResultList();
		}
		catch (Exception e)
		{
			throw new Exception("Fehler beim Abrufen der Kunden", e);
		}
	}
	
	@Override
	public List<Rechnung> getRechnungsliste() throws Exception
	{
		try
		{
			TypedQuery<Rechnung> query = em.createNamedQuery("Rechnung.findAll", Rechnung.class);
			return query.getResultList();
		}
		catch (Exception e)
		{
			throw new Exception("Fehler beim Abrufen der Rechnungen", e);
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
	
	@Override
	public void kundenloeschen(Kunde k) throws Exception
	{
		try
		{
			em.getTransaction().begin();
			em.remove(k);
			em.getTransaction().commit();
		}
		catch (Exception e)
		{
			em.getTransaction().rollback();
			throw new Exception("Fehler beim Löschen eines Weines", e);
		}
	}
	
	@Override
	public void speichern() throws Exception
	{
		try
		{
			em.getTransaction().begin();
			em.getTransaction().commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new Exception("Fehler beim Speichern der Änderungen", e);
		}
	}
	
	@Override
	public int getGroessteRechnungsnr() throws Exception
	{
		int i = -1;
		for(Rechnung r: getRechnungsliste())
		{
			if(r.getRechnungsnr() > i)
				i = r.getRechnungsnr();
		}
		return ++i;
	}
}
