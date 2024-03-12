import java.util.*;

public class PlayerRankings {

    /** Instance Variables */
    HashMap<String, Integer> wins, losses;
    ArrayList<String> rankings;

    public PlayerRankings() {
        wins = new HashMap<>();
        losses = new HashMap<>();
        rankings = new ArrayList<>();
    }

    public void addPlayer(String username, int setsWon, int setsLost) {
        if(rankings.contains(username)){
            wins.put(username, wins.get(username) + setsWon);
            losses.put(username, losses.get(username) + setsLost);
        } else {
            wins.put(username, setsWon);
            losses.put(username, setsLost);
        }
        rankings.remove(username);
        for(int i = 0; i < rankings.size(); i++){
            if(getWinRate(rankings.get(i)) < getWinRate(username)){
                rankings.add(i,username);
                break;
            }
        }
        if(!rankings.contains(username)){
            rankings.add(username);
        }
    }

    public String topTen(){
        String topTen = "";
        for(int i = 0; i < 10 && i < rankings.size(); i++){
            topTen += (rankings.get(i) + " ");
        }
        return topTen;
    }
    
    public double getWinRate(String username){
        return ((double)wins.get(username)) / losses.get(username);
    }
}