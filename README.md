# CPSC 210 Personal Project: Beehive Directory
This application will contain a directoy used to manage individual hives. The hives will have an arbitrary number of hives with fields including name, location, color, primary pollen source, secondary pollen source, and notes unique to each hive. In a directory, one can add, remove, view, edit, sort, or obtain metrics. Individuals will be able to sort by pollen source in the order of primary, secondary, and irrelevant. Individuals may also get the metrics of the specifications of hives to better improve marketing techniques and resource allocation in practical farm markets.

This project is intended to be used by local Canadian farm markets, but it can also be used by hobby beekeepers or small businesses.

This project interests me because of my experience in beekeeping where I volunteered to manage 2 hives. This experience had me wondering how local markets manage to provide a wide variety of honey despite being situated in one location. With my basic knowledge of beekeeping, I was inspired develop a directory for beehives.


## User Stories
- As a user, I want to be able to **add** hives and apply any required specifications including name and location.
-	As a user, I want to be able to **remove** hives.
-	As a user, I want to **view** all hives.

-	As a user, I want to specify or overwrite the **name**, **location**, **color**, **primary pollen source**, **secondary pollen source**, and **additional notes** of a hive.

- As a user, I want to **sort by a specified pollen source** in the order of primary, secondary, and irrelevant.
- As a user, I want to **obtain metrics of location, color, primary pollen sources, and secondary pollen sources** where the metric is the frequency of which each distinct value of each field is present in a directory.

- As a user, I want to have the option to **save hives** to file, if chosen, from the main directory.
- As a user, I want to have the option to **load hives** to file, if chosen, from the main directory.
- As a user, I want to prompt the user to **save before quitting** the program.

## Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the `add` button found in the header, entering a name and then a location where a hive will appear. Repeat this an arbitrary number of times (this must be repeated at least 11 times to trigger a scrollbar. You can do this quickly by spamming `add`, `enter` key, `enter` key where the name and location will default to a value of `Unspecified`).
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by loading the program by clicking the `load` button, clicking the `sort` button, selecting `pollen source`, entering `Lavender`, and observing the reordered list from primary pollen, secondary pollen, to irrelevant. The pollen sources can be found below the name of the hive in the format of:
> Pollens: \<primary pollen source\>; \<secondary pollen source\>
- You can locate my visual component by finding a logo on the top lefthand corner of the frame (in the header) upon first running the program (the main directory). The same logo can also be observed in the window icon.
- You can save the state of my application by clicking the `save` button found in the header, a message will appear to inform the user about the successful save. Saving can also be performed by clicking `yes` to the prompt after attempting to close the main window. To ensure that all fields have been correctly saved, you can edit the fields of a hive by clicking the `edit` button corresponding to the hive. Edit the fields by entering a unique value and clicking `confirm` <ins>for each field</ins>; exit the editting window, click the `save` button, and observe the change in `hives.json` or after re-running the program (re-run the program and click the `load` button).
- You can reload the state of my application by clicking the `load` button found in the header, a message will appear to inform the user about the successful load; the program will now display all hives that were previously saved. To ensure that all fields have been correctly loaded, you can click the edit button of a hive and compare the field value to the `hives.json` value.

*NOTE WHEN EDITING A HIVE: You cannot specify a secondary pollen source until you have specified a primary pollen source. Likewise, you cannot set a primary pollen source to* `Unspecified` *until you have changed the secondary pollen source to* `Unspecified`*.*

## Phase 4: Task 2
## Events performed
- Loaded hives.
- Edited all fields of Hive 1.
- Collected the metrics of all hives.

### Sample
> The color of Final Hive has been changed to other
> 
> The primary pollen source of Final Hive has been changed to Unspecified
> 
> The secondary pollen source of Final Hive has been changed to Unspecified
> 
> The notes of Final Hive has been changed to Unspecified
> 
> Loaded Final Hive with the location of Iceland to the directory.
> 
> The color of Lola's Hive has been changed to other
> 
> The primary pollen source of Lola's Hive has been changed to Lavender
> 
> The secondary pollen source of Lola's Hive has been changed to Unspecified
> 
> The notes of Lola's Hive has been changed to Need pest control ASAP.
> 
> Loaded Lola's Hive with the location of BC to the directory.
> 
> The color of Hive1 has been changed to light
> 
> The primary pollen source of Hive1 has been changed to Wild flower
> 
> The secondary pollen source of Hive1 has been changed to Lavender
> 
> The notes of Hive1 has been changed to First hive to exist.
> 
> Loaded Hive1 with the location of Alberta to the directory.
> 
> The color of Kirb has been changed to golden
> 
> The primary pollen source of Kirb has been changed to Blue Orchid
> 
> The secondary pollen source of Kirb has been changed to Blossom
> 
> The notes of Kirb has been changed to Reference!?!?
> 
> Loaded Kirb with the location of Japan to the directory.
> 
> The name of Final Hive has been changed to Edited Name
> 
> The location of Edited Name has been changed to Edited Location
> 
> The color of Edited Name has been changed to golden
> 
> The primary pollen source of Edited Name has been changed to Edited Primary Pollen
> 
> The secondary pollen source of Edited Name has been changed to Edited Secondary Pollen
> 
> The notes of Edited Name has been changed to Edited Notes
> 
> The metrics of hives were collected for Location
> 
> The metrics of hives were collected for Color
> 
> The metrics of hives were collected for Primary Pollen Source
> 
> The metrics of hives were collected for Secondary Pollen Source

## Phase 4: Task 3
If I had more time to work on this project, I would have taken the time to refactor my GUI to use the Singleton pattern for my `DirectoryFrame`. My `DirectoryFrame` has 4 associations which is unideal as 3 of those 4 associations (`HiveField`, `EditFrame`, `DirectoryWindow`) only have an association to access a single method or to simply pass the directory object to other classes. If I had implemented the Singleton pattern for `DirectoryFrame`, my coupling would have been reduced; the Singleton pattern is also appropriate here since there is only one instance of `DirectoryFrame` throughout the entire program. 

Additionally, I would have moved some functionality methods found in `DirectoryFrame` to a different class as the `DirectoryFrame` is supposed to represent the GUI for the main directory and should ideally not include any functionality methods. Moving functionality methods to a different class would result in higher cohesion thus making my program better follow the ‘Single responsibility principle.’ I could have also moved some common methods declared in the `Frames` interface to an abstract class as the classes which implement this interface have near identical method bodies; since classes can only extend one, and only one, class, I was unable to fulfill this design choice.


## Sites Used
- [Wikimedia](https://commons.wikimedia.org/wiki/File:OpenMoji-black_1F41D.svg) was used under Creative Commons License for logo.
- Some code from [Bro Code JFrame tutorial](https://youtu.be/Kmgo00avvEw?si=dPZW_kdZlPOpXF3a) was used.
- StackOverflow citations can be found in the comments of the code (either beneath the specifications or in the class-level comment).
