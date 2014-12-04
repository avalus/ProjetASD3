import java.lang.*;
import java.util.*;
import java.io.*;

class Page
{
	Vector<Arbre> p;
	
	public Page()
	{
		p = new Vector<Arbre>();
	}
	/*
	public void inserer(int i, Arbre a)
	{
		p.set(i, a);
	}
	*/
	public int getId(int index)
	{
		return p.get(index).getVal();
	}
	
	public void supprimerAt(int index)
	{
		p.removeElementAt(index);
	}
	
	public void creerAt(int index, BufferedReader b)
	{
		p.set(index, Arbre.creer(b));
	}
}
