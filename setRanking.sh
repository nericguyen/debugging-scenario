set -e
javac PlayerRankings.java 
javac RankingsCalc.java

rm tournaments.txt

for file in $1/*.txt
do
    echo $file >> tournaments.txt
done

java RankingsCalc tournaments.txt