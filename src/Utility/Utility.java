package Utility;

public class Utility {

    public Boolean checkDouble(String value) {
        try {
            Integer.parseInt(value);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}