import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Здесь хранятся ВСЕ ячейки строк str из таблицы
    static List<List<String>> mas = new ArrayList(); // Двухмерный массив
    // Кол-во строк в mas
    static int countLine = 0;

    public static void main(String[] args) {
        // Считываем и записываем все ячейки из таблицы в двухмерный массив mas
        readFile("D:\\bank\\input.csv");
        // Выполняем транзакции и сохраняем результат в mas
        transactionBank("D:\\bank", "input.csv", "result.csv");
        // Сохраняем mas в файл result
        saveFile("D:\\bank\\result.csv");
    }

    // Метод считывает и записывает все ячейки из таблицы в двухмерный массив mas
    public static void readFile(String fileInputName) {
        try {
            File file = new File(fileInputName);
            // Входной поток, транслирующий байты в символы, где Windows-1251 — кодировка (чтобы распознавались русские буквы)
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "Windows-1251");
            // Буферизированный входной символьный поток
            BufferedReader br = new BufferedReader(read);
            // Здесь хранится строка из таблицы
            String str = "";
            // Здесь хранятся ячейки строки str из таблицы
            String[] token;
            // Берем строку из таблицы
            boolean help = true;
            while ((str = br.readLine()) != null) {
                // Пропускаем 1 строку в таблице (она нам не нужна)
                if (help) {
                    help = false;
                }
                else {
                    // Разделяем строку из таблицы на ячейки
                    token = str.split(";");
                    mas.add(new ArrayList<String>());
                    mas.get(countLine).add(token[0]);
                    mas.get(countLine).add(token[1]);
                    mas.get(countLine).add(token[2]);
                    countLine++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Метод выполняет транзакции и сохраняет результат в mas
    public static void transactionBank(String pathway, String fileInputName, String fileResultName) {
        try {
            File patwayFile = new File(pathway);
            // Смотрим все файлы, которые находятся на pathway
            File allFiles[] = patwayFile.listFiles();
            String fileName;
            // Смотрим все файлы, кроме input.csv и result.csv
            for (int i = 0; i < allFiles.length; i++)
            {
                fileName = allFiles[i].getName();
                if (!fileName.equals(fileInputName) && !fileName.equals(fileResultName)) {
                    // Входной поток, транслирующий байты в символы, где Windows-1251 — кодировка (чтобы распознавались русские буквы)
                    InputStreamReader reader = new InputStreamReader(new FileInputStream(allFiles[i]), "Windows-1251");
                    // Буферизированный входной символьный поток
                    BufferedReader br = new BufferedReader(reader);
                    // Здесь хранится строка из таблицы
                    String str = "";
                    // Здесь хранятся ячейки строки str из таблицы
                    String[] token;
                    // Берем строку из таблицы и изменяем mas
                    boolean help = true;
                    while ((str = br.readLine()) != null) {
                        // Пропускаем 1 строку в таблице (она нам не нужна)
                        if (help) {
                            help = false;
                        }
                        else {
                            // Разделяем строку из таблицы на ячейки
                            token = str.split(";");
                            for (int j = 0; j < countLine; j++) {
                                // Ищем того откуда переводим деньги
                                if (token[0].equals(mas.get(j).get(0))) {
                                    // Старая сумма
                                    Double oldMoney1 = Double.parseDouble(mas.get(j).get(2));
                                    // Сколько вычитаем из старой суммы
                                    Double fixMoney1 = Double.parseDouble(token[4]);
                                    // Новая сумма
                                    String newMoney1 = (oldMoney1 - fixMoney1) + "";
                                    // Если хватает денег на выполнение транзакции, тогда продолжаем (иначе выводим сообщение)
                                    if ((oldMoney1 - fixMoney1) >= 0) {
                                        // Удаляем старую сумму
                                        mas.get(j).remove(2);
                                        // Сохраняем измененную сумму на место старой
                                        mas.get(j).add(2, newMoney1);
                                        for (int k = 0; k < countLine; k++) {
                                            // Ищем того куда переводим деньги
                                            if (token[2].equals(mas.get(k).get(0))) {
                                                // Старая сумма
                                                Double oldMoney2 = Double.parseDouble(mas.get(k).get(2));
                                                // Сколько вычитаем из старой суммы
                                                Double fixMoney2 = Double.parseDouble(token[4]);
                                                // Новая сумма
                                                String newMoney2 = (oldMoney2 + fixMoney2) + "";
                                                // Удаляем старую сумму
                                                mas.get(k).remove(2);
                                                // Сохраняем измененную сумму на место старой
                                                mas.get(k).add(2, newMoney2);
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("Транзакция " + allFiles[i].getName() + " не может быть выполнена"
                                        + " при переводе из " + token[0] + " в " + token[2] + " из-за нехватки средств!!!");
                                    }
                                }

                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Метод сохраняет mas в файл result
    public static void saveFile(String fileResultName) {
        try {
            File file = new File(fileResultName);
            // Выходной поток, где Windows-1251 — кодировка (чтобы распознавались русские буквы)
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(fileResultName), "Windows-1251");
            // Записываем данные в файл result
            write.append("Название компании" + ";" + "Лицевой счет" + ";" + "Первоначальный бюджет \n");
            for (int i = 0; i < countLine; i++) {
                write.append(mas.get(i).get(0) + ";");
                write.append(mas.get(i).get(1) + ";");
                write.append(mas.get(i).get(2) + "\n");
            }
            // Закрываем поток
            write.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
