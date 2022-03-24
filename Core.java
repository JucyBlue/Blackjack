import java.util.ArrayList;
public class Core{
    private static ArrayList<String> playerHand = new ArrayList<String>();
    private static ArrayList<String> dealerHand = new ArrayList<String>();
    private static ArrayList<String> decodedList = new ArrayList<String>();
    static Deck myDeck = new Deck(1);
    static Decode decode = new Decode();
    static boolean hide = true;
    
    public static void main(String[] args){
        clearConsole();
        //play();
        myDeck.shuffle();
        playerHand.add(myDeck.pullCard());
        playerHand.add(myDeck.pullCard());
        System.out.println(printList(playerHand, false, true));

        System.out.println(myDeck.check(playerHand));
        

        
    }


    public static void newHand(){
        playerHand.add(myDeck.pullCard());
        playerHand.add(myDeck.pullCard());
        dealerHand.add(myDeck.pullCard());
        dealerHand.add(myDeck.pullCard());
        displayCards();
        input(myDeck.check(playerHand));
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

    private static void input(boolean pair){
        System.out.print("\n|1-HIT | 2-STAND | 3-DOUBLE |");
    }

    private static void play(){
        myDeck.shuffle();
        newHand();
       
    }


    private static void displayCards(){
        clearConsole();
        System.out.print("---------------Blackjack---------------\nDrug Dealer Hand:\n" 
        + printList(dealerHand, true ,true) + "\n\nYour Hand:\n" + printList(playerHand, false,true) 
        + "\n---------------------------------------");
        
    }

    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}