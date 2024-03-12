set -e
javac PlayerRankings.java 
javac RankingsCalc.java

rm tournaments.txt

for file in TournamentResults/*.txt
do
    echo $file >> tournaments.txt
done

java RankingsCalc tournaments.txt