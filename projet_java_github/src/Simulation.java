import java.util.ArrayList;
//MALISEV Vadim 21302160

/**
 * Cette classe contient toutes les méthodes qui permette de gérer les ressources comme leur déplacement et autre.
 * elle met aussi a jour le terrain eleve et rajoute les ressources si elle meurent ou apparaissent
 * @author MALISEV Vadim
 * @version 1
 */

public class Simulation {
    private Terrain t;
    private ArrayList<Ressource> liste;
    /**
     * Correspond a un agent qui est accessible par n'importe quelle classe
     */
    public Agent a;

    /**
     * Constructeur de la classe Simulation, il construit un terrrain, une liste des ressources présentes et un agent
     * @param largeur largeur du terrain
     * @param longeur longeur du terrain
     */
    public Simulation(int largeur, int longeur) { //creation terrain vide
        t = new Terrain (largeur, longeur);
        this.liste = t.lesRessources();
        this.a = new Agent(largeur, longeur);

    }

    /**
     * Constructeur de la classe Simulation, il construit un terrrain, une liste des ressources présentes et un agent, et il y rajoute n ressource qui sont aléatoirement des algue, huitre, poissons ou crustacé et UNIQUEMENT un agent
     * @param largeur largeur du terrain
     * @param longeur longeur du terrain
     * @param nb_ressource nombre de ressources que l'on veut rajouter
     * @param nb_agent il DOIT OBLIGATOIREMENT ETRE A 1
     * @throws GestionExeption si le nombre de ressources est plus grand que le nombre de cases disponbiles
     */
    public Simulation (int largeur, int longeur, int nb_ressource, int nb_agent) throws GestionExeption  { //creation terrain avec des resources et agents
        t = new Terrain (largeur, longeur);
        if (nb_ressource > largeur*longeur){
            throw new GestionExeption("il y a trop de ressource en parametre le maximum est :"+largeur*longeur);
        }
        for (int i = 0; i<nb_ressource; i++){ //pour chaque ressource
            int nb_aléatoire = VariableAleatoire.ValeurAleatoire(2);//(int) (Math.random()*(2)+1); //soit c'est ressource statique soit ressource en mouvement d'ou random 2
            if (nb_aléatoire == 1){
                nb_aléatoire = VariableAleatoire.ValeurAleatoire(3); //trois type de ressource statique
                if (nb_aléatoire == 1){
                    Algue_verte r = new Algue_verte("A_verte"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase(VariableAleatoire.ValeurAleatoire(longeur), VariableAleatoire.ValeurAleatoire(largeur), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
                if (nb_aléatoire == 2){
                    Algue_toxique r = new Algue_toxique("A_toxique"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase(VariableAleatoire.ValeurAleatoire(longeur), VariableAleatoire.ValeurAleatoire(largeur), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
                if (nb_aléatoire == 3){
                    Huitre r = new Huitre("Huitre"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase(VariableAleatoire.ValeurAleatoire(longeur), VariableAleatoire.ValeurAleatoire(largeur), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
            }
            if (nb_aléatoire == 2){ //l'autre cas ressource en mouvement
                nb_aléatoire = VariableAleatoire.ValeurAleatoire(4); //4 ressources en mouvement
                if (nb_aléatoire == 1){
                    Poisson_hebivore r = new Poisson_hebivore("P_herbivore"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase(VariableAleatoire.ValeurAleatoire(longeur), VariableAleatoire.ValeurAleatoire(largeur), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
                if (nb_aléatoire == 2){
                    Poisson_carnivore r = new Poisson_carnivore("P_carnivore"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase(VariableAleatoire.ValeurAleatoire(longeur), VariableAleatoire.ValeurAleatoire(largeur), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
                if (nb_aléatoire == 1){
                    Poisson_invasif r = new Poisson_invasif("P_invasif"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase(VariableAleatoire.ValeurAleatoire(longeur), VariableAleatoire.ValeurAleatoire(largeur), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
                if (nb_aléatoire == 1){
                    Crustace r = new Crustace("Crustacé"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase(VariableAleatoire.ValeurAleatoire(longeur), VariableAleatoire.ValeurAleatoire(largeur), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
            }
        }
        this.liste=t.lesRessources(); //créé un array liste avec toutes les ressources

        //creation des agents
        this.a = new Agent(largeur, longeur);
    }

    /**
     * Permet a la ressource de se déplacer d'une case dans la direction actuelle, si il arrive a une bordure
     * du terrain, elle fait demi-tour et continue son déplacement, de plus a chaque déplacement il y a 25% 
     * de chance que l'agent change de direction aléatoirement
     * @param r correspond a la ressource que l'on veut deplacer
     */
    public void deplacement(RessourcesMouvements r) {
        int ancienneLigne = r.getLigne();
        int ancienneColonne = r.getColonne();

        int nouvelleLigne = ancienneLigne;
        int nouvelleColonne = ancienneColonne;

        int direction = r.getDirection();

        // calcul de la case cible
        if (direction == VariableAleatoire.HAUT) { // haut
            nouvelleLigne--;
        }
        else if (direction == VariableAleatoire.BAS) { // bas
            nouvelleLigne++;
        }
        else if (direction == VariableAleatoire.GAUCHE) { // gauche
            nouvelleColonne--;
        }
        else if (direction == VariableAleatoire.DROITE) { // droite
            nouvelleColonne++;
        }

        // si la case cible est hors terrain, on fait demi-tour
        if (!t.sontValides(nouvelleLigne, nouvelleColonne)) {
            if (direction == VariableAleatoire.HAUT) r.ModifierDirection(VariableAleatoire.BAS);
            else if (direction == VariableAleatoire.BAS) r.ModifierDirection(VariableAleatoire.HAUT);
            else if (direction == VariableAleatoire.GAUCHE) r.ModifierDirection(VariableAleatoire.DROITE);
            else if (direction == VariableAleatoire.DROITE) r.ModifierDirection(VariableAleatoire.GAUCHE);

            // on recalcule avec la nouvelle direction
            nouvelleLigne = ancienneLigne;
            nouvelleColonne = ancienneColonne;

            if (r.getDirection() == 1) nouvelleLigne--;
            else if (r.getDirection() == 2) nouvelleLigne++;
            else if (r.getDirection() == 3) nouvelleColonne--;
            else if (r.getDirection() == 4) nouvelleColonne++;
        }

        // si après demi-tour la case n'est toujours pas valide, on ne bouge pas
        if (!t.sontValides(nouvelleLigne, nouvelleColonne)) {
            return;
        }

        // si la case est occupée
        if (!t.caseEstVide(nouvelleLigne, nouvelleColonne)) {
            Ressource cible = t.getCase(nouvelleLigne, nouvelleColonne);

            if (!r.manger(cible)) {
                System.out.println(r + " ne peut pas manger " + cible + " donc ne bouge pas");
                return;
            }

            System.out.println(r + " mange " + cible);
            t.viderCase(cible.getLigne(), cible.getColonne()); 
            liste.remove(cible); 
        }

        // déplacement final
        t.viderCase(ancienneLigne, ancienneColonne);
        r.setPosition(nouvelleLigne, nouvelleColonne);
        t.setCase(nouvelleLigne, nouvelleColonne, r);

        if (Math.random() <= 0.25) {
            r.ModifierDirection((int)(Math.random() * 4) + 1);
        }
    }   

    /**
     * Fait comment déplacement mais pour toutes les ressources presentent sur le terrain grace a l'array liste 
     * qui stock toutes les ressources présentes sur le terrain
     */
    public void Deplacement_toute_ressource(){
        ArrayList<Ressource> copie = new ArrayList<>(liste); //anticipe le probleme si une ressource est mangé et donc supprimé de la liste pendant le parcours de la liste
        for (Ressource i : copie){
            if (i instanceof InterfaceMobile ){
                RessourcesMouvements rm = (RessourcesMouvements) i;
                if ((rm.getLigne() == -1) && (rm.getColonne()==-1)){
                    continue;
                }
                else{
                    if (rm.doit_mourir()==true){
                    t.viderCase(rm.getLigne(),rm.getColonne());
                    liste.remove(rm);
                    System.out.println(rm.toString()+" est mort de faim");
                    }
                    else{
                        rm.diminution_quantite();
                        deplacement(rm);
                    }
                }
            }
            if (i instanceof InterfaceStatique ){
                RessourcesStatiques rm = (RessourcesStatiques) i;
                if (rm.getLigne() == -1 && rm.getColonne() == -1) {
                    continue;
                }
                if (rm.doit_mourir()==true){
                    t.viderCase(rm.getLigne(),rm.getColonne());
                    liste.remove(rm);
                    System.out.println(rm.toString()+" est mort");
                }
                else{
                    rm.incrementation_quantite();
                }
            }
        }
    }

    /**
     * si on doit ajouter un ressource dans ce cas on fera appelle a cette méthode.
     * elle construit n'importe quelle type de ressource aléatoirement et la rajoute aléatoirement sur le terrain,
     * avant de l'a rajouter dans la case elle verifie que la casse n'est pas deja occupé. D'ou la valeur de 
     * retour qui est u boolean
     * @param t correspond au terrain créé dans le constructeur de simulation
     * @param largeur la largeur de tu terrain passé en parametre
     * @param longeur le longeur de tu terrain passé en parametre
     * @return true si on a rejouté un ressource, false sinon
     */
    public Ressource ajouter_ressource(Terrain t, int largeur, int longeur){//, ArrayList<Ressource> liste, int largeur, int longeur){
            int nb_aléatoire = (int) (Math.random()*(2)+1); //soit c'est ressource statique soit ressource en mouvement d'ou random 2
            Ressource r = null;
            if (nb_aléatoire == 1){
                nb_aléatoire = (int) (Math.random()*(3)+1); //trois type de ressource statique
                if (nb_aléatoire == 1){
                    r = new Algue_verte("A_verte"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase((int) (Math.random()*(longeur)+1), (int) (Math.random()*(largeur)+1), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
                if (nb_aléatoire == 2){
                    r = new Algue_toxique("A_toxique"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase((int) (Math.random()*(longeur)+1), (int) (Math.random()*(largeur)+1), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
                if (nb_aléatoire == 3){
                    r = new Huitre("Huitre"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase((int) (Math.random()*(longeur)+1), (int) (Math.random()*(largeur)+1), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
            }
            if (nb_aléatoire == 2){ //l'autre cas ressource en mouvement
                nb_aléatoire = (int) (Math.random()*(4)+1); //4 ressources en mouvement
                if (nb_aléatoire == 1){
                    r = new Poisson_hebivore("P_herbivore"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase((int) (Math.random()*(longeur)+1), (int) (Math.random()*(largeur)+1), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
                if (nb_aléatoire == 2){
                    r = new Poisson_carnivore("P_carnivore"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase((int) (Math.random()*(longeur)+1), (int) (Math.random()*(largeur)+1), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
                if (nb_aléatoire == 3){
                    r = new Poisson_invasif("P_invasif"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase((int) (Math.random()*(longeur)+1), (int) (Math.random()*(largeur)+1), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
                if (nb_aléatoire == 4){
                    r = new Crustace("Crustacé"); //la position du tableu va de 1 à la longueur/largeur
                    t.setCase((int) (Math.random()*(longeur)+1), (int) (Math.random()*(largeur)+1), r); //un entier dans [MIN,MAX] : int val=(int)(Math.random()*(MAX-MIN+1)+MIN);
                }
            }
            //System.out.println("test test"+r+" est apparue");
            return r;
            //liste.add(r); //rajoute la ressource à la liste 
        }
        

    /**
     * C'est un boucle for qui met a jour n fois le terrain, en prennant en compte les déplacements des ressources,
     * si elles sont mortes, mangées... 
     * cette méthode actualise le terrain en gros
     * @param n correspond au nombre de tour de bouble de deplacement qu'on veut faire
     */
    public void n_tour_de_boucle(int n){
        for (int i = 0; i<n; i++){
            System.out.println("TOUR DE BOUBLE NUMERO: "+(i+1)+" \n");
            System.out.println("Deplacement des ressources en cours et affichage de la chasse:\n");
            Deplacement_toute_ressource();
            System.out.println("\nLISTE DES RESSOURCES:");
            this.affiche_liste_ressources();
            System.out.println("AFFICHAGE DU TERRAIN MISE A JOUR");
            this.afficher_terrain(3);
            if (Math.random()<=0.25){ //ajoute une ressource aléatoirement avec un probabilité de 25% tout les tours
                Ressource r = this.ajouter_ressource(this.t, this.t.nbLignes, this.t.nbColonnes);
                System.out.println(r+"une nouvelle ressource est apparue");
                if (r.getLigne() >= 1 && r.getColonne() >= 1){ //si la ressource a été ajouté dans le terrain
                    this.t.setCase(r.getLigne(), r.getColonne(), r);
                    this.liste.add(r);
                }
                else{
                    System.out.println("la ressource n'a pas pu être ajoutée dans le terrain car la case était déjà occupé");
                }
            }
            // deplacement agent
            this.a.deplacement();
            if (i%2 == 0){
                this.a.decremente_solde();
            }
            if (this.a.peche(this.t) == true){
                Ressource r_temp = this.t.getCase(this.a.getPosx(), this.a.getPosy());
                t.viderCase(r_temp.getLigne(),r_temp.getColonne());
                liste.remove(r_temp);
                System.out.println(r_temp.toString()+" a été peché");
            }
            System.out.println(this.a.toString());
            System.out.println("\n");
        }
        System.out.println("FIN DE LA SIMULATION");
        System.out.println("LE SOLDE FINAL DE L'AGENT EST DE :"+this.a.getSolde()+" ET SA LISTE DE PECHE EST :"+this.a.getNb_poisson_peche());

    }

    /**
     * Affiche le terrain avec toutes les ressources prensentes dessus
     * @param nombre_caractere les n première lettre du nom de la classe qui sera affiché
     */
    public void afficher_terrain(int nombre_caractere){ 
        t.afficher(nombre_caractere);
    }

    /**
     * Affiche la liste de toutes les ressources presentes sur le terrain
     */
    public void affiche_liste_ressources(){
        for (Ressource i : liste){
            System.out.println(i.toString());
        }
        System.out.println("\n");
    }
}
