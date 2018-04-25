## Structure of the communication array
| a| a| a| a| a| a|
|---------------------|--------------------------------|----------------------------|-------------------------|-------------------------|-----|
|What to build (0-99) | Positions of trees (1000-2999) | Where to attack (3000-4999)|Unit -> Archon (10 long) | Unit -> Archon (10 long)|...  |

## Encoding for the broadcast from the unit to the archon
| 0| 1| 2| 3| 4| 5|
|---------------------|--------------------------------|----------------------------|-------------------------|-------------------------|-----|
|Unit type | X-coordinate | Y-coordinate | Number of enemies within sensor radius | |...  |

## Unit types
| 0| 1| 2| 3| 4| 5|
|---------------------|--------------------------------|----------------------------|-------------------------|-------------------------|-----|
| Archon | Gardener | Soldier | Tank | Scout | Lumberjack |
