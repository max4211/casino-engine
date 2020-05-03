/**
 * Package that contains all the UI classes that are needed to run a card game.
 * The game itself is controlled through GameView, which essentially serves as a UI controller.
 * From GameView, method calls trickle into smaller components that are composed within the class, such as PlayerView or BetView.
 * All classes implement the StylizedNode interface, creating "mutable JavaFX nodes" which can be obtained via a getView() call and have CSS IDs set to their class name.
 * @author Eric Doppelt
 * */
package UI.GameView;