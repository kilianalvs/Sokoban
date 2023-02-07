                # Sokoban

The [Sokoban](https://en.wikipedia.org/wiki/Sokoban) is a puzzle game in which the player has to move all boxes to their storage locations

## Rules

The player can move horizontally and vertically, but never through a wall or a box.

They can only push boxes. And, the boxes can not be moved on a square with another box or a wall.

The number of boxes equals the number of storage locations.

The puzzle is solved when all boxes are placed at storage locations.

A map is defined by a rectangle (W by H) of squares.

Each square has a state :
- Wall: nothing can be moved on this squares
- Floor: a free squares on which the player or a box can be moved
- Player: the current player position
- Box: a box to move to a storage
- Storage: a floor square marked as destination for a box
- Stored Box: a box on a storage location



## 1. Map

Every class of this part must be implemented in the package `com.gitlab.sokoban.domain.model`.

### 1.1. Positions and squares

A position is defined by two integers X and Y. These coordinates cannot be changed once created

A square is defined by a position and a state:
- Player
- Wall
- Empty
- Box
- Storage
- BoxStored

A square cannot be changed after its creation

1. Create the enum `State`
2. Create the class `Position` and `Square`
3. Two positions should be equal if the Xs and the Ys are equal. Create a test to check this property
4. Two squares should be equal if the position and the state are equal. Create a test to check this property

### 1.2. Map

A map contains a list of squares.

It can say if the square at a given position is a wall or not

It can say if a position is in the map or not. For example, on the map of 4x4, the position 12;34 is not inside the map. But the position 2;2 is inside the map.

1. Create the class `Map`. A map is created from a size and a list of squares
2. Create the method `isWall`. It returns true if the given position is a wall. False otherwise
3. Create the method `inside`. It returns true if the given position is in its boundaries. False otherwise
4. Two maps are equal if their sizes and all their squares are equal.

### 1.2. Generator

A map is generated from a text file containing the state of each square, as below:
```
    #####
    #   #
    #$  #
  ###  $##
  #  $ $ #
### # ## #   ######
#   # ## #####  ..#
# $  $          ..#
##### ### #@##  ..#
    #     #########
    #######
```

`#` : wall
`$` : box
`.` : storage
`@` : player
` `: floor

For now, we are building the map. We will only focus on the walls and floors.


1. Create a class `Utils` which will be used to convert a string to a list of squares
2. Create the static method `toTiles`. It takes a `String` as an input and returns a list of `Square`
3. Create a class `MapBuilder` with a static method `build`. It takes a `String` as input and returns a `Map`


## 2. The game

Once we have our map, we can try to display it


### 2.1. Sokoban

A game is represented by an object of the class `Sokoban` which will contain the map, the player position, the boxes, etc.

1. Create a class `Sokoban` in the package `domain.model`
2. Add an `map` attribute which represents the current map

### 2.2. Getting the sokoban

We need to access to the current Sokoban game to display it. The current game could be stored in a database, a file, in-memory, etc. And, the code should be agnostic of the way we store data

1. Create an interface `GameResources` in the package `domain.features`
2. Add a method `get` that returns a `Sokoban`
3. Create a class `InMemoryGameResources` inheriting from `GameResources` in the package `infra`
4. Implements the `InMemoryGameResources.get` method in order to create a `Sokoban` from the map above (chapter 1.2)
5. Remove the current method `index` in `FrontDispatcher` and uncomment the other implementation
6. Run the application and open the url `localhost:8080` in a browser

In order to store our game in a database or a file, we just need to implement another class inheriting from `GameResources`. The rest of the code will not be impacted.

### 2.3. Rules

Now, we need to implement the rules in `Sokoban`

1. Add a `player` attribute which represents the current position of a player
2. Add a method `move` which take a direction (create an enum for that) and moves the player in the given direction
3. Add the rule : *the player cannot move on a wall*
4. Add an attribute `boxes` which contains the list of all boxes on the map
5. Add the rule : *the player can push a box*. The box should change its position
6. Add the rule : *the player cannot push a box against a wall*

### 2.4. Playing

1. Create a class `Play` in the package `domain.features`
2. Add an attribute `GameResources`
3. Add a method `invoke` that takes a direction as parameter and returns the updated `Sokoban` game
4. Implements the method `invoke` as following:
   - Get the sokoban in the resource
   - Update the sokoban with the given move
   - Store the sokoban in the resource
