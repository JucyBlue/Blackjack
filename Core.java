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
    static boolean pair = false;
    static boolean dealersPlay = false;
    public static void main(String[] args){
        play();
    }

    private static void play(){
        dealersPlay = false;
        playerHand.clear();
        clearConsole();
        myDeck.shuffle();
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
        checkForBust(playerHand);
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

    private static void input(){ // myDeck.check(playerHand) is used to get the bool "pair"
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
                case 1:
                    hit();
                    break;
                case 2:
                    break;
                

            }
    }

    public static void dealerPlay(){
        dealersPlay = true;
        while(getTotal(dealerHand) < 17){

            dealerHand.add(myDeck.pullCard());
            checkForBust(dealerHand);

        }
        show(false);
        handResults();
    }

    private static void handResults(){
        clearConsole();
        displayCards(false);
        /*
            1 = player wins
            2 = dealers wins
            default = push
        */
        switch(whoWins()){
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
    }

    public static void hit(){
        playerHand.add(myDeck.pullCard());
        if(checkForBust(playerHand)){
            whoWins();
        } 
        else{
            input();
        }
    }

    private static void displayCards(boolean hideDealer){
        clearConsole();
        System.out.print("---------------Blackjack---------------\nDrug Dealers Hand:\n" 
        + printList(dealerHand, hideDealer ,true) + "\n\nYour Hand:\n" + printList(playerHand, false, true) // playerHand(list, hide, decodeList)
        + "\n---------------------------------------");
    }

    private static boolean checkForBust(ArrayList<String> list){
        int total = getTotal(list);
        if(total > 21) {
            return true;
        }
        return false;
    }

    private static int getTotal(ArrayList<String> list){
        int total = 0;
        for(int i = 0; i < list.size(); i++){
            total += decode.singleValue(list.get(i), "num");
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

    private static int whoWins(){
        if(checkForBust(playerHand)){
            return 2;
        }
        else
        {
            if(checkForBust(dealerHand)){
                return 2;
            }
            else{
                if(getTotal(playerHand) > getTotal(dealerHand)){
                    return 1;
                }
                else if(getTotal(playerHand) == getTotal(dealerHand)){
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