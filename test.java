import java.lang*;
import java.util*;
import java.io.*;
import java.util.Scanner;
public class Test
{

	public static void main(string []args)
	{
		String chaine = "";
		String fichier ="";
		Chapitre c;
		
   		int [] tab;
		tab = new int[args.length-1];
		
		for(int i = 1; i < args.length; ++i)
		{	try{
				InputStream ips = new FileInputStream(args[i]); 
				InputStreamReader ipsr = new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					System.out.println(ligne);
					chaine+=ligne+"\n";
			    	}
			        br.close(); 
			   }		
			  catch (Exception e){
				System.out.println(e.toString());
			  }
		}
		

	}


}
