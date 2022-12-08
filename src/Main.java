import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ScanerException {
        int i = 0;
        while (i<1){
            Scanner scn = new Scanner(System.in);
            System.out.print("Введите выражение: ");
            String exp = scn.nextLine();
            System.out.println(calc(exp));
        }
    }
    public static String calc(String input) throws ScanerException {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexAction = {"\\+", "-", "/", "\\*"};
        int actionIndex = -1;
        int result;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        if(actionIndex == -1){
            throw new ScanerException("throws Exception //т.к. строка не является математической операцией");
        }
        String[] data = input.split(regexAction[actionIndex]);
        if (data.length > 2) {
            throw new ScanerException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
                if (a>10 || b>10 || a<1 || b<1){
                    throw new ScanerException("throws Exception //т.к. калькулятор принимает числа от 1 до 10 включительно");
                }
            }
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            if (isRoman) {
                return converter.intToRoman(result);
            } else {
                return Integer.toString(result);
            }
        } else {
            throw new ScanerException("throws Exception //т.к. используются одновременно разные системы счисления");
        }
    }
}