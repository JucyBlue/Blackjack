import java.util.ArrayList;
import java.util.Scanner;
public class Main{
    private static ArrayList<String> playerHand = new ArrayList<String>();
    private static ArrayList<String> playerSplitHand = new ArrayList<String>();
    private static ArrayList<String> dealerHand = new ArrayList<String>();
    private static ArrayList<String> decodedList = new ArrayList<String>();
    static Deck myDeck = new Deck(1);
    static Decode decode = new Decode();
    static boolean hide = true;
    static Scanner scan = new Scanner(System.in);
    static boolean pair = false;
    static boolean dealersPlay = false;
    static String splitStatus = "false";
    static int pot = 5;
    static int bank = 1000;
    //static String splitCard = "";
    //private static boolean checkForBust;
    public static void main(String[] args){
        myDeck.shuffle();
        myDeck.splitTemp();
        play();
    } //CURRENT TASK: when check win check for split win

    private static void play(){
        dealersPlay = false;
        playerHand.clear();
        playerSplitHand.clear();
        newHand();
        pair = myDeck.checkPair(playerHand);
        input();
        dealerPlay();
    }
    //shrimp

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

    private static void input(){ //myDeck.check(playerHand) is used to get the bool "pair"
            int input;
            show(true);
            do{ 
                if(scan.hasNextInt()) {
                    input = scan.nextInt();
                    break;
                }else{
                    invalid("Number");
                }
            }
            while(true);
            switch(input){
                case 1: //HIT
                    if(splitStatus == "hand2") hit(playerSplitHand);
                    else hit(playerHand);
                    input();
                    break;
                case 2: //STAND
                    
                    break;
                case 3: //DOUBLE
                    bank -= pot;
                    pot *= 2;
                    hit(playerHand);
                    break;
                case 4: //SPLIT
                    splitStatus = "hand1";
                    playerSplitHand.add(playerHand.get(0)); //get the first card of players hand and add it to split
                    playerHand.remove(0); //remove 1st card
                    hit(playerHand); 
                    hit(playerSplitHand);
                    input();
                    break;
                default:
            }

            if(splitStatus == "hand1"){
                splitStatus = "hand2";
                input();
                        
            }
    }

    public static void dealerPlay(){
        dealersPlay = true;
        while(getTotal(dealerHand) < 17){
            dealerHand.add(myDeck.pullCard());
            checkForBust(dealerHand);
        }
        show(false);
        handResults(playerHand);
        if(playerSplitHand.size() == 0) handResults(playerSplitHand);
    }

    private static void handResults(ArrayList<String> hand){
        displayCards(false);
        /*
            1 = player wins
            2 = dealers wins
            default = push
        */

            switch(whoWins(hand)){
                case 1:
                    System.out.println("player wins");
                    break;
                case 2:
                    System.out.println("dealer wins");
                    break;
                default:

                    System.out.println("push");
                    break;
            }
        
        
        System.out.printf(" P:%s D:%s", getTotal(playerHand), getTotal(dealerHand));
        System.out.println("\n(ENTER)");
        pause();
        
        play();
    }

   public static void hit(ArrayList<String> hand){
        hand.add(myDeck.pullCard());
        if(checkForBust(playerHand)){
            //whoWins();
        } 
    } 

    private static void displayCards(boolean hideDealer){
        clearConsole();
        System.out.printf("BANK:%s\n---------------Blackjack---------------\nDrug Dealers Hand:\n%s\n\nPot:%s\n\nYour Hand:%s\n%s",bank ,printList(dealerHand, hideDealer,true), pot ,stringBust(playerHand),printList(playerHand, false, true));

        if(splitStatus != "false") System.out.printf("Your Hand #2:%s\n%s\n", stringBust(playerSplitHand),printList(playerSplitHand, false, true));
       // + printList(dealerHand, hideDealer ,true) +"\n\nYour Hand:\n" + printList(playerHand, false, true) // playerHand(list, hide, decodeList)
       // + "\n---------------------------------------");
    }

    private static boolean checkForBust(ArrayList<String> list){
        int total = getTotal(list);
        if(total > 21) {
            return true;
        }
        return false;
    }
    private static String stringBust(ArrayList<String> list){
        int total = getTotal(list);
        if(total > 21) {
            return "(BUST)";
        }
        return "";
    }

    private static int getTotal(ArrayList<String> list){
        int total = 0;
        int singleValue;
        for(int i = 0; i < list.size(); i++){
            singleValue = decode.singleValue(list.get(i), "num");
            if(singleValue > 10) singleValue = 10;
            total += singleValue;
        }
        
        return total;
    }

    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void invalid(String type){
        System.out.println("Invaid input, please enter a " + type + "\n(ENTER)");
        pause();
    }

    private static int whoWins(ArrayList<String> hand){
        if(checkForBust(hand)){
            return 2; //player busts
        }
        else
        {
            if(checkForBust(hand)){
                return 1; //dealer busts too
            }
            else{
                if(getTotal(hand) > getTotal(dealerHand)){
                    return 1;
                }
                else if(getTotal(hand) == getTotal(dealerHand)){
                    return 3;
                }
                else{
                    return 2; 
                }
            }
        }
    }

    private static void pause(){
        scan.nextLine();
        scan.nextLine();
    }

    private static void show(boolean showDealer){
                clearConsole();
                displayCards(showDealer);
                System.out.print("\n| 1-HIT | 2-STAND | 3-DOUBLE |");
                if(pair) System.out.print("4-SPLIT |");
                System.out.print("\n:");    
    }

}