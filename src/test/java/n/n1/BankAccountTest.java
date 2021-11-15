package n.n1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankAccountTest {
    @Test
    void test_TransferBigMoneyAndBack() throws InterruptedException {
        int number = 1000; // 10 Thousand
        int amount = 25000000; // 25 Million
        int wealth = 100000000; // 100 Million

        List<BankAccount> sourceBankAccounts = new ArrayList<>(number);
        List<BankAccount> targetBankAccounts = new ArrayList<>(number);

        for(int i = 0; i < number; i++) {
            sourceBankAccounts.add(new BankAccount(wealth));
            targetBankAccounts.add(new BankAccount());
        }

        final Thread[] threads = new Thread[number * 2];
        for(int i = 0; i < number; i++) {
            threads[i] = new Thread(new AccountTask(
                    sourceBankAccounts.get(i), targetBankAccounts.get(i), amount));
            threads[i + number] = new Thread(new AccountTask(
                    targetBankAccounts.get(i), sourceBankAccounts.get(i), amount));
            threads[i].start();
            threads[i + number].start();
        }

        for(final Thread thread : threads) {
            thread.join();
        }

        int errorCount = 0;

        for(int i = 0; i < sourceBankAccounts.size(); i++) {
            if(sourceBankAccounts.get(i).getBalance() != wealth) {
                System.out.println("Source Account " + i + ": " + sourceBankAccounts.get(i).getBalance() + " $");
                errorCount++;
            }
        }

        System.out.println(errorCount + " of " + sourceBankAccounts.size() + " Accounts were wrong!");

        assertEquals(0, errorCount);
    }
}
