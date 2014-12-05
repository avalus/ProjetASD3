import java.io.File;
import java.util.Scanner;

class EnsDisjoint
{
	private int[] id; 
	private int[] sz;//chaque case sz[id] du tableau contient la taille de l'ensemble
	private int nb; 
	private int N;

	public EnsDisjoint(int N)
	{
		this.N = N;
		int count = N;

		id = new int[N];
		
		for(int i = 0; i < N; i++)
		{
			id[i] = i;//au départ chaque ensemble a pour identifiant son unique elt
		}

		sz = new int[N];
		
		for(int i = 0; i < N; i++){
			sz[i] = 1; //au départ chaque ensemble n'a qu'un elt
		}

		System.out.print("Liste des ensembles de début: ");
		
		for(int a = 0; a < N; a++)
		{
			System.out.print(id[a]);
		}
	}//fin constructeur

	public int nbElt() //nb total d'ensembles courant
	{ 
		return nb;
	}

	public boolean connecte(int p, int q)
	{
		return classe(p) == classe(q); 
	}

	public int classe(int p)
	{
		while (p != id[p])
		{ 
			p = id[p];
		}
		
		return p;
	}
	
    /* 
	public int classe(int p) {
   		if (p != id[p]){id[p] = find(id[p]);}
   		return id[p];
	}*/
	
	public void union(int p, int q)
	{
		int i = classe(p);
		int j = classe(q);
		if (i == j)
		{
			return;
		}

		System.out.println("Le nombre de pages du chapitre contenant la page " + p + " est : " + sz[i]);
		System.out.println("Le nombre de pages du chapitre contenant la page " + q + " est : " + sz[j]);
		
		if(sz[i] < sz[j]) //si la taille du groupe de p est plu petit que celle de q
		{ 
			id[i] = j; //alors l'id du groupe de p devient celui de q
			sz[j] += sz[i]; // on met à jour la taille du nouveau groupe de q obtenu
		}
		else //sinon on  la même opération de façon symétrique
		{ 
			id[j] = i;
			sz[i] += sz[j];
		}
        nb--; //decrement nb

		System.out.println(p + " et " + q + " sont maintenant connectés.");
		System.out.println("Le chapitre contenant la page  " + p + " est : "+ id[p]);
		System.out.println("Le chapitre contenant la page  "+ q + " est : " + id[q]);
		System.out.print(" ensemble courant : ");
		
		for(int a = 0; a < N; a++)
		{
		     System.out.print(id[a]);
		}
		
		System.out.println("\n");
	}//fin union()
      public void affiche()
       {


       }

	 
}
