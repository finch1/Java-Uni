public class Hotel {

    private int _number = 0;
    private String _name = "";
    private String _city = "";

    public Hotel(int _number) {
        this._number = _number;
    }
    public Hotel(String _name, String _city) {
        this._city = _city;
        this._name = _name;
    }

    public Hotel(int _number, String _name, String _city) {
        this._number = _number;
        this._name = _name;
        this._city = _city;
    }

    public int get_number() {
        return _number;
    }

    public String get_name() {
        return _name;
    }

    public String get_city() {
        return _city;
    }

    public void displayHotel(){
        System.out.format(  "Hotel Number: %3s, Hotel Name: %-5s, City: %-10s\n", this.get_number(),this.get_name(),this.get_city());
    }

    public void displayHotelName(){
        System.out.format(  "Hotel Number: %3s, Hotel Name: %-5s\n", this.get_number(), this.get_name());
    }

    public void displayHotelCity(){
        System.out.format(  "Hotel Number: %3s, City: %-10s\n", this.get_number(), this.get_city());
    }

}
