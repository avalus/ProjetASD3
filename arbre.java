
import java.lang.*;
import java.util.*;
import java.io.*;


class Arbre {

  char val;
  Arbre down, next;
  final static char FM = '\0';// pour le marquage du fin de mot
  private static Stack path = new Stack ();//variable global, utile pour l'affichage 
  private static  String mot = "";

  public Arbre(char val) { this.val = val ;down = next =null; }
  public Arbre(char val, Arbre b, Arbre s) {
    this.val = val ;
    this.down = b ; next = s;
  }
 

   public static char getChar(String s, int i) {
    return i < s.length() ? s.charAt(i) : FM;
  } 

   public static boolean contient(String s, int i, Arbre t) {
      char c = getChar(s,i) ;  // premier caractère du suffixe
      if (c == FM) {return t.val == FM; // car liste triée
      }else{ for ( ; t != null ; t = t.next) {if (c == t.val){ return contient(s, i+1, t.next); 
                                              }else if (c < t.val){ return false; } // car liste triée
               }
               return false ;
       }
     }

      /* Conserver une interface simple */
      public static boolean contient(String s, Arbre t) { return contient(s, 0, t); }



      public static Arbre ajouter(Arbre a, String s) { return ajouter(a, s, 0) ; }

  /* nous voulons ajouter le mot w à un Arbre A = (c1, A1) ; ⋯ ; (cn, An).
     Si w est le mot vide, on ajoute ('\0', _ ) à A.
     Sinon, w = cw' et nous procédons un peu comme pour un ajout dans une liste triée,...
  */
       public static Arbre ajouter(Arbre t, String s, int i) {//  t peut être vide
           char c = getChar(s, i) ;
           if (c == FM) {if (t != null && t.val == FM) {return t ; 
                        } else {return new Arbre(FM, null, t) ; // Ajoute effectivement le mot vide.  
                        }
            }else {return ajoutDansList(t, c, s, i+1) ;
            }
          } // ne renvoie jamais null


              
 	public static Arbre ajoutDansList(Arbre t, char c, String s, int i) {
	      if (t == null || c < t.val) {return new Arbre(c, ajouter(null, s, i), t); // Ajouter devant   
               } else if (c == t.val) {  return new Arbre(c,ajouter(t.down, s, i), t.next) ;
               } else {return new Arbre(t.val, t.down,ajoutDansList(t.next, c, s, i)); //  ajouter plus loin    
               }
            }
	
	
	

         public static Arbre creer(BufferedReader in) {

                 Arbre r = null ;

                 String line ;

                 try {

                       while ((line = in.readLine()) != null) {

                             r = ajouter(r, line) ;

                        }

                  }catch (IOException e){System.err.println(e.getMessage()) ;

                                         System.exit(2);

                   }

              return r ;

        }
  public static void affiche_(PrintWriter out, Arbre t) {

  if (t != null) {

    char c = t.val ;
    

    if (c == FM) {
      mot +=path.toString();
      String mm ="";
      mm += mot;
      String m = "";
      for(int i = 0; i<mot.length(); ++i)
       {
           int j = i+1;
           int car =(int)mot.charAt(i); 
	  if( 33 <= car && car <= 127){ if((int)mot.charAt(i) == 44 &&(int)mot.charAt(j) == 44){System.out.println(m);m = "";}else if((int)mot.charAt(i) != 44) {m += mot.charAt(i);}
          }
       }
      

      mot = ""; 

    } else { 

       path.push(c); affiche_(out, t.down); path.pop() ;

    }

      affiche_(out, t.next) ;

  }

}

	public static void affiche(PrintStream o, Arbre t) {
		 PrintWriter out = new PrintWriter (o) ;
		 affiche_(out, t) ;
		 out.flush() ; // C'est plus rapide, mais il faut vider
	}

   public static void affiche(Arbre t) { affiche(System.out, t); }


             public static Arbre inter(Arbre a, Arbre b) {
                  if (a.val == FM && b.val == FM) { return new Arbre(FM, null, interList(a.next, b.next)) ;
                  } else { return interList(a, b); }
              }



              public static Arbre interList(Arbre a, Arbre b) {
                   if (a == null || b == null) {
                        return null ;
                    } else if (a.val < b.val) { return interList(a.next, b) ;
                    } else if (b.val < a.val) { return interList(a, b.next) ;
                    } else {  Arbre d = inter(a.down, b.down) ;// a.val == b.val
                              if (d == null) {  return interList(a.next, b.next) ;
                               } else { return new Arbre(a.val, d, interList(a.next, b.next)) ; }
                    }
                }


} 
   
 
