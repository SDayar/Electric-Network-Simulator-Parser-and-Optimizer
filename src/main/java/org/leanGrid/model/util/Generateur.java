/**
 * Classe Maison
 * @author BALDE Asmaou
 **/

package org.leanGrid.model.util;

import java.util.Objects;

/**
 * Représente un générateur électrique permettant d'alimenter des maisons.
 * Chaque générateur possède un nom et une capacité maximale en KW.
 **/
public class Generateur
{
    /** Nom du générateur */
    private String nom;
    /** Capacité maximale du générateur en KW */
    private int capacite;
    /** Charge du générateur (selon les maisons connectées à lui) */
    private int chargeActuelle;

    /**
     * Construit un générateur avec un nom et une capacité donnée.
     * @param nom le nom du générateur
     * @param capacite la capacité maximale en kW
     */
    public Generateur(String nom, int capacite)
    {
        this.nom = nom;
        this.capacite = capacite;
        chargeActuelle = 0;
    }

    /**
     * Retourne la capacité maximale du générateur.
     * @return la capacité en kW
     */
    public int getCapacite()
    {
        return this.capacite;
    }

    /**
     * Retourne le nom du générateur.
     * @return le nom du générateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom du générateur.
     * @param nom le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne la charge actuelle du générateur.
     * @return la charge actuelle
     */
    public int getChargeActuelle()
    {
        return chargeActuelle;
    }

    /**
     * Modifie la charge du générateur.
     * @param newCharge la nouvelle charge du générateur
     */
    public void setChargeActuelle(int newCharge) {
        this.chargeActuelle = newCharge;
    }

    /**
     * Modifie la capacité maximale du générateur.
     * @param capacite la nouvelle capacité en kW
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }


    /**
     * Compare ce générateur à un autre objet.
     * Deux générateurs sont égaux s'ils ont le même nom et la même capacité.
     * @param obj l'objet à comparer
     * @return true si les générateurs sont équivalents, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Generateur) || obj == null)
            return false;
        if (obj == this)
            return true;
        Generateur g = (Generateur) obj;
        return g.nom.equals(this.nom);
    }
    
    /**
     * Redéfinition du hashCode de maison
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.nom);
    }

    /**
     * Retourne une représentation textuelle du générateur.
     * @return une chaîne décrivant le générateur
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Generateur(").append(nom).append(", ").append(capacite).append("KW").append(", Charge actuelle = ").append(getChargeActuelle()).append("KW)");
        return sb.toString();
    }
}
