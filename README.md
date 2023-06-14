# Robot Cleaner App

# Design
The rectangular factory floor is represented by the `Terrain`(x, y) class
The core class is the `Robot` class which accepts a sequence of commands 
via `handleInputCommands` updating its `Position` as a side effect.
Most business logic is encapsulated inside the `Robot` class
The `Position` class represents the `Robot` position on the floor, including its
(x,y) coordinates plus its orientation (North, South, East, West). It is an immutable
class, a new `Position` is obtained creating a new one from the old one.
A Position is made up of a `Coords` class (aka coordinates) and a `Heading` class (aka orientation).
The commands a Robot can process are represented by the `Command` class and there are
3 hardcoded instances defined upfront according to requirements: MoveForward, TurnRight,
TurnLeft.
Input parsing and results printing logic is located inside the `io` package.

Any error in the input makes the program throw an exception. The errors could be
a formatting error, an erroneous initial position for any robot, or an invalid sequence of
commands, either because the command is unrecognized or because the robot ends up outside the floor limits.
# Build 
```
./gradlew clean build
```

# Testing
```
./gradlew test
```

# Run 
```
./gradlew run --args="<filepath>"
```