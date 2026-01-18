package org.leanGrid;

import org.leanGrid.model.NetworkFactory;
import org.leanGrid.model.NetworkOperator;
import org.leanGrid.model.util.Consommation;
import org.leanGrid.model.util.Generateur;
import org.leanGrid.model.util.Maison;
import org.leanGrid.model.util.Reseau;

public class TestInstance1Menu2
{
    public static void main(String[] args) {
        NetworkOperator net = new NetworkOperator();

        // Création des générateurs
        Generateur g1 = NetworkFactory.fabricGenerateur("G1", 60);
        Generateur g2 = NetworkFactory.fabricGenerateur("G2", 60);


        net.addGenerateur(g1);
        net.addGenerateur(g2);


        // Création des maisons
        Maison m1 = NetworkFactory.fabricMaison("M1", Consommation.FORTE);
        Maison m2 = NetworkFactory.fabricMaison("M2", Consommation.FORTE);
        Maison m3 = NetworkFactory.fabricMaison("M3", Consommation.NORMAL);
        Maison m4 = NetworkFactory.fabricMaison("M4", Consommation.BASSE);


        net.addMaison(m1);
        net.addMaison(m2);
        net.addMaison(m3);
        net.addMaison(m4);

        // Connexions initiales
        net.addConnection(NetworkFactory.fabricConnection(m1, g1));
        net.addConnection(NetworkFactory.fabricConnection(m2, g2));
        net.addConnection(NetworkFactory.fabricConnection(m3, g1));
        net.addConnection(NetworkFactory.fabricConnection(m4, g2));

        // Création du réseau
        Reseau reseau = NetworkFactory.fabricReseau(net.getGenerateurs(), net.getMaisons(), net.getConnections());
        net.addReseau(reseau);

        // Lancement du menu2
        net.menuNetworkManagement(reseau);
    }
}
