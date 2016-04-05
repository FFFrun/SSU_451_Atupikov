import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        while(true) {
            System.out.print("Введите выражение: ");
            try {
                BufferedReader str = new BufferedReader(new InputStreamReader(System.in)); // Класс для считывания
                String express;
                express = str.readLine(); // Берем строку
                if (express.equals("Close")) {
                    break;
                }
                else {
                    express = OPN(express); // Переводим выражение в польскую нотацию
                    System.out.println(calculate(express)); // Считаем то что получилось в польской нотации и выводим результат
                }
            } catch (Exception e) { // Если происходит ошибка, которая соответствует классу Exception
                System.out.println(e.getMessage()); // Выводим текст ошибки
            }
        }
    }



    // Метод возвращает приоритет операции *,/,^,+,- от 1 до 3
    private static byte priorityOperations(char operation) {
        if (operation == '^') {
            return 3;
        }
        else if ((operation == '*') || (operation == '/')) {
            return 2;
        }
        else { // Тут если + или -
            return 1;
        }
    }



    // Метод возвращает true, если value - символ
    private static boolean ifOperation(char value) {
        if ((value == '-') || (value == '+') || (value == '*') || (value == '/') || (value == '^')) {
            return true;
        }
        else {
            return false;
        }
    }



    // Алгоритм преобразующий входную строку express в обратную польскую нотацию
    private static String OPN(String express) throws Exception {
        // Идентичен StringBuffer - типо массива
        StringBuilder helpStack = new StringBuilder("");
        StringBuilder outExpress = new StringBuilder("");
        char symbolExpress, symbolStack;

        for (int i = 0; i < express.length(); i++) {
            symbolExpress = express.charAt(i); // Берем символ из строки express
            if (ifOperation(symbolExpress)) { // Если этот символ ^,*,+,-,/
                while (helpStack.length() > 0) {
                    symbolStack = helpStack.substring(helpStack.length()-1).charAt(0); // Берем из стека 1 последний символ
                    // Смотрим приоритеты
                    if (ifOperation(symbolStack) && (priorityOperations(symbolExpress) <= priorityOperations(symbolStack))) {
                        outExpress.append(" ").append(symbolStack).append(" "); // Формируем выходную строку
                        helpStack.setLength(helpStack.length()-1); // Устанавливаем длину строки (т.к. 1 символ взяли)
                    }
                    else {
                        outExpress.append(" ");
                        break;
                    }
                }
                outExpress.append(" ");
                helpStack.append(symbolExpress);
            }
            else if ('(' == symbolExpress) { // Иначе если {
                helpStack.append(symbolExpress);
            }
            else if (')' == symbolExpress) { // Иначе если }
                symbolStack = helpStack.substring(helpStack.length()-1).charAt(0);
                while ('(' != symbolStack) {
                    if (helpStack.length() < 1) {
                        throw new Exception("Ошибка разбора скобок. Проверьте правильность выражения.");
                    }
                    outExpress.append(" ").append(symbolStack);
                    helpStack.setLength(helpStack.length()-1);
                    symbolStack = helpStack.substring(helpStack.length()-1).charAt(0);
                }
                helpStack.setLength(helpStack.length()-1);
            }
            else { // Иначе если число
                outExpress.append(symbolExpress);
            }
        }

        // Если в стеке остались операторы, добавляем их в выходную строку
        while (helpStack.length() > 0) {
            outExpress.append(" ").append(helpStack.substring(helpStack.length()-1));
            helpStack.setLength(helpStack.length()-1);
        }
        return  outExpress.toString();
    }



    // Метод считает значение выражения, записанного в польской нотации
    private static double calculate(String express) throws Exception {
        double first = 0, second = 0;
        String token;
        Deque<Double> stack = new ArrayDeque<Double>(); // Двухсторонний список
        StringTokenizer str = new StringTokenizer(express); // StringTokenizer применяется для разбиения строк
        while(str.hasMoreTokens()) { // hasMoreTokens возвращает true , если в строке еще есть слова, и false , если слов больше нет.
            try {
                token = str.nextToken().trim(); // trim - убирает пробелы
                if (1 == token.length() && ifOperation(token.charAt(0))) {
                    if (stack.size() < 2) {
                        throw new Exception("Неверное количество данных в стеке для операции " + token);
                    }
                    second = stack.pop();
                    first = stack.pop();
                    switch (token.charAt(0)) {
                        case '+':
                            first += second;
                            break;
                        case '-':
                            first -= second;
                            break;
                        case '/':
                            first /= second;
                            break;
                        case '*':
                            first *= second;
                            break;
                        case '^':
                            first = Math.pow(first, second);
                            break;
                        default:
                            throw new Exception("Недопустимая операция " + token);
                    }
                    stack.push(first);
                }
                else {
                    first = Double.parseDouble(token);
                    stack.push(first);
                }
            }
            catch (Exception e) {
                throw new Exception("Недопустимый символ в выражении");
            }
        }

        if (stack.size() > 1) {
            throw new Exception("Количество операторов не соответствует количеству операндов");
        }
        return stack.pop();
    }
}
