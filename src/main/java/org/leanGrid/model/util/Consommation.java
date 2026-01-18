/**
 * Classe Maison
 * @author BALDE Asmaou
 **/


package org.leanGrid.model.util;


/**
 * Représente les niveaux de consommation électrique possibles pour une maison.
 * Chaque niveau est associé à une valeur fixe en KW.
 */

public enum Consommation
{

   BASSE(10), NORMAL(20), FORTE(40);

    /** Valeur de consommation en kW associée à l'énumération */
   private final int value;

    /**
     * Constructeur privé de l'énumération.
     * @param value la valeur de consommation en KW
     */
   private Consommation(final int value){
       this.value = value;
   }

    /**
     * Retourne la valeur de la consommation en KW
     * @return la valeur de la consommation
     */
   public int getValue(){
       return value;
   }

   public static String toStringAll(){
       Consommation[] c= Consommation.values();
       StringBuffer sb = new StringBuffer("[");
       for(int i = 0; i < c.length-1; i++){
           sb.append(c[i]+ ", ");
       }
       sb.append(c[c.length-1]+ "]");

       return sb.toString();
   }

}
