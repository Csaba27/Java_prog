package simcard;

public abstract class SimCard implements Comparable<SimCard> {
    protected String serialNumber;
    protected String pinCode;
    protected boolean active;
    protected boolean blocked;
    protected int balance;

    public SimCard(String serialNumber, String pinCode) {
        this.serialNumber = serialNumber;
        this.pinCode = pinCode;
    }

    public abstract boolean activate(String pinCode) throws Exception;

    public abstract boolean unblock();

    public boolean isActive() {
        return active;
    }

    public boolean isBlocked() {
        return this.blocked;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public int compareTo(SimCard o) {
        return Integer.compare(this.balance, o.balance);
    }
}
