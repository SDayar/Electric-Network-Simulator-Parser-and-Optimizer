/**
 * Classe NetworkSerializer
 * @author YAHIATENE Melissa
 * @author BALDE Asmaou
 * @since 09/12/25
 * @see Generateur
 * @see Maison
 * @see Connection
 * @see Consommation
 * @see NetworkFactory
 * @see NetworkOperator
 **/

package org.leanGrid.model;

import org.leanGrid.model.util.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.exit;


/**
 * La classe {@code NetworkSerializer} fournit des méthodes utilitaires pour
 * analyser (parser) et exporter des réseaux électriques représentés sous
 * forme textuelle.
 * <p>
 * Elle permet de :
 * <ul>
 *   <li>Lire un fichier texte décrivant un réseau (générateurs, maisons, connexions)</li>
 *   <li>Construire un objet {@code Reseau} valide à partir de ce fichier</li>
 *   <li>Exporter un réseau existant vers un fichier texte au format attendu</li>
 * </ul>
 * <p>
 * Le format attendu pour chaque ligne est :<br>
 * <ul>
 *   <li>{@code generateur(nom, capacite).}</li>
 *   <li>{@code maison(nom, consommation).}</li>
 *   <li>{@code connexion(entite1, entite2).}</li>
 * </ul>
 * Les entités doivent être déclarées dans l'ordre : générateurs → maisons → connexions.
 */
public class NetworkSerializer {
    enum Phase { GENERATEUR, MAISON, CONNEXION }
    /**
     * Analyse un fichier texte décrivant un réseau et construit l'objet {@code Reseau}.
     * <p>
     * Le fichier doit respecter le format attendu et l'ordre des entités.
     * En cas d'erreur de format ou de contenu invalide, une exception est levée
     * ou un message d'erreur est affiché.
     *
     * @param file chemin du fichier texte à analyser
     * @return un objet {@code Reseau} construit à partir du fichier
     */
    public static Reseau parser(String file){

        NetworkOperator net = new NetworkOperator();
        Reseau reseau = null;

        try(BufferedReader br = new BufferedReader(new FileReader(file))){

            String ligne = null;
            List<String> lines = new ArrayList<String>();

            while( (ligne=br.readLine()) !=null ) {
                lines.add(ligne);
            }

            

            Phase currentPhase = Phase.GENERATEUR;

            for (String line : lines) {

                try {

                    line = line.trim();
                    line = line.replaceAll("\\s+", " ");
                    line = line.replaceAll("\\s*\\(\\s*", "(");
                    line = line.replaceAll("\\s*\\)\\s*", ")");
                    line = line.replaceAll("\\s*,\\s*", ",");
                    line = line.replaceAll("\\s*\\.\\s*$", ".");


                    if(line.isEmpty()){
                        throw new IllegalArgumentException("Erreur sur la ligne : < " + line + " >\n\tCette ligne est vide.");
                    }

                    else if (!line.endsWith(".")) {
                        throw new IllegalArgumentException("Erreur sur la ligne : < " + line + " >\n\tCette ligne ne se termine pas par un point (.)");
                    }

                    else if (!line.contains("(") || !line.contains(")") || line.indexOf("(") > line.indexOf(")")) {
                        throw new IllegalArgumentException("Erreur sur la ligne : < " + line + " >\n\tFormat invalide : parenthèses manquantes ou mal placées.");
                    }

                    else if ( (line.split("\\(")[1].split("\\)")[0].split(",")).length != 2 ){
                        throw new IllegalArgumentException("Erreur sur la ligne : < " + line + " >\n\tFormat ligne invalide ! Format attendu : entité(param1,param2).");
                    }

                    else if (line.startsWith("generateur")) {
                        if (currentPhase != Phase.GENERATEUR) {
                            throw new IllegalArgumentException("Erreur sur la ligne : < " + line + " >\n\tLes générateurs doivent être déclarés en premier et regroupés !");
                        }
                        net.getGenerateurs().add(parserGenerateur(line));
                    }

                    else if (line.startsWith("maison")) {
                        if (currentPhase == Phase.GENERATEUR) {
                            // première maison rencontrée → on bascule définitivement en phase MAISON
                            currentPhase = Phase.MAISON;
                        } else if (currentPhase != Phase.MAISON) {
                            throw new IllegalArgumentException("Erreur sur la ligne : < " + line + " >\n\tLes maisons doivent apparaître après les générateurs et avant les connexions !");
                        }
                        net.getMaisons().add(parserMaison(line));
                    }

                    else if (line.startsWith("connexion")) {
                        if (currentPhase != Phase.MAISON && currentPhase != Phase.CONNEXION) {
                            throw new IllegalArgumentException("Erreur sur la ligne : < " + line + " >\n\tLes connexions doivent apparaître après les maisons !");
                        }
                        currentPhase = Phase.CONNEXION;
                        net.getConnections().add(parserConnection(line, net));
                    }

                    if (!line.matches("^(generateur|maison|connexion)\\s*\\(\\s*[^,]+?\\s*,\\s*[^)]+?\\s*\\)\\s*\\.\\s*$")) {
                        throw new IllegalArgumentException("Erreur sur la ligne : < " + line + " >\n\tFormat invalide ! Format attendu : entité(param1,param2).");
                    }

                }
                catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Fichier introuvable : " +e.getMessage());
            exit(1);
        }
        catch(IOException e){
            System.err.println("Erreur d'entrée/sortie : " +e.getMessage());
            exit(1);
        }
        catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
            exit(1);
        }

        if(net.isValidReseau(net.getGenerateurs(), net.getMaisons(), net.getConnections())) {
            reseau = NetworkFactory.fabricReseau(net.getGenerateurs(), net.getMaisons(), net.getConnections());
        }

        if(reseau == null) {
            System.err.println(("Opération échouée !"));
            exit(1);
        }

        return reseau;
    }

    /**
     * Analyse une ligne décrivant une maison et crée l'objet {@code Maison}.
     *
     * @param line ligne au format {@code maison(nom, consommation).}
     * @return l'objet {@code Maison} correspondant, ou {@code null} si la consommation est invalide
     */
    public static Maison parserMaison(String line){

        Maison m = null;
        String[] data = line.split("\\(")[1].split("\\)")[0].split(",");
        String nom = data[0].trim();
        try {
            Consommation c = Consommation.valueOf(data[1].trim().toUpperCase());
            m = NetworkFactory.fabricMaison(nom, c);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erreur sur la ligne : < " + line + " >\n\tConsommation invalide. Valeurs possibles : " + Consommation.toStringAll());
        }

        return m;

    }

    /**
     * Analyse une ligne décrivant un générateur et crée l'objet {@code Generateur}.
     *
     * @param line ligne au format {@code generateur(nom, capacite).}
     * @return l'objet {@code Generateur} correspondant, ou {@code null} si la capacité n'est pas un entier valide
     */
    public static Generateur parserGenerateur(String line){

        Generateur g = null;
        try{
            String[] data = line.split("\\(")[1].split("\\)")[0].split(",");
            String nom = data[0].trim();
            int capacite  = Integer.parseInt(data[1].trim());

            g = NetworkFactory.fabricGenerateur(nom, capacite);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("Erreur sur la ligne : < " + line + " >\n\tla capacité du générateur doit être un nombre entier valide !");
        }
        return g;
    }

    /**
     * Analyse une ligne décrivant une connexion entre une maison et un générateur.
     * <p>
     * Vérifie que les entités mentionnées existent dans le réseau en cours de construction.
     *
     * @param line ligne au format {@code connexion(entite1, entite2).}
     * @param net  opérateur réseau contenant les entités déjà créées
     * @return l'objet {@code Connection} correspondant
     */
    public static Connection parserConnection(String line, NetworkOperator net){

        Generateur gen = null;
        Maison mais = null;

        String[] data = line.split("\\(")[1].split("\\)")[0].split(",");
        if (net.isGenerateur(data[0].trim()))
            gen = net.getTByName(net.getGenerateurs(), data[0].trim(), Generateur::getNom); // {Genetaur::getNom} fait référence à la méthode {@code getNom()} de la classe {@code Generateur}.
        else if (net.isGenerateur(data[1].trim()))
            gen = net.getTByName(net.getGenerateurs(), data[1].trim(), Generateur::getNom);

        if (net.isMaison(data[0].trim()))
            mais = net.getTByName(net.getMaisons(), data[0].trim(), Maison::getNom); // {Genetaur::getNom} fait référence à la méthode {@code getNom()} de la classe {@code Generateur}.
        else if (net.isMaison(data[1].trim()))
            mais = net.getTByName(net.getMaisons(), data[1].trim(), Maison::getNom);

        if(mais == null || gen == null){
            System.err.println("Erreur sur la ligne : < " + line + " >");
            if(mais == null) {
                System.err.println("\tLa maison de cette connexion ne figure pas dans le fichier.");
            }
            if(gen == null){
                System.err.println("\tLe générateur de cette connexion ne figure pas dans le fichier.");
            }

            exit(1);
        }

        return NetworkFactory.fabricConnection(mais, gen);
    }

    /**
     * Exporte un réseau vers un fichier texte en respectant le format attendu.
     * <p>
     * Les générateurs sont écrits en premier, suivis des maisons, puis des connexions.
     *
     * @param reseau le réseau à exporter
     * @param file   le fichier cible dans lequel écrire la description du réseau
     */
    public static void Export(Reseau reseau, File file)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file)))
        {
            // On commence par les générateurs
            for(Generateur g : reseau.getGenerateurs())
            {
                bw.write("generateur(" + g.getNom() + ", " + g.getCapacite() + ").");
                bw.newLine();
            }

            // On écrit les maisons
            for(Maison m : reseau.getMaisons())
            {
                bw.write("maison(" + m.getNom() + ", "+ m.getConsommation()+ ").");
                bw.newLine();
            }

            // Et enfin les connexions
            for(Connection c : reseau.getConnexions())
            {
                bw.write("connexion("+ c.getGenerateur().getNom() + ", " + c.getMaison().getNom() + ").");
                bw.newLine();
            }
            System.out.println("Sauvegarde effectuée dans " + file);
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Fichier introuvable : " +e.getMessage());
        }
        catch(IOException e)
        {
            System.err.println("Erreur lors de la sauvegarde du fichier " + e.getMessage());
        }
    }

}
