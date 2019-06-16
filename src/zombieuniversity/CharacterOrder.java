/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombieuniversity;

import zombieuniversity.character.Character;

/**
 *
 * @author Paul, Ian, Daniel
 */
class CharacterOrder {
    public static boolean endScenario(Character[] characters) {
        int alive = 0;
        for (int i = 0; i < characters.length; i++) {
            if (characters[i].isAlive()) {
                alive++;
            }
        }
        return alive == 0;
    }

    public void attackCycle(Character[] attackers, Character[] defenders) {
        for (int a = 0; a < attackers.length; a++) {
            //every zombie in attackers attack every zombie in defenders exactly once
            for (int d = 0; d < defenders.length; d++) {
                if (attackers[a].isAlive()) {
                    attackers[a].attack(defenders[d]);
                   /*
                    System.out.println(attackers[a].toString() + " attacked " + defenders[d].toString()
                            + ". the defender's health is now " + defenders[d].getHealth());
                    */
                }
            }
        }
    }

    public void attackRotation(Character[] survivors, Character[] zombies) {
        // Loop if we have at least 1 element in both arrays
        if(zombies.length > 0 && survivors.length > 0) {
            while (!endScenario(survivors) && !endScenario(zombies)) {
                attackCycle(survivors, zombies);
                attackCycle(zombies, survivors);
            }
        }
        
        // Count the remaining alive survivors
        int numAlive = 0;
        for (Character c : survivors) {
            if (c.isAlive()) {
                numAlive++;
            }
        }
        System.out.println("It seems that " + numAlive + " have made it to safety.");
    }
}