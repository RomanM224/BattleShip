package com.maistruk.battleship.model;

public class TableCreater {
    
    private FieldChecker fieldChecker;
    
    public FieldChecker getFieldChecker() {
        return fieldChecker;
    }

    public void setFieldChecker(FieldChecker fieldChecker) {
        this.fieldChecker = fieldChecker;
    }

    public String getMyTablePart(int i, int j) {
        if(i == 0 && j == 0) {
            return "";
        } else if(i == 0 && j == 1) {
            return "1";
        } else if(i == 0 && j == 2) {
            return "2";
        } else if(i == 0 && j == 3) {
            return "3";
        } else if(i == 0 && j == 4) {
            return "4";
        } else if(i == 0 && j == 5) {
            return "5";
        } else if(i == 0 && j == 6) {
            return "6";
        } else if(i == 0 && j == 7) {
            return "7";
        } else if(i == 1 && j == 0) {
            return "A";
        } else if(i == 2 && j == 0) {
            return "B";
        } else if(i == 3 && j == 0) {
            return "C";
        } else if(i == 4 && j == 0) {
            return "D";
        } else if(i == 5 && j == 0) {
            return "E";
        } else if(i == 6 && j == 0) {
            return "F";
        } else if(i == 7 && j == 0) {
            return "G";
        } else {
            String fieldName = getLatter(i) + j;
            String name = "enemyHit_" + fieldName;
            return "<input type=\"checkbox\" name=\"" + name + "\""  + fieldChecker.ifExistFieldMyFleet(fieldName) +  ">" ;
        }
    }
    
    public String getEnemyTablePart(int i, int j) {
        if(i == 0 && j == 0) {
            return "";
        } else if(i == 0 && j == 1) {
            return "1";
        } else if(i == 0 && j == 2) {
            return "2";
        } else if(i == 0 && j == 3) {
            return "3";
        } else if(i == 0 && j == 4) {
            return "4";
        } else if(i == 0 && j == 5) {
            return "5";
        } else if(i == 0 && j == 6) {
            return "6";
        } else if(i == 0 && j == 7) {
            return "7";
        } else if(i == 1 && j == 0) {
            return "A";
        } else if(i == 2 && j == 0) {
            return "B";
        } else if(i == 3 && j == 0) {
            return "C";
        } else if(i == 4 && j == 0) {
            return "D";
        } else if(i == 5 && j == 0) {
            return "E";
        } else if(i == 6 && j == 0) {
            return "F";
        } else if(i == 7 && j == 0) {
            return "G";
        } else {
            String fieldName = getLatter(i) + j;
            String name = "myHit_" + fieldName;
            return "<input type=\"checkbox\" name=\"" + name + "\""  + fieldChecker.ifExistFieldEnemyFleet(fieldName) +  ">" ;
        }
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
