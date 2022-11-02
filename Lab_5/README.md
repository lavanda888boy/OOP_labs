# MULTI-LEVEL PARKING SIMULATION (PART 4)

At the final stage of developing my simulation I made it autonomous by introducing different setup parameters into the `Simulation` class.
They include the maximum capacity of a parking level, the percentage of different types of parking places, the chances of appearing for a certain type of car etc.
I also added the concept of handling the waiting cars which cannot be parked at the moment and have to wait until there is a free parking place for them (if there are too many cars waiting then the income flow is decreased or increased otherwise; at the beginning more cars are entering the parking than leaving it).
Moreover, the simulation calculates total amount of money collected by the parking. I used it as the main parameter for the further analysis.

The `PaymentTerminal` class handles all the cash cases; depending on the time the driver spends at the parking he has to pay a certain amount of cash. I assumed it to be 5$ for 15-60 minutes staying and 15$ for more than one hour of time. 

Here are some of my conclusions(`IMPORTANT:` Basic conditions are when 85% of places are dedicated to `simple` cars, 10% to `electric` cars and 5% for `disability` cars; `income coefficient` is 0.7; `average time spent` is 60 minutes all the calculations are made within a `human minute`):
  1. Average cash collected in default conditions is `30$`;
  2. By increasing the income coefficient up to `0.9` and decreasing the average time spent down to `40 minutes`, we increase the cash amount by `10$`;
  3. Proceeding in the opposite way and making the income coefficient `0.6` and time spent `80 minutes`, we start to make even more cash,- `50$`;
  4. Finally, using default conditions, but dedicating `90%` of all parking places to simple cars, we make our profit equal `70$`;
  
These are just few conclusions we can make according to my simulation and there are plenty of variations for all its parameters but I tried to focus on the most important ones.
