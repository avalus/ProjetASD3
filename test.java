import java.lang.*;
import java.util.*;
import java.io.*;

class Test
{
	public static void main(String []args)
	{
		Page p1 = new Page();
		
		for(int i = 0; i < args.length-1; ++i) // dico inclus
		{
			
			p1.p.add(i, new Arbre());
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
		
		Arbre.affiche(p1.p.get(p1.p.size()-1));
	}

}
