package org.iesfm.bank;

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
