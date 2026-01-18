package org.leanGrid;

import org.leanGrid.model.NetworkSerializer;
import org.leanGrid.model.util.Reseau;

public class TestParser {
    public static void main(String[] args) {

        Reseau reseau1 = NetworkSerializer.parser("src/test/java/org/leanGrid/FichiersTest/reseau.txt");
        System.out.println(reseau1);

        Reseau reseau2 = NetworkSerializer.parser("src/test/java/org/leanGrid/FichiersTest/instance1.txt");
        System.out.println(reseau2);

        Reseau reseau3 = NetworkSerializer.parser("src/test/java/org/leanGrid/FichiersTest/instance2.txt");
        System.out.println(reseau3);

        Reseau reseau4 = NetworkSerializer.parser("src/test/java/org/leanGrid/FichiersTest/instance3.txt");
        System.out.println(reseau4);

        Reseau reseau5 = NetworkSerializer.parser("src/test/java/org/leanGrid/FichiersTest/instance4.txt");
        System.out.println(reseau5);

        Reseau reseau6 = NetworkSerializer.parser("src/test/java/org/leanGrid/FichiersTest/instance5.txt");
        System.out.println(reseau6);

        Reseau reseau7 = NetworkSerializer.parser("src/test/java/org/leanGrid/FichiersTest/instance6.txt");
        System.out.println(reseau7);

        Reseau reseau8 = NetworkSerializer.parser("src/test/java/org/leanGrid/FichiersTest/instance7.txt");
        System.out.println(reseau8);

        Reseau reseau9 = NetworkSerializer.parser("src/test/java/org/leanGrid/FichiersTest/instance_tres_grande1.txt");
        System.out.println(reseau9);

        Reseau reseau10 = NetworkSerializer.parser("src/test/java/org/leanGrid/FichiersTest/instance_tres_grande2.txt");
        System.out.println(reseau10);


    }

}
