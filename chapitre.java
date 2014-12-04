import java.lang.*;
import java.util.*;
import java.io.*;

class Chapitre
{
	Vector<Vector> c;
	
	public Chapitre()
	{
		c = new Vector<Vector>();
	}
	
	public void ajouterPage(Page p)
	{
		c.add(p);
	}
	
	public void ajouterPageAt(int index, Page p)
	{
		c.add(index, p);
	}
	
	public void supprimerPage(int index)
	{
		c.removeElementAt(index);
	}
}
