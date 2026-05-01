//MALISEV Vadim 21302160

import java.util.ArrayList;

/**
 * Cette classe représente l'agent qui se déplace sur le terrain, pêche des ressources et gère son solde.
 * L'agent a une position (posx, posy), une direction de déplacement, un solde qui représente la valeur totale des ressources pêchées, et une liste des ressources pêchées.
 * L'agent peut se déplacer dans les quatre directions (haut, bas, gauche, droite) et peut pêcher des ressources présentes sur le terrain. 
 * Le solde de l'agent est mis à jour en fonction des ressources pêchées, et il peut également perdre de la valeur en fonction de certaines conditions (par exemple, pêcher un poisson invasif).
 * 
 * @author MALISEV Vadim
 * @version 1.0
 */

public class Agent {
    private final int x_max;
    private final int y_max;
    private int direction; //entre 1 et 4 1:haut, 2:bas, 3:gauche, 4:droite
    private int posx;
    private int posy;
    private int solde;
    private ArrayList<Ressource> liste_peche;
    private int nb_poisson_peche;


    /**
     * Crée un agent avec une position et une direction aléatoires.
     *
     * @param x_max nombre de lignes du terrain, donc position maximale pour les colonnes
     * @param y_max nombre de colonnes du terrain, donc position maximale pour les lignes
     */
    public Agent(int x_max, int y_max){ 
        //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
        this.x_max = x_max;
        this.y_max = y_max;
        this.liste_peche = new ArrayList<>();
        this.posx = (int) (VariableAleatoire.ValeurAleatoire(x_max)); //position x aléatoire entre 1 et x_max
        this.posy = (int) (VariableAleatoire.ValeurAleatoire(y_max)); //position y aléatoire entre 1 et y_max
        this.solde = 0;
        this.direction = (int) (VariableAleatoire.ValeurAleatoire(4));
        this.nb_poisson_peche = 0;
    }

    /**
     * Constructeur d'un agent identique a un agent passer un parametre (Constructeur Copie)
     * @param a agent qui va etre clonné à l'indentique.
     */
    public Agent(Agent a) {
        this.x_max = a.x_max;
        this.y_max = a.y_max;
        this.posx = a.posx;
        this.posy = a.posy;
        this.solde = a.solde;
        this.direction = a.direction;
        this.nb_poisson_peche = a.nb_poisson_peche;
        this.liste_peche = new ArrayList<>(a.liste_peche);
    }


    /**
     * Permet d'avoir le solde de l'agent
     * @return le solde de l'agent
     */
    public int getSolde(){
        return this.solde;
    }

    /**
     * Permet de savoir le nombre de poisson pecher par l'agent
     * @return nombre de poisson peché
     */
    public int getNb_poisson_peche(){
        return this.nb_poisson_peche;
    }


    /**
     * Permet de savoir la direction actuelle de l'agent, il peut soit aller en haut ==1, soit en bas ==2, soit à gauche ==3, soit à droite ==4
     * @return la direction en int
     */
    public int getDirection(){
        return this.direction;
    }

    /**
     * Permet de savoir la position x (Colonne) de l'agent actuellemnt
     * @return la position x
     */
    public int getPosx(){
        return this.posx;
    }

    /**
     * Permet de savoir la position y (Ligne) de l'agent actuellemnt
     * @return la position y
     */
    public int getPosy(){
        return this.posy;
    }

    /**
     * Permet de modifier la direction de l'agent, exemple il se deplacait vers le haut, apres cette méthode il ira dans la direction passé en paramètre
     * @param direction la nouvelle direction (haut, bas, gauche, droit)
     */
    public void ModifierDirection(int direction){
        this.direction = direction;
    }

    /**
     * Permet de savoir si les coordonées passées en paramètre sont valide, c'est a dire que l'agent sera bel et bien encore sur le terrain
     * @param posx la future position x de l'agent après son déplacement
     * @param posy la future position y de l'agent après son déplacement
     * @return true si la position est valable, false sinon
     */
    public boolean sontValides(int posx, int posy){
        if (posx >=1 && posx<=this.x_max && posy>=1 && posy<=this.y_max){
            return true;
        }
        return false;
    }


    /**
     * Permet de mettre a jour le solde, si l'agent attrape une resssource son solde augmente du nombre de quantité de la ressource attrapé, Attention si le poisson est invasif, il perd en solde la quantité de se poisson invasif exemple (Crustacé quantité 10, le nouveau solde sera solde + 10)
     * @param r la ressource pêchée
     */
    public void mise_a_jour_solde(Ressource r){
        if (r instanceof Huitre || r instanceof RessourcesMouvements){
            if (r instanceof Poisson_invasif){ //c'est un poisson invasrif, donc le solde doit diminuer
                this.solde -= -5;
            }
            else{
                this.solde += r.getQuantite(); //c'et une ressouce classique donc il doit etre incrementé 
            }
        }
    }

    /**
     * Chaque 2 tour le solde se décremente de 1 (frais d'essence), decremente_solde permet de decrementer uniquement de 1 le solde
     */
    public void decremente_solde(){
        this.solde--;
    }

    /**
     * Permet de savoir si l'agent se trouve sur la meme position qu'une ressource et peu le manger par conséquence, renvoie true si c'est la cas, false sinon
     * @param t Correspond à terrain où se trouve les ressources
     * @return true si l'agent a manger une ressource, false sinon
     */
    public boolean peche(Terrain t){
        if(t.caseEstVide(this.posx, this.posy) == false){
            Ressource r = t.getCase(this.posx, this.posy);
            this.mise_a_jour_solde(r); //l'agent recupere la quantité de la ressource pechée
            liste_peche.add(r); //mise a jour de la liste de poisson peché
            this.nb_poisson_peche++;
            return true;
        }
        return false;
    }

    /**
     * Permet a l'agent de se déplacer d'une case dans la direction actuelle, si il arrive a une bordure du terrain, il fait demi-tour et continue son déplacement, de plus a chaque déplacement il y a 25% de chance que l'agent change de direction aléatoirement
     */
    public void deplacement() {
        int ancienneLigne = this.posx;
        int ancienneColonne = this.posy;

        int nouvelleLigne = ancienneLigne;
        int nouvelleColonne = ancienneColonne;

        int direction = this.getDirection();

        // calcul de la case cible
        if (direction == 1) { // haut
            nouvelleLigne--;
        }
        else if (direction == 2) { // bas
            nouvelleLigne++;
        }
        else if (direction == 3) { // gauche
            nouvelleColonne--;
        }
        else if (direction == 4) { // droite
            nouvelleColonne++;
        }

        // si la case cible est hors terrain, on fait demi-tour
        if (this.sontValides(nouvelleLigne, nouvelleColonne)==false) {
            if (direction == 1) this.ModifierDirection(2);
            else if (direction == 2) this.ModifierDirection(1);
            else if (direction == 3) this.ModifierDirection(4);
            else if (direction == 4) this.ModifierDirection(3);

            // on recalcule avec la nouvelle direction
            nouvelleLigne = ancienneLigne;
            nouvelleColonne = ancienneColonne;

            if (this.getDirection() == 1) nouvelleLigne--;
            else if (this.getDirection() == 2) nouvelleLigne++;
            else if (this.getDirection() == 3) nouvelleColonne--;
            else if (this.getDirection() == 4) nouvelleColonne++;
        }

        this.posx = nouvelleLigne; 
        this.posy = nouvelleColonne;

        if (Math.random() <= 0.25) { // le position change aléatoirement avec une probabilité de 25%
            this.ModifierDirection((int)(Math.random() * 4) + 1);
        }
    }   

    /**
     * Permet d'afficher les informations de l'agent, notamment sa position, son solde, sa direction et la liste des ressources pêchées
     * @return une chaîne de caractères représentant les informations de l'agent
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
        String affichage_liste="";
        if (liste_peche.isEmpty() == true){
            affichage_liste="pas encore de poisson pêché";
        }
        else{
            for (Ressource i : liste_peche){
                affichage_liste+=i.toString();
            }
        }
        return "Position du bateau en position x:"+this.posx+", Position y:"+this.posy+" le solde est de :"+this.solde+" sa direction est :"+res+" et sa liste de peche est :"+affichage_liste;
    }


    
}