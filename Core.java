public class Core{
    public static void main(String[] args){
        Deck myDeck = new Deck(3);
        myDeck.shuffle();
        for(int i =0; i < myDeck.deckSize(); i++){
            System.out.println(myDeck.deck.get(i));
        }
    }
}