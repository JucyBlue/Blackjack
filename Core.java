import java.util.ArrayList;
public class Core{
    private static ArrayList<String> playerHand = new ArrayList<String>();
    private static ArrayList<String> dealerHand = new ArrayList<String>();
    private static ArrayList<String> decodedList = new ArrayList<String>();
    static Deck myDeck = new Deck(1);
    static Decode decode = new Decode();
    
    public static void main(String[] args){
        
        //myDeck.shuffle();
        newHand();
        
        printList(playerHand);
        System.out.println("\n");
        printList(myDeck.deck);
        displayCards();
        
    }


    public static void newHand(){
        playerHand.add(myDeck.pullCard());
        playerHand.add(myDeck.pullCard());
        dealerHand.add(myDeck.pullCard());
        dealerHand.add(myDeck.pullCard());
        dealerHand.add(myDeck.pullCard());
        dealerHand.add(myDeck.pullCard());
    }

    private static String printList(ArrayList<String> list){
        String combindedString = "";
        decodedList = decode.decodeList(list);
        System.out.println("\n");
        for(int i = 0; i < list.size(); i++){
            combindedString = combindedString + "- " +decodedList.get(i);
            if(i+1 < list.size()) combindedString = combindedString + "\n";
        }
        return combindedString;
    }


    private static void displayCards(){
        clearConsole();
        System.out.print("---------------Blackjack---------------\nDealers Hand:\n" 
        + printList(dealerHand) + "\n\nYour Hand:\n" + printList(playerHand));
    }

    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



    


}