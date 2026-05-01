//MALISEV Vadim 21302160

/**
* Cette classe est extendu de RessourcesMouvements, elle contient un constructeur pour 
* Poisson_carnivore et la méthode manger qui à été override pour correspondre a Poisson_carnivore
* par defaut la quantite de cette ressource est 3
* @author MALISEV Vadim
* @version 1.0
*/

public class Poisson_carnivore extends RessourcesMouvements{
    /**
     * C'est le constructeur pour la ressource Poisson_carnivore
     * @param nom_ressource correspond au nom de la classe de la ressource ici "Poisson_carnivore"
     */
    public Poisson_carnivore (String nom_ressource){
        super(nom_ressource, 5);
    }

    @Override
    /**
     * Test si la ressource passer en parametre peut etre manger par un Poisson_carnivore, un poisson carnivore peut
     * manger tout sauf les poissons invasifs
     * @param r correspond a la ressource qui va surment etre mangée
     * @return renvoie true si la ressource à été mangé, false sinon
     */
    public boolean manger (Ressource r){
        if (r instanceof RessourcesStatiques|| r instanceof Poisson_hebivore ){
            return super.manger(r);
        }
        return false;
    } 
}