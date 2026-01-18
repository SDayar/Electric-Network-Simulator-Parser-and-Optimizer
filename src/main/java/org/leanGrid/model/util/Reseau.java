package org.leanGrid.model.util;
import java.util.ArrayList;
import java.util.List;



/**
 * Classe Réseau
 * @author SAIFIDINE Dayar
 * @since 30/10/25
 * @version 27/11/25
 * @see Generateur
 * @see Maison
 * @see Connection
 **/

public class Reseau{  
   
    /**
     * Ensemble des générateurs
    **/
    private List<Generateur> generateurs;


    /**
     * Ensemble des maisons (consommateurs)
     **/
    private List<Maison> maisons;

    /**
     * Ensemble des connexions
     **/
    private List<Connection> connexions;


     /**
     * Cout du réseau
    **/
    Cout cout;

    /**
    *Variable d'instance code qui va servir au hashCode
    **/
    private int code = 31;

    /**
     * variable de classe qui va servir à l'implémentation du hashCode
     */ 
    public static int codeCompteur = 1;

    /**
     * Constructeur
     * @param generateurs la liste des générateurs
     * @param maisons la liste des maisons
     * @param connexions la liste des connexions
     **/

    public Reseau(List<Generateur> generateurs, List<Maison> maisons,List<Connection> connexions){
        this.generateurs = generateurs;
        this.maisons = maisons;
        this.connexions = connexions;
        cout = new Cout();//cout du réseau
        codeCompteur++;//Incrémentation du compteur de code pour le hashCode
    }

   /**
    * Permet de récupérer les générateurs du réseau
    * @return generateurs
    */
   public List<Generateur> getGenerateurs(){
        return generateurs;
   }
   /**
    * Permet de récupérer les maisons du réseau
    * @return maisons
    */
    public List<Maison> getMaisons(){
        return maisons;
    }
    /**
     * Permet de récupérer les connexions du réseau
     * @return connexions
     */
    public List<Connection> getConnexions(){
        return connexions;
    }
   
    /**
    * Retourne le nombre de maisons dans le réseau
    * @return nombre de maisons
    **/
    public int getNombreMaisons(){
        return maisons.size();
    }

    /** 
     * Retourne le nombre de générateurs dans le réseau
     * @return nombre de générateurs
    **/
    public int getNombreGenerateurs(){
        return generateurs.size();
    }

    /**
     * Retourne le nombre de connexions dans le réseau
     * @return nombre de connexions
     */
    public int getNombreConnexions(){
        return connexions.size();
    }

    /**
     * Retourne le cout du réseau
     * @return Cout
     */
    public double getCout(){
        return cout.getCout();
    }
    /**
     * Permet de modifier la pénalité associée à la surcharge du réseau
     *@param penalite la valeur de la pénalité
     */
    public void setPenalite(int penalite){
        if(penalite <=0){
            //Si la pénalité est inférieure ou égale à 0, on ne l'affecte pas au réseau
            System.err.println("Impossible d'affecter une pénalité inférieure ou égale à 0.\n");
        }  
        else{
            cout.setPenalite(penalite);
        }

    }
    /**
     * Permet de récupérer la pénalité associée à la surcharge du réseau
     * @return penalite
     */
    public int getPenalite(){
        return cout.getPenalite();
    }
   
    /**
     * Permet de récupérer le temps de calcul du cout associé au réseau
     * @return performance
     */
    public double getPerformance(){
        //À implémenter dans une future version
        return cout.getPerformance();
    }
    /**
     * Permet de récupérer la dispersion du réseau
     * @return dispersion
     */
    public double getDispersion(){
        return cout.getDispersion();
    }
    /**
     * Permet de récupérer la pénalisation des surcharges 
     * @return surcharge
     */
    public double getSurcharge(){
        return cout.getSurcharge();
    }
     /**
     * Permet de récupérer le taux d'utilisation d'un générateur
     * @return taux d'utilisation
     */
    public double getTauxFromGen(Generateur generateur){
        return cout.getTauxFromGen(generateur);
    }
     /**
     * Permet de récupérer le taux d'utilisation moyen du réseau
     * @return taux d'utilisation moyen
     */
    public double getTauxMoyen(){
        return cout.getTauxMoyen();
    }

    /**
     * Permet de récupérer la connexion associée à une maison 
     * @param maison La maison
     * @return La connexion si elle existe, null sinon
     */
    public Connection getCoByMaison(Maison maison){
            for(Connection c : connexions){
                if(c.getMaison() == maison){
                    return c;
                }
            }
        //Si la maison n'a pas été trouvée dans les connexions, retourner null.
        return null;

    }
    /**
    * Permet de récupérer la connexion associée à une maison et un générateur
    * @param maison la maison
    * @param generateur le générateur
    * @return la connexion si elle existe, null sinon.
    */
    public Connection getCoByMaisonAndGenerateur(Maison maison, Generateur generateur){
        for(Connection c : connexions){
            if(c.getMaison().equals(maison) && c.   getGenerateur().equals(generateur)){
                return c;
            }
        }
        return null;
    }
    
    /**
     * Retourne les connexions rattachées à un générateur
     * @param generateur le générateur
     * @return connexion
     */
    public List<Connection> getCoByGenerateur(Generateur generateur){
        try{
            if(!generateurs.contains(generateur)){
                System.out.println(generateur+" n'existe pas dans le réseau.\n");
            }else{
                List<Connection> connexionsByGen = new ArrayList<>();
                boolean addResult;
                for(Connection c : connexions){
                    if(c.getGenerateur().equals(generateur)){
                        addResult = connexionsByGen.add(c);
                        if(addResult == false){
                            System.out.println("Une erreur dans la collection des connexions de "+generateur+" dans le réseau est survenue.\n");
                            return null;
                        }
                    }
                }
                return connexionsByGen;
            }
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }catch(ClassCastException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * Retourne les maisons connectées à un générateur g
     * @param generateur le générateur
     * @return maisons
     */
    public List<Maison> getMaisonsByGenerateur(Generateur generateur){
        try{
            List<Connection> connexionsByGen = getCoByGenerateur(generateur);
            List<Maison> maisonsByGen = new ArrayList<>();
            boolean addResult;
            for(Connection c : connexionsByGen){
                addResult = maisonsByGen.add(c.getMaison());
                if(addResult == false){
                    System.out.println("Une erreur est survenue lors de la récupération de la liste des maisons connectées à "+generateur+".\n");
                }
            }
            return maisonsByGen;

        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * Permet de supprimer une connexion dans le réseau et de mettre à jour la charge du générateur
     * @param connexion La connexion à supprimer
     */
    public void supprimerConnexion(Connection connexion)
    {
        Maison maison = connexion.getMaison();
        Generateur generateur = connexion.getGenerateur();

        generateur.setChargeActuelle(generateur.getChargeActuelle() - (maison.getConsommation().getValue()));
        connexions.remove(connexion);
    }

    /**
     * Permet d'ajouter une connexion dans le réseau
     * @param connexion La connexion à ajouter
     */
<<<<<<< HEAD
    public void ajouterConnexion(Connection connexion)
    {
        connexions.add(connexion);
    }
=======
    public void ajouterConnexion(Connection connexion) {connexions.add(connexion);}
>>>>>>> melissa

    /**
     * Permet de vérifier si une maison est présente dans le réseau.
     *@param nomMaison Le nom de la maison
     *@return La maison si elle y est, null sinon
     */
    public Maison getMaisonByName(String nomMaison)
    {
            for(Maison m : maisons)
            {
                if (m.getNom().equalsIgnoreCase(nomMaison))
                    return m;
            }
            return null;
    }

    /**
     * Vérifie si une maison portant le nom spécifié existe dans la liste des maisons.
     * @param nomMaison le nom à vérifier
     * @return true si l'élément correspondant au nom est une maison, false sinon
     */
    public boolean isMaison(String nomMaison)
    {
        for(Maison m : maisons){
            if(m.getNom().equalsIgnoreCase(nomMaison)){
                return true;
            }
        }
        return false;
    }

     /**
     * Permet de vérifier si un générateur est présente dans le réseau. Retourne le générateur si il y est, null sinon.
     *@param nomGenerateur Le nom du générateur
     *@return Le generateur s'il existe, null sinon
     */
    public Generateur getGenerateurByName(String nomGenerateur){
        try{
            for(Generateur g : generateurs){
                if(g.getNom().equalsIgnoreCase(nomGenerateur)){
                    return g;
                }
            }
        }catch(NullPointerException e){
            System.out.println("Impossible d'effectuer l'opération. Vérifiez le nom du générateur.");
        }
        return null;
    }

    /**
     * Vérifie si un générateur portant le nom spécifié existe dans la liste des générateurs.
     * @param nomGenerateur le nom à vérifier
     * @return {@code true} si l'élément correspondant au nom est une maison, {@code false} sinon
     */
    public boolean isGenerateur(String nomGenerateur)
    {
        for(Generateur g : generateurs){
            if(g.getNom().equalsIgnoreCase(nomGenerateur)){
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si un générateur portant le nom spécifié existe dans la liste des générateurs.
     * @param connexion  La connexion
     * @return {@code true} si l'élément correspondant au nom est une maison, {@code false} sinon
     */
    public boolean isConnexion(Connection connexion) {
        Generateur generateur = connexion.getGenerateur();
        Maison maison = connexion.getMaison();

            if (!(isGenerateur(generateur.getNom())) && !(isMaison(maison.getNom())))
                return false;
            else if (isGenerateur(generateur.getNom()) && !(isMaison(maison.getNom())))
                return false;
            else if (isMaison(maison.getNom()) && !(isGenerateur(generateur.getNom())))
                return false;
            else {
                return connexions.contains(connexion);
            }
    }


    /**
     * Retourne la description d'un réseau : Générateurs, consommateurs, connexions, cout du réseau, dispersion, etc.
     * @return description du réseau
     **/
     @Override
    public String toString() {
        StringBuilder description = new StringBuilder("\n===================================================================================\n");

        description.append("Réseau : \n\n");
        //Generateur(Capacité, taux utilisation) = Maison1, Maison2, etc
        for(Generateur g : generateurs){
            description.append(g);
            description.append(" : ");
            for(Connection c : connexions){
                if(c.getGenerateur().equals(g)){
                    description.append(c.getMaison());
                    description.append(" ");
                }
            }
            description.append("\n");
            description.append("-> Taux d'utilisation = ");
            description.append(String.format("%.2f", cout.getTauxFromGen(g))); 
            description.append("\n\n");  
        }
        description.append("Performances du réseau :\n\t");
        //Ajout des valeurs du cout, dispersion, pénalité et surcharge
        description.append("Cout = ");
        description.append(String.format("%.3f",this.getCout()));
        description.append("\n\tDispersion = ");
        description.append(String.format("%.3f",this.getDispersion()));
        description.append("\n\tPénalité = ");
        description.append(this.getPenalite());
        description.append("\n\tSurcharge = ");
        description.append(String.format("%.3f",this.getSurcharge())).append("\n");
        description.append("\n===================================================================================\n");
        return description.toString(); 
    }

    /**
     * Compare deux réseaux
     * @return True s'ils possèdent les mêmes réseaux. False sinon
     **/
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }else if(this == obj){
            return true;
        }else{
            Reseau r = (Reseau) obj;
            return r.getMaisons().equals(maisons) && r.getGenerateurs().equals(generateurs) && r.getConnexions().equals(connexions); 
        } 
    }
    /**
     * Redéfinition du hashCode d'un réseau
     * @return hashCode
     */
    @Override
    public int hashCode(){
     return code*Reseau.codeCompteur;   
    }


    /**
    *  Classe interne et utilitaire qui permet de calculer le cout d'une instance de Reseau
    **/    
    private class Cout {
            
        /**
         * Pénalité affectée à la surcharge du réseau par défaut est égal à 10.
         **/
        private int penalite = 10;

        /**
        * Performance (en temps | ms)
        **/
        private double performance = 0;

        /**
         * Modifie la penalite dans le calcul du cout
         * Penalite appartient à [|1 ,+inf|]
         * @param penalite la nouvelle valeur de la pénalité
         */
        public void setPenalite(int penalite){
                this.penalite = penalite;
                System.out.println("Nouvelle pénalité affectée au réseau : "+penalite+"\n");
        }

        /**
         * Retourne la penalite
         * @return penalite
         */
        public int getPenalite(){
            return penalite;
        }

        /**
         * Retourne le taux d'utilisation moyen du réseau
         * @return taux moyen
         **/
        private double calculerTauxMoyen() {
            int nb_generateurs;
            double tauxMoyen = 0;
            if((nb_generateurs=Reseau.this.getNombreGenerateurs())>0){
                //Si le nombre de générateurs est supérieur à 0
                double sommeTaux = 0;
                for(Generateur g : generateurs){
                    sommeTaux += getTauxFromGen(g);
                }
                tauxMoyen = sommeTaux/nb_generateurs;
            }else{
                System.err.println("Impossible de calculer le taux d'utilisation moyen réel du réseau car le nombre de générateurs est égal à 0.\n");
            }
            return tauxMoyen;
        }

        /**
         * Retourne la dispersion du réseau
         * @return dispersion
         **/
        private double calculerDispersion() {
            double tauxMoyen = calculerTauxMoyen(); //Taux d'utilisation moyen du réseau
            double dispersion = 0;
            for(Generateur g : generateurs){
                //Formule de la dispersion : somme(générateur g) |taux(g) - taux_moyen|
                dispersion+= Math.abs(getTauxFromGen(g)-tauxMoyen);
            }
            return dispersion;
        }
                        
        /**
         * Retourne la surcharge
         * @return surcharge
         **/
        private double calculerSurcharge() {
            double surcharge = 0;
            for(Generateur g : generateurs){
                    surcharge += Math.max(0, (double)(getTauxFromGen(g)-1));
                }      
            return surcharge;            
        }

        /**
         * Calcule le cout du réseau
         * @return cout
         */
        private double calculerCout(){
            long debut = System.nanoTime();
            double cout = calculerDispersion()+penalite*calculerSurcharge();
            long fin = System.nanoTime();
            performance =  (double)(fin-debut)/1000000.0;//Conversion en milliseconde
            return cout;
        }

        /**
         * Permet de récupérer le cout du réseau
         * @return cout
         */
        public double getCout(){
            return calculerCout();
        }
        
        /**
         * Permet de récupérer la pénalisation des surcharges du réseau
         * @return surcharge
         */
        public double getSurcharge(){
            return calculerSurcharge();
        }

        /**
         * Permet de récupérer la dispersion du associée au réseau
         * @return dispersion
         */
        public double getDispersion(){
            return calculerDispersion();
        }

        /**
         * Retourne la performance en ms
         * @return oerformance
         */
        public double getPerformance(){
            return performance;
        }

        /**
         * Calcule le taux d'utilisation d'un générateur
         *  @param generateur le générateur
         */
        public double getTauxFromGen(Generateur generateur){
            double taux = 0;
            if(generateurs.contains(generateur)){  
                try{
                    taux = (double)generateur.getChargeActuelle()/(double)generateur.getCapacite(); 
                }catch(ArithmeticException e){
                    System.err.println("Impossible de calculer le taux d'utilisation du générateur "+generateur+" car sa capacité maximale est égale à 0.\n");
                }
                
            }else{
                System.err.println("Impossible de calculer le taux d'utilisation du générateur "+generateur+" car il n'existe pas dans le réseau.\n");
            }
            return taux;
        }
        public double getTauxMoyen(){
            return calculerTauxMoyen();
        }
    }
  
}
