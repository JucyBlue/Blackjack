import java.util.ArrayList;
import java.util.Scanner;
//System.out.println(x);
public class Core{
    private static ArrayList<String> playerHand = new ArrayList<String>();
    private static ArrayList<String> dealerHand = new ArrayList<String>();
    private static ArrayList<String> decodedList = new ArrayList<String>();
    static Deck myDeck = new Deck(1);
    static Decode decode = new Decode();
    static boolean hide = true;
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        //clearConsole();
        System.out.println(playerHand.size() + "---pp");

        play();
    }

    private static void play(){
        clearConsole();
        myDeck.shuffle();
        newHand();
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
            int input;
            
            
            do{
                
                clearConsole();
                displayCards();
                checkForBust(playerHand);
                pause();
                System.out.print("\n| 1-HIT | 2-STAND | 3-DOUBLE |");
                if(pair) System.out.print("4-SPLIT |");
                System.out.print("\n:");
                if(scan.hasNextInt()) {
                    input = scan.nextInt();
                    break;
                }else{
                    invalid("Number");
                }
            }
            while(true);
            
            switch(input){
                case 1:
                    hit();
                    break;
                case 2:

                    break;
                

            }
    }

    public static void hit(){
        playerHand.add(myDeck.pullCard());
        
        input(myDeck.check(playerHand));
    }

    private static void displayCards(){
        clearConsole();
        System.out.print("---------------Blackjack---------------\nDrug Dealers Hand:\n" 
        + printList(dealerHand, true ,true) + "\n\nYour Hand:\n" + printList(playerHand, false, true) // playerHand(list, hide, decodeList)
        + "\n---------------------------------------");
    }

    private static void checkForBust(ArrayList<String> list){
        int total = 0;
        for(int i = 0; i < list.size(); i++){
            total += decode.singleValue(list.get(i), "num");
        }
        System.out.println(total + "==");

    }

    

    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void invalid(String type){
        
        System.out.println("Invaid input, please enter a " + type + "\n(ENTER)");
        pause();
    }

    private static void pause(){
        scan.nextLine();
        scan.nextLine();

    }
}