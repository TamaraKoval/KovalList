import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int k = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> purchases = new ArrayList<>();

        System.out.println("Добро пожаловать в планировщик покупок!");
        while (k < 5) {
            introMenu();
            String input = scanner.nextLine();
            if (end(input)) { break; }

            try {
                int choice = Integer.parseInt(input);
                if (!testMenuNum(choice)) { continue; }

                switch (choice) {
                    case 1:
                        input = inputAdd(scanner);
                        add(purchases, input);
                        break;
                    case 2:
                        printList(purchases);
                        break;
                    case 3:
                        printList(purchases);
                        if (!purchases.isEmpty()) {
                            input = inputRemove(scanner);
                            remove(purchases, input);
                        }
                        break;
                    case 4:
                        input = inputSearch(scanner);
                        purchaseSearch(input, purchases);
                        break;
                }

                pause(scanner);
            } catch (NumberFormatException exception) {
                menuError();
            }
        }
        testBot();
    }

    public static void introMenu() {
        System.out.println("---------------------------------");
        System.out.println("Выберите жалаемую операцию либо введите end для выхода:");
        System.out.println("1. Добавить покупку");
        System.out.println("2. Посмотреть список покупок");
        System.out.println("3. Удалить покупку");
        System.out.println("4. Найти покупку");
    }

    public static void menuError() {
        System.out.println("Вы вводите не цифры, а что-то другое! Повторите попытку");
        k++;
    }

    public static void pause(Scanner scanner) {
        System.out.println("Нажмите любую клавишу для продолжения");
        String pause = scanner.nextLine();
    }

    public static void testBot() {
        if (k >= 5) {
            System.out.println("Вы производите подозрительные действия, мне кажется, что Вы - бот! Пока-пока!");
        }
    }

    public static boolean tooShort(String str) {
        if (str.length() < 2) {
            System.out.println("Введенное Вами значение слишком короткое!");
            k++;
            return true;
        } else {
            return false;
        }
    }

    public static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }

    public static boolean end(String str) {
        if (str.equals("end")) {
            System.out.println("До встречи на следующих покупках, пока-пока!=)");
            return true;
        } else {
            return false;
        }
    }

    public static boolean testMenuNum(int menuNum) {
        if (menuNum < 1 || menuNum > 4) {
            System.out.println("В меню нет опции под номером " + menuNum + "!");
            System.out.println("Повторите попытку");
            k++;
            return false;
        } else {
            return true;
        }
    }

    public static String inputAdd(Scanner scanner) {
        String input;
        System.out.println("Вы выбрали опцию добавления покупки в список. Введите ее название:");
        input = scanner.nextLine();
        return input;
    }
    
    public static void add(List<String> list, String str) {
        if (!tooShort(str)) {
            list.add(capitalize(str));
            System.out.println("Итоге в списке покупок: " + list.size());
        }
    }

    public static void printList(List<String> list) {
        if (list.isEmpty()) {
            System.out.println("В Вашей корзине пока ничего нет");
        } else {
            System.out.println("Список Ваших покупок: ");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
    }

    public static String inputRemove(Scanner scanner) {
        String input;
        System.out.println("Введите номер или название покупки для удаления");
        input = scanner.nextLine();
        return input;
    }
    
    public static void removeByInt(List<String> list, String str) {
        int purchaseToRemove = Integer.parseInt(str) - 1;
        if (purchaseToRemove < list.size()) {
            System.out.println("Покупка \"" + capitalize(list.get(purchaseToRemove)) + "\" удалена!");
            list.remove(purchaseToRemove);
            printList(list);
        } else {
            System.out.println("В списке нет продукта с номером " + (purchaseToRemove + 1));
        }
    }

    public static void removeByString(List<String> list, String str) {
        if (!tooShort(str)) {
            String purchaseToRemove = capitalize(str);
            if (list.contains(purchaseToRemove)) {
                System.out.println("Покупка \"" + purchaseToRemove + "\" удалена!");
                list.remove(purchaseToRemove);
                printList(list);
            } else {
                System.out.println("Товар с таким названием не найден");
            }
        }
    }

    public static void remove(List<String> list, String str) {
        try {
            removeByInt(list, str);
        } catch (NumberFormatException exception) {
            removeByString(list, str);
        }
    }

    public static String inputSearch(Scanner scanner) {
        String input;
        System.out.println("Вы вошли в режим поиска. Введите ключевое слово:");
        input = scanner.nextLine();
        return input;
    }
    
    public static void purchaseSearch(String str, List<String> list) {
        if (!tooShort(str)) {
            if (list.isEmpty()) {
                System.out.println("В Вашей корзине пока ничего нет");
            } else {
                int k = 0;
                String strLower = str.toLowerCase();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).toLowerCase().contains(strLower)) {
                        System.out.println((i + 1) + ". " + list.get(i));
                        k++;
                    }
                }
                if (k == 0) {
                    System.out.println("По Вашему запросу ничего не найдено!");
                }
            }
        }
    }
}

