package com.maistruk.battleship.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.maistruk.battleship.model.Field;
import com.maistruk.battleship.model.Fleet;
import com.maistruk.battleship.model.Ship;

@Service
public class GamaManager {

    public Fleet generateFleet(List<Field> fields) {
        List<Integer> places = new ArrayList<Integer>();
        Ship ship = new Ship();
        List<Field> shipFields = new ArrayList<>();
        List<Ship> ships = new ArrayList<>();
        int i = 0;
        while (i < fields.size()) {
            boolean check = true;
            for (Integer place : places) {
                if (place.equals(i)) {
                    check = false;
                }
            }
            boolean plusSeven = false;
            if (check) {
                Field field = fields.get(i);
                if (field.getValue() == true) {
                    shipFields.add(new Field(field.getName(), field.getValue()));
                    places.add(i);
                    if (i <= 47 && fields.get(i + 1).getValue() == true) {
                    } else if (i <= 40 && fields.get(i + 7).getValue() == true) {
                        plusSeven = true;
                    } else {
                        ship.setShipFields(shipFields);
                        ships.add(ship);
                        ship = new Ship();
                        shipFields = new ArrayList<>();
                        i = -1;
                    }
                }
            }
            if (plusSeven) {
                i += 7;
            } else {
                i++;
            }
        }
        Fleet fleet = new Fleet();
        fleet.setShips(ships);
        return fleet;
    }

    public List<Field> generateShips() {
        List<Field> fields = getBattleground();
        Random random = new Random();
        generateFirstShip(fields, random);
        generateHorizontalShip(fields, random);
        generateHorizontalShip(fields, random);

        return fields;
    }

    public void generateFirstShip(List<Field> fields, Random random) {
        int fieldLetter = random.nextInt(7) + 1;
        int fieldNumber = random.nextInt(5) + 1;
        String fieldName = "enemy_" + getLatter(fieldLetter) + fieldNumber;
        int fieldIndex = getIndexOfElement(fields, fieldName);
        for (int i = fieldIndex; i <= fieldIndex + 2; i++) {
            fields.get(i).setValue(true);
        }
    }

    public void generateHorizontalShip(List<Field> fields, Random random) {
        boolean check = true;
        while (check) {
            check = false;
            int fieldLetter = random.nextInt(7) + 1;
            int fieldNumber = random.nextInt(5) + 1;
            String fieldName = "enemy_" + getLatter(fieldLetter) + fieldNumber;
            int fieldIndex = getIndexOfElement(fields, fieldName);
            check = checkHorizontalShip(fields, fieldName, fieldIndex);
            if (check == false) {
                for (int i = fieldIndex; i <= fieldIndex + 2; i++) {
                    fields.get(i).setValue(true);
                }
            }
        }
    }

    private boolean checkHorizontalShip(List<Field> fields, String fieldName, int fieldIndex) {
        if (fieldName.equals("enemy_a1")) {
            return checkA1HorizontalShip(fields);
        } else if (fieldName.equals("enemy_a5")) {
            return checkA5HorizontalShip(fields);
        } else if (fieldName.equals("enemy_g1")) {
            return checkG1HorizontalShip(fields);
        } else if (fieldName.equals("enemy_g5")) {
            return checkG5HorizontalShip(fields);
        } else if (fieldName.substring(7).equals("1")) {
            return check1HorizontalShip(fields, fieldIndex);
        } else if (fieldName.substring(7).equals("5")) {
            return check5HorizontalShip(fields, fieldIndex);
        } else if (fieldName.substring(6, 7).equals("a")) {
            return checkAHorizontalShip(fields, fieldIndex);
        } else if (fieldName.substring(6, 7).equals("g")) {
            return checkGHorizontalShip(fields, fieldIndex);
        } else {
            return checkRestHorizontalShip(fields, fieldIndex);
        }
    }

    private boolean checkA1HorizontalShip(List<Field> fields) {
        for (int i = 0; i <= 3; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = 7; i <= 10; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkA5HorizontalShip(List<Field> fields) {
        for (int i = 3; i <= 6; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = 10; i <= 13; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkG1HorizontalShip(List<Field> fields) {
        for (int i = 35; i <= 38; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = 42; i <= 45; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkG5HorizontalShip(List<Field> fields) {
        for (int i = 38; i <= 41; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = 45; i <= 48; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean check1HorizontalShip(List<Field> fields, int fieldIndex) {
        for (int i = fieldIndex; i <= fieldIndex + 3; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex - 7; i <= fieldIndex - 4; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex + 7; i <= fieldIndex + 10; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean check5HorizontalShip(List<Field> fields, int fieldIndex) {
        for (int i = fieldIndex - 1; i <= fieldIndex + 2; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex - 8; i <= fieldIndex - 5; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex + 6; i <= fieldIndex + 9; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAHorizontalShip(List<Field> fields, int fieldIndex) {
        for (int i = fieldIndex - 1; i <= fieldIndex + 3; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex + 6; i <= fieldIndex + 10; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkGHorizontalShip(List<Field> fields, int fieldIndex) {
        for (int i = fieldIndex - 1; i <= fieldIndex + 3; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex - 8; i <= fieldIndex - 4; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRestHorizontalShip(List<Field> fields, int fieldIndex) {
        for (int i = fieldIndex - 1; i <= fieldIndex + 3; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex - 8; i <= fieldIndex - 4; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        for (int i = fieldIndex + 6; i <= fieldIndex + 10; i++) {
            if (fields.get(i).getValue() == true) {
                return true;
            }
        }
        return false;
    }

    private List<Field> getBattleground() {
        List<Field> fields = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                String parameter = "enemy_" + getLatter(i) + j;
                Field field = new Field(parameter, false);
                fields.add(field);
            }
        }
        return fields;
    }

    public int getIndexOfElement(List<Field> fields, String fieldName) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getName().equals(fieldName)) {
                return i;
            }
        }
        return 0;
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
