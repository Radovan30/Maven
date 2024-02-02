package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class IsPrimeTest {
    int number = 13;
    @Test
    public void isPrime() {
        assertTrue(IsPrime.isPrime(number));
    }
}