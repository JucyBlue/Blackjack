import java.util.ArrayList;
import java.util.Collections;
/*
    SPADES = 0
    HEARTS = 1
    DIAMONDS = 2
    CLUBS = 3

    1-13 CARD VALUES
*/
public class Deck {
        
        public ArrayList<String> deck = new ArrayList<String>();

        public Deck(int num){
           
            for(int k = 0; k < num; k++){
                for(int i = 0; i < 4;i++){
                    for(int j = 1; j<14;j++){
                        deck.add(j +"-"+ i);
                    }
                }
            }
        }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public int deckSize(){
        return deck.size();
    } 

    public void pullCard(){
        System.out.println(deck.get(0));
        System.out.println(deck.get(0));
    }
}
