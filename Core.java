import java.util.ArrayList;
public class Core{
    private static ArrayList<String> playerHand = new ArrayList<String>();
    static Deck myDeck = new Deck(3);
    static Decode decode = new Decode();
    public static void main(String[] args){
        
        myDeck.shuffle();
        newHand();
        System.out.println(playerHand.get(0));
        System.out.println(playerHand.get(1));
    }


    public static void newHand(){
        playerHand.add(myDeck.pullCard());
        playerHand.add(myDeck.pullCard());
    }



    


}