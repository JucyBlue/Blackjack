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
    }


    public static void newHand(){
        playerHand.add(myDeck.pullCard());
        playerHand.add(myDeck.pullCard());
        dealerHand.add(myDeck.pullCard());
        dealerHand.add(myDeck.pullCard());
    }

    private static void printList(ArrayList<String> list){
        decodedList = decode.decodeList(list);
        System.out.println("\n");
        for(int i = 0; i < list.size(); i++){
            System.out.print( decodedList.get(i) + "|" );
        }
    }



    


}