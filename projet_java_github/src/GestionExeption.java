//MALISEV Vadim 21302160

/**
 * Cette classe extends Exception, elle contient uniquement le constructeur de la GestionExeption 
 * qui test si le nombre de de ressources a placer sur le terrain n'est pas trop grand par rapport au nombre de case disponible 
 * @author MALISEV Vadim
 * @version 1
 */

public class GestionExeption extends Exception {
    /**
     * correspond a la l'erreur qui sera throw si on a une erreur
     * @param message correspond au message d'erreur qui sera affiché
     */
    public GestionExeption(String message) {
        super(message);
    }
}