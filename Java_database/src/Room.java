public class Room {

    private int _room_number = 0;
    private int _hotel_number = 0;
    private float _room_price = 0;
    private String _room_type = "";



    public Room(int _room_number, int _hotel_number, String _room_type, float _room_price) {
        this._room_number = _room_number;
        this._hotel_number = _hotel_number;
        this._room_price = _room_price;
        this._room_type = _room_type;
    }

    public Room(String _room_type, float _room_price) {
        this._room_price = _room_price;
        this._room_type = _room_type;
    }

    public Room(int _room_number) {
        this._room_number = _room_number;
    }

    public int get_room_number() {
        return _room_number;
    }

    public int get_hotel_number() {
        return _hotel_number;
    }

    public float get_room_price() {
        return _room_price;
    }

    public String get_room_type() {
        return _room_type;
    }

    public void displayRoom(){
        System.out.format(  "Room Number: %3s, Hotel Number: %-5s, Room Price: %-5s, Room Type: %-10s\n",
                this.get_room_number(),
                this.get_hotel_number(),
                this.get_room_price(),
                this.get_room_type());
    }



}