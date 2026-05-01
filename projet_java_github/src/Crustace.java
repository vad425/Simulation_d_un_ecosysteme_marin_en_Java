//MALISEV Vadim 21302160

/**
 * Cette classe extends de RessourcesMouvements, elle contient uniquement le constructeur de la ressource Algue_verte
 * @author MALISEV Vadim
 * @version 1
 */

public class Crustace extends RessourcesMouvements{
    /**
     * Constructeur de la ressource Crustace
     * @param nom_ressource correspond au nom de la classe de la ressource ici "Crustace"
     */
    public Crustace (String nom_ressource){
        super(nom_ressource,10);
    }

    @Override
    /**
     * Test si la ressource passée en paramètre peut etre mange par un crustace,
     * si c'est le cas elle le mange avec la méthode manger de RessourcesMouvements et retourne true, sinon false
     * @param r correspond a la ressource qui se trouve sur le meme case que le crustace
     * @return retourne true si la ressource passée en parametren est mangé, sinon false
     */
    public boolean manger (Ressource r){
        if (r instanceof Huitre ){ //les crustacés ne peuvent manger que des huitres
            return super.manger(r);
        }
        return false;
    } 
}