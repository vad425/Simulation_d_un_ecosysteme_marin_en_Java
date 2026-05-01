//MALISEV Vadim 21302160

/**
 * Cette classe permet de faire la simulation de notre environement, 
 * on peut choisir le nombre de ressources presentes au départ de la simulation et 
 * les dimensions du terrain
 * @author MALISEV Vadim
 * @version 1
 */

public class TestSimulation {
/**
 * Point d'entrée du programme.
 * Initialise une simulation 5x5 avec 20 ressources et 1 agent dans ce cas precis
 * affiche le terrain initial puis joue 15 tours.
 *
 * @param args arguments de la ligne de commande (non utilisés)
 */
    public static void main(String [] args){
        try{
            Simulation s = new Simulation(5, 5, 20, 1);
            //System.out.println(s.a.toString());
            System.out.println("TERRAIN INITIAL: \n");
            s.afficher_terrain(3);
            System.out.println("LISTE DES RESSOURCES:\n");
            s.affiche_liste_ressources();
            s.n_tour_de_boucle(15);
        }
        catch (GestionExeption e){
            System.out.println("Erreur sur le nombre de ressources: "+e.getMessage());
        }

        
        //TEST CONSTRUCTEUR COPIE
        // Agent a2 = new Agent(s.a);
        // System.out.println("agent copier"+a2.toString());
        // for (int i =0; i<10; i++){
        //     a2.decremente_solde();
        // }
        // System.out.println("agent copier"+a2.toString()+"\n\n");
        // System.out.println("original"+s.a.toString());
    
    }
}