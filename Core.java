import java.util.ArrayList;
public class Core{
    private static ArrayList<String> playerHand = new ArrayList<String>();
    private static ArrayList<String> dealerHand = new ArrayList<String>();
    private static ArrayList<String> decodedList = new ArrayList<String>();
    static Deck myDeck = new Deck(1);
    static Decode decode = new Decode();
    static boolean hide = true;
    
    public static void main(String[] args){
        play();
    }

    private static void play(){
        clearConsole();
        myDeck.shuffle();
        newHand();
        displayCards();
        input(myDeck.check(playerHand));
    }

    public static void newHand(){
        playerHand.add(myDeck.pullCard());
        playerHand.add(myDeck.pullCard());
        dealerHand.add(myDeck.pullCard());
        dealerHand.add(myDeck.pullCard());
    }

    private static String printList(ArrayList<String> list, boolean hide, boolean decodeTheList){
        String combindedString = "";
        if(decodeTheList) decodedList = decode.decodeList(list);
        else decodedList = list;
        System.out.println("\n");
        for(int i = 0; i < list.size(); i++){ // if hide is true and index == 2 then hide the string
            if(hide && i == 1) combindedString += "- ?? of ?????"; 
            else combindedString += "- " +decodedList.get(i);
            if(i+1 < list.size()) combindedString = combindedString + "\n";
        }
        return combindedString;
    }

    private static void input(boolean pair){ // myDeck.check(playerHand) is used to get the bool "pair"
        System.out.print("\n|1-HIT | 2-STAND | 3-DOUBLE |");
        if(pair) System.out.print(" SPLIT |");
    }



    private static void displayCards(){
        clearConsole();
        System.out.print("---------------Blackjack---------------\nDrug Dealer Hand:\n" 
        + printList(dealerHand, true ,true) + "\n\nYour Hand:\n" + printList(playerHand, false, true) // playerHand(list, hide, decodeList)
        + "\n---------------------------------------");
        
    }

    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}