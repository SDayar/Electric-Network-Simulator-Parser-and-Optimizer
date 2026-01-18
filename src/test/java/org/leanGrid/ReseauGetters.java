package org.leanGrid;

import java.util.ArrayList;
import java.util.List;

import org.leanGrid.model.util.*;

public class ReseauGetters {
    public static void main(String args[]){
        List<Maison> maisons = new ArrayList<>();
        List<Generateur> generateurs = new ArrayList<>();
        List<Connection> connexions = new ArrayList<>();
        Maison M1 = new Maison("M1",Consommation.FORTE);
        Maison M2 = new Maison("M2",Consommation.FORTE);
        Maison M3 = new Maison("M3", Consommation.NORMAL);
        Maison M4 = new Maison("M4", Consommation.BASSE);
        Maison M5 = new Maison("M1", Consommation.FORTE);
        Generateur G1 = new Generateur("G1", 60);
        Generateur G2 = new Generateur("G2", 60);
        Generateur G3 = new Generateur("G3", 40);
        Connection C1 = new Connection(M1,G1);
        Connection C2 = new Connection(M3, G1);
        Connection C3 = new Connection(M2,G2);
        Connection C4 = new Connection(M4, G2);
        Connection C5 = new Connection(M5, G3);
        maisons.add(M1);
        maisons.add(M2);
        maisons.add(M3);
        maisons.add(M4);
        generateurs.add(G1);
        generateurs.add(G2);
        connexions.add(C1);
        connexions.add(C2);
        connexions.add(C3);
        connexions.add(C4);
        
        try{
            Reseau reseau = new Reseau(generateurs, maisons, connexions);
            /******Affichage du réseau******* */
            System.out.println(reseau);
            /**************getGenerateurs()******* */
            System.out.println("Générateurs :\n");
            for(Generateur g : reseau.getGenerateurs()){
                System.out.println(g+"\n");
            }
            /***************getMaisons()********** */
            System.out.println("\n\nMaisons :\n");
            for(Maison m : reseau.getMaisons()){
                System.out.println(m+"\n");
            }
            /****************getConnexionx()****** */
             System.out.println("\n\nConnexions :\n");
            for(Connection c : reseau.getConnexions()){
                System.out.println(c+"\n");
            }
            /****************getCout()******** */
             System.out.println("reseau.getCout() = "+reseau.getCout()+"\n");
             /****************getDispersion()******** */
             System.out.println("reseau.getDispersion() = "+reseau.getDispersion()+"\n");
             /****************getPenalite()******** */
             System.out.println("reseau.getPenalite() = "+reseau.getPenalite()+"\n");
             /****************getSurcharge()******** */
             System.out.println("reseau.getSurcharge() = "+reseau.getSurcharge()+"\n");
             /****************getNombreGenerateurs , getNombreMaisons() et getNombreConnexions() ******** */
             System.out.println("reseau.getNombreGenerateurs() = "+reseau.getNombreGenerateurs()+"\n"+"getNombreMaisons() = "+reseau.getNombreMaisons()+"\ngetNombreConnexions() = "+reseau.getNombreConnexions()+"\n");
             /*****************getCoByMaison(maison) *************/
             System.out.println("Connexion de la maison M1 : "+reseau.getCoByMaison(M1)+"\n");
            
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }
}
