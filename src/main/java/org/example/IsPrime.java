package org.example;

public class IsPrime {

    public IsPrime() {}

    // metoda ktera zjistuje jestli je dane cislo prvocislem
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i < Math.sqrt(number); i++) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
