# Use Cases
> By Eric Doppelt and Max Smith

> Write at least 8 use cases per person on your team that describe specific features you expect to complete. Focus on those features you plan to complete during the first two sprints (i.e., the next two weeks — the project's highest priority features). Note, they can be similar to those given in the assignment specification, but should include more details based on your project's goals.


#### Case 1 - Select Game
Upon startup, the User is greeted with a LobbyView (that can also be entered via the MenuView at any time). The LobbyView allows the user to select a game (by picking an icon) which prompts a FileChooser for XML data within that genre. The file is then sent off to an XML Parsing module for GameObject creation. 

#### Case 2 - Create Game
An XML Parsing module translates the game (data file) the user selected into an ecosystem of interdependent objects that form the Game MVC (model/logic, view/interface, and controller/medium). The XML data files are rich with preferences which drive substitution in the GameCreation. Classes such as a Deck and HandEvaluator are configured based on this information.


#### Case 3 - Begin Game
The stages of a game are determined inside of XML files with actions and sequencing data tags. To start the game, all players must perform an entry action, which is to place a bet. At the moment, bets are Generic (the `Bet` acts on random cards from the dealer) or Specific (a `Bet` that selects cards, such as in Roulette). Additionally, these bets are subject to limits (minimum, maximum and quanity) dictated by the XML file. For example, to start a Blackjack-esque game, a Generic Bet must be placed which results in Cards being accepted.

#### Case 4 - Dealer Action
The next component that makes a round in a game operate are Dealer Actions (alternating with player actions). A dealer has one purpose: to distribute cards. These card distributions are either of type communal (goes to all) or individual (single player gets them). Either way, the dealer uses their deck (configured from a data file) to select the cards. For example, a Poker game starts with the distribution of two individual cards to all players.

#### Case 5 - Determine Player to Act
When it is time for a player to act, there is a GameLoop internal to the Table which determines action order. The GameLoop iterates through the Table's List of Player objects and their associated Bets. The first Bet encountered that is still active to the round (determined by a boolean indicative of a player having posted the entry minimum and not attained a losing playerHand thus far) is then operated on.

#### Case 6 - Player Performs Action
Once a player to act has been identified, said player is presented with a list of actions dependent on the GameState. The initial action filter will be based on an EarlyGame, MidGame, PostGame actions. However, a later stage of error handling (hopefully in the second sprint) will seek to validate actions selected (e.g. shouldn't be able to split non identical cards, or check when someone has raised before you), possibly not even presenting these actions to the user. Additionally, note that Actions are performed on a Bet (e.g. a Check action does nothing, a `Fold` action flags the Bet to be garbage collected).

#### Case 7 - Hand Evaluation
Everytime a PlayerAction is performed, the Bet (encapsulation of a Wager and Hand) is then reevaluated and classified   according to the data files. The playerHand is first attempted to be classified as a Losing Hand, and if it satisfies one of those conditions, the Bet has a boolean value that is adjusted to eliminate the player from the round (or assign the ClassifiedHand value as Losing). Next, it is evaluated in decreasing order of WinningHands (once a WinningHand is classified, the classification algorithm is done).

#### Case 8 - Garbage Collection
After each PlayerAction, a GarbageCollection algorithm in the Table goes through all Bets and evaluates their current standing in the Game/Round. If a Bet falls under the Losing Hand category, it will continue to exist in the rounds. However, the next time the Bet is given an opportunity to perform an Action, it will be skipped over (only active Bets can perform actions).

#### Case 9 - Bet Evaluation
At the termination of the final round in a game, the Bet's are evaluated against their preconfigured (through XML) competitive group (e.g. Group is poker and Adversary is blackjack). The BetEvaluation module is given a Collection of Bet objects, iterates through them using a HandComparator (which has a single compareTo command) and assigns a final Win/Loss value to each playerHand.  Additionally, the BetEvaluation assigns a multiplier to all Bets that Won based on the PayoffMultiplier tag in the XML file.

#### Case 10 - Updating Bankrolls
Once all hands have been evaluated (and one - or more if they tie - winner exists), the Table will exectute a cashBet() method on the entire List of Players' Bets, which then updates their bankroll based on the Win/Loss status of each bet and their value (multipliers adjusted in BetEvaluation stage).

#### Case 11 - Updating Group Bankrolls
The earlier example given is based on a adversarial bet structure, where it is one person versus the dealer. In a poker scenario, the bet payout is different. After the hands have been marked as Winners and Losers, Losing hands values are added to a escrow value. After all losing hands have been cashed, the winners then split the escrow amongst themselves evenly.

#### Case 12 - PlayerView
When inside of gameplay, the game is rendered in a series of nested  JavaFX elements. One of the core elements is the PlayerView, which itself has nested Bet View (s), ActionBoxView, Bankroll, and Name. These elements are rendered from information in the Model (backend), and change based on which player is active (as dictated by the Table).

#### Case 13 - Creating a New Game
Per the design goal of making the program as data driven as possible, creating a new game should only be limited by Java code available to support the game. As such, new game rules are trivial to add (by modifying tags) and can result in novel combinations of game logic. For example, one could create a BlackJack style game (adversary competitive circle) where there exists a pair bet with a payoff of 3:1 by adding this to the WinningHands hierarchy tag.

#### Case 14 - Modifying the Deck
The Deck objects are also completely data driven, and each card is explicitly defined inside of a Deck XML file (where each card has two properties, Value and Suit). To create a deck without queens, one could just duplicate the StandardDeck XML file and remove all reference to queens. Likewise, one could create a new suit called oranges and change all of the hearts to oranges. Evaluation of hands is still the same, as it only revolves around relationships between card parameters (for example a flush is when all card parameters are the same - and all cards could be of a made up suit called orange).

#### Case 15 - Playing at Multiple Tables
By design, a Table is an independent entity driving a game and has all of the Objects it needs to function without managerial oversight. By keeping the LobbyView accessible, multiple games can be played concurrently by simply selecting another GameIcon (indirect XML selection) at startup.

#### Case 16 - Toggling Display to Dark Mode
Through the MenuView, the user has the ability to change preferences regarding the Game visual rendering. They can select from multiple color pallette themes (for example DarkMode) which results in the swapping in a new CSS file, hence changing all colors.