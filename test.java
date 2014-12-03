import java.lang*;
import java.util*;
import java.io.*;
import java.util.Scanner;
public class Test
{

	public static void main(string []args)
	{
		  try{

			InputStream ips = new FileInputStream("txt"); 

			InputStreamReader ipsr = new InputStreamReader(ips);

			BufferedReader br=new BufferedReader(ipsr);

                	Arbre a = creer(br);

                        affiche(a);

		}		

		catch (Exception e){

			System.out.println(e.toString());

		}
		

	}


}
