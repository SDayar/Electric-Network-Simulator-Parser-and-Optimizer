package org.leanGrid;

import org.leanGrid.model.*;
import org.leanGrid.model.util.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestSauvegarde
{
    public static void main(String[] args)
    {
        Generateur gen1 = new Generateur("gen1", 60);
        Generateur gen2 = new Generateur("gen2", 45);
        Generateur gen3 = new Generateur("gen3", 20);
        Generateur gen4 = new Generateur("gen4", 42);

        Maison maison1 = new Maison("maison1", Consommation.NORMAL);
        Maison maison2 = new Maison("maison2", Consommation.BASSE);
        Maison maison3 = new Maison("maison3", Consommation.NORMAL);
        Maison maison4 = new Maison("maison4", Consommation.FORTE);
        Maison maison5 = new Maison("maison5", Consommation.FORTE);
        Maison maison6 = new Maison("maison6", Consommation.NORMAL);

        Connection c1 = new Connection(maison1, gen1);
        Connection c2 = new Connection(maison2, gen2);
        Connection c3 = new Connection(maison3, gen3);
        Connection c4 = new Connection(maison4, gen4);
        Connection c5 = new Connection(maison5, gen2);
        Connection c6 = new Connection(maison6, gen3);

        List<Generateur> listeGen = new ArrayList<Generateur>();
        List<Maison> listeMaisons = new ArrayList<Maison>();
        List<Connection> listeCo = new ArrayList<Connection>();

        listeGen.add(gen1);
        listeGen.add(gen2);
        listeGen.add(gen3);
        listeGen.add(gen4);

        listeMaisons.add(maison1);
        listeMaisons.add(maison2);
        listeMaisons.add(maison3);
        listeMaisons.add(maison4);
        listeMaisons.add(maison5);
        listeMaisons.add(maison6);

        listeCo.add(c1);
        listeCo.add(c2);
        listeCo.add(c3);
        listeCo.add(c4);
        listeCo.add(c5);
        listeCo.add(c6);

        Reseau reseau = new Reseau(listeGen,listeMaisons, listeCo);

        File f = new File("ExportedFiles" + File.separator + "testSauvegarde1.txt");
        NetworkSerializer.Export(reseau, f);

    }


}
