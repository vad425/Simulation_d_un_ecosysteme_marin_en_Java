//MALISEV Vadim 21302160

/**
 * Cette classe est une classe statique, qui permet d'avoir un entier aléatoire entre 1 et max ou bien 
 * un entier entre min et max
 * @author MALISEV Vadim
 * @version 1
 */
public class VariableAleatoire {
    /**
     * correspond la direction vers le haut qui correspond a 1
     */
    public final static int HAUT = 1;
    /**
     * correspond la direction vers le bas qui correspond a 2
     */
    public final static int BAS = 2;
    /**
     * correspond la direction vers la gauche qui correspond a 3
     */
    public final static int GAUCHE = 3;
    /**
     * correspond la direction vers la droite qui correspond a 4
     */
    public final static int DROITE = 4;

    /**
     * renvoie un nombre aléatoire entre 1 et max
     * @param max le plus grand entier qu'on veut avoir
     * @return int entre 1 et max
     */
    public static int ValeurAleatoire(int max){
        return (int) (Math.random()*(max)+1); //nobre aléatoire entre 1 et max inclu
    }

    /**
     * renvoie un nombre aléatoire entre min et max
     * @param max le plus grand entier qu'on veut avoir
     * @param min le plus petit entier qu'on veut avoir
     * @return int entre min et max
     */
    public static int ValeurAleatoire(int max, int min){
        return (int) (Math.random()*(max-min+1)+min); //nobre aléatoire entre min et max inclu
    }
}