package academy.pocu.comp2500.lab11;

import academy.pocu.comp2500.lab11.pocu.PermanentlyClosedException;
import academy.pocu.comp2500.lab11.pocu.Product;
import academy.pocu.comp2500.lab11.pocu.ProductNotFoundException;
import academy.pocu.comp2500.lab11.pocu.User;
import academy.pocu.comp2500.lab11.pocu.Wallet;
import academy.pocu.comp2500.lab11.pocu.Warehouse;
import academy.pocu.comp2500.lab11.pocu.WarehouseType;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.ConcurrentLinkedDeque;

public class App {
    public void run(BufferedReader in, PrintStream out, PrintStream err) {
        User user = new User();
        WarehouseType select;
        while (true) {
            printWarehouse(out);
            String input = "";
            try {
                input = in.readLine();
            } catch (IOException e) {
                err.println(e);
                return;
            }

            if (input.equals("exit")) {
                return;
            }

            int number;
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }

            if (number > WarehouseType.values().length || number <= 0) {
                continue;
            } else {
                select = WarehouseType.values()[number - 1];
                break;
            }
        }
        Warehouse warehouse = new Warehouse(select);

        SafeWallet wallet;

        try {
            wallet = new SafeWallet(user);
        } catch (IllegalAccessException e) {
            err.println("AUTH_ERROR");
            return;
        }

        while (true) {
            out.println("BALANCE: " + wallet.getAmount());
            printProducts(out, warehouse);
            String input = "";
            try {
                input = in.readLine();
            } catch (IOException e) {
                err.println(e);
                return;
            }

            int number;
            if (input.equals("exit")) {
                return;
            }

            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }

            if (number > warehouse.getProducts().size() || number <= 0) {
                continue;
            }
            Product selectedProduct = null;
            try {
                selectedProduct = warehouse.getProducts().get(number - 1);
                if (!wallet.withdraw(selectedProduct.getPrice())) {
                    continue;
                }
                warehouse.removeProduct(selectedProduct.getId());
            } catch (ProductNotFoundException e) {
                wallet.deposit(selectedProduct.getPrice());
                continue;
            }
        }
    }

    private void printWarehouse(PrintStream out) {
        out.println("WAREHOUSE: Choose your warehouse!");
        int index = 1;
        for (WarehouseType each : WarehouseType.values()) {
            out.println(String.format("%d. %s", index++, each));
        }
    }

    private void printProducts(PrintStream out, Warehouse warehouse) {
        out.println("PRODUCT_LIST: Choose the product you want to buy!");
        int index = 1;
        for (Product each : warehouse.getProducts()) {
            out.println(String.format("%d. %s            %d", index++, each.getName(), each.getPrice()));
        }
    }
}
