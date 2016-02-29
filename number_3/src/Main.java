import java.util.Scanner;

public class Main {
    // Проверка - является ли строка числом
    public static boolean checkString(String string) {
        if (string == null) return false;
        return string.matches("^-?\\d+$");
    }

    public static void main(String []arg) {
        InterfaceBasket basket = new Basket();
        while (true) {
            System.out.print("Enter number: \n" +
                    "1 - add Product \n" +
                    "2 - remove Product \n" +
                    "3 - update Product Quantity \n" +
                    "4 - clear \n" +
                    "5 - get Products \n" +
                    "6 - get Product Quantity \n" +
                    "7 - exit \n" +
                    "Number: ");
            Scanner str = new Scanner(System.in);
            String valueStr = str.next();

            if (checkString(valueStr)) { // Если ввели число
                Integer value = Integer.parseInt(valueStr); // string -> int
                // Добавить товар и его количество
                if (value == 1) {
                    System.out.print("Enter Product: ");
                    Scanner strProduct = new Scanner(System.in);
                    String product = strProduct.next();
                    System.out.print("Enter Quantity: ");
                    Scanner strQuantity = new Scanner(System.in);
                    String quantityStr = strQuantity.next();
                    if (checkString(quantityStr)) { // Если ввели число
                        Integer quantity = Integer.parseInt(quantityStr); // string -> int
                        basket.addProduct(product, quantity);
                    }
                    else {
                        System.out.println("The quantity must be a integer!  \n");
                    }
                }
                // Удалить товар
                else if (value == 2) {
                    System.out.print("Enter Product: ");
                    Scanner strProduct = new Scanner(System.in);
                    String product = strProduct.next();
                    basket.removeProduct(product);
                }
                // Обновление количества товара
                else if (value == 3) {
                    System.out.print("Enter Product: ");
                    Scanner strProduct = new Scanner(System.in);
                    String product = strProduct.next();
                    System.out.print("Enter Quantity: ");
                    Scanner strQuantity = new Scanner(System.in);
                    String quantityStr = strQuantity.next();
                    if (checkString(quantityStr)) { // Если ввели число
                        Integer quantity = Integer.parseInt(quantityStr); // string -> int
                        basket.updateProductQuantity(product, quantity);
                    }
                    else {
                        System.out.println("The quantity must be a integer!  \n");
                    }
                }
                // Очистить корзину
                else if (value == 4) {
                    basket.clear();
                }
                // Вывести все товары
                else if (value == 5) {
                    System.out.print("Products: ");
                    System.out.println(basket.getProducts() + "\n");
                }
                // Вывести количество товара
                else if (value == 6) {
                    System.out.print("Enter Product: ");
                    Scanner strProduct = new Scanner(System.in);
                    String product = strProduct.next();
                    System.out.print("Quantity: ");
                    System.out.println(basket.getProductQuantity(product) + "\n");
                }
                // Выход
                else if (value == 7) {
                    break;
                } else {
                    System.out.println("Wrong number!  \n");
                }
            }
            else {
                System.out.println("The number must be a integer!  \n");
            }
        }
    }
}
