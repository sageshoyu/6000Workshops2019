public class FunctionDemo {

    static void uselessFunction() {
        System.out.println("Don't use me.");
        System.out.println("I can't do much.");

    }
    
    static int doubleMe(int x) {
        int doublex = x + x;
        return doublex;
    }

    static double adding(double y, double z) {
        return y+z;
    }
    
    static int cube(int x) {
        int x_squared = x*x;
        int x_cubed = x_squared*x;
        return x_cubed;

    }
    
    public static void main(String[] args) {
        uselessFunction();
        uselessFunction();

        System.out.println(doubleMe(7));
        System.out.println(doubleMe(14141));
        
        System.out.println(adding(4.5,215.2));

        int input = 7;
        int input_squared = input * input;
        int input_cubed = input_squared * input;
        System.out.println(input_cubed);

         input = 123;
         input_squared = input * input;
         input_cubed = input_squared * input;
        System.out.println(input_cubed);
        

        int seven_cubed = cube(7);
        System.out.println(7*7*7);


        


    }



}