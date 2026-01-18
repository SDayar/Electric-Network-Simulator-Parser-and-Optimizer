/**
 * Classe Connection
 * @author YAHIATENE Melissa
 * @see Generateur
 * @see Maison
 **/

package org.leanGrid.model.util;

import java.util.HashSet;
import java.util.Objects;

/**
 * Représente une connexion entre une maison et un générateur
 *
 * @see Generateur
 * @see Maison
 */

public class Connection{

    //Attributs
    /**
     * La maison à connecter
     */
    private Maison m;
    /**
     * Le générateur à associer à la maison
     */
    private Generateur g;

    //Constructeur
    /**
     * Crée une nouvelle connexion entre une maison et un générateur.
     * @param m la maison concernée
     * @param g le générateur associé
     */
    public Connection(Maison m, Generateur g) {
        this.m = m;
        this.g = g;
        g.setChargeActuelle(g.getChargeActuelle() + (m.getConsommation().getValue())); // mettre à jour la charge du générateur
    }

    //Getters
    /**
     * Retourne la maison connectée.
     *
     * @return la maison de cette connexion
     */
    public Maison getMaison() {
        return m;
    }

    /**
     * Retourne le générateur associé.
     *
     * @return le générateur de cette connexion
     */
    public Generateur getGenerateur() {
        return g;
    }

    //Setters
    /**
     * Définit la nouvelle maison de cette connexion.
     *
     * @param m la maison à connecter
     */
    public void setMaison(Maison m) {
        this.m = m;
    }

    /**
     * Définit le nouveau générateur de cette connexion.
     *
     * @param g le générateur à associer
     */
    public void setG(Generateur g) {
        this.g = g;
    }

    /**
     * Vérifie si deux connexions sont équivalentes : si elles relient la même maison au même générateur.
     *
     * @param obj la connexion à comparer
     * @return true si les deux connexions sont identiques, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Connection c = (Connection) obj;
        return Objects.equals(this.getMaison(), c.getMaison()) && Objects.equals(this.getGenerateur(), c.getGenerateur());
    }

    /**
     * Calcule le code de hachage de cette connexion en se basant sur la maison et le générateur associés.
     * Ce code est utilisé pour garantir l'unicité des connexions dans la {@link HashSet} de connexions.
     *
     * @return un entier représentant le code de hachage de cette connexion
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getMaison(), this.getGenerateur());
    }

    /**
     * Retourne une représentation textuelle de la connexion
     * @return une chaîne de caractères représentant la connexion
     */
    @Override
    public String toString(){
        return "( "+this.m.getNom()+" "+g.getNom()+" )";
    }

    /**
     * Calcule le rapport entre la consommation de la maison et la capacité du générateur qui lui est associé
     * Utile pour le calcul du taux d'utilisation du générateur
     * @return ce rapport calculé
     */
    public Double poids(){
        return (double) m.getConsommation().getValue() / g.getCapacite() ;
    }
}
