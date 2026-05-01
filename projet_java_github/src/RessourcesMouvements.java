//MALISEV Vadim 21302160

/**
 * Cette classe abstraite est extendu de Ressources, elle contient un constructeur pour 
 * les ressources en mouvements, et different méthode tel que doit_mourrir ...
 * @author MALISEV Vadim
 * @version 1.0
 */

public abstract class RessourcesMouvements extends Ressource implements InterfaceMobile{
    /**
     * Direction de déplacement de la ressource.
     * Valeurs possibles : 1=haut, 2=bas, 3=gauche, 4=droite.
     */
    protected int direction; //entre 1 et 4 1:haut, 2:bas, 3:gauche, 4:droite
    /**
     * Constructeur pour la classe RessourcesMouvements
     * @param nom_ressource corespond a nom de la classe, ex : "RessourcesMouvements"
     * @param quantite correspond a la quantite qu'on veut de cette ressource.
     */
    public RessourcesMouvements (String nom_ressource, int quantite){
        super(nom_ressource, quantite); 
        this.direction = (int) (Math.random()*(4)+1);
    }

    @Override 
    /**
     * Test si la Ressource devra mourrir parce que la quantité est nulle
     * @return true si la ressource doit être retirée du terrain
     */
    public boolean doit_mourir (){
        if (this.getQuantite() == 0){
            return true;
        }
        return false;
    }

    @Override 
    /**
     * @return la direction actuelle (1=haut, 2=bas, 3=gauche, 4=droite)
     */
    public int getDirection(){
        return this.direction;
    }

    @Override 
    /**
     * Modifie la direction de déplacement.
     * @param direction corespond a la nouvelle direction dans laquelle la ressource va se déplacer
     */
    public void ModifierDirection(int direction){
        this.direction = direction;
    }

    @Override 
    /**
     * Décrémente la quantité de la ressource à chaque tour.
     */
    public void diminution_quantite(){
        this.setQuantite(this.getQuantite()-1);
    }

    @Override 
    /**
     * Tente de manger la ressource r.
     * @param r correspond a la ressource qui va potentielement etre manger, car elle se trouve sur la meme case qu'une autre ressource
     * @return true si le repas est possible, false sinon
     */
    public boolean manger(Ressource r){ //la version mange tout valable pour poisson ivasif
        if (r instanceof Algue_toxique){
            if (this.getQuantite()-r.getQuantite()<0){
                this.setQuantite(0);
                return true;
            }
            else {
                this.setQuantite(this.getQuantite()-r.getQuantite());
                return true;
            }
        }
        else{
            this.setQuantite(r.getQuantite()+this.getQuantite());
            return true;
        }
    }

    /**
     * Override de toString() en rajoutant la direction dedans 
     * @return le toString de de ressources + la direction qui a été ajouté
     */
    public String toString(){
        String res ="";
        if (direction == 1){
            res+="haut";
        }
        if (direction == 2){
            res+="bas";
        }
        if (direction == 3){
            res+="gauche";
        }
        if (direction == 4){
            res+="droite";
        }
        return super.toString()+", direction = "+res;
    }
}