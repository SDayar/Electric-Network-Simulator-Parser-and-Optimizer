/**
 * Classe Maison
 * @author BALDE Asmaou
 **/

package org.leanGrid.model.util;

import java.util.Objects;

/**
 * Représente une maison avec un niveau de consommation électrique.
 */
public class Maison
{
    /** Nom de la maison */
    private String nom;
    /** Le niveau de consommation de la maison */
    private Consommation consommation;

    /**
     * Construit une maison avec un nom et un niveau de consommation donné.
     * @param nom le nom du générateur
     * @param consommation le niveau de consommation de la maison
     */
    public Maison(String nom, Consommation consommation)
    {
        this.nom = nom;
        this.consommation = consommation;
    }

    /**
     * Retourne le nom de la maison.
     * @return le nom de la maison
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le niveau de consommation de la maison.
     * @return le niveau de consommation de la maison
     */
    public Consommation getConsommation() {
        return consommation;
    }

    /**
     * Modifie le niveau de consommation d'une maison.
     * @param consommation le nouveau niveau de consommation
     */
    public void setConsommation(Consommation consommation) {
        this.consommation = consommation;
    }

    /**
     * Modifie le nom de la maison.
     * @param nom le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Compare cette maison à un autre objet.
     * Deux Maisons sont égales si elles ont le même nom
     * @param obj l'objet à comparer
     * @return true si les maisons sont équivalentes, false sinon
     */
    @Override
    public boolean equals(Object obj) {
       if(this == obj) return true;
       if(obj == null || this.getClass() != obj.getClass()) return false;
       Maison maison = (Maison)obj;
       return Objects.equals(this.nom, maison.nom);
    }

    /**
     * Redéfinition du hashCode de maison
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.nom);
    }

    /**
     * Retourne une représentation textuelle de la maison.
     * @return une chaîne décrivant la maison
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Maison(").append(nom).append(", ").append(consommation.name()).append(")");
        return sb.toString();
    }
}
