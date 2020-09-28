package com.maistruk.battleship.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerAI {

    private List<Field> enemyHits = new ArrayList<>();
    private Random random = new Random();
    private List<Integer> index = new ArrayList<>();
    private Fleet myFleet;

    

    public ComputerAI() {
        enemyHits = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                String parameter = "enemyHit_" + getLatter(i) + j;
                Field field = new Field(parameter, false);
                enemyHits.add(field);
                System.out.println(field);
            }
        }
    }

    public List<Field> getEnemyHits() {
        return enemyHits;
    }

    public void setEnemyHits(List<Field> enemyHits) {
        this.enemyHits = enemyHits;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public List<Integer> getIndex() {
        return index;
    }

    public void setIndex(List<Integer> index) {
        this.index = index;
    }

    public Fleet getMyFleet() {
        return myFleet;
    }

    public void setMyFleet(Fleet myFleet) {
        this.myFleet = myFleet;
    }
    
    public void enemyHit() {
        boolean check = true;
        int hitIndex = 0;
        while(check) {
            check = false;
            hitIndex = random.nextInt(49);
            for(Integer num : index) {
                if(num.equals(hitIndex)) {
                    check = true;
                }
            }
        }
        enemyHits.get(hitIndex).setValue(true);
        index.add(hitIndex);
    }
    
    public void addFleet(Fleet fleet) {
        for(Field fleetField : fleet.getAllFields()) {
            for(Field hitField : enemyHits) {
                String myShip = fleetField.getName().substring(3, 5);
                String enemyHit = hitField.getName().substring(9, 11);
                if(myShip.equals(enemyHit) && fleetField.getValue() == true ) {
                    hitField.setValue(true);
                }
            }
        }
    }
    
    public String ifExistField(String fieldName) {
        String myField = "my_" + fieldName.substring(9, 11);
        myField = myFleet.ifExistField(myField);
        if(myField.equals("checked")) {
            return "checked";
        }
        for (Field field : enemyHits) {
            if (field.getName().equals(fieldName) && field.getValue() == true) {
                return "checked";
            }
        }
        return "";
    }

    public String getLatter(int i) {
        if (i == 1) {
            return "a";
        } else if (i == 2) {
            return "b";
        } else if (i == 3) {
            return "c";
        } else if (i == 4) {
            return "d";
        } else if (i == 5) {
            return "e";
        } else if (i == 6) {
            return "f";
        } else if (i == 7) {
            return "g";
        } else {
            return null;
        }
    }

}
