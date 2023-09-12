# MULTI-LEVEL PARKING SIMULATION (PART 5 - REFACTORING)

<p align = justify> At the fifth stage of developing my parking simulation I applied, first of all, <b><i>SOLID</i></b> principles to make my code look better and be more precise. Also I restructured my project so that it corresponds to the <b><i>MVP</i></b> architectural pattern. New classes therefore were introduced and divided into three main categories (<b><i>Model, View, Presenter</i></b>) and two additional (<b><i>ConnectionInterfaces, Repository</i></b>).</p>

<p align = justify> For almost all the classes I created model classes, then assigned a controller if it was necessary and then added a view. Some of the controllers are more "global" and contain other low-level controllers. The full file structure will be presented on the screenshot below. </p>

<p align = center> <img src=https://github.com/lavanda888boy/OOP_labs_UTM/blob/main/Lab_6/file_structure.png /> </p>
