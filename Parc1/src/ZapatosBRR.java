import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Product {
    private String name;
    private int initialInventory;
    private int currentInventory;

    public Product(String name, int initialInventory) {
        this.name = name;
        this.initialInventory = initialInventory;
        this.currentInventory = initialInventory;
    }

    public String getName() {
        return name;
    }

    public int getInitialInventory() {
        return initialInventory;
    }

    public int getCurrentInventory() {
        return currentInventory;
    }

    public void setSold(int quantity) {
        currentInventory -= quantity;
    }

    public void restockIfSoldOut() {
        if (currentInventory == 0) {
            currentInventory = initialInventory;
        }
    }
}

public class ZapatosBRR {
    private static Map<String, Product> inventory = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nOpciones:");
            System.out.println("1. Agregar producto");
            System.out.println("2. Vender producto");
            System.out.println("3. Mostrar inventario");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente

            switch (option) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    sellProduct();
                    break;
                case 3:
                    displayInventory();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la cantidad inicial de inventario: ");
        int initialInventory = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        Product product = new Product(name, initialInventory);
        inventory.put(name, product);
        System.out.println("Producto agregado al inventario.");
    }

    private static void sellProduct() {
        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();
        Product product = inventory.get(name);

        if (product == null) {
            System.out.println("El producto no existe en el inventario.");
            return;
        }

        System.out.print("Ingrese la cantidad a vender: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        if (quantity > product.getCurrentInventory()) {
            System.out.println("No hay suficiente stock disponible para esta venta.");
        } else {
            product.setSold(quantity);
            System.out.println("Venta realizada con éxito.");
            product.restockIfSoldOut();
        }
    }

    private static void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }

        System.out.println("Inventario:");
        for (Product product : inventory.values()) {
            System.out.printf("Nombre: %-20s Inventario inicial: %5d Inventario actual: %5d\n",
                    product.getName(), product.getInitialInventory(), product.getCurrentInventory());
        }
    }
}