import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws ScanerException {

        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexAction = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }

        if (actionIndex == -1) {
            throw new ScanerException("throws Exception //т.к. строка не является математической операцие");
        }
        String[] data = exp.split(regexAction[actionIndex]);
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
            }
            int result;
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
                System.out.println(converter.intToRoman(result));
            } else {
                System.out.println(result);
            }
        } else {
            throw new ScanerException("throws Exception //т.к. используются одновременно разные системы счисления");
        }
    }
}
