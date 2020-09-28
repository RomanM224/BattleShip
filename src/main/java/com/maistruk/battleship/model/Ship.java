package com.maistruk.battleship.model;

import java.util.List;

public class Ship {
    
    private List<Field> shipFields;

    public List<Field> getShipFields() {
        return shipFields;
    }

    public void setShipFields(List<Field> shipFields) {
        this.shipFields = shipFields;
    }

    @Override
    public String toString() {
        return "Ship [shipFields=" + shipFields + "]";
    }

    
    

}
