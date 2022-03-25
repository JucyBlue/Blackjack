import java.util.ArrayList;

public class Decode {
    public Decode(){
        
    }

    public ArrayList<String> decodeList(ArrayList<String> oldList){ //2D array might be easier
        ArrayList<String> newList = new ArrayList<String>();
        //System.out.println("---" + singleValue(oldList.get(0)));
        for(int i = 0; i < oldList.size(); i++){
            newList.add(i,(getNum(singleValue(oldList.get(i), "num")) + " of " + getSuit(singleValue(oldList.get(i), "suit"))));
        } 

        return newList;


    }

    public void newHand(){
        
    }

    public int singleValue(String wholeValue, String type){
            int decoded = -1;
            if(type == "num"){
                if(wholeValue.length() == 3){
                    decoded = Character.getNumericValue(wholeValue.charAt(0));
                }
                else{
                    decoded = Integer.parseInt(wholeValue.substring(0, 2));
                }
            }
            else{
                decoded = Character.getNumericValue(wholeValue.charAt(wholeValue.length()-1));
            }
           

            return decoded;
            

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

    private String getSuit(int value){
        
        switch (value) {
            case 0:   return "Spades";
            case 1:   return "Hearts";
            case 2:   return "Diamonds";
            default: return "Clubs";
        }
    }
}
