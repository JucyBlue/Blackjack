import java.util.ArrayList;
public class Core{
    private static ArrayList<String> playerHand = new ArrayList<String>();
    static Deck myDeck = new Deck(1);
    static Decode decode = new Decode();
    public static void main(String[] args){
        
        myDeck.shuffle();
        newHand();
        printList(playerHand);
        printList(myDeck.deck);
        decode.decodeList(myDeck.deck);
    }


    public static void newHand(){
        playerHand.add(myDeck.pullCard());
        playerHand.add(myDeck.pullCard());
        playerHand.add(myDeck.pullCard());
        playerHand.add(myDeck.pullCard());
    }

    private static void printList(ArrayList<String> list){
        System.out.println("\n");
        for(int i = 0; i < list.size(); i++){
            System.out.print(" " + list.get(i));
        }
    }



    


}