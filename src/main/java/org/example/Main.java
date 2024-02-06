package org.example;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        System.out.println("Zadejte cestu k souboru: ");
        Scanner scanner = new Scanner(System.in);
        String pathEntry = scanner.nextLine();

        File path = new File(pathEntry);

        // Kontrola zda existuje soubot - jestli ANO tak se pokracuje v praci / jestli NE tak se program ukonci
        if(path.exists())
        {
            String fileName = path.getName();

            logger.info("Cesta k souboru je spravna, pokracuji ve cteni souboru - " + fileName);
            System.out.println("Cesta k souboru je spravna, pokracuji ve cteni souboru " + fileName);

            try {
                // Prace z excelem
                FileInputStream file = new FileInputStream(path);
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                DataFormatter dataFormatter = new DataFormatter();

                // Ziska prvni list v souboru (nazev listu "List1")
                XSSFSheet sheet = workbook.getSheet("List1");

                // Projde vsechny radky v listu
                for (Row row : sheet) {
                    // Ziska hodnoty z konkretniho sloupce (napr. sloupec B/1)
                    Cell cell = row.getCell(1);
                    String cellValue = dataFormatter.formatCellValue(cell);

                    try {
                        int integer = Integer.parseInt(cellValue);
                        if (IsPrime.isPrime(integer)) {
                            String integerString = String.valueOf(integer);
                            logger.info("Prvocislo - " + integerString);
                            System.out.println(integer);
                        }
                    }
                    catch (NumberFormatException e) {
                        // Ignorujeme řádky neobsahující celé číslo
                    }
                }

                logger.info("Ukonceno cteni souboru, zaviram soubor!");
                System.out.println("Ukonceno cteni souboru, zaviram soubor!");
                file.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
                logger.info("Chyba pri cteni souboru " + e.getMessage());
            }

        } else {
            logger.info("Soubor neexistuje, nebo je spatne zadana cesta k souboru!");
            System.out.println("Soubor neexistuje, nebo je spatne zadana cesta k souboru!");
        }
    }
}