public class Guest {

    private int _number = 0;
    private String _name = "";
    private String _add = "";

    public Guest(int _number) {
        this._number = _number;
    }
    public Guest(String _name, String _add) {
        this._add = _add;
        this._name = _name;
    }

    public Guest(int _number, String _name, String _add) {
        this._number = _number;
        this._name = _name;
        this._add = _add;
    }

    public int get_number() {
        return _number;
    }

    public String get_name() {
        return _name;
    }

    public String get_add() {
        return _add;
    }

    public void displayGuest(){
        System.out.format(  "Guest Number: %3s, Guest Name: %-5s, Guest Add: %-10s\n", this.get_number(),this.get_name(),this.get_add());
    }

    public void displayGuestName(){
        System.out.format(  "Guest Number: %3s, Guest Name: %-5s\n", this.get_number(), this.get_name());
    }

    public void displayGuestAdd(){
        System.out.format(  "Hotel Number: %3s, City: %-10s\n", this.get_number(), this.get_add());
    }

}
