import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String []arg) {
        Scanner str = new Scanner(System.in);
        Integer value = str.nextInt();
        part_1(value);
        //part_2(value);
    }


    public static void part_1(Integer year) {
        if ((year >= 1800) && (year <= 2016)) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.DAY_OF_MONTH, 13);
            for (int i = 0; i <= 11; i++) {
                calendar.set(Calendar.MONTH, i);
                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                    String result = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US);
                    System.out.print(result + "\n");
                }
            }
        }
        else {
            System.out.print("Год должен принадлежать интервалу от 1800 до 2016\n");
        }
    }


    public static void part_2(Integer month) {
        if ((month >= 0) && (month <= 11)) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, 13);
            int countYear = 0;
            for (int i = 1800; i <= 2016; i++) {
                calendar.set(Calendar.YEAR, i);
                if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
                    Integer result = calendar.get(Calendar.YEAR);
                    System.out.print(result + "\n");
                    countYear++;
                }
            }
            System.out.print("Найдено годов: " + countYear + "\n");
        }
        else {
            System.out.print("Месяц должен принадлежать интервалу от 0 до 11\n");
        }
    }
}
