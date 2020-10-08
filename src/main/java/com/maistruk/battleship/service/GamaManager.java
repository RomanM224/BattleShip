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
        List<Integer> fieldIndex = new ArrayList<>();

        boolean check = false;
        for (int i = 0; i < fields.size(); i++) {
            if (!fieldIndex.contains(i) ) {
                if ((fields.get(i).getValue() == true) && (i < 99) && (fields.get(i + 1).getValue() == true) && (i % 10 != 9)) {
                    while ((i < 99) && (fields.get(i + 1).getValue())) {
                        shipFields.add(fields.get(i));
                        fieldIndex.add(i);
                        i++;
                    }
                    shipFields.add(fields.get(i));
                    ship.setShipFields(shipFields);
                    ship.setShipSize(shipFields.size());
                    ships.add(ship);
                    shipFields = new ArrayList<>();
                    ship = new Ship();
                    fieldIndex.add(i);
                    i = 0;
                } else if ((fields.get(i).getValue() == true) && (i < 90) && (fields.get(i + 10).getValue() == true)) {
                    while ((i < 90) && (fields.get(i + 10).getValue())) {
                        shipFields.add(fields.get(i));
                        fieldIndex.add(i);
                        i+=10;
                    }
                    shipFields.add(fields.get(i));
                    ship.setShipFields(shipFields);
                    ship.setShipSize(shipFields.size());
                    ships.add(ship);
                    shipFields = new ArrayList<>();
                    ship = new Ship();
                    fieldIndex.add(i);
                    i = 0;
                } else if (fields.get(i).getValue() == true) {
                    shipFields.add(fields.get(i));
                    ship.setShipFields(shipFields);
                    ship.setShipSize(shipFields.size());
                    ships.add(ship);
                    shipFields = new ArrayList<>();
                    ship = new Ship();
                    fieldIndex.add(i);
                    i = 0;
                }
            }
        }
        Fleet fleet = new Fleet();
        fleet.setShips(ships);
        return fleet;
    }
}
