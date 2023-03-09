import java.util.Scanner;

public class Main {
    static String[] arrayRome = new String[]{"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };
    static int num0, num1, resultArab;
    static boolean examRome;
    static char operation;
    static String result;


    public static void main(String[] args) throws Exception{
        Scanner sr = new Scanner(System.in);
        System.out.println("Введите математическую операцию (два операнда и один оператор (+, -, /, *)):");
        String input = sr.nextLine();
        input = input.replaceAll("\\s", "");
        System.out.println("Результат: " + calc(input));
    }

    public static String calc(String input) throws Exception {
        String[] numbers = input.split("[+*/\\-]");
        if (numbers.length > 2)
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if (numbers.length < 2)
            throw new Exception("Строка не является математической операцией - два операнда и один оператор (+, -, /, *)");

        if (examRome(numbers[0]) && examRome(numbers[1])) {
            num0 = romeToArab(numbers[0]);
            num1 = romeToArab(numbers[1]);
            examRome = true;
        } else if (!examRome(numbers[0]) && !examRome(numbers[1])) {
            examRome = false;
            try {
                num0 = Integer.valueOf(numbers[0]);
                num1 = Integer.valueOf(numbers[1]);
            } catch (NumberFormatException e) {
                throw new Exception("Принимаются только целые цисла");
            }
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        operation = oper(input);
        if (num0 > 10 || num1 > 10 || num0 <= 0 || num1 <= 0) {
            throw new Exception("Принимаются числа от 1 до 10 включительно");
        }
        switch (operation) {
            case '+' -> resultArab = num0 + num1;
            case '-' -> resultArab = num0 - num1;
            case '*' -> resultArab = num0 * num1;
            case '/' -> resultArab = num0 / num1;
        }
        if (examRome && resultArab <= 0)
            throw new Exception("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
        if (!examRome)
            result = String.valueOf(resultArab);
        if (examRome)
            result = arabToRome(resultArab);
        return result;
    }

    static int romeToArab(String num) throws Exception {
        if (num.equalsIgnoreCase("I"))
            return 1;
        if (num.equalsIgnoreCase("II"))
            return 2;
        if (num.equalsIgnoreCase("III"))
            return 3;
        if (num.equalsIgnoreCase("IV"))
            return 4;
        if (num.equalsIgnoreCase("V"))
            return 5;
        if (num.equalsIgnoreCase("VI"))
            return 6;
        if (num.equalsIgnoreCase("VII"))
            return 7;
        if (num.equalsIgnoreCase("VIII"))
            return 8;
        if (num.equalsIgnoreCase("IX"))
            return 9;
        if (num.equalsIgnoreCase("X"))
            return 10;
        throw new Exception("Принимаются числа от 1 до 10 включительно");
    }

    static boolean examRome(String num) {
        for (String s:arrayRome) {
            if (num.equalsIgnoreCase(s))
                return true;
        }
        return false;
    }

    static char oper(String input) {
        if (input.contains("+"))
            return '+';
        if (input.contains("-"))
            return '-';
        if (input.contains("*"))
            return '*';
        if (input.contains("/"))
            return '/';
        return 0;
    }

    static String arabToRome(int arab) {
        return arrayRome[arab];
    }
}