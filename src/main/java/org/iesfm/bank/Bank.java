package org.iesfm.bank;

import org.iesfm.bank.exceptions.AccountNotFoundException;
import org.iesfm.bank.exceptions.CustomerNotFoundException;
import org.iesfm.bank.exceptions.InsufficientFundsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

public class Bank implements IBank {
    private final static Logger log = LoggerFactory.getLogger(Bank.class);

    private String bankName;
    private Customer[] customers;


    public Bank(String bankName, Customer[] customers) {
        this.bankName = bankName;
        this.customers = customers;
    }

    public void showBank() {
        log.info("Nombre del banco: " + bankName);
        log.info("Lista de clientes: ");
        for (Customer customer : customers) {
            customer.showCustomer();
        }
    }

    @Override
    public Customer getCustomer(String nif) throws CustomerNotFoundException {
        for (Customer customer: customers) {
            if (customer.getNif().equals(nif)){
                return customer;
            }
        }
        throw new CustomerNotFoundException();
    }

    @Override
    public Account getAccount(String nif, String iban) throws CustomerNotFoundException, AccountNotFoundException {
        Customer customer = getCustomer(nif);
        for (Account account: customer.getAccounts()) {
            if (account.getAccountNumber().equals(iban)) {
                return account;
            }
        }
        throw new AccountNotFoundException();
    }

    @Override
    public void ingressMoney(String nif, String iban, int amount) throws CustomerNotFoundException, AccountNotFoundException {
        Account account = getAccount(nif, iban);
        account.setBalance((account.getBalance() + amount));
    }

    @Override
    public void takeMoney(String nif, String iban, int amount) throws CustomerNotFoundException, AccountNotFoundException, InsufficientFundsException {
        Account account = getAccount(nif, iban);
        if (amount > account.getBalance()) {
            throw new InsufficientFundsException();
        } else {
            account.setBalance((account.getBalance() - amount));
        }
    }

    @Override
    public void transferMoney(String nif, String ibanOrigin, String ibanDestination, int amount) throws CustomerNotFoundException, AccountNotFoundException, InsufficientFundsException {
        Account ingressAccount = getAccount(nif, ibanDestination);
        Account takeAccount = getAccount(nif, ibanOrigin);
        if (amount > takeAccount.getBalance()) {
            throw new InsufficientFundsException();
        } else {
            takeAccount.setBalance((takeAccount.getBalance() - amount));
            ingressAccount.setBalance((ingressAccount.getBalance() + amount));
        }
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(bankName, bank.bankName) && Arrays.equals(customers, bank.customers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(bankName);
        result = 31 * result + Arrays.hashCode(customers);
        return result;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankName='" + bankName + '\'' +
                ", customers=" + Arrays.toString(customers) +
                '}';
    }
}
