package org.iesfm.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

public class Customer {
    private final static Logger log = LoggerFactory.getLogger(Customer.class);

    private String name;
    private String surnames;
    private String nif;
    private Account[] accounts;

    public Customer(String name, String surnames, String nif, Account[] accounts) {
        this.name = name;
        this.surnames = surnames;
        this.nif = nif;
        this.accounts = accounts;
    }

    public void showCustomer() {
        log.info("Nombre: " + name);
        log.info("Apellidos: " + surnames);
        log.info("NIF: " + nif);
        log.info("Lista de cuentas: ");
        for (Account account: accounts) {
            account.showAccount();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(surnames, customer.surnames) && Objects.equals(nif, customer.nif) && Arrays.equals(accounts, customer.accounts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, surnames, nif);
        result = 31 * result + Arrays.hashCode(accounts);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surnames='" + surnames + '\'' +
                ", nif='" + nif + '\'' +
                ", accounts=" + Arrays.toString(accounts) +
                '}';
    }
}
