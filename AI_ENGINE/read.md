# Create a turn based game AI Engine


## Idea
### Given any game chess, sudoku, etc.. this engine can able to play
### This repo will contain code With/without using following concepts:-
 * extensible
 * modular
 * readability
 * coretness 

##  Without solid
    APIs to be exposed :-
    1) play game/start a game       (Board start())
    2) make move                    (void move(Board board, Player player, Move move))
    3) get result                   (GameResult isComplete())
    Creating required objects on the fly which happen in interview
    Each Object has some idea encapsulated in them
        1) Board class - A place where pieces can move in turn based game
        2) Player is user who participated in game
        3) Move 
    Start function that assign a board on which player will play
    move does action on board but return nothing
    isComplete return the gameresult in which bunch of if else condition will be written

    So now using Tic Tac Toe as an example we design this game engine
        1) write the logic in isComplete() function for tic tac toe instance
            - win
            - loose
            - draw
        2) Thought process/getting sence of bad/redudant code which can be improves
            - writting same lines of code means need to improve (think where are the things can be improved on a high level)
            - problem - how should be GameResult object is constructed given its state is well known to isComplete() function which is a problem
            - all classes and object in same file which leads to tight coupling 
                -> (move all classes in different files which leads to flexible in changing different classes individually)
                -> also it is easy to find which code is where now
                ->(APIs are endpoints that other developers can use to interact with your system. There is no other way to connect with your system except using API)
                    - by clearly defining rules for every interaction through API's, you want to restrict access to data structure.
                    - using this api we restrict the amount of interactions that different systems can have with my internal system.
            Phase 2: more extensible code than previous 
                - seperate and decide which class have similar meaning and accoridng to that place them in same folder
                - Like here there are 4 folders:-
                    -> api:  main contains all the function which is the way other systems can interact with my system-> so we can say it as GamesEngine
                    -> boards: TicTacToe is a type of board its not specific to a game(telling status of each playable game it either can be tictactoe or chess or other). A board is like a blue-print for other boards like TicTacToe, Chess
                    -> game: it store board and moves that are possible on the board, gamesresult also comes on this package so all the information about game will be here
                    -> user: player can be here but it is int game (its not anti pattern) as we store statistics of the game or profile etc in there.

                    Where should Cell be defined?
                        -> can every boards have cells -> i dont know or No (then think)
                        -> If boards can exist without cells then we should not put cells in boards. we should put it in specific TicTacToe board
            Phase 3: now creating logic for move and see what is problem when not using SOLID principles
                - 

            Debug phase
                - It will throw nullpointer exception as firstCharecter can be null and it not handled.
                - also handle diag and revdiag
                - print board state after every move


##  SOLID Principle

###     S-Single Responsibility Principle
####        Ex- Unix programming, single command do only one thing but do it very well and can do many things when small composable functions put together
###     O-Open Closed Principle
###     L-Liskov Substitution Principle
###     I-Interface Segregation Principle
###     D-Dependency Segregation Principle


### Now Implementing SOLID one by one/case by case in AI_ENGINE

####        Step 1 -> seperate the classes and objects in different file.
####        Step 2 -> Implementing Single responsibility principle in GameEngine first.
