package org.leanGrid;

import org.leanGrid.model.NetworkFactory;
import org.leanGrid.model.NetworkOperator;
import org.leanGrid.model.util.Consommation;
import org.leanGrid.model.util.Generateur;
import org.leanGrid.model.util.Maison;
import org.leanGrid.model.util.Reseau;

public class TestMenu2 {
    public static void main(String[] args) {
        NetworkOperator net = new NetworkOperator();

        // Création des générateurs
        Generateur g1 = NetworkFactory.fabricGenerateur("G1", 100);
        Generateur g2 = NetworkFactory.fabricGenerateur("G2", 60);
        Generateur g3 = NetworkFactory.fabricGenerateur("G3", 120);
        Generateur g4 = NetworkFactory.fabricGenerateur("G4", 90);

        net.addGenerateur(g1);
        net.addGenerateur(g2);
        net.addGenerateur(g3);
        net.addGenerateur(g4);

        // Création des maisons
        Maison m1 = NetworkFactory.fabricMaison("M1", Consommation.BASSE);
        Maison m2 = NetworkFactory.fabricMaison("M2", Consommation.FORTE);
        Maison m3 = NetworkFactory.fabricMaison("M3", Consommation.FORTE);
        Maison m4 = NetworkFactory.fabricMaison("M4", Consommation.NORMAL);
        Maison m5 = NetworkFactory.fabricMaison("M5", Consommation.FORTE);

        net.addMaison(m1);
        net.addMaison(m2);
        net.addMaison(m3);
        net.addMaison(m4);
        net.addMaison(m5);

        // Connexions initiales
        net.addConnection(NetworkFactory.fabricConnection(m1, g1));
        net.addConnection(NetworkFactory.fabricConnection(m2, g2));
        net.addConnection(NetworkFactory.fabricConnection(m3, g3));
        net.addConnection(NetworkFactory.fabricConnection(m4, g4));
        net.addConnection(NetworkFactory.fabricConnection(m5, g1));

        // Création du réseau
        Reseau reseau = NetworkFactory.fabricReseau(net.getGenerateurs(), net.getMaisons(), net.getConnections());
        net.addReseau(reseau);

        // Lancement du menu2
        net.menuNetworkManagement(reseau);
    }
}
