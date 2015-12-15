package kellerbuch.persistence;

import javax.persistence.*;

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
}
