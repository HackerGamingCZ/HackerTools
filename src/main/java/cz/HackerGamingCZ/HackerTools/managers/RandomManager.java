package cz.HackerGamingCZ.HackerTools.managers;

import java.util.Random;

public class RandomManager {

    private Random random;

    public RandomManager(){
        random = new Random();
    }

    public int nextInt(int maxNumberToGet){
        return random.nextInt(maxNumberToGet+1);
    }

}
