package test;

import simcard.AdvancedSimCard;
import simcard.BasicSimCard;
import simcard.SimCard;
import utils.SimCardCSVHandler;

import java.util.ArrayList;
import java.util.Collections;


public class SimCardTest {

    public static void printSimCards(ArrayList<SimCard> simCards) {
        System.out.printf("%s %10s %8s %10s %12s %15s\n",
                "Serial Number", "PIN Code", "Active", "Type", "PUK code", "Balance");
        System.out.println("-".repeat(75));
        for (SimCard simCard : simCards) {
            System.out.printf("%s %10s %10s %10s %14s %15d\n",
                    simCard.getSerialNumber(),
                    simCard.getPinCode(),
                    (simCard.isActive() ? "Yes" : "No"),
                    (simCard.getClass().equals(BasicSimCard.class) ? "BASIC" : "ADVANCED"),
                    (simCard instanceof AdvancedSimCard ? ((AdvancedSimCard) simCard).getPukCode() : ""),
                    simCard.getBalance());
        }
    }

    public static ArrayList<SimCard> activateNonActiveCards(ArrayList<SimCard> simCards) {
        for (SimCard simCard : simCards) {
            if (!simCard.isActive())
                simCard.setActive(true);
        }
        return simCards;
    }

    public static AdvancedSimCard getMaximalCardPukCode(ArrayList<SimCard> simCards) {
        ArrayList<AdvancedSimCard> advancedSimCards = new ArrayList<>();
        for (SimCard simCard : simCards) {
            if (simCard instanceof AdvancedSimCard)
                advancedSimCards.add((AdvancedSimCard) simCard);
        }

        int maxi = 0;

        for (int i = 0; i < advancedSimCards.size() - 1; i++) {
            AdvancedSimCard card = advancedSimCards.get(i);
            int puk1 = Integer.parseInt(card.getPukCode());
            int puk2 = Integer.parseInt(advancedSimCards.get(i + 1).getPukCode());
            if (puk1 > puk2) {
                maxi = i;
            }
        }

        return advancedSimCards.get(maxi);
    }

    public static void main(String[] args) {
        BasicSimCard simCard = new BasicSimCard("11255557754", "15436");
        try {
            simCard.activate("54545");
            simCard.activate("24412");
            simCard.activate("75339");
            simCard.activate("123546");
        } catch (Exception e) {
            e.printStackTrace();
        }

        simCard.unblock();

        AdvancedSimCard simCard1 = new AdvancedSimCard("1123644987820", "564891", "1111");
        try {
            simCard1.activate("56123");
            simCard1.activate("23624");
            simCard1.activate("71354");
            simCard1.activate("961357");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 1111
        simCard1.unblock();

        SimCardCSVHandler csvHandler = new SimCardCSVHandler();
        ArrayList<SimCard> simCards = csvHandler.loadSimCards();
        printSimCards(simCards);

        System.out.println();

        Collections.sort(simCards);
        printSimCards(simCards);

        int totalBalance = 0;
        for (SimCard card : simCards) {
            if (!(card instanceof AdvancedSimCard)) {
                totalBalance += card.getBalance();
            }
        }

        System.out.printf("\nTotal balance of advanced sim cards: %d\n", totalBalance);
        System.out.println("Max PUK code card is: " + getMaximalCardPukCode(simCards).getPukCode());
    }
}
