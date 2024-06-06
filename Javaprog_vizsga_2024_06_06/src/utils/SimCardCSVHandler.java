package utils;

import simcard.AdvancedSimCard;
import simcard.BasicSimCard;
import simcard.SimCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SimCardCSVHandler {
    public ArrayList<SimCard> loadSimCards() {
        ArrayList<SimCard> simCards = new ArrayList<>();
        File file = new File("simcards.csv");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Scanner sc = new Scanner(scanner.nextLine());
                sc.useDelimiter(",");
                String serialNumber = sc.next();
                String pinCode = sc.next();
                boolean active = sc.nextBoolean();
                int balance = sc.nextInt();

                if (sc.hasNext()) {
                    String pukCode = sc.next();
                    AdvancedSimCard simCard = new AdvancedSimCard(serialNumber, pinCode, pukCode);
                    simCard.setActive(active);
                    simCard.setBalance(balance);
                    simCards.add(simCard);
                } else {
                    BasicSimCard simCard = new BasicSimCard(serialNumber, pinCode);
                    simCard.setActive(active);
                    simCard.setBalance(balance);
                    simCards.add(simCard);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return simCards;
    }
}
