import java.lang.*;
import java.util.*;
import java.io.*;
import java.util.Scanner;

/*!
 * Classe Arbre, gestion des arbres des application
 */

class Arbre
{
	char val;
	Arbre down, next;
	final static char FM = '\0'; //fpour le marquage de fin de mot
	private static Stack<Character> path = new Stack<Character>();//variable global, utile pour l'affichage 
    private static String mot = "";
    private static int size = 0;//nombre de mot dans l'arbre
    
    public Arbre()
    {
		this.val = '\0';
		down = next = null;
              
	}
    
	public Arbre(char val)
	{
		this.val = val; down = next = null;
                
	}
	public int getSize()
        {
		return size;
	}

	public int getVal()
	{
		return val;
	}
	
        public void increment(){size++;}
	public Arbre(char val, Arbre b, Arbre s)
	{
		this.val = val;
		this.down = b; next = s;
	}

	public static char getChar(String s, int i)
	{
		return i < s.length() ? s.charAt(i) : FM;
	} 

	public static boolean contient(String s, int i, Arbre t)
	{
		char c = getChar(s,i) ;  // premier caractère du suffixe
		if (c == FM)
		{
			return t.val == FM; // car liste triée
		}
		else
		{
			for(/*t*/; t != null ; t = t.next)
			{
				if (c == t.val)
				{
					return contient(s, i+1, t.next); 
                }
                else if(c < t.val)
                {
					return false;
				} // car liste triée
			}
			return false;
		}
	}

	/* Conserver une interface simple */
	public static boolean contient(String s, Arbre t)
	{
		return contient(s, 0, t);
	}
	
	public static Arbre ajouter(Arbre a, String s)
	{
		
 
	
		return ajouter(a, s, 0);
	}

	/* nous voulons ajouter le mot w à un Arbre A = (c1, A1) ; ⋯ ; (cn, An).
		Si w est le mot vide, on ajoute ('\0', _ ) à A.
		Sinon, w = cw' et nous procédons un peu comme pour un ajout dans une liste triée...
	*/
	public static Arbre ajouter(Arbre t, String s, int i)//t peut être vide
	{
		char c = getChar(s, i);
                
             
		if(c == FM)
		{
			
			if(t != null && t.val == FM)
			{
				
				return t; 
			}
			else
			{
				
				return new Arbre(FM, null, t); // Ajoute effectivement le mot vide.  
            		}
		}
		else
		{
			return ajoutDansList(t, c, s, i+1);
		}
	} // ne renvoie jamais null
          
 	public static Arbre ajoutDansList(Arbre t, char c, String s, int i)
 	{
		if(t == null || c < t.val)
		{
			return new Arbre(c, ajouter(null, s, i), t); // Ajouter devant   
        }
        else if(c == t.val)
        {
			return new Arbre(c,ajouter(t.down, s, i), t.next);
		}
		else
		{
			return new Arbre(t.val, t.down,ajoutDansList(t.next, c, s, i)); //  ajouter plus loin    
        }
	}

	public void union(Arbre b)
    {
		if (b != null)
		{
    		char c = b.val;

    		if(c == FM)
    		{  
				mot += path.toString();
      		    ajouter(this,mot);
                mot = ""; 
			}
			else
			{
           		path.push(c);
          		this.union(b.down); path.pop();
    	  	}
            this.union(b.next);
		}
	}

 

    public static Arbre creer(BufferedReader in)
    {
		Arbre r = null;
		String line;

		try
		{
			while((line = in.readLine()) != null)
			{
				r = ajouter(r, line);
                             
			}
        }
        catch(IOException e)
        {
			System.err.println(e.getMessage());
            System.exit(2);
        }

		return r;
	}
	
	public static void affiche_(PrintWriter out, Arbre t)
	{
		if(t != null)
		{
			char c = t.val;
			
			if (c == FM)
			{	size++;
				/*mot +=path.toString();
				String mm ="";
				mm += mot;
				String m = "";
				for(int i = 0; i<mot.length(); ++i)
				{
					int j = i+1;
					int car =(int)mot.charAt(i);
					
					if( 33 <= car && car <= 127)
					{
						if((int)mot.charAt(i) == 44 &&(int)mot.charAt(j) == 44)
						{
							System.out.println(m); m = "";
						}
						else if((int)mot.charAt(i) != 44)
						{
							m += mot.charAt(i);
						}
					}
				}
				
				mot = ""; */
				System.out.println(path);
			}
			else
			{ 
				path.push(c); affiche_(out, t.down); path.pop();
			}
			
			affiche_(out, t.next) ;
		}
	}

	public static void affiche(PrintStream o, Arbre t)
	{
		 PrintWriter out = new PrintWriter(o);
		 affiche_(out, t);
		 out.flush(); // C'est plus rapide, mais il faut vider
	}

	public static void affiche(Arbre t){
		affiche(System.out, t);
	}

	public static Arbre inter(Arbre a, Arbre b)
	{
		if(a.val == FM && b.val == FM)
		{
			return new Arbre(FM, null, interList(a.next, b.next));
		}
		else
		{
			return interList(a, b);
		}
	}

	public static Arbre interList(Arbre a, Arbre b)
	{
		if (a == null || b == null)
		{
			return null;
		}
		else if(a.val < b.val)
		{
			return interList(a.next, b);
		}
		else if(b.val < a.val){
			return interList(a, b.next);
		}
		else
		{  
			Arbre d = inter(a.down, b.down); // a.val == b.val
			
                        if (d == null)
                         {
				return interList(a.next, b.next);
                         }
                        else
                        { 
				return new Arbre(a.val, d, interList(a.next, b.next));
			}
		}
	}


	public static void main(String []args)
	{
		Page p1 = new Page();
                Page p2 = new Page();
                EnsDisjoint ens = new EnsDisjoint(args.length-1);
		
		for(int i = 0; i < args.length-1; ++i) // dico inclus
		{
			
			p1.p.add(i, new Arbre());
		}
		
                for(int i = 0; i < args.length-1; ++i) // dico inclus
		{
			
			p2.p.add(i, new Arbre());
		}
		for(int i = 0; i < args.length-1; ++i)
		{
			try
			{
				InputStream ips = new FileInputStream(args[i]);

				InputStreamReader ipsr = new InputStreamReader(ips);

				BufferedReader br = new BufferedReader(ipsr);
				
				p1.creerAt(i, br);
				
				
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}

                for(int i = 0; i < p1.p.size(); ++i)
                {
                  Arbre g = inter( p1.p.get(i),p1.p.get(p1.p.size()-1));
                 if(g != null)
                 { p1.p.set(i,g);}

		}
		
		//affiche(p1.p.get(2));
		System.out.println("\n");
                System.out.println(p1.p.get(3).getVal());
               // Arbre g = inter(p1.p.get(1),p1.p.get(2));
               // affiche(g);
           int l = Integer.parseInt(args[args.length - 1]);
             for(int i = 0; i < p1.p.size(); ++i)
              {
		 for(int j = p1.p.size()-2; j >i ; j--)
		{ 
                   affiche( inter(p1.p.get(i), p1.p.get(j)) );
                   if(inter(p1.p.get(i), p1.p.get(j)).getSize()>= l)
		    {
                      //int a = p1.p.get(i).getVal();
                      //int b = p1.p.get(j).getVal(); 
                     // if(i<p1.p.size()-2)
                        ens.union(i+1,j+1);
                    }
                   System.out.println("\n"); 
                }
	      }

	}



} 
   
 
