package com;

import java.io.Serializable;

public class Room implements Serializable{

    private final int _room_number;
    private final String _room_type;
    private final int _room_capacity;
    private final int _room_phone;


    public Room(int _room_number, String _room_type, int _room_capacity, int _room_phone) {
        this._room_number = _room_number;
        this._room_type = _room_type;
        this._room_capacity = _room_capacity;
        this._room_phone = _room_phone;

    }

    public int get_room_number() {
        return _room_number;
    }

    public String get_room_type() {
        return _room_type;
    }

    public int get_room_capacity() {
        return _room_capacity;
    }

    public int get_room_phone() {
        return _room_phone;
    }

}