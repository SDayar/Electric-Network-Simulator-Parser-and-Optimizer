package org.leanGrid;

import org.leanGrid.model.*;
import org.leanGrid.model.util.*;

public class TestInstance2Menu2
{
    public static void main(String[] args)
    {
        NetworkOperator net = new NetworkOperator();

        // Création des générateurs
        Generateur g1 = NetworkFactory.fabricGenerateur("G1", 60);
        Generateur g2 = NetworkFactory.fabricGenerateur("G2", 60);

        net.addGenerateur(g1);
        net.addGenerateur(g2);

        // Création des maisons
        Maison m1 = NetworkFactory.fabricMaison("M1", Consommation.FORTE);   // 40 kW
        Maison m2 = NetworkFactory.fabricMaison("M2", Consommation.FORTE);   // 40 kW
        Maison m3 = NetworkFactory.fabricMaison("M3", Consommation.NORMAL); // 20 kW
        Maison m4 = NetworkFactory.fabricMaison("M4", Consommation.BASSE);   // 10 kW

        net.addMaison(m1);
        net.addMaison(m2);
        net.addMaison(m3);
        net.addMaison(m4);

        // Connexions
        net.addConnection(NetworkFactory.fabricConnection(m1, g2));
        net.addConnection(NetworkFactory.fabricConnection(m3, g1));
        net.addConnection(NetworkFactory.fabricConnection(m2, g2));
        net.addConnection(NetworkFactory.fabricConnection(m4, g2));

        // Création du réseau
        Reseau reseau = NetworkFactory.fabricReseau(net.getGenerateurs(), net.getMaisons(), net.getConnections());
        net.addReseau(reseau);

        net.menu2(reseau);


    }
}
