# Smash Player Rankings Debugging Scenario

In the following blogpost, I will be going over a hypothetical debugging scenario with a student in which I will include the following:

* A student posting a screenshot showing a symptom and a description of a guess at the bug/some sense of what the failure-inducing input is
* A response from a TA asking a leading question or suggesting a command to try
* Another screenshot/terminal output showing what information the student got from trying that, and a clear description of what the bug is
* The file & directory structure needed
* The contents of each file before fixing the bug
* The full command line (or lines) you ran to trigger the bug
* A description of what to edit to fix the bug

## Overview of Student Program

Our hypothetical student **Eric** was assigned with the following task:
* Create a directory that holds text files of player data of recent UCSD Smash tournaments in the following format

  [player] [set wins] [set losses]
* Create a java program that stores every player's overall set wins and losses, and contains methods for printing out the current Top 10 players based
  ranked solely by their win rate (wins/total sets).
* Create another java file with a main method that properly instantiates the prior java program and prints out the Top 10 players.
* Write a bash script which compiles and runs the main method and takes the name of the directory containing the tournament data as the sole argument.

## Student Post

Eric has spent the past hour wondering why his code is compiling but faces errors at runtime.  He decided to consult the wonderful TAs on Edstem for help.
He posts the following screenshot with the following message:
![image](https://github.com/nericguyen/debugging-scenario/assets/149546505/f064a018-7fae-4731-b638-93d8dfae3d6a)

*"I've never seen this error message before about "Java heap space," but I can assume from the name that the program is probably making too many objects for it to
be able to store.  I think the code I wrote for storing player data might be too inefficient that it can't handle the size of the text files.  My last guess is that 
the size of the array/ArrayList is getting too big for the computer to handle."*

Eric's brain is fried and he cannot think straight.  He is capable of fixing the bug, but he just needs a gentle nudge in the right thinking direction.

## The Horror of Being a TA

In the perspective of a TA, the screenshot and comment is a little less than helpful.  There are no images of the code so the error code is all they have to work
with.  Nevertheless, the error code IS all that is really necessary to start some brainstorming.  While it does not work all of the time, the last few lines of the 
error symptom is the MOST valuable.

`at PlayerRankings.addPlayer(PlayerRankings.java:26)` and `at RankingsCalc.main(RankingsCalc.java:35)`

Most of the times, these tell the coder what file, method, and line number is acting improperly.  The TA works with what they have and sends the following message:

*"Hey Eric! You are right in your assumption of what the java.lang.OutOfMemoryError message means.  The heap seems to be creating either too many objects or your array
is being expanded beyond processing capabilities.  You can always search up what certain error messages mean!  Since this error is likely an infinite loop of some sort, 
the best advice I can give you is to assess your for and while loops, for they are the main sources of unwanted infinite loops.  You may have certain variables not updating
as you planned. Specifically, look at the last two lines in the error message to determine where to start debugging."*

## Hallelujah

![image](https://github.com/nericguyen/debugging-scenario/assets/149546505/212204b3-d115-4c91-9b5a-02b39adceec4)

After many minutes of trial and error (and crying), Eric finally spots the bug!  He does the proper changes and is happily greeted with the correct output.

## The Hard Part

The TA pats their back knowing they saved another student's soul, but what was happening behind the scenes? Let's delve into the actual code and see what changes 
Eric made.  First, starting with the directory structure:

![image](https://github.com/nericguyen/debugging-scenario/assets/149546505/eac85079-24a1-41e4-b55e-5489e06fc97f)

The text files that contain the tournament standings look like this:

![image](https://github.com/nericguyen/debugging-scenario/assets/149546505/e5ff1616-8a1f-4020-986d-10d7ce4cca74)

Eric started by making the following java programs:

![image](https://github.com/nericguyen/debugging-scenario/assets/149546505/e5f89c58-dec3-465b-ba24-4999d5324699)

![image](https://github.com/nericguyen/debugging-scenario/assets/149546505/f894f94a-641b-421c-a6ec-e228da8efcce)

He then made the following simple bash script:

![image](https://github.com/nericguyen/debugging-scenario/assets/149546505/93e8ebe9-d153-4f08-a207-c6796b166707)

After running the bash script, the following file is made with these contents:

![image](https://github.com/nericguyen/debugging-scenario/assets/149546505/243058b8-37c7-481d-ac19-30a7a40995f6)

After running the full command line `bash setRanking.sh TournamentResults`, the error is produced as shown in the Edstem post.
As the TA said, Eric should start looking at for/while loops that at line 26 in PlayerRankings.java.  Eric sees the following code block

![image](https://github.com/nericguyen/debugging-scenario/assets/149546505/ca065f50-e624-49b5-8c76-136f6b29d55c)

At first, he doesn't notice the error.  The size is finite and should eventually stop as i increments.  Then, he notices something.  The size itself is being updated
INSIDE the for loop!!!  Line 26 adds an element to the ArrayList, and it seems to be continually adding elements.  A player only needs to be added once into the ranking,
so Eric was supposed to cease the for loop after the player was added.  Eric makes the adjustment as follows.

![image](https://github.com/nericguyen/debugging-scenario/assets/149546505/f2099670-46ac-40c6-9e25-2b59991d724e)

Eric types `bash setRanking.sh TournamentResults` again and pees his pants a little when he sees the correct output displayed below:

![image](https://github.com/nericguyen/debugging-scenario/assets/149546505/647fdaa3-500f-4ede-b3b7-184b89d55e85)

The break statement ensures that Eric's code exits that for loop after adding the player to the rankings, and fortunately his code contained to other bugs!
He submits his PA on time and receives an A+ and sleeps promptly afterward.

# Reflection

I can not believe the quarter is almost over, I am getting emotional.  I think one of the coolest things I learned this quarter was actually from the most recent lab.
Github is still very unfamiliar to me, so learning about the Issues and Pull Requests was really cool.  Essentially, they are comments that people can leave on public
codes or suggestions for fixing code!  I think it's really interactive, and right after I left an issue on an old high school friend's repository and then we chatted
a bit on iMessage after he saw my issue.  I guess the main thing I learned though from this second half of the quarter is that you learn by doing! You can't learn everything by just reading, and you gotta be hands-on with your learning.
