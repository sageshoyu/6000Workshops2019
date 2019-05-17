public class Car {
    int gas;

    public Car() {
        gas = 10;
    }
    
    
    void runCar() {
        if (gas > 0) {
            System.out.println("VROOOOOOOOOM");
            gas = gas - 1;
        } else {
            System.out.println("NO GAS LEFT");
        }

    }

    void setGas(int gasAmount) {
        gas = gasAmount;
    }




}