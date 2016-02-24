# Tic Tac Toe (Noughts and Crosses) Game
 
[![Build Status](https://travis-ci.org/RabeaGleissner/java-tic-tac-toe.svg?branch=master)](https://travis-ci.org/RabeaGleissner/java-tic-tac-toe)

You need to have Maven installed to run the application.

#### How to run the game

```
git clone https://github.com/RabeaGleissner/java-tic-tac-toe.git

cd java-tic-tac-toe

mvn package

```
To play the game in the console, run this command:

`java -cp target/tic-tac-toe-1.0-SNAPSHOT.jar de.rabea.ConsoleMain`

To play the game with a graphical userinterface, use this command:

`java -cp target/tic-tac-toe-1.0-SNAPSHOT.jar de.rabea.GuiMain`


#### How to run the tests

`cd` into the java-tic-tac-toe folder in your terminal and type
 - `mvn test` to run tests while excluding the slower running tests
 - `mvn integration-test` to run all tests

