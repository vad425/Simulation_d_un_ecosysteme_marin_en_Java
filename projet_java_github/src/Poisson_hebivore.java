//MALISEV Vadim 21302160

/**
* Cette classe est extendu de RessourcesMouvements, elle contient un constructeur pour 
* Poisson_herbivore et la méthode manger qui à été override pour correspondre a Poisson_hebivore
* @author MALISEV Vadim
* @version 1.0
*/

public class Poisson_hebivore extends RessourcesMouvements{
    /**
     * C'est le constructeur pour la ressource Poisson_herbivore;
     * par defaut le quantite de cette ressource est 3
     * @param nom_ressource correspond au nom de la classe de la ressource ici "Poisson_herbivore"
     */
    public Poisson_hebivore (String nom_ressource){
        super(nom_ressource, 3);
    }

    @Override
    /**
     * Test si la ressource passer en parametre peut etre manger par un Poisson_herbivore, un poisson herbivore peut
     * manger tout sauf les poissons invasifs, et les poisson carnivore
     * @param r correspond a la ressource qui va surment etre mangée
     * @return renvoie true si la ressource à été mangé, false sinon
     */
    public boolean manger (Ressource r){
        if (r instanceof RessourcesStatiques ){
            return super.manger(r);
        }
        return false;
    } 
}