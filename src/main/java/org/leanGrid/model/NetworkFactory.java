/**
 * Classe NetworkFactory
 * @author YAHIATENE Melissa
 * @see Generateur
 * @see Maison
 * @see Connection
 * @see Reseau
 **/

package org.leanGrid.model;

import java.util.List;

import org.leanGrid.model.util.Connection;
import org.leanGrid.model.util.Consommation;
import org.leanGrid.model.util.Generateur;
import org.leanGrid.model.util.Maison;
import org.leanGrid.model.util.Reseau;

/**
 * Classe utilitaire centralisant la création des entités du réseau.
 * Fournit des méthodes statiques pour instancier les objets principaux du modèle :
 * <ul>
 *     <li>{@link Generateur} : à partir d'un nom et d'une capacité</li>
 *     <li>{@link Maison} : à partir d'un nom et d'une consommation</li>
 *     <li>{@link Connection} : à partir d'une maison et d'un générateur</li>
 *     <li>{@link Reseau} : à partir de listes de générateurs, de maisons et d'un ensemble de connexions</li>
 * </ul>
 */
public class NetworkFactory {

    /**
     * Crée une instance de {@link Generateur} à partir d'un nom et d'une capacité maximale.
     *
     * @param nom le nom du générateur
     * @param capacite la capacité maximale du générateur
     * @return une nouvelle instance de {@code Generateur}
     */
    public static Generateur fabricGenerateur(String nom, int capacite){
        return new Generateur(nom, capacite);
    }

    /**
     * Crée une instance de {@link Maison} à partir d'un nom et d'une consommation.
     *
     * @param nom le nom de la maison
     * @param c la consommation associée à la maison
     * @return une nouvelle instance de {@code Maison}
     */
    public static Maison fabricMaison(String nom, Consommation c){
        return new Maison(nom, c);
    }

    /**
     * Crée une instance de {@link Connection} entre une maison et un générateur.
     *
     * @param m la maison à connecter
     * @param g le générateur à connecter
     * @return une nouvelle instance de {@code Connection}
     */
    public static Connection fabricConnection(Maison m, Generateur g){
        return new Connection(m,g);
    }

    /**
     * Crée une instance de {@link Reseau} à partir d'une liste de générateurs, d'une liste de maisons
     * et d'un ensemble de connexions.
     *
     * @param generateurs la liste des générateurs du réseau
     * @param maisons la liste des maisons du réseau
     * @param connections l'ensemble des connexions entre maisons et générateurs
     * @return une nouvelle instance de {@code Reseau}
     */
    public static Reseau fabricReseau(List<Generateur> generateurs, List<Maison> maisons, List<Connection> connections){
        return new Reseau(generateurs, maisons, connections);

}
}
