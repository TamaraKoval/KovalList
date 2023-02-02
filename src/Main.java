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
                        System.out.println("Вы выбрали опцию добавления покупки в список. Введите ее название");
                        input = scanner.nextLine();
                        if (tooShort(input, k)) {
                            break;
                        } else {
                            purchases.add(capitalize(input));
                            System.out.println("Итоге в списке покупок: " + purchases.size());
                            break;
                        }

                    case 2:
                        System.out.println("Вы запросили список ваших покупок");
                        if (purchases.isEmpty()) {
                            System.out.println("В вашей корзине пока ничего нет");
                            break;
                        } else {
                            for (int i = 0; i < purchases.size(); i++) {
                                System.out.println((i + 1) + ". " + purchases.get(i));
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

    public static boolean tooShort(String str, int k) {
        if (str.length() < 2) {
            System.out.println("Вводимое вами название слишком короткое, попробуйте снова");
            k++;
            return true;
        } else {
            return false;
        }
    }

    public static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }
}

