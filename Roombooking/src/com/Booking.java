package com;

import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Booking implements Serializable{

    private final int _id;
    private final String _date;
    private final String _time;
    private final String _duration;
    private final int _roomNumber;
    private final User _bookedBy;
    private final String _bookedByName;
    private final int _estimatedAttendies;
    private final String _eventName;

    public Booking(int _id, String _date, String _time, String _duration, int _roomNumber, User _bookedBy, String _bookedByName, int _estimatedAttendies, String _eventName) {
        this._id = _id;
        this._date = _date;
        this._time = _time;
        this._duration = _duration;
        this._roomNumber = _roomNumber;
        this._bookedBy = _bookedBy;
        this._bookedByName = _bookedByName;
        this._estimatedAttendies = _estimatedAttendies;
        this._eventName = _eventName;
    }

    public int get_id() {
        return _id;
    }

    public String get_date() {
        return _date;
    }

    public String get_time() {
        return _time;
    }

    public String get_duration() {
        return _duration;
    }

    public String get_roomNumber() {
        return String.valueOf(_roomNumber);
    }

    public int get_roomNumberInt() {
        return _roomNumber;
    }

    public User get_bookedBy() {
        return _bookedBy;
    }

    public String get_estimatedAttendies() {
        return String.valueOf(_estimatedAttendies);
    }

    public String get_eventName() {
        return _eventName;
    }

    public String get_bookedByName() {
        return _bookedByName;
    }


}