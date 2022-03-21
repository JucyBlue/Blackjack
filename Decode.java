import java.util.ArrayList;

public class Decode {
    public Decode(){
        
    }

    public ArrayList<String> decodeList(ArrayList<String> oldList){ //2D array might be easier
        ArrayList<String> newList = new ArrayList<String>();
        System.out.println("---" + singleValue(oldList.get(0)));
        for(int i = 0; i < oldList.size(); i++){
            //newList.add(i, getNum(deck.get(i)));
        }

        return newList;


    }

    public void newHand(){
        
    }

    private int singleValue(String wholeValue){
            if(wholeValue.length() == 3){
                return Character.getNumericValue(wholeValue.charAt(0));
            }
            else{
                return Integer.parseInt(wholeValue.substring(0, 1));
            }
            

    }



    private String getNum(int value){
        switch (value) {
            case 1:   return "Ace";
            case 2:   return "2";
            case 3:   return "3";
            case 4:   return "4";
            case 5:   return "5";
            case 6:   return "6";
            case 7:   return "7";
            case 8:   return "8";
            case 9:   return "9";
            case 10:  return "10";
            case 11:  return "Jack";
            case 12:  return "Queen";
            default:  return "King"; 
        }
    }
}
