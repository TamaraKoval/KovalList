import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> purchases = new ArrayList<>();
        int k = 0;

        System.out.println("Добро пожаловать в планировщик покупок!");
        while (k < 5) {
            printSep();
            introMenu();

            String input = scanner.nextLine();
            if (input.equals("end")) {
                System.out.println("До встречи на следующих покупках, пока-пока!=)");
                break;
            }
            try {
                int choice = Integer.parseInt(input);
                if (choice < 1 || choice > 4) {
                    System.out.println("В меню нет опции под номером " + choice + "!");
                    System.out.println("Повторите попытку");
                    k++;
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.println("Вы выбрали опцию добавления покупки в список. Введите ее название:");
                        input = scanner.nextLine();
                        if (tooShort(input)) {
                            System.out.println("Введенное Вами значение слишком короткое!");
                            k++;
                        } else {
                            purchases.add(capitalize(input));
                            System.out.println("Итоге в списке покупок: " + purchases.size());
                        }
                        break;

                    case 2:
                        printList(purchases);
                        break;

                    case 3:
                        printList(purchases);
                        if (!purchases.isEmpty()) {
                            System.out.println("Введите номер или название покупки для удаления");
                            input = scanner.nextLine();
                            try {
                                int purchaseToRemove = Integer.parseInt(input) - 1;
                                if (purchaseToRemove < purchases.size()) {
                                    System.out.println("Покупка \"" + capitalize(purchases.get(purchaseToRemove)) + "\" удалена!");
                                    purchases.remove(purchaseToRemove);
                                    printList(purchases);
                                } else {
                                    System.out.println("В списке нет продукта с номером " + (purchaseToRemove + 1));
                                }
                            } catch (NumberFormatException exception) {
                                String purchaseToRemove = capitalize(input);
                                if (purchases.contains(purchaseToRemove)) {
                                    System.out.println("Покупка " + purchaseToRemove + "удалена!");
                                    purchases.remove(purchaseToRemove);
                                    printList(purchases);
                                } else {
                                    System.out.println("Товар с таким названием не найден");
                                }
                            }
                        }

                }
                System.out.println("Нажмите любую клавишу для продолжения");
                String pause = scanner.nextLine();

            } catch (NumberFormatException exception) {
                System.out.println("Вы вводите не цифры, а что-то другое! Повторите попытку");
                k++;
            }
        }

        if (k >= 5) {
            System.out.println("Вы производите подозрительные действия, мне кажется, что Вы - бот! Пока-пока!");
        }
    }

    public static void introMenu() {
        System.out.println("Выберите жалаемую операцию либо введите end для выхода:");
        System.out.println("1. Добавить покупку");
        System.out.println("2. Посмотреть список покупок");
        System.out.println("3. Удалить покупку");
        System.out.println("4. Найти покупку");
    }

    public static void printSep() {
        System.out.println("---------------------------------");
    }

    public static boolean tooShort(String str) {
        return str.length() < 2;
    }

    public static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
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
}

