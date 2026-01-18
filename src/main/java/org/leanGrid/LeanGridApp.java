/**
 * Classe LeanGridApp
 * @author YAHIATENE Melissa
 * @see NetworkOperator
 **/

package org.leanGrid;
import org.leanGrid.model.NetworkOperator;

public class LeanGridApp
{
    public static void main( String[] args )
    {
        NetworkOperator net = new NetworkOperator();

        try {
            if (args.length == 2) {
                String file = args[0];
                int severite = Integer.parseInt(args[1]);

                if (severite <= 1) {
                    System.err.println("Erreur : la sévérité doit être un entier strictement supérieur à 1.");
                    return;
                }

                net.menuNetworkLoader(file, severite);
            } else {
                net.menuNetworkBuilder();
            }
        }
        catch (NumberFormatException e) {
            System.err.println("Erreur : le second argument (sévérité) doit être un entier.");
        }
        catch (SecurityException e) {
            System.err.println("Erreur : Accès refusé au fichier ou ressource protégée.");
        }

    }
}
