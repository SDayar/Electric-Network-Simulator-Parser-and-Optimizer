/**
 * Classe NetworkOperator
 * @author YAHIATENE Melissa
 * @author BALDE Asmaou
 * @see Generateur
 * @see Maison
 * @see Connection
 * @see Reseau
 * @see Consommation
 * @see NetworkFactory
 **/
package org.leanGrid.model;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;

import org.leanGrid.model.util.Algorithme;
import org.leanGrid.model.util.Connection;
import org.leanGrid.model.util.Consommation;
import org.leanGrid.model.util.Generateur;
import org.leanGrid.model.util.Maison;
import org.leanGrid.model.util.Reseau;

/**
 * Représente le gestionnaire principal des réseaux et centralise les différentes opérations sur les éléments du réseau
 * @see Maison
 * @see Generateur
 * @see Connection
 * @see Reseau
 */
public class NetworkOperator {

    //Attribus
    /**
     * * La liste des maisons créées
     */
    private List<Maison> maisons;

    /**
     * * La liste des générateurs créés
     */
    private List<Generateur> generateurs;

    /**
     * * La liste des connexions créées
     */
    private List<Connection> connections;

    /**
     * * La liste des réseaux créés
     */
    private Set<Reseau> reseaux; // Penser à supprimer cet attribut ?

    //Constructeur

    /**
     * Créé une instance NetworkOperator et initialise ses attributs
     */
    public NetworkOperator(){
        maisons = new ArrayList<>();
        generateurs = new ArrayList<>();
        connections = new ArrayList<>();
        reseaux = new HashSet<>();
    }

    /**
     * Retourne la liste des générateurs
     * @return liste des générateurs
     */
    public List<Generateur> getGenerateurs() {
        return generateurs;
    }

    /**
     * Retourne la liste de connexions
     * @return La liste de connexions
     */
    public List<Connection> getConnections() {
        return connections;
    }

    /**
     * Retourne la liste des maisons
     * @return La liste des maisons
     */
    public List<Maison> getMaisons() {
        return maisons;
    }

    /**
     * Retourne la liste des réseaux
     * @return Le set de réseaux
     */
    public Set<Reseau> getReseaux() {
        return reseaux;
    }

    /**
     * Ajoute une maison à la liste des maisons créées
     * Vérifie si la maison existe déjà, si oui, mettre à jour sa consommation
     * @param maison la maison à ajouter
     */
    public void addMaison(Maison maison){
        if(!maisons.contains(maison)){
            maisons.add(maison);
             System.out.println("La maison a été créée avec succès !");
        }
        else{
            (maisons.get(maisons.indexOf(maison))).setConsommation(maison.getConsommation());
            System.out.println("La maison que vous avez saisie a déjà été créée, sa consommation a été mise à  jour !\n");
        }
    }

    /**
     * Ajoute un générateur à la liste des générateurs créés
     * Vérifie si le générateur existe déjà, si oui, mettre à jour sa capacité
     * @param generateur le générateur à ajouter
     */
    public void addGenerateur(Generateur generateur){
        if(!generateurs.contains(generateur)){
            generateurs.add(generateur);
            System.out.println("Le générateur a été créé avec succès !");
            System.out.println(generateur.toString());
        }
        else{
            (generateurs.get(generateurs.indexOf(generateur))).setCapacite(generateur.getCapacite());
            System.out.println("Le générateur que vous avez saisi a déjà été créé, sa capacité a été mise à  jour !\n");
        }
    }

    /**
     * Ajoute une connexion à la liste des connexions créées
     * Vérifie si la connexion existe déjà
     * @param c la connexion à ajouter
     */
    public void addConnection(Connection c){
        if(connections.contains(c))
            System.out.println("Cette connexion existe déjà !");
        else{
            connections.add(c);
            System.out.println("La connexion a été créée avec succès !");
            System.out.println(c.getGenerateur().toString());
        }
    }

    /**
     * Ajoute un réseau à la liste des réseaux créés
     * Vérifie si le réseau existe déjà
     * @param reseau le réseau à ajouter
     */
    public void addReseau(Reseau reseau){
        if(!reseaux.add(reseau))
            System.out.println("Ce réseau existe déjà !");

    }


    //Méthodes utilitaires pour menu :

    /**
     * Vérifie si une maison portant le nom spécifié existe dans la liste des maisons.
     * @param nom le nom à vérifier
     * @return true si l'élément correspondant au nom est une maison, false sinon
     */
    public boolean isMaison(String nom){
        boolean result = false;
        for(int i=0; i<maisons.size() && !result; i++){
            if(nom.equalsIgnoreCase(maisons.get(i).getNom())){
                result = true;
            }
        }
        return result;
    }

    /**
     * Vérifie si un générateur portant le nom spécifié existe dans la liste des générateurs.
     * @param nom le nom à vérifier
     * @return {@code true} si l'élément correspondant au nom est une maison, {@code false} sinon
     */
    public boolean isGenerateur(String nom){
        boolean result = false;
        for(int i=0; i<generateurs.size() && !result; i++){
            if(nom.equalsIgnoreCase(generateurs.get(i).getNom())){
                result = true;
            }
        }
        return result;
    }

    /**
     * Méthode utilitaire générique permettant de retrouver un élément dans une liste à partir de son nom,
     * en utilisant une fonction d'extraction de nom. Cette méthode factorise la logique de recherche
     * utilisée dans {@code getMaisonByName} et {@code getGenerateurByName}, afin d'éviter la duplication de code.
     *
     * @param <T>     le type des éléments contenus dans la liste
     * @param list    la liste des éléments à parcourir
     * @param name    le nom recherché
     * @param getNom  une fonction permettant d'extraire le nom de chaque élément de type {@code T}
     * @return l'élément dont le nom correspond à {@code name}, ou {@code null} si aucun ne correspond
     */
    /* Méthode utilitaire générique qui retourne l'élément correspondant au nom donné (depuis une liste générique), c'est une factorisation générique de getMaisonByName et getGenerateurByName (évite la duplication du code) */
    public <T> T getTByName(List<T> list, String name, Function<T, String> getNom){  //getNom est une fonction qui utilisera la méthode apply() de l'interface Function pour extraire le nom d'un objet
        for(T t : list){
            if(name.equalsIgnoreCase(getNom.apply(t))) {  //apply() retourne le nom de l'objet t de type T
                return t;
            }
        }
        return null;
    }

    /**
     * Retourne le générateur auquel la maison donnée est connectée
     * @param maison La maison donnée
     * @return le générateur auquel elle est connectée
     */
    public Generateur getGenerateurSelonMaison(Maison maison){
        for(Connection c : connections){
            if(c.getMaison().equals(maison))
                return c.getGenerateur();
        }
        return null;
    }


    /**
     * Vérifie qu'une connexion entre une maison et un générateur existe
     * @param maison le nom de la maison
     * @param generateur Le nom du générateur
     * @return true si elle existe, false sinon
     */
    public boolean isConnexion(Maison maison, Generateur generateur)
    {
        boolean result = false;
        for(Connection c : connections)
        {
           if(c.getMaison().equals(maison) && c.getGenerateur().equals(generateur))
                result = true;
        }
        return result;
    }

    /**
     * Supprime une connexion dans la liste de connexions
     * @param c La Connexion
     */
    public void supprimerConnexion(Connection c)
    {
        connections.remove(c);
        
    }

    /**
     * Permet de récupérer la connexion associée à une maison et un générateur
    @param maison la maison
    @param generateur le générateur
    @return la connexion si elle existe, null sinon. 
    */
    public Connection getConnection(Maison maison, Generateur generateur){
        for(Connection c : connections){
            if(c.getMaison().equals(maison) && c.getGenerateur().equals(generateur))
                return c;
        }
        return null;
    }

    /**
     * Supprime une maison dans la liste de maisons
     * @param maison La maison à supprimer
     */
    public void supprimerMaison(Maison maison)
    {
        maisons.remove(maison);
    }
    

    /**
     * Permet de vérifier si une maison donnée est raccordée à un générateur
     * @param maison La maison donnée
     * @return true si la maison est raccordée à un générateur, false sinon. 
     */
    public boolean isMaisonConnected(Maison maison, List<Connection> connections)
    {

        for(Connection c : connections){
            if(c.getMaison().equals(maison))
                return true;
        }
        return false;
    }

    /**
     * Identifie toutes les maisons du réseau donné qui ne sont pas connectées.
     *
     * @param maisons les maisons qui vont constituer le réseau
     * @return une liste des maisons invalides, car non connectées
     */
    private List<Maison> maisonsInvalides(List<Maison> maisons, List<Connection> connections){

        List<Maison> maisonsInvalides = new ArrayList<>();
        for(Maison m : maisons){
            if(!isMaisonConnected(m, connections)){
                maisonsInvalides.add(m);
            }
        }
        return maisonsInvalides;
    }

    /**
     * Calcule la somme des demandes de toutes les maisons créées.
     * Cette somme est obtenue en additionnant les valeurs de consommation
     * de chaque maison.
     *
     * @return la somme des consommations des maisons
     */
    public int sommeDemandes(List<Maison> maisons){
        int somme=0;
        for(Maison maison : maisons){
            somme += maison.getConsommation().getValue();
        }
        return somme;
    }

    /**
     * Calcule la somme des capacités de tous les générateurs créés.
     * Cette somme est obtenue en additionnant les capacités de chaque générateur.
     *
     * @return la somme des capacités des générateurs
     */
    public int sommeCapacites(List<Generateur> generateurs){
        int somme=0;
        for(Generateur generateur : generateurs){
            somme += generateur.getCapacite();
        }
        return somme;
    }

    /**
     * Vérifie si le réseau donné qu'on s'apprête à créer est valide.
     *
     * @param maisons l'ensemble des maisons qui vont constituer le réseau
     * @param generateurs l'ensemble des générateurs qui vont constituer le réseau
     * @param connections l'ensemble des connexions qui vont constituer le réseau
     *
     * @return true si le réseau est valide, false sinon
     */
    public boolean isValidReseau(List<Generateur> generateurs, List<Maison> maisons, List<Connection> connections){

        if(generateurs.isEmpty() || maisons.isEmpty() || connections.isEmpty()) {
            System.err.println("Impossible de créer le réseau : Il faut au moins une maison, un générateur et une connexion !");
            return false;
        }

        else if(!maisonsInvalides(maisons, connections).isEmpty()){
            System.err.print("Le réseau est invalide car il contient des maisons non connectées !\nLes maisons invalides : [ ");
            for(int i=0; i< (maisonsInvalides(maisons, connections).size()); i++){
                System.err.print(maisonsInvalides(maisons, connections).get(i)+ " ");
            }
            System.err.println("]");
            return false;
        }
        else if(this.sommeDemandes(maisons) > this.sommeCapacites(generateurs)){
            System.err.println("Impossible de créer le réseau : la somme des charges de demande électrique des maisons est supérieure à la somme des capacités maximales des générateurs !");
            return false;
        }

        return true;
    }


    /**
     * Affiche le menu principal en ligne de commande permettant à l'utilisateur d'interagir avec le réseau LeanGrid.
     *
     * Ce menu propose les opérations suivantes :
     * <ul>
     *     <li>Ajout d'un générateur avec saisie du nom et de la capacité</li>
     *     <li>Ajout d'une maison avec saisie du nom et de la consommation</li>
     *     <li>Création d'une connexion entre une maison et un générateur existants</li>
     *     <li>Construction d'un réseau à partir des entités actuelles et passage au menu secondaire</li>
     * </ul>
     * Les saisies sont interprétées via {@link StringTokenizer}, et les entités sont créées à l'aide de {@link NetworkFactory}.
     * En cas d'entrée invalide ou incomplète, le menu est réaffiché.
     */

    public void menuNetworkBuilder(){

        Scanner sc = new Scanner(System.in);
        int rep;

        do{
            System.out.println("\n===================================================================================");
            System.out.println(" 1) Ajouter un générateur.");
            System.out.println(" 2) Ajouter une maison.");
            System.out.println(" 3) Ajouter une connexion entre une maison et un générateur.");
            System.out.println(" 4) Supprimer une connexion existante entre une maison et un générateur.");
            System.out.println(" 5) Fin.");
            System.out.println("===================================================================================\n");
            System.out.print("Votre choix ?  ");

            try{
                rep = sc.nextInt(); //lecture du choix saisi
                sc.nextLine(); //consommer le retour à la ligne
                switch (rep) {

                    case 1: //Ajouter un générateur
                        System.out.println("Nom et Capacité maximale du générateur ? <Saisie sous la forme : NOM CAPACITE > ");
                        StringTokenizer st1 = new StringTokenizer(sc.nextLine());
                        Generateur g = null;
                        if (st1.countTokens() == 2) {
                            String nom = st1.nextToken();
                            try{
                                int capacite = Integer.parseInt(st1.nextToken());
                                g = NetworkFactory.fabricGenerateur(nom, capacite);  //fabriquer le générateur

                                this.addGenerateur(g); //ajout du générateur s'il n'existe pas, sinon maj sa capacité
                            }catch (NumberFormatException e) {
                                System.err.println("Erreur : la capacité doit être un nombre entier valide !");
                                break;
                            }
                        }
                        else
                        {
                            System.err.println("Erreur lors de la création du générateur");
                        }
                        break;

                    case 2: //Ajouter une maison

                        System.out.println("Nom et Consommation de la maison ? <Saisie sous la forme : NOM CONSOMMATION( " + Consommation.toStringAll() + " ) > ");

                        try{
                            StringTokenizer st2 = new StringTokenizer(sc.nextLine());
                            Maison m = null;
                            if (st2.countTokens() == 2) {
                                String nom = st2.nextToken();
                                String conso = st2.nextToken();

                                m = NetworkFactory.fabricMaison(nom, Consommation.valueOf(conso.toUpperCase()));

                                this.addMaison(m); //ajout de la maison si elle n'existe pas, sinon maj sa consommation
                            }
                            else
                            {
                                System.err.println("Erreur lors de la création de la maison");
                            }

                        }catch(IllegalArgumentException e){
                            System.err.println("Valeurs saisies incorrectes !");
                        }
                        break;

                    case 3: //Ajouter une connexion entre le générateur et la maison ajoutés
                        System.out.println("Nom de la maison ET nom du générateur ? ");
                        StringTokenizer st3 = new StringTokenizer(sc.nextLine());

                        if (st3.countTokens() == 2) {
                            String t1 = st3.nextToken();
                            String t2 = st3.nextToken();

                            Maison mais = null;
                            Generateur gen = null;

                            //Verifier si l'utilisateur a saisi le nom du générateur d'abord puis le nom de la maison ou le contraire
                            if (isGenerateur(t1))
                                gen = getTByName(generateurs, t1, Generateur::getNom); // {Genetaur::getNom} fait référence à la méthode {@code getNom()} de la classe {@code Generateur}.
                            else if (isGenerateur(t2))
                                gen = getTByName(generateurs, t2, Generateur::getNom);

                            if (isMaison(t1))
                                mais = getTByName(maisons, t1, Maison::getNom); // {Maison::getNom} fait référence à la méthode {@code getNom()} de la classe {@code Maison}.
                            else if (isMaison(t2))
                                mais = getTByName(maisons, t2, Maison::getNom);


                            if (mais == null && gen == null) {
                                System.err.println("Erreur : ni "+ t1 + " ni " + t2 + " n'existent !");
                            }
                            else if (mais == null) {
                                System.err.println("Erreur : la maison n'a pas été créée auparavant !");
                            }
                            else if (gen == null) {
                                System.err.println("Erreur : le générateur n'a pas été créé auparavant !");
                            }
                            else{
                                Connection c = NetworkFactory.fabricConnection(mais, gen); //fabriquer la connexion

                                this.addConnection(c); //ajout de la connexion si elle n'existe pas
                            }
                        }

                        break;
                    case 4: //Supprimer une connexion entre le générateur et la maison ajoutés
                        System.out.println("Nom de la maison ET nom du générateur ? ");
                        StringTokenizer st4 = new StringTokenizer(sc.nextLine());

                        if (st4.countTokens() == 2) {
                            String t11 = st4.nextToken();
                            String t22 = st4.nextToken();

                            Maison mais = null;
                            Generateur gen = null;

                            //Verifier si l'utilisateur a saisi le nom du générateur d'abord puis le nom de la maison ou le contraire
                            if (isGenerateur(t11))
                                gen = getTByName(generateurs, t11, Generateur::getNom); // {Genetaur::getNom} fait référence à la méthode {@code getNom()} de la classe {@code Generateur}.
                            else if (isGenerateur(t22))
                                gen = getTByName(generateurs, t22, Generateur::getNom);

                            if(isMaison(t11))
                                mais = getTByName(maisons, t11, Maison::getNom); // {Maison::getNom} fait référence à la méthode {@code getNom()} de la classe {@code Maison}.
                            else if (isMaison(t22))
                                mais = getTByName(maisons, t22, Maison::getNom);


                            if (mais == null && gen == null) {
                                System.err.println("Erreur : ni "+ t11 + " ni " + t22 + " n'existent !");
                            }
                            else if (mais == null) {
                                System.err.println("Erreur : la maison n'a pas été créée auparavant !");
                            }
                            else if (gen == null) {
                                System.err.println("Erreur : le générateur n'a pas été créé auparavant !");
                            }
                            else if(getConnection(mais, gen)==null) {
                                System.err.println("La connexion [" + t11 + " " + t22 + "] n'a pas été créée auparavant !");
                            }
                            else{
                                this.supprimerConnexion(this.getConnection(mais, gen));
                                gen.setChargeActuelle(gen.getChargeActuelle() - (mais.getConsommation().getValue()));
                                System.out.println("Connexion supprimée !!");
                            }
                        }
                        break;
                    case 5: //Création du réseau

                        if(isValidReseau(generateurs, maisons, connections)) {
                            Reseau reseau = NetworkFactory.fabricReseau(generateurs, maisons, connections);
                            this.addReseau(reseau);
                            System.out.println("Le réseau a été créé avec succès !\n");

                            this.menuNetworkManagement(reseau); //Passage au deuxième menu après la création du réseau
                        }
                        else{
                            System.err.println("Opération échouée !");
                        }
                        break;
                    default: //Saisie incorrecte ; retour au menu
                        System.err.println("Choix incorrect ! Veuillez saisir un nombre correct");
                }
            }
            catch(InputMismatchException e){
                System.err.println("Choix invalide. Veuillez saisir un entier !");
                sc.nextLine(); //vider le buffer
                rep=-1; //relancer le menu ( via le do while)
            }

        }while(rep != 5);

        sc.close(); //fermeture du flux d'entrée Scanner
    }



    /**
     * Affiche le menu de traitement des maisons non connectées dans un réseau.
     *
     * Ce menu est déclenché lorsqu'une maison n'est pas raccordée à un générateur.
     * Il propose à l'utilisateur plusieurs choix :
     * <ul>
     *     <li>Raccorder la maison à un générateur existant du réseau.</li>
     *     <li>Supprimer la maison du réseau.</li>
     *     <li>Quitter le menu (dans ce cas, la maison est supprimée pour garantir la validité du réseau).</li>
     * </ul>
     *
     * Les saisies utilisateur sont interprétées via {@link java.util.Scanner}.
     * En cas d'entrée invalide, des messages d'erreur sont affichés et le menu est réaffiché.
     *
     * @param maison la maison non connectée à traiter
     * @param reseau le réseau électrique auquel appartient la maison
     */

    public void menuUnconnectedHouse(Maison maison, Reseau reseau)
    {
        boolean raccorder = false;
        boolean supp = false;
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("La maison (" + maison + ") est sans connexion, voulez vous la raccorder à un générateur ?");
        do
        {
            System.out.println("\n===================================================================================");
            System.out.println("1) Oui.");
            System.out.println("2) Non la supprimer.");
            System.out.println("3) Quitter.");
            System.out.println("===================================================================================\n");
            System.out.println("Votre choix : ");

            try {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1: // On raccorde la maison à un générateur
                        System.out.println("Veuillez saisir le nom du générateur : ");
                        String nomGenerateur = sc.nextLine();
                        if(reseau.isGenerateur(nomGenerateur))// Si le générateur existe
                        {

                            Generateur gen = reseau.getGenerateurByName(nomGenerateur);
                            Connection newCo = NetworkFactory.fabricConnection(maison, gen);
                            reseau.ajouterConnexion(newCo);
                            System.out.println(maison.getNom() + " a été correctement raccordée au générateur " + gen.getNom() + " !");
                            raccorder = true;
                        }
                        else
                        {
                            System.err.println("Le générateur "+ nomGenerateur + " n'existe pas dans le réseau");
                        }
                        break;

                    case 2:    // Suppression de la maison
                        reseau.getMaisons().remove(maison);
                        System.out.println("La maison "+ maison.getNom()+ " a été supprimée");
                        supp = true;
                        break;

                    case 3:    // Fin programme
                        if(isMaison(maison.getNom())) {
                            // S'il ne l'a ni raccordé ni supprimé
                            // On supprime la maison du réseau pour qu'il reste valide
                            reseau.getMaisons().remove(maison);
                            System.out.println("La maison " + maison.getNom() + " a été supprimée");
                        }
                        break;

                    default:
                        System.err.println("Choix incorrect ! Veuillez saisir un nombre correct.");
                }

            } catch (InputMismatchException e) {
                System.err.println("Choix invalide ! Veuillez saisir un entier.");
                sc.nextLine();
                choice = -1;
            }

        }
        while (!(choice == 3 || raccorder || supp));



    }


    /**
     * Affiche le menu secondaire permettant de gérer un réseau électrique déjà créé.
     *
     * Ce menu offre plusieurs opérations de gestion et d'analyse sur le réseau LeanGrid :
     * <ul>
     *     <li>Calculer et afficher les métriques du réseau (dispersion, surcharge et coût total).</li>
     *     <li>Modifier une connexion existante entre une maison et un générateur.</li>
     *     <li>Afficher l'état actuel du réseau (maisons, générateurs et connexions).</li>
     *     <li>Quitter le menu.</li>
     * </ul>
     *
     * Les saisies utilisateur sont interprétées via {@link java.util.StringTokenizer}.
     * En cas d'entrée invalide ou inexistante, des messages d'erreur explicites sont affichés.
     *
     * @param reseau le réseau électrique sur lequel les opérations de gestion sont effectuées
     */
    public void menuNetworkManagement(Reseau reseau)
    {
        Scanner sc = new Scanner(System.in);
        int choix;

        do
        {
            System.out.println("\n===================================================================================");
            System.out.println("1) Calculer le coût du réseau.");
            System.out.println("2) Modifier une connexion.");
            System.out.println("3) Afficher le réseau.");
            System.out.println("4) Quitter.");
            System.out.println("===================================================================================\n");
            System.out.print("Votre choix : ");

            try
            {
                choix = sc.nextInt();
                sc.nextLine();
                switch (choix)
                {
                    case 1: // Calcul et affichage (dispersion, surcharge et coût)
                        double surcharge = reseau.getSurcharge();
                        double dispersion = reseau.getDispersion();
                        double cout = reseau.getCout();

                        System.out.printf("Surchage : %.3f\n", surcharge);
                        System.out.printf("Dispersion : %.3f\n",  dispersion);
                        System.out.printf("Coût du réseau (sévérité = %d) : %.3f\n", reseau.getPenalite(), cout);

                        break;

                    case 2: //Modifier une connexion
                        System.out.println("Saisir la connexion à modifier (exemple : M1 G1) : ");
                        StringTokenizer st = new StringTokenizer(sc.nextLine());
                        if(st.countTokens() == 2)
                        {
                            String t1 = st.nextToken();
                            String t2 = st.nextToken();

                            Maison m = null;
                            Generateur g = null;

                            // On vérifie l'ordre de saisie du user
                            if(reseau.isMaison(t1))
                                m = reseau.getMaisonByName(t1);
                            else if (reseau.isGenerateur(t1))
                                g = reseau.getGenerateurByName(t1);

                            if(reseau.isMaison(t2))
                                m =  reseau.getMaisonByName(t2);
                            else if (reseau.isGenerateur(t2))
                                g = reseau.getGenerateurByName(t2);

                            // Vérification de l'existence de la maison et du générateur
                            if(m == null && g == null)
                                System.err.println("La connexion entre " + t1 + " et " + t2 + " n'existe pas car ni "+ t1 +" ni "+ t2 +" n'existent dans le réseau");
                            else if (m == null)
                                System.err.println("La connexion entre " + t1 + " et " + t2 + " n'existe pas car la maison n'existe pas dans le réseau");
                            else if (g == null)
                                System.err.println("La connexion entre " + t1 + " et " + t2 + " n'existe pas car le générateur n'existe pas dans le réseau");
                            else
                            {
                                // On vérifie si la connexion existe
                                Connection oldConnexion = reseau.getCoByMaisonAndGenerateur(m,g);
                                if(reseau.isConnexion(oldConnexion)) // Elle existe
                                {
                                    //Saisie de la nouvelle connexion
                                    System.out.println("Saisir la nouvelle connexion : ");
                                    st =  new StringTokenizer(sc.nextLine());
                                    if (st.countTokens() == 2)
                                    {
                                        String tok1 = st.nextToken();
                                        String tok2 = st.nextToken();

                                        Maison newMaison = null;
                                        Generateur newGen = null;

                                        // On vérifie l'existence des données saisies
                                        if(reseau.isMaison(tok1))
                                            newMaison = reseau.getMaisonByName(tok1);
                                        else if (isGenerateur(tok1))
                                            newGen = reseau.getGenerateurByName(tok1);

                                        if (reseau.isMaison(tok2))
                                            newMaison = reseau.getMaisonByName(tok2);
                                        else if (reseau.isGenerateur(tok2))
                                            newGen = reseau.getGenerateurByName(tok2);

                                        // On vérifie leur existence dans le réseau
                                        if(newMaison == null && newGen == null)
                                            System.err.println("La connexion entre " + tok1 + " et " + tok2 + " ne peut être créée car ni  "+ tok1 +" ni "+ tok2 +" n'existent dans le réseau");
                                        else if (newMaison == null)
                                            System.err.println("La connexion entre " + tok1 + " et " + tok2 + "ne peut être créée car la maison n'existe pas dans le réseau");
                                        else if (newGen == null)
                                            System.err.println("La connexion entre " + tok1 + " et " + tok2 + " ne peut être créée car le générateur n'existe pas dans le réseau");
                                        else
                                        {
                                            // On vérifie si l'user souhaite modifier le générateur ou la maison
                                            // Dans le cas où il a ressaisi les mêmes données.
                                            if(m.equals(newMaison) && g.equals(newGen))
                                            {
                                                System.out.println("Aucune modification effectuée");
                                            }
                                            else if (!(g.equals(newGen))) // Modification du générateur
                                            {
                                                // On supprime l'ancienne co et ajoute la nouvelle
                                                reseau.supprimerConnexion(oldConnexion);
                                                Connection newConnexion = NetworkFactory.fabricConnection(newMaison,newGen);
                                                reseau.ajouterConnexion(newConnexion);
                                                System.out.println("Modification effectuée avec succès !!");

                                            }
                                            else if (!(m.equals(newMaison))) // Si la maison est modifiée
                                            {
                                                // On supprime la connexion de la nouvelle maison si elle existe.
                                                // Elle existe sûrement, car toute maison dans le réseau (valide) est connectée à au plus un générateur
                                                Connection co = reseau.getCoByMaison(newMaison);
                                                reseau.supprimerConnexion(co);

                                                // Suppression de l'ancienne connexion
                                                reseau.supprimerConnexion(oldConnexion);

                                                Connection nouvConnexion = NetworkFactory.fabricConnection(newMaison,newGen);
                                                reseau.ajouterConnexion(nouvConnexion);
                                                System.out.println("Modification effectuée avec succès !!");

                                                // On demande à l'utilisateur s'il veut raccorder l'ancienne maison à un autre générateur
                                                // Car elle est maintenant sans connexion.
                                                menuUnconnectedHouse(m,reseau);
                                            }
                                        }

                                    }
                                    else
                                    {
                                        System.err.println("Erreur de saisie, veuillez entrer la maison et le générateur séparés par un espace");
                                    }
                                }
                                else
                                {
                                    System.err.println("La connexion "+ m.getNom()+" " + g.getNom() +" n'existe pas dans le réseau");
                                }
                            }
                        }
                        else
                        {
                            System.err.println("Erreur de saisie, veuillez entrer la maison et le générateur séparés par un espace");
                        }
                        break;

                    case 3 : // Affichage du réseau actuel
                        System.out.println(reseau);
                        break;

                    case 4 : // Fin du programme
                        System.out.println("Fin du programme !! ");
                        break;

                    default :
                        System.err.println("Choix incorrect ! Veuillez saisir un nombre correct.");
                }

            }catch(InputMismatchException e)
            {
                System.err.println("Choix invalide ! Veuillez saisir un entier.");
                sc.nextLine();
                choix = -1;
            }



<<<<<<< HEAD
                    
                    System.out.printf("Surchage : %.3f\n", surcharge);
                    System.out.printf("Dispersion : %.3f\n",  dispersion);
                    System.out.printf("Coût du réseau (sévérité = %d) : %.3f\n", reseau.getPenalite(), cout);

                    break;

                case 2: //Modifier une connexion
                    System.out.println("Saisir la connexion à modifier (exemple : M1 G1) : ");
                    StringTokenizer st = new StringTokenizer(sc.nextLine());
                    if(st.countTokens() == 2)
                    {
                        String t1 = st.nextToken();
                        String t2 = st.nextToken();

                        Maison m = null;
                        Generateur g = null;

                        // On vérifie l'ordre de saisie du user
                        if(reseau.isMaison(t1))
                            m = reseau.getMaisonByName(t1);
                        else if (reseau.isGenerateur(t1))
                            g = reseau.getGenerateurByName(t1);

                        if(reseau.isMaison(t2))
                            m =  reseau.getMaisonByName(t2);
                        else if (reseau.isGenerateur(t2))
                            g = reseau.getGenerateurByName(t2);

                        // Vérification de l'existence de la maison et du générateur
                        if(m == null && g == null)
                            System.err.println("La connexion entre " + t1 + " et " + t2 + " n'existe pas car ni "+ t1 +" ni "+ t2 +" n'existent dans le réseau");
                        else if (m == null)
                            System.err.println("La connexion entre " + t1 + " et " + t2 + " n'existe pas car la maison n'existe pas dans le réseau");
                        else if (g == null)
                            System.err.println("La connexion entre " + t1 + " et " + t2 + " n'existe pas car le générateur n'existe pas dans le réseau");
                        else
                        {
                            // On vérifie si la connexion existe
                            Connection oldConnexion = NetworkFactory.fabricConnection(m,g);
                            if(reseau.isConnexion(oldConnexion)) // Elle existe
                            {
                                //Saisie de la nouvelle connexion
                                System.out.println("Saisir la nouvelle connexion : ");
                                st =  new StringTokenizer(sc.nextLine());
                                if (st.countTokens() == 2)
                                {
                                    String tok1 = st.nextToken();
                                    String tok2 = st.nextToken();

                                    Maison newMaison = null;
                                    Generateur newGen = null;

                                    // On vérifie l'existence des données saisies
                                    if(reseau.isMaison(tok1))
                                        newMaison = reseau.getMaisonByName(tok1);
                                    else if (isGenerateur(tok1))
                                        newGen = reseau.getGenerateurByName(tok1);

                                    if (reseau.isMaison(tok2))
                                        newMaison = reseau.getMaisonByName(tok2);
                                    else if (reseau.isGenerateur(tok2))
                                        newGen = reseau.getGenerateurByName(tok2);

                                    // On vérifie leur existence dans le réseau
                                    if(newMaison == null && newGen == null)
                                        System.err.println("La connexion entre " + tok1 + " et " + tok2 + " ne peut être créée car ni  "+ tok1 +" ni "+ tok2 +" n'existent dans le réseau");
                                    else if (newMaison == null)
                                        System.err.println("La connexion entre " + tok1 + " et " + tok2 + "ne peut être créée car la maison n'existe pas dans le réseau");
                                    else if (newGen == null)
                                        System.err.println("La connexion entre " + tok1 + " et " + tok2 + " ne peut être créée car le générateur n'existe pas dans le réseau");
                                    else
                                    {
                                        // On vérifie si l'user souhaite modifier le générateur ou la maison
                                        // Dans le cas où il a ressaisi les mêmes données.
                                        if(m.equals(newMaison) && g.equals(newGen))
                                        {
                                            System.out.println("Aucune modification effectuée");
                                        }
                                        else if (!(g.equals(newGen))) // Modification du générateur
                                        {
                                            // On supprime l'ancienne co et ajoute la nouvelle
                                            reseau.supprimerConnexion(oldConnexion);
                                            Connection newConnexion = NetworkFactory.fabricConnection(newMaison,newGen);
                                            reseau.ajouterConnexion(newConnexion);
                                            System.out.println("Modification effectuée avec succès !!");

                                        }
                                        else if (!(m.equals(newMaison))) // Si la maison est modifiée
                                        {
                                            // On supprime la connexion de la nouvelle maison si elle existe.
                                            // Elle existe sûrement, car toute maison dans le réseau (valide) est connectée à au plus un générateur
                                            Connection co = reseau.getCoByMaison(newMaison);
                                            reseau.supprimerConnexion(co);

                                            // Suppression de l'ancienne connexion
                                            reseau.supprimerConnexion(oldConnexion);

                                            Connection nouvConnexion = NetworkFactory.fabricConnection(newMaison,newGen);
                                            reseau.ajouterConnexion(nouvConnexion);
                                            System.out.println("Modification effectuée avec succès !!");

                                            // On demande à l'utilisateur s'il veut raccorder l'ancienne maison à un autre générateur
                                            // Car elle est maintenant sans connexion.
                                            menu3(m,reseau);
                                        }
                                    }

                                }
                                else
                                {
                                    System.err.println("Erreur de saisie, veuillez entrer la maison et le générateur séparés par un espace");
                                }
                            }
                            else
                            {
                                System.err.println("La connexion "+ m.getNom()+" " + g.getNom() +" n'existe pas dans le réseau");
                            }
                        }
                    }
                    else
                    {
                        System.err.println("Erreur de saisie, veuillez entrer la maison et le générateur séparés par un espace");
                    }
                    break;

                case 3 : // Affichage du réseau actuel
                    System.out.println(reseau);
                    break;

                case 4 : // Fin du programme
                    System.out.println("Fin du programme !! ");
                    break;

                default :
                    System.out.print("Erreur de saisie");
                    break;
            }
=======
>>>>>>> melissa
        }
        while (choix != 4);
        sc.close();
    }


    /**
     * Affiche le menu de chargement et de gestion d'un réseau LeanGrid depuis un fichier texte.
     *
     * Cette méthode permet à l'utilisateur de :
     * <ul>
     *     <li>Charger un réseau existant à partir d'un fichier via {@link NetworkSerializer#parser(String)}.</li>
     *     <li>Résoudre automatiquement le réseau et afficher son état actuel.</li>
     *     <li>Sauvegarder la solution courante dans un fichier texte (dans le dossier "ExportedFiles").</li>
     *     <li>Quitter le programme.</li>
     * </ul>
     *
     * Fonctionnement :
     * <ul>
     *     <li>Le réseau est reconstruit à partir du fichier fourni en paramètre.</li>
     *     <li>Un menu interactif est affiché en ligne de commande, avec saisie via {@link java.util.Scanner}.</li>
     *     <li>Lors de la sauvegarde, si le fichier existe déjà, l'utilisateur peut choisir de l'écraser ou d'annuler.</li>
     * </ul>
     *
     * @param file     le chemin du fichier texte décrivant le réseau à charger
     * @param severite le paramètre de sévérité (utilisé pour la pénalisation des surcharges dans le calcul du coût)
     *
     * @throws IllegalArgumentException si le fichier est invalide ou ne permet pas de reconstruire un réseau
     */

    public void menuNetworkLoader(String file, int severite)
    {
        Reseau reseau = NetworkSerializer.parser(file); //création du réseau depuis le fichier texte "file"

        Algorithme outilAlgorithme = new Algorithme(reseau, severite);
        Scanner sc = new Scanner(System.in);
        int rep;

        do{
            System.out.println("\n===================================================================================");
            System.out.println(" 1) Résolution automatique.");
            System.out.println(" 2) Sauvegarde de la solution actuelle.");
            System.out.println(" 3) Fin.");
            System.out.println("===================================================================================\n");
            System.out.print("Votre choix ?  ");

            try{
                rep = sc.nextInt(); //lecture du choix saisi
                sc.nextLine(); //consommer le retour à la ligne
                switch (rep) {

                    case 1:
                        System.out.println(outilAlgorithme.launch());
                        break;

                    case 2:
                        System.out.println("Nom du fichier de sauvegarde : ");
                        String nom = sc.nextLine();
                        File fichier = new File(nom);
                        File filePath;

                        if(nom.contains("/") || nom.contains("\\") || fichier.isAbsolute())
                        {
                            if(!nom.toLowerCase().endsWith(".txt"))
                                filePath = new File(fichier + ".txt");
                            else
                                filePath = fichier;
                        }
                        else
                        {
                            // on vérifie si le nom du fichier contient déjà l'extension .txt
                            if(!nom.toLowerCase().endsWith(".txt"))
                                nom += ".txt";
                            File dossier = new File("ExportedFiles");
                            // Création du dossier s'il n'existe pas déjà
                            if(!dossier.exists())
                                dossier.mkdir();
                            filePath = new File(dossier, nom);
                        }

                        // Vérification que le fichier de sauvegarde est différent de celui d'importation
                        File importedFile = new File(file);
                        if(filePath.getAbsoluteFile().equals(importedFile.getAbsoluteFile()))
                        {
                            System.err.println("Le fichier de sauvegarde ne peut pas être identique au fichier d'importation.");
                        }
                        else
                        {
                            // Vérification de l'existence du fichier de sauvegarde
                            if(filePath.exists())
                            {
                                System.out.println("Le fichier " + nom + " existe déjà, voulez vous l'écraser ? (O/N) : ");
                                String choix = sc.nextLine();
                                if(choix.equalsIgnoreCase("O"))
                                    NetworkSerializer.Export(reseau, filePath);
                                else if (choix.equalsIgnoreCase("N"))
                                    System.out.println("Sauvegarde annulée");
                                else System.out.println("Erreur de saisie, Annulation de la sauvegarde");

                            }
                            else
                            {
                                NetworkSerializer.Export(reseau, filePath);
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Fin du programme !! ");
                        break;

                    default: //Saisie incorrecte ; retour au menu
                        System.err.println("Choix incorrect ! Veuillez saisir un nombre correct.");
                }
            }
            catch(InputMismatchException e){
                System.err.println("Choix invalide ! Veuillez saisir un entier.");
                sc.nextLine(); //vider le buffer
                rep=-1; //relancer le menu ( via le do while)
            }



        }while(rep != 3);

        sc.close(); //fermeture du flux d'entrée Scanner
    }

}
