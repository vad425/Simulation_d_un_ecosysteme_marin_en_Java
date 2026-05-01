//MALISEV Vadim 21302160

/**
 * Interface pour toute ressource fixe sur le terrain.
 * Une ressource statique croît au fil du temps jusqu'à mourir (surpopulation).
 * Étend Vivant car une ressource statique peut aussi mourir.
 * @author MALISEV Vadim
 * @version 1
 */


public interface InterfaceStatique extends InterfaceVivant {
    /**
     * Incrémente la quantité de la ressource à chaque tour.
     */
    void incrementation_quantite();
}