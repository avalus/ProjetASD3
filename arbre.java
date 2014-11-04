import java.lang*;
import java.util*;

class Arbre
{
	private char elt;
	private Arbre []fils;

	public Arbre( char id)
	{
	  elt = id;
	  fils = new Arbre()[26];

	}

	public void inserer( char car )
	{
	  this.elt = car;
      this.fils = new Arbre()[26];
	}

}
