//MALISEV Vadim 21302160

/**
* Cette interface définit les méthodes communes à toute ressource vivante du terrain.
* Toute ressource (statique ou mobile) peut mourir.
* @author MALISEV Vadim
* @version 1.0
*/

public interface InterfaceVivant {
    /**
     * Test si la Ressource devra mourrir parce que la quantité est nulle
     * @return true si la ressource doit être retirée du terrain
     */
    boolean doit_mourir();

    /**
     * Retourne la coordonnée x de la ressource
     * @return true si la ressource doit être retirée du terrain
     */
    int getLigne();

    /**
     * Retourne la coordonnée y de la ressource
     * @return true si la ressource doit être retirée du terrain
     */
    int getColonne();
}
