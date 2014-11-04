import java.lang*;
import java.util*;

class Avl
{
	private int elt;
	private int bal;
	private Avl d;
	private Avl g;
	public Avl<k>( int id,int bal)
	{
	  elt = id;
	  this.bal = bal;
	  d = null;
	  g = null;
	}
	
	public (avl,int) ajouter( int x, avl A )
	{	    
/* retourne le nouvel arbre et la variation de la hauteur */
 
		if (A = null)
		{
 	 		A = new Avl(0,0); 
			A.elt ← x; A.bal ← 0;
			return (A,1);
		}
		else if(x = A.elt)
			return (A,0);
		else if (x > A.elt) alors
			(A.d,int h)= ajouter(x,A.d);
		else
		{
			(A.g,int h) = ajouter(x,A.g); h ← -h;}
			if (h = 0) 
				return (A,0);
			else
				A.bal = A.bal + h;
				A =  equilibrer(A);
			if (A.bal = 0) alors
				return (A,0);
			else
  			return (A,1);
		}
	}

	public Avl rotG(Avl A)
    {
		Avl B; int a,b;
		B = A.d;
		a = A.bal; b = B.bal;
		A.d = B.g; B.g = A; /* rotation */
		A.bal = a-max(b,0)-1;
		B.bal = min(a-2,a+b-2,b-1);
		return B;
	}
     
    public Avl rotD(Avl A)
    {
		Avl B; int a,b;
		B = A.g;
		a = A.bal; b = B.bal;
		A.g = B.d; B.d = A; /* rotation */
		A.bal = a-max(b,0)-1;
		B.bal = min(a-2,a+b-2,b-1);
		return B;
	}

    public Avl dRotG(Avl A)
	{
		A.d = rotD(A.d);
		return rotG(A);
	}

     public Avl dRotD(Avl A)
	{
		A.g = rotG(A.g);
		return rotD(A);
	}
   

/*****Entrée A arbre tel que A g , A d sont AVL -2 ≤ bal(A) ≤ 2*****/
    public avl equilibrer(avl A) 
	{   
		if (A.bal = 2) 
		{
			if (A.d.bal >= 0) 
				return rotG(A);
			else 
			{ 
				A.d = rotD(A.d);
				return rotG(A);
			}
		}
		else if (A.bal = -2) 
		{
			if (A.g.bal <= 0)
				return rotD(A);
			else 
			{
				A.g = rotG(A.g);
				return rotD(A);
			}
		}
		else return A;
	}    

	public (Avl,int) enlever(int x, Avl A)
/* retourne le nouvel arbre et la variation de la hauteur*/\
	{
		if (A = null)
		{
			return (A,0);
		}
		else if (x > A.elt) 
		{
			(A .d ,h) = enlever(x,A.d);
		}
		else if (x < A.elt) 
		{
    		(A .g ,int h) = enlever(x,A.g); h ← -h;}
		else if (A.g = null) 
		{
			return (A.d,-1);
		}
		else if (A.d = null
		{
			return (A.g,-1);
		}
		else 
		{
			A.elt = min(A.d);
			(A.d,h) = OTERMIN(A.d);
		}
		if (h == 0) return (A,0);
		else 
		{
			A.bal ← A.bal + h;
			A ← equilibrer(A);
			if (A.bal = 0) alors return (A,-1);
			else return (A,0);
		}
	}

	public (Avl,int) oterMin(Avl A) 
	{
		if(A.g == null) 
		{
			min = A.elt;
			return (A.d,-1);
		}
		else
		{
			(A .g ,int h) ← oterMin(A.g); h = -h;
		}
		if (h == 0)
			return(A,0);
		else {
			A.bal ← A.bal + h;
			A ← equilibrer(A);
			if (A.bal = 0) 
				return (A,-1);
			else
				return (A,0);
			}
		}

	public int min(Avl A)
	{
		if(A != null)
		{
			Avl courant = A;
			while(courant .g != null)
			{
				courant = courant.g;
			}
         return courant.elt;
		else System.out.println("ERROR, structure vide");
	}
}
