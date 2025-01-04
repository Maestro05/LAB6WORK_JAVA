import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MenuItem {
    String name;
    double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void display() {
        System.out.println(name + " - " + price + " руб.");
    }

    public double getPrice() {
        return price;
    }
}

class MainDish extends MenuItem {
    public MainDish(String name, double price) {
        super(name, price);
    }

    @Override
    public void display() {
        System.out.print("[Главное блюдо] ");
        super.display();
    }
}

class Appetizer extends MenuItem {
    public Appetizer(String name, double price) {
        super(name, price);
    }

    @Override
    public void display() {
        System.out.print("[Закуска] ");
        super.display();
    }
}

class Drink extends MenuItem {
    public Drink(String name, double price) {
        super(name, price);
    }

    @Override
    public void display() {
        System.out.print("[Напиток] ");
        super.display();
    }
}

class Dessert extends MenuItem {
    public Dessert(String name, double price) {
        super(name, price);
    }

    @Override
    public void display() {
        System.out.print("[Десерт] ");
        super.display();
    }
}

class Order {
    List<MenuItem> items = new ArrayList<>();
    private static int orderCount = 0;
    private int orderNumber;

    public Order() {
        this.orderNumber = ++orderCount;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void displayOrder() {
        double total = 0;
        System.out.println("\nВаш заказ #" + orderNumber + ":");
        for (MenuItem item : items) {
            item.display();
            total += item.getPrice();
        }
        System.out.println("Общая сумма: " + total + " руб.");
    }

    public static int getOrderCount() {
        return orderCount;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    // Метод для глубокого клонирования
    public Order deepClone() {
        Order clonedOrder = new Order();
        for (MenuItem item : items) {
            // создаем новый объект для каждого элемента
            clonedOrder.addItem(new MenuItem(item.name, item.price));
        }
        return clonedOrder;
    }

    // Метод для мелкого клонирования
    public Order shallowClone() {
        Order clonedOrder = new Order();
        clonedOrder.items = this.items;  // Ссылка на тот же список
        return clonedOrder;
    }
}

public class Main {

    // Метод для отображения главного меню
    public static void displayMenu() {
        System.out.println("\nВыберите категорию меню:");
        System.out.println("1. Главное блюдо");
        System.out.println("2. Закуски");
        System.out.println("3. Напитки");
        System.out.println("4. Десерты");
        System.out.println("5. Завершить заказ");
        System.out.println("6. Выйти из программы");
        System.out.println("7. Клонировать заказ");
        System.out.print("Введите номер категории: ");
    }

    // Метод для отображения главных блюд
    public static void displayMainDishes() {
        System.out.println("\nГлавные блюда:");
        System.out.println("1. Борщ - 150 руб.");
        System.out.println("2. Стейк - 300 руб.");
        System.out.println("3. Пельмени - 180 руб.");
        System.out.println("4. Ризотто - 220 руб.");
    }

    // Метод для отображения закусок
    public static void displayAppetizers() {
        System.out.println("\nЗакуски:");
        System.out.println("1. Салат Цезарь - 120 руб.");
        System.out.println("2. Оливье - 100 руб.");
        System.out.println("3. Блины с икрой - 150 руб.");
        System.out.println("4. Тосты с авокадо - 110 руб.");
    }

    // Метод для отображения напитков
    public static void displayDrinks() {
        System.out.println("\nНапитки:");
        System.out.println("1. Кола - 50 руб.");
        System.out.println("2. Минеральная вода - 40 руб.");
        System.out.println("3. Сок апельсиновый - 70 руб.");
        System.out.println("4. Чай черный - 60 руб.");
    }

    // Метод для отображения десертов
    public static void displayDesserts() {
        System.out.println("\nДесерты:");
        System.out.println("1. Торт Наполеон - 80 руб.");
        System.out.println("2. Мороженое - 60 руб.");
        System.out.println("3. Чизкейк - 120 руб.");
        System.out.println("4. Пирог с яблоками - 90 руб.");
    }

    // Метод для отображения всех заказов
    public static void displayAllOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("Нет заказов для клонирования.");
            return;
        }

        System.out.println("\nВсе заказы:");
        for (Order order : orders) {
            order.displayOrder();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Списки блюд
        MainDish[] mainDishes = {
            new MainDish("Борщ", 150),
            new MainDish("Стейк", 300),
            new MainDish("Пельмени", 180),
            new MainDish("Ризотто", 220)
        };

        Appetizer[] appetizers = {
            new Appetizer("Салат Цезарь", 120),
            new Appetizer("Оливье", 100),
            new Appetizer("Блины с икрой", 150),
            new Appetizer("Тосты с авокадо", 110)
        };

        Drink[] drinks = {
            new Drink("Кола", 50),
            new Drink("Минеральная вода", 40),
            new Drink("Сок апельсиновый", 70),
            new Drink("Чай черный", 60)
        };

        Dessert[] desserts = {
            new Dessert("Торт Наполеон", 80),
            new Dessert("Мороженое", 60),
            new Dessert("Чизкейк", 120),
            new Dessert("Пирог с яблоками", 90)
        };

        List<Order> orders = new ArrayList<>();
        Order currentOrder = null;
        boolean exit = false;

        while (!exit) {
            displayMenu();

            int category = scanner.nextInt();

            switch (category) {
                case 1: {
                    displayMainDishes();
                    System.out.print("Выберите главное блюдо (1-4): ");
                    int choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        if (currentOrder == null) currentOrder = new Order();
                        currentOrder.addItem(mainDishes[choice - 1]);
                    } else {
                        System.out.println("Некорректный выбор!");
                    }
                    break;
                }
                case 2: {
                    displayAppetizers();
                    System.out.print("Выберите закуску (1-4): ");
                    int choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        if (currentOrder == null) currentOrder = new Order();
                        currentOrder.addItem(appetizers[choice - 1]);
                    } else {
                        System.out.println("Некорректный выбор!");
                    }
                    break;
                }
                case 3: {
                    displayDrinks();
                    System.out.print("Выберите напиток (1-4): ");
                    int choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        if (currentOrder == null) currentOrder = new Order();
                        currentOrder.addItem(drinks[choice - 1]);
                    } else {
                        System.out.println("Некорректный выбор!");
                    }
                    break;
                }
                case 4: {
                    displayDesserts();
                    System.out.print("Выберите десерт (1-4): ");
                    int choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        if (currentOrder == null) currentOrder = new Order();
                        currentOrder.addItem(desserts[choice - 1]);
                    } else {
                        System.out.println("Некорректный выбор!");
                    }
                    break;
                }
                case 5:
                    if (currentOrder != null) {
                        currentOrder.displayOrder();
                        orders.add(currentOrder);
                        currentOrder = null;
                    } else {
                        System.out.println("Нет текущего заказа!");
                    }
                    break;
                case 6:
                    exit = true;
                    break;
                case 7: {
                    // Выбор клонирования
                    System.out.println("\nВыберите тип клонирования:");
                    System.out.println("1. Мелкое клонирование");
                    System.out.println("2. Глубокое клонирование");
                    System.out.print("Введите ваш выбор (1-2): ");
                    int cloneType = scanner.nextInt();

                    displayAllOrders(orders); // Показываем все заказы перед клонированием

                    System.out.print("Введите номер заказа для клонирования: ");
                    int orderToClone = scanner.nextInt();
                    if (orderToClone > 0 && orderToClone <= orders.size()) {
                        Order originalOrder = orders.get(orderToClone - 1);
                        Order clonedOrder = null;
                        if (cloneType == 1) {
                            clonedOrder = originalOrder.shallowClone();
                            System.out.println("Мелкое клонирование выполнено.");
                        } else if (cloneType == 2) {
                            clonedOrder = originalOrder.deepClone();
                            System.out.println("Глубокое клонирование выполнено.");
                        }
                        if (clonedOrder != null) {
                            clonedOrder.displayOrder();
                            orders.add(clonedOrder);
                        }
                    } else {
                        System.out.println("Некорректный выбор заказа!");
                    }
                    break;
                }
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    break;
            }
        }

        // Вывод количества заказов
        System.out.println("\nВсего сделано заказов: " + Order.getOrderCount());
    }
}
