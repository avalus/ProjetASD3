import java.lang*;
import java.util*;

class Avl
{
	private int elt, bal;
	private Avl g, d;

	public Avl(int id, int bal)
	{
		elt = id;
		this.bal = bal;
		d = null;
		g = null;
	}
	
	/* Retourne l'arbre après ajout de l'élément x + équilibrage*/
	public Avl ajouter(int x, Avl A)
	{	    
		if (A = null)
		{
 	 		A = new Avl(0,0); 
			A.elt = x; A.bal = 0;

			return (A);
		}
		else if(x = A.elt)
		{
			return (A);
		}
		else if (x > A.elt)
		{
			A.d = ajouter(x, A.d);
		}
		else
		{	
			A.g = ajouter(x, A.g); 
			int h = A.g.bal; h = -h;
		}

		if (h = 0)
		{
			return (A);
		}
		else
		{
			A.bal = A.bal + h;
			A = equilibrer(A);
		}
		
		if (A.bal = 0)
		{
			return (A);
		}
		else
		{
  			return (A);
		}
	}

	/* Entrée A arbre tel que A.g , A.d sont des AVL -2 ≤ bal(A) ≤ 2 */
	public Avl equilibrer(Avl A) 
	{   
		if (A.bal = 2) 
		{
			if (A.d.bal >= 0)
			{
				return rotG(A);
			}
			else 
			{ 
				A.d = rotD(A.d);

				return rotG(A);
			}
		}
		else if (A.bal = -2) 
		{
			if (A.g.bal <= 0)
			{
				return rotD(A);
			}
			else 
			{
				A.g = rotG(A.g);

				return rotD(A);
			}
		}
		else 
		{
			return A;
		}
	}

	/* Retourne l'arbre après suppression de l'élément x + équilibrage */
	public Avl enlever(int x, Avl A)
	{
		if (A = null)
		{
			return (A,0);
		}
		else if (x > A.elt) 
		{
			A.d = enlever(x, A.d);
		}
		else if (x < A.elt) 
		{
	    		A.g = enlever(x, A.g);
		        int h = A.g.bal; h = -h;
		}
		else if (A.g = null) 
		{
			return A.d;
		}
		else if (A.d = null)
		{
			return A.g;
		}
		else 
		{
			A.elt = min(A.d);
			A.d = oterMin(A.d);
		}
		if (h == 0) 
		{
			return A;
		}
		else 
		{
			A.bal = A.bal + h;
			A = equilibrer(A);
			if (A.bal = 0)
			{
				return A;
			}
			else
			{
				return A;
			}
		}
	}
	
	/* Retourne l'arbre après ROTATION GAUCHE */
	public Avl rotG(Avl A)
    	{
		Avl B; 
		int a, b;

		B = A.d;
		a = A.bal; b = B.bal;
		A.d = B.g; B.g = A; /* rotation */
		A.bal = a-max(b,0)-1;
		B.bal = min(a-2, a+b-2, b-1);

		return B;
	}

	/* Retourne l'arbre après ROTATION DROITE */
	public Avl rotD(Avl A)
	{
		Avl B; 
		int a,b;

		B = A.g;
		a = A.bal; b = B.bal;
		A.g = B.d; B.d = A; /* rotation */
		A.bal = a-max(b,0)-1;
		B.bal = min(a-2, a+b-2, b-1);

		return B;
	}

	/* Retourne l'arbre après DOUBLE ROTATION GAUCHE */
    	public Avl dRotG(Avl A)
	{
		A.d = rotD(A.d);

		return rotG(A);
	}

	/* Retourne l'arbre après DOUBLE ROTATION DROITE */
     	public Avl dRotD(Avl A)
	{
		A.g = rotG(A.g);

		return rotD(A);
	}
	
	/* Retourne l'arbre après suppression de l'élément min + équilibrage */
	public Avl oterMin(Avl A) 
	{
		if(A.g == null) 
		{
			min = A.elt;

			return A.d;
		}
		else
		{
			A.g = oterMin(A.g); 
			int h = A.g.bal; h = -h;
		}
		if (h == 0)
		{
			return A;
		}
		else 
		{
			A.bal = A.bal + h;
			A = equilibrer(A);
			if (A.bal = 0)
			{
				return A;
			}
			else
			{
				return A;
			}
		}
	
	/* Retourne l'élement minimum de l'AVL */
	public int min(Avl A)
	{
		if(A != null)
		{
			Avl courant = A;

			while(courant.g != null)
			{
				courant = courant.g;
			}

         		return courant.elt;
		}
		else 
		{
			System.out.println("Erreur : structure vide");
		}
	}
}
