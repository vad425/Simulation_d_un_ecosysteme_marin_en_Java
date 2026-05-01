//MALISEV Vadim 21302160

/**
 * Cette classe abstraite est extendu de Ressources, elle contient un constructeur pour 
 * les ressources en mouvements, et different méthode tel que doit_mourrir ...
 * @author MALISEV Vadim
 * @version 1.0
 */

public abstract class RessourcesStatiques extends Ressource implements InterfaceStatique{
    /**
     * Constructeur pour la classe RessourcesStatiques, la quantité de cette ressources sera aléatoire entre 1 et 3 et meurt quand elle atteint 10
     * @param nom_ressource corespond a nom de la classe, ex : "RessourcesStatiques"
     */
    public RessourcesStatiques (String nom_ressource){
        super(nom_ressource, (int) (Math.random()*(3)+1)); //crée une ressource avec une quatitité entre 1 et 3 aléatoirement.
    }

    @Override 
    /**
     * incrémentation de la quantite de la ressource à chaque tour.
     */
    public void incrementation_quantite(){
        this.setQuantite(this.getQuantite()+1);
    }

    @Override 
    /**
     * Test si la Ressource devra mourrir parce que la quantité vaut 10
     * @return true si la ressource doit être retirée du terrain
     */
    public boolean doit_mourir (){
        if (this.getQuantite() == 10){
            return true;
        }
        return false;
    }

}