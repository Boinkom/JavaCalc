package org.example;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String number = new Scanner(System.in).nextLine();
        System.out.println(calc(number));
    }

    public static String calc(String input) {

        String[] line = input.split(" ");
        String otvet = "";

        int flag = 0;

        if (line.length !=3 ) {
            System.out.println("Не корректный ввод");
        }
        else if (isArabic(input)) {
            flag = 1;
        }
        else if (isRoman(input)) {
            flag = 2;
        }
        else{
            System.out.println("Не корректный ввод");
        }

        switch (flag){
            case 1: if (normNumber(line)){
                int x = Integer.parseInt(line[0]);
                int y = Integer.parseInt(line[2]);
                switch (line[1]){
                    case "+": otvet = Integer.toString(x + y);
                    break;
                    case "-": otvet = Integer.toString(x - y);
                    break;
                    case "*": otvet = Integer.toString(x * y) ;
                    break;
                    case "/": otvet = Integer.toString(x / y);
                    break;
                }
            }
                break;
            case 2: int x = RomanToInteger(line[0]), y = RomanToInteger(line[2]);
                    switch (line[1]){
                        case "+": otvet = Integer.toString(x + y);
                            break;
                        case "-": otvet = Integer.toString(x - y);
                            break;
                        case "*": otvet = Integer.toString(x * y) ;
                            break;
                        case "/": otvet = Integer.toString(x / y);
                            break;
                    }
                    otvet = IntegerToRoman(Integer.parseInt(otvet));
        }

        return otvet;
    }


    static boolean isArabic(String input) {

        String arabicPattern = "^[- 0-9+*/]+$";
        return Pattern.matches(arabicPattern, input);
    }

    static boolean isRoman(String input) {

        String romanPattern = "^[- IVXL+*/]+$";
        return Pattern.matches(romanPattern, input);
    }
    static boolean normNumber(String[] input){
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[2]);
        boolean flag = false;
        if ((x >= 0 || x <= 10) && (y>=0 || y<=10)) {
            flag = true;
        }
        return flag;
    }

    static int RomanToInteger(String roman) {
        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char symbol = roman.charAt(i);
            int value = getRomanValue(symbol);

            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }
        return result;
    }

     static int getRomanValue(char symbol) {
        switch (symbol) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            default:
                System.out.println("Неправильный символ римской цифры: " + symbol);
        }
        return 0;
    }

    private static String IntegerToRoman(int num) {
        if (num <= 0) {
            System.out.println("Число должно быть положительным");
        }

        String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C" };
        int[] romanValues = {1, 4, 5, 9, 10, 40, 50, 90, 100};

        StringBuilder roman = new StringBuilder();

        for (int i = romanSymbols.length - 1; i >= 0; i--) {
            while (num >= romanValues[i]) {
                roman.append(romanSymbols[i]);
                num -= romanValues[i];
            }
        }

        return roman.toString();
    }

}