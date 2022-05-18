import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите выражение в формате a + b, где a и b числа от 1 до 10 или от I до X:");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println("Ответ: " + calc(input));
    }
    public static String calc(String input) {
        String[] arr = input.split(" ");
        if (arr.length < 3) {
            throw new Error("строка не является математической операцией");
        } else if (arr.length > 3) {
            throw new Error("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String strNum1 = arr[0];
        String strNum2 = arr[2];
        String operator = arr[1];
        String result = "";
        if (strNum1.matches("\\d+") && strNum2.matches("\\d+")) {
            int num1 = Integer.parseInt(strNum1);
            int num2 = Integer.parseInt(strNum2);
            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                throw new Error("не допустимое число, принимаются только числа в интевале [1-10]");
            }
            result = exp(num1, num2, operator);
        } else if (strNum1.matches("[IVX]+") && strNum2.matches("[IVX]+")) {
            RomanNum arabnum1 = RomanNum.valueOf(strNum1);
            RomanNum arabnum2 = RomanNum.valueOf(strNum2);
            int num1 = arabnum1.getarabNum();
            int num2 = arabnum2.getarabNum();
            result = exp(num1, num2, operator);
            int res = Integer.parseInt(result);
            if (res < 1){
                throw new Error("в римской системе нет отрицательных чисел");
            }
            result = convert(res);
        } else throw new Error("используются одновременно разные системы счисления");
        return result;
    }

    //  Решаем выражение
    public static String exp(int num1, int num2, String operator) {
        int res = 0;
        switch (operator) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num1 / num2;
                break;
        }
        String expResult = Integer.toString(res);
        return expResult;
    }

    //  Переводим римские в арабские цифры
    enum RomanNum {
        I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);
        private final int arabNum;
        RomanNum(int arabNum) {
            this.arabNum = arabNum;
        }
        public int getarabNum() {
            return arabNum;
        }
    }

    //  Переводим арабские цифры в римские
    public static String romanDigit(int n, String one, String five, String ten){
        if(n >= 1) {
            if(n == 1) {
                return one;
            } else if (n == 2) {
                return one + one;
            } else if (n == 3) {
                return one + one + one;
            } else if (n==4) {
                return one + five;
            } else if (n == 5) {
                return five;
            } else if (n == 6) {
                return five + one;
            } else if (n == 7) {
                return five + one + one;
            } else if (n == 8) {
                return five + one + one + one;
            } else if (n == 9) {
                return one + ten;
            }
        }
        return "";
    }
    public static String convert(int number){
        String romanOnes = romanDigit( number%10, "I", "V", "X");
        number /=10;
        String romanTens = romanDigit( number%10, "X", "L", "C");
        number /=10;
        String romanHundreds = romanDigit(number%10, "C", "D", "M");
        number /=10;
        String romanThousands = romanDigit(number%10, "M", "", "");
        String result = romanThousands + romanHundreds + romanTens + romanOnes;
        return result;
    }
}