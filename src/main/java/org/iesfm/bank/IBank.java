package org.iesfm.bank;

import org.iesfm.bank.exceptions.AccountNotFoundException;
import org.iesfm.bank.exceptions.CustomerNotFoundException;
import org.iesfm.bank.exceptions.InsufficientFundsException;

public interface IBank {
    Customer getCustomer(String nif) throws CustomerNotFoundException;
    Account getAccount(String nif, String iban) throws CustomerNotFoundException, AccountNotFoundException;
    void ingressMoney(String nif, String iban,int amount) throws CustomerNotFoundException, AccountNotFoundException;
    void takeMoney(String nif, String iban, int amount) throws CustomerNotFoundException, AccountNotFoundException, InsufficientFundsException;
    void transferMoney(String nif, String ibanOrigin, String ibanDestination, int amount) throws CustomerNotFoundException, AccountNotFoundException, InsufficientFundsException;
}
