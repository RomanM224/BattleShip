package com.maistruk.battleship.model;

import java.util.List;

public class FieldChecker {

    private List<Field> fields;
    private Fleet fleet;
    private List<Field> shipsFields;

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(Fleet fleet) {
        this.fleet = fleet;
    }
    
    public List<Field> getShipsFields() {
        return shipsFields;
    }

    public void setShipsFields(List<Field> shipsFields) {
        this.shipsFields = shipsFields;
    }

    public String ifExistFieldMyFleet(String fieldName) {
        boolean matchesWithMyShips = matchesWithMyShips(fieldName);
        boolean matchesWithFields = matchesWithMyFields(fieldName);
        if(matchesWithMyShips && matchesWithFields) {
            return "checked";
        } else if(matchesWithMyShips || matchesWithFields) {
            return "checked";
        }else {
            return "";
        }
    }
    
    public boolean matchesWithMyFields(String fieldName) {
        fieldName = "enemyHit_" + fieldName;
        for (Field field : fields) {
            if (field.getName().equals(fieldName) && field.getValue() == true) {
                return true;
            }
        }
        return false;
    }
    
    public boolean matchesWithMyShips(String fieldName) {
        fieldName = "my_" + fieldName;
        for(Field field : shipsFields) {
            if(field.getName().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }
    
    
    public String ifExistFieldEnemyFleet(String fieldName) {
        boolean matchesWithEnemyShips = matchesWithEnemyShips(fieldName);
        boolean matchesWithEnemyFields = matchesWithEnemyFields(fieldName);
        if(matchesWithEnemyShips && matchesWithEnemyFields) {
            return "checked";
        } else if(matchesWithEnemyFields) {
            return "checked";
        }else {
            return "";
        }
    }
    
    public boolean matchesWithEnemyFields(String fieldName) {
        fieldName = "myHit_" + fieldName;
        for (Field field : fields) {
            if (field.getName().equals(fieldName) && field.getValue() == true) {
                return true;
            }
        }
        return false;
    }
    
    public boolean matchesWithEnemyShips(String fieldName) {
        fieldName = "enemy_" + fieldName;
        for(Field field : shipsFields) {
            if(field.getName().equals(fieldName) && field.getValue() == true) {
                return true;
            }
        }
        return false;
    }

}
