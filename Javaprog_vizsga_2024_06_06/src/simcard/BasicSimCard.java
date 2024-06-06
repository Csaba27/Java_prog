package simcard;

public class BasicSimCard extends SimCard {
    private int failedAttempts;
    private final static int MAX_ATTEMPTS = 3;

    public BasicSimCard(String serialNumber, String pinCode) {
        super(serialNumber, pinCode);
    }

    @Override
    public boolean unblock() {
        return false;
    }

    @Override
    public boolean activate(String pinCode) throws Exception {
        if (isActive() || isBlocked()) {
            throw new Exception("The card is active or blocked!");
        }
        if (!hasMoreAttempts()) {
            throw new Exception("No more attempts!");
        }

        if (pinCode.equals(this.pinCode)) {
            this.active = true;
            return true;
        }
        throw new Exception("Pincode is incorrect!");
    }

    public boolean hasMoreAttempts() {
        return MAX_ATTEMPTS < this.failedAttempts;
    }
}
