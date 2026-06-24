
package karen.bank;

import java.util.*;

class Account {
    private String accountNumber;
    private String holderName;
    private double balance;

    public Account(String accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class BankSystem {
    private Map<String, Account> accounts = new HashMap<>();

    public void createAccount(String accNumber, String name) {
        accounts.put(accNumber, new Account(accNumber, name));
    }

    public Account getAccount(String accNumber) {
        return accounts.get(accNumber);
    }
}

public class KarenBankApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankSystem bank = new BankSystem();

        while (true) {
            System.out.println("\n=== Karen Bank ===");
            System.out.println("1. Criar conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Ver saldo");
            System.out.println("0. Sair");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Número da conta: ");
                    String acc = scanner.nextLine();
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    bank.createAccount(acc, name);
                    System.out.println("Conta criada!");
                    break;

                case 2:
                    System.out.print("Conta: ");
                    acc = scanner.nextLine();
                    Account a = bank.getAccount(acc);
                    if (a != null) {
                        System.out.print("Valor: ");
                        double val = scanner.nextDouble();
                        a.deposit(val);
                        System.out.println("Depósito realizado.");
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 3:
                    System.out.print("Conta: ");
                    acc = scanner.nextLine();
                    a = bank.getAccount(acc);
                    if (a != null) {
                        System.out.print("Valor: ");
                        double val = scanner.nextDouble();
                        if (a.withdraw(val)) {
                            System.out.println("Saque realizado.");
                        } else {
                            System.out.println("Saldo insuficiente.");
                        }
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 4:
                    System.out.print("Conta: ");
                    acc = scanner.nextLine();
                    a = bank.getAccount(acc);
                    if (a != null) {
                        System.out.println("Saldo: " + a.getBalance());
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
