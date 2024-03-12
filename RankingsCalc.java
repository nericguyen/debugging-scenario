import java.util.*;
import java.io.*;

public class RankingsCalc {
    public static void main(String[] args) throws FileNotFoundException{
        PlayerRankings playerRankings = new PlayerRankings();
        
        File myObj = new File(args[0]);
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> tourneys = new ArrayList<>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            tourneys.add(data);
        }

        for(int k = 0; k < tourneys.size(); k++) {
            File tourneyResults = new File(tourneys.get(k));
            Scanner playerReader = new Scanner(tourneyResults);
            ArrayList<String> playersAndSets = new ArrayList<>();
            while(playerReader.hasNextLine()) {
                String data = playerReader.nextLine();
                playersAndSets.add(data);
            }

            playerReader.close();
            
            for(String playerAndSets : playersAndSets){
                String[] playerData = playerAndSets.split(" ");
                int setsLost = Integer.parseInt(playerData[playerData.length - 1]);
                int setsWon = Integer.parseInt(playerData[playerData.length - 2]);
                String player = "";
                for(int i = 0; i < playerData.length - 2; i++){
                    player += playerData[i];
                }
                playerRankings.addPlayer(player,setsWon,setsLost);
            }
        }

        System.out.println(playerRankings.topTen());

        myReader.close();
    }
}
