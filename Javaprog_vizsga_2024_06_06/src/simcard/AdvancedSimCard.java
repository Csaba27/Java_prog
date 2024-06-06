package simcard;

import java.util.Scanner;

public class AdvancedSimCard extends BasicSimCard {
    protected String pukCode;

    public AdvancedSimCard(String serialNumber, String pinCode, String pukCode) {
        super(serialNumber, pinCode);
        this.pukCode = pukCode;
    }

    @Override
    public boolean unblock() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the pukcode: ");
        while (true) {
            if (sc.nextLine().equals(pukCode)) {
                this.active = true;
                System.out.println("Card is activated!");
                return true;
            } else {
                System.out.println("Puk Code is incorrect, enter again: ");
            }
        }
    }

    public String getPukCode() {
        return pukCode;
    }
}
