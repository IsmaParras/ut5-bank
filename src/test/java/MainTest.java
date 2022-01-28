import org.iesfm.bank.Account;
import org.iesfm.bank.Bank;
import org.iesfm.bank.Customer;
import org.iesfm.bank.exceptions.CustomerNotFoundException;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {
    static Account[] accountsList = {
            new Account("7520",
                    2300),
            new Account("1125",
                    10800)
    };

    static Customer[] customersList = {
            new Customer("Juan",
                    "Pérez",
                    "54400877B",
                    accountsList)
    };

    static Bank bank =  new Bank("Santander", customersList);

    @Test
    public void verifyGetCustomerFound() throws CustomerNotFoundException{
        Customer customerFound = bank.getCustomer("54400877B");
        Assert.assertEquals(customerFound.getNif(), "54400877B");
    }

    @Test(expected = CustomerNotFoundException.class)
    public void verifyGetCustomerNotFound() throws CustomerNotFoundException{
        Customer customerNotFound = bank.getCustomer("57788266M");
        Assert.assertEquals(customerNotFound.getNif(), "57784544C");
    }


}
