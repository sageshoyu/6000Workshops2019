public class BoolDemo {

   public static void main(String[] args) {
        int UVIndex = 536536;
        int inchesOfSnow = 0;

        boolean isTheSunShining = UVIndex > 40 || inchesOfSnow <= 1;
        

        if (isTheSunShining) {
            System.out.println("THE SUN IS SHINING WAAAAAA");
        } else {
            System.out.println("Seasonal depression is coming soon.");
            System.out.println("Winter is coming.");
        }
    }


}