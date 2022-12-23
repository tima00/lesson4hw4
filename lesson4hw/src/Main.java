import java.util.Arrays;
import java.util.Random;

public class Main {
    private static int bossHealth = 700;
    private static int bossDamage = 50;
    private static String bossDefenceType = "";

    private static int[] heroesHealth = {270, 280, 260, 100};
    private static int[] heroesDamage = {20, 15, 25, 0};
    private static String[] heroesAttackType = {"Physical", "Magical", "Kinetic",  "Medic"};
    private static int roundCounter = 1;

    public static void main(String[] args) {
        printStatistic();
        while (isGameOver() != true){
            round();
            roundCounter++;
        }

    }
    public static void printStatistic(){
        System.out.println("____");
        System.out.println("Round:"+ roundCounter);
        System.out.println("Boss health: "+ bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]);
        }
        System.out.println("________");
    }

    public static void bossHits(){
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0){
                if (heroesHealth[i] - bossDamage < 0){
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHits(){
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0){
                if (heroesAttackType[i] == bossDefenceType){
                    heroesDamage[i] = 0;
                    System.out.println(" Boss reflected " + heroesAttackType[i]);
                }else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            } else {
                if (bossHealth - heroesDamage [i] < 0){
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    public static boolean isGameOver(){
        if (bossHealth <= 0){
            System.out.println("Heroes won!");
            return  true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead == true){
            System.out.println("Boss won!");
        }
        return allHeroesDead;
    }


    public static void changeDefenceType(){
        int randomIndex = new Random().nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println(" Boss choice: " + bossDefenceType);
    }

    public static void  temp(){
        int heTemp = new Random(0).nextInt(80) + 20;
        if (heroesHealth[3] > 0){
            for (int i = 0; i < heroesHealth.length; i++){
                if (i == 3) continue;
                if (heroesHealth[i] < 100 && heroesHealth[i] > 0){
                    heroesHealth[i] = heroesHealth[i] + heTemp;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(heroesHealth));
    }

    public static  void  round(){
        if (bossHealth > 0){
            changeDefenceType();
            bossHits();
        }
        heroesHits();
        printStatistic();

        temp();
    }


}