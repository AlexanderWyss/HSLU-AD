package n.n1;

public class AccountTask implements Runnable {
    private BankAccount source;
    private BankAccount destination;
    private int amount;

    AccountTask(BankAccount source, BankAccount destination, int amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    @Override
    public void run() {
        float hideableAmount = 250;
        int microTransactionCount = Math.round(amount / hideableAmount);

        for(int i = 0; i < microTransactionCount; i++) {
            source.transfer(destination, (int)hideableAmount);
        }
    }
}
