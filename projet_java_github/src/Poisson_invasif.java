//MALISEV Vadim 21302160

/**
* Cette classe est extendu de RessourcesMouvements, elle contient un constructeur pour 
* Poisson_invasif et la méthode manger qui à été override pour correspondre a Poisson_invasif
* par defaut la quantite de cette ressource est 3
* @author MALISEV Vadim
* @version 1.0
*/

public class Poisson_invasif extends RessourcesMouvements{
    /**
     * C'est le constructeur pour la ressource Poisson_invasif
     * @param nom_ressource correspond au nom de la classe de la ressource ici "Poisson_invasif"
     */
    public Poisson_invasif (String nom_ressource){
        super(nom_ressource, 10);
    }
}