import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String []arg) {
        Scanner str = new Scanner(System.in); // Создаём объект класса Scanner
        Integer chislo = str.nextInt(); // Считываем число
        //part_1(chislo); // 1 Часть
        part_2(chislo); // 2 Часть
    }


    public static void part_1(Integer god) { // 1 Часть
        if ((god >= 1800) && (god <= 2016)) {
            Calendar calendar = Calendar.getInstance(); // Объект класса Calendar
            calendar.set(Calendar.YEAR, god); // Год ставим
            calendar.set(Calendar.DAY_OF_MONTH, 13); // Ставим 13 число
            for (int i = 0; i <= 11; i++) {
                calendar.set(Calendar.MONTH, i); // Месяц
                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) { // Если это пятница
                    String rez = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US);
                    System.out.print(rez + "\n"); // Выводим месяц
                }
            }
        }
        else {
            System.out.print("Год должен принадлежать интервалу от 1800 до 2016\n");
        }
    }


    public static void part_2(Integer mes) { // 2 Часть
        if ((mes >= 0) && (mes <= 11)) {
            Calendar calendar = Calendar.getInstance(); // Объект класса Calendar
            calendar.set(Calendar.MONTH, mes); // Месяц ставим
            calendar.set(Calendar.DAY_OF_MONTH, 13); // Ставим 13 число
            int schet_god = 0; // Кол-во годов
            for (int i = 1800; i <= 2016; i++) {
                calendar.set(Calendar.YEAR, i); // Год
                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) { // Если это пятница
                    Integer rez = calendar.get(Calendar.YEAR);
                    System.out.print(rez + "\n"); // Выводим год
                    schet_god++;
                }
            }
            System.out.print("Найдено годов: " + schet_god + "\n"); // Выводим кол-во этих годов
        }
        else {
            System.out.print("Месяц должен принадлежать интервалу от 0 до 11\n");
        }
    }
}
