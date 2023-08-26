# Java Boat Racing Game

Welcome to the Java Boat Racing Game! This project simulates an exciting boat racing game where players compete to reach the finish line first while navigating through various challenges and chance events on the track.

## Introduction
This project aims to involve object-oriented concepts to represent different aspects of the game, such as players, tiles, dice, and chance events. The game involves strategy, luck, and decision-making as players navigate the track filled with currents, traps, and chance tiles.

## Features
#### Basic Features
- Simulates a boat racing game with turns, challenges, and strategic choices.
- Incorporates special chance event tiles that introduce an element of unpredictability.
- Supports two-player gameplay, allowing friendly competition and fun interactions.
- Includes an intuitive menu system for easy navigation and game initiation.
- Provides a leaderboard feature to track top players based on their performance.


#### Chance Event Tiles
These tiles trigger different events that can either help or hinder a player's progress. 

| Event             | Description                                                       |
|-------------------|-------------------------------------------------------------------|
| **Gambit**        | Guess a number, and if correct, your boat advances significantly. |
| **Cannon**        | Roll a dice to move the opponent's boat backward.                 |
| **Vortex**        | Your boat gets stuck temporarily, causing you to miss a turn.     |
| **Lightning Zap** | Swap positions with your opponent's boat.                       |



## Examples
#### Main Menu
``` plaintext
              |    |    |
             )_)  )_)  )_)
            )___))___))___)\      [ BOAT RACING GAME ]
           )____)____)_____)\\
         _____|____|____|____\\\___
---------\\                   /------------------------
  ^^^^^ ^^^^^^^^^^^^^^^^^^^^^ ^^ ^^^^^^  ^^^^^^^^^^^
    ^^^^      ^^^^     ^^^    ^^^^^^    ^^^^^   ^^

1. Start Game
2. Instructions
3. Show Leaderboard
4. Exit
```

#### Leaderboard
``` plaintext
        *Top 5 Highscores*
================================
    Name                   Score     
================================
1   sumito                 25        
2   greta                  27        
3   merlyn                 27        
4   donny                  28        
5   shaq                   31        
================================
```

#### Gameplay
```
ROUND 23
Player 2's (McGee) Turn
Press [Enter] to roll the dice

Player 2 has rolled a 2

McGee's boat lands on a Chance tile 

An apparition appears before McGee, offering up a gambit...
"Guess a number and roll the dice", it says.

If the guess is right, advance by twice the dice value. If not, move backwards accordingly.
Guess [1 - 6]: 4
The dice value is... 2!

> "Better luck next time..." - The apparition pushes McGee backwards by 4 tiles.

P2 Position: 87
====================================================================================================
     **          T         T T TC  C T*   CT   T    *    T C CCT*      C   CC* C 1 C   2   * T    * 
====================================================================================================
```
