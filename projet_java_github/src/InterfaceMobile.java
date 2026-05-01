//MALISEV Vadim 21302160

/**
 * Interface pour toute ressource capable de se déplacer,
 * de manger et de perdre de la vitalité.
 * Étend Vivant car une ressource mobile peut aussi mourir.
 * @author MALISEV Vadim
 * @version 1.0
 */

public interface InterfaceMobile extends InterfaceVivant {
    /**
     * Tente de manger la ressource r.
     * @param r correspond a la ressource qui va potentielement etre manger, car elle se trouve sur la meme case qu'une autre ressource
     * @return true si le repas est possible, false sinon
     */
    boolean manger(Ressource r);
 
    /**
     * Décrémente la vitalité de la ressource à chaque tour.
     */
    void diminution_quantite();
 
    /**
     * @return la direction actuelle (1=haut, 2=bas, 3=gauche, 4=droite)
     */
    int getDirection();
 
    /**
     * Modifie la direction de déplacement.
     * @param direction corespond a la nouvelle direction dans laquelle la ressource va se déplacer
     */
    void ModifierDirection(int direction);

}