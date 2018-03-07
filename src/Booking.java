public class Booking {

    private int _guest_number = 0;
    private int _hotel_number = 0;
    private int _room_number = 0;
    private String _dateFrom = "";
    private String _dateTo = "";

    public Booking(int _guest_number, int _hotel_number, String _dateFrom, String _dateTo, int _room_number) {
        this._guest_number = _guest_number;
        this._hotel_number = _hotel_number;
        this._room_number = _room_number;
        this._dateFrom = _dateFrom;
        this._dateTo = _dateTo;
    }

    public Booking(int _guest_number, String _dateFrom, String _dateTo) {
        this._guest_number = _guest_number;
        this._dateFrom = _dateFrom;
        this._dateTo = _dateTo;
    }

    public Booking(int _guest_number) {
        this._guest_number = _guest_number;
    }

    public int get_guest_number() {
        return _guest_number;
    }

    public int get_hotel_number() {
        return _hotel_number;
    }

    public int get_room_number() {
        return _room_number;
    }

    public String get_dateFrom() {
        return _dateFrom;
    }

    public String get_dateTo() {
        return _dateTo;
    }

    public void displayBooking(){
        System.out.format(  "Guest Number: %3s, Hotel Number: %-3s, Room Number: %-3s, Date From: %-10s, Date To: %-10s\n",
                this.get_guest_number(),
                this.get_hotel_number(),
                this.get_room_number(),
                this.get_dateFrom(),
                this.get_dateTo());
    }

}
