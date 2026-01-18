package org.leanGrid;

import org.leanGrid.model.*;
import org.leanGrid.model.util.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestSauvegardeTGinstance2 {
    public static void main(String[] args) {
        // Générateurs
        Generateur gen1 = new Generateur("gen1", 70);
        Generateur gen2 = new Generateur("gen2", 350);
        Generateur gen3 = new Generateur("gen3", 460);
        Generateur gen4 = new Generateur("gen4", 162);
        Generateur gen5 = new Generateur("gen5", 278);
        Generateur gen6 = new Generateur("gen6", 33);
        Generateur gen7 = new Generateur("gen7", 223);

        // Maisons
        Maison maison1 = new Maison("maison1", Consommation.NORMAL);
        Maison maison2 = new Maison("maison2", Consommation.BASSE);
        Maison maison3 = new Maison("maison3", Consommation.NORMAL);
        Maison maison4 = new Maison("maison4", Consommation.FORTE);
        Maison maison5 = new Maison("maison5", Consommation.FORTE);
        Maison maison6 = new Maison("maison6", Consommation.NORMAL);
        Maison maison7 = new Maison("maison7", Consommation.NORMAL);
        Maison maison8 = new Maison("maison8", Consommation.FORTE);
        Maison maison9 = new Maison("maison9", Consommation.BASSE);
        Maison maison10 = new Maison("maison10", Consommation.NORMAL);
        Maison maison11 = new Maison("maison11", Consommation.BASSE);
        Maison maison12 = new Maison("maison12", Consommation.NORMAL);
        Maison maison13 = new Maison("maison13", Consommation.FORTE);
        Maison maison14 = new Maison("maison14", Consommation.FORTE);
        Maison maison15 = new Maison("maison15", Consommation.NORMAL);
        Maison maison16 = new Maison("maison16", Consommation.NORMAL);
        Maison maison17 = new Maison("maison17", Consommation.FORTE);
        Maison maison18 = new Maison("maison18", Consommation.BASSE);

        // Connexions
        List<Connection> listeCo = new ArrayList<>();
        listeCo.add(new Connection(maison1, gen1));
        listeCo.add(new Connection(maison2, gen2));
        listeCo.add(new Connection(maison3, gen3));
        listeCo.add(new Connection(maison4, gen4));
        listeCo.add(new Connection(maison5, gen2));
        listeCo.add(new Connection(maison6, gen3));
        listeCo.add(new Connection(maison7, gen4));
        listeCo.add(new Connection(maison8, gen2));
        listeCo.add(new Connection(maison9, gen3));
        listeCo.add(new Connection(maison10, gen1));
        listeCo.add(new Connection(maison11, gen2));
        listeCo.add(new Connection(maison12, gen3));
        listeCo.add(new Connection(maison13, gen4));
        listeCo.add(new Connection(maison14, gen2));
        listeCo.add(new Connection(maison15, gen3));
        listeCo.add(new Connection(maison16, gen4));
        listeCo.add(new Connection(maison17, gen2));
        listeCo.add(new Connection(maison18, gen3));

        // Listes
        List<Generateur> listeGen = new ArrayList<>();
        listeGen.add(gen1); listeGen.add(gen2); listeGen.add(gen3);
        listeGen.add(gen4); listeGen.add(gen5); listeGen.add(gen6); listeGen.add(gen7);

        List<Maison> listeMaisons = new ArrayList<>();
        listeMaisons.add(maison1); listeMaisons.add(maison2); listeMaisons.add(maison3);
        listeMaisons.add(maison4); listeMaisons.add(maison5); listeMaisons.add(maison6);
        listeMaisons.add(maison7); listeMaisons.add(maison8); listeMaisons.add(maison9);
        listeMaisons.add(maison10); listeMaisons.add(maison11); listeMaisons.add(maison12);
        listeMaisons.add(maison13); listeMaisons.add(maison14); listeMaisons.add(maison15);
        listeMaisons.add(maison16); listeMaisons.add(maison17); listeMaisons.add(maison18);

        // Création du réseau
        Reseau reseau = new Reseau(listeGen, listeMaisons, listeCo);

        // Sauvegarde dans ExportedFiles
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom du fichier : ");
        String nom = sc.nextLine();
        File dossier = new File("ExportedFiles");
        File f = new File(dossier, nom + ".txt");

        if(f.exists())
        {
            System.out.println("Le fichier " + nom + "existe déjà, voulez vous l'écraser ? (O/N) : ");
            String choix = sc.nextLine();
            if(choix.equalsIgnoreCase("O"))
            {
                NetworkSerializer.Export(reseau, f);
            } else if (choix.equalsIgnoreCase("N"))
                System.out.println("Sauvegarde annulée");

        }


    }
}
