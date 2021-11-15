package n.n1;

final class BankAccount {
    private int balance;

    /**
     * Erzeugt ein Bankkonto mit einem Anfangssaldo.
     *
     * @param balance Anfangssaldo
     */
    BankAccount(final int balance) {
        this.balance = balance;
    }

    /**
     * Erzeugt ein Bankkonto mit Kontostand Null.
     */
    BankAccount() {
        this(0);
    }

    /**
     * Gibt den aktuellen Kontostand zurück.
     *
     * @return Kontostand.
     */
    int getBalance() {
        return this.balance;
    }

    /**
     * Addiert zum bestehen Kontostand einen Betrag hinzu.
     *
     * @param amount Einzuzahlender Betrag
     */
    private void deposit(final int amount) {
        synchronized (this) {
            this.balance += amount;
        }
    }

    /**
     * Überweist einen Betrag vom aktuellen Bankkonto an ein Ziel-Bankkonto.
     *
     * @param target Bankkonto auf welches der Betrag überwiesen wird.
     * @param amount zu überweisender Betrag.
     */
    void transfer(final BankAccount target, final int amount) {
        synchronized (this){
            this.balance -= amount;
        }
        target.deposit(amount);
    }
}
