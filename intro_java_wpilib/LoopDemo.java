public class LoopDemo {

    public static void main(String[] args) {
        
        String exclamation = "DOG";

        int i = 0;
        
        while (i < 10) {
            System.out.println(exclamation);
            i = i + 1;
        }

        System.out.println("");

        for (int k = 0; k < 10; k = k + 1) {
            System.out.println(exclamation);
        }


    }



}