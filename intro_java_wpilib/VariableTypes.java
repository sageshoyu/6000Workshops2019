public class VariableTypes {

    public static void main(String[] args) {
        int integer;
        double fraction = 1.0;
        boolean hypothetical;
        String listOChars;
        char singleChar = 's';

        integer = 0;
        hypothetical = true;

        listOChars = "Hello World";
        
        System.out.println(listOChars + " " + fraction);
        System.out.println("A bool value: " + hypothetical);

        System.out.println(listOChars);


        double oneAndAHalf = 1.5;
        double result = 3.0/2.0 + 1 - 1;

        boolean isSame = oneAndAHalf == result;
        System.out.println(isSame);
        
    }


}