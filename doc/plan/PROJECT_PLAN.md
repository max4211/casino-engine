# Project Plan
> By Eric Doppelt and Max Smith

> Write your plan for how to manage and prioritize the project in a single file, PROJECT_PLAN.md, that describes the APIs each team member plans to take primary and secondary responsibility for and a rough timeline of how the team will order the work to complete the program. Specifically, each person should take responsibility for specific features and use cases they intend to work on during each Sprint. This requires the team to agree on the feature priorities and set goals for what to complete at the end of each Sprint (i.e., each week).

### Responsibilities

Due to the complexity of this project, we have spent an extensive amount of time making our design goal to be data driven. With this in mind, we want to cater to our areas of expertise that we have developed in past projects, while also allowing each other to try something new.

With this in mind, Eric will be handling frontend mainly, but he will be working on minor features in the backend. Max, on the other playerHand, will be a back end specialist but will support some front end work.

Since there are only two people in our group, both of which live near COVID-19 outbreaks, we recognize that the following schedule may have to change a bit as we progress. But in the meantime, here is our general plan divided into modules and their APIs:

Eric will focus mainly on the Internal and External frontend, while also doing some all-purpose external backend work relating to XML and the classes it interfaces with most.

Primary:
* `GameView`
* `XMLParsing`
* `Card Distribution`
* `LobbyView`

Secondary:
* `Hand Evaluator`
* `Table`
* `Action Generator`



Max will focus primarily on APIs associated with backend/model and controller logic, plus support for frontend/data parsing.

Primary
* `Table`
* `Bet Evaluator`
* `Hand Evaluator`
* `Bet Comparison`
* `Action Generator`

Secondary:
* `LobbyView`
* `XML Parser`


### Sprint Backlog
> Full list of intended features

Submitted in Survey
* Dark Mode
* Game Area Editor (settings/preferences)
* Running score (of bankroll)
* Game Analytics (history)
* Undo/Redo turns


### Sprint 1

*Goals:*
Focus on strong design while implementing the basic functionality for one game as determined in our `DESIGN_PLAN.md`.

Implement beta versions of all modules (except `LobbyView`) for one basic card game: blackjack.

*Features:*

Eric:
1. GameView and its subclasses (BetView, WagerView, etc.)
2. XMLWriter and XMLReader
3. Card Distribution (Card, Deck, Dealer)

Max:
1. Table and associated classes (Players, Hands)
2. Evaluation suite (Bet and Hand)
3. Comparison suite (Hand)
4. Action generator
5. Adversary strategy profile

### Sprint 2
*Goals:* 
Extend basic design to *all* card-based game types including Five Card Draw and Texas Hold'em.

Further the frontend development with additions of Lobby and Dark Mode.

Iron out the controller/model separation, and determine more abstractions to improve data driven design and minimze the evil *hard coding*.

*Features:*

Eric:
1. Implement Dark Mode settings in the UI.
2. Add a LobbyView with UI access to XML.
3. Spend time finalizing nuances of GameView, since realistically it will take more than just one week.
4. Wager bets and error popups

Max:
1. Filter and error handle player actions
2. Add new Actions for new Games
3. Add new Decks for new Games
4. Add new Hand Classifier to Poker Games
5. Group strategy profile

### Complete

*Goals:*
Potential game extension to encompass Roulette. Refactor code to allow for seamless extension via data files.

Focus on real extensions that are not just new games, including Game Area Editor and potentially Stat Keeping.

Note that for the last week, we believe it would be best to work together. It is hard to predict whether the frontend or backend will require more time, so we want to be flexible with how we want to divert resources. With that said, the following features will need to be done:

*Features:*
1. Finishing touches on main design/implementation
2. Game Area Editor (more Eric)
3. Game Analytics (more Max)
4. Refactoring
5. Emhpasis on adding new flexibility through preferences to the game (such as dealer AI strategy)
