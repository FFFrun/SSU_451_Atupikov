import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket implements InterfaceBasket {
    Map quantityProduct = new HashMap<String, Integer>();

    public Basket() {
    }

    // Добавление товара
    public void addProduct(String product, int quantity) {
        if (quantityProduct.containsKey(product)) { // Если данный товар имеется, то прибавляем количество
            quantityProduct.put(product, (Integer)quantityProduct.get(product) + quantity);
        }
        else {
            quantityProduct.put(product, quantity);
        }
        System.out.println("Completed! \n");
    }

    // Удалить товар
    public void removeProduct(String product) {
        if (quantityProduct.containsKey(product)) { // Если данный товар имеется, то удаляем его
            quantityProduct.remove(product);
            System.out.println("Completed! \n");
        }
        else {
            System.out.println("The product is no longer available! \n");
        }
    }

    // Обновление количества товара
    public void updateProductQuantity(String product, int quantity) {
        if (quantityProduct.containsKey(product)) { // Если данный товар имеется, то обновляем его количество заданным значением
            quantityProduct.put(product, quantity);
            System.out.println("Completed! \n");
        }
        else {
            System.out.println("The product is no longer available! \n");
        }
    }

    // Очистить корзину
    public void clear() {
        quantityProduct.clear();
        System.out.println("Completed! \n");
    }

    // Вывести все товары
    public List<String> getProducts() {
        List<String> list = new ArrayList<String>(quantityProduct.keySet()); // Выводим все ключи из map
        return list;
    }

    // Вывести количество товара
    public int getProductQuantity(String product) {
        if (quantityProduct.containsKey(product)) { // Если данный товар имеется, то выводим его количество
            int quantity = (Integer)quantityProduct.get(product);
            return quantity;
        }
        else {
            return 0;
        }
    }
}
