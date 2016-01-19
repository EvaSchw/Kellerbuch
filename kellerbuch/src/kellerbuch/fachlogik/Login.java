package kellerbuch.fachlogik;

import javax.persistence.*;

@Entity
@Table(name="l_login")
@NamedQuery(name="Login.findAll", query="Select l from Login l")
public class Login
{
	@Id
	@JoinColumn(name="l_k_id")
	@OneToOne(fetch=FetchType.LAZY)
	private Kunde kunde;
	@Column(name="l_user")
	private String username;
	@Column(name="l_passwd")
	private String passwd;
	
	public Login()
	{
	}
	
	public Login(Kunde kunde, String username, String passwd)
	{
		this.kunde = kunde;
		setUsername(username);
		setPasswd(passwd);
	}
	
	public Kunde getKunde()
	{
		return this.kunde;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPasswd()
	{
		return passwd;
	}
	
	public void setPasswd(String passwd)
	{
		this.passwd = passwd;
	}
	
	@Override
	public String toString()
	{
		return kunde + ", " + username + ", " + passwd;
	}
}
