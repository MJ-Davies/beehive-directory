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

# Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the add button found in the header, entering a name and then a location where a hive will appear. Repeat this an arbitrary number of times (this must be repeated at least 11 times to trigger a scrollbar).
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by...
- You can locate my visual component by finding a logo on the top lefthand corner of the frame (in the header) upon first loading the program (the main directory). The same logo can also be observed in the window icon.
- You can save the state of my application by clicking the save button found in the header, a message will appear to confirm the save. This can also be performed by clicking yes to the prompt after attempting to close the program.
- You can reload the state of my application by clicking the load button found in the header, a message will appear to confirm a successful load; the program will now display all hives that were loaded.

## Sites Used
- [Wikimedia](https://commons.wikimedia.org/wiki/File:OpenMoji-black_1F41D.svg) was used under Creative Commons License for logo.
- Some code from [Bro Code JFrame tutorial](https://youtu.be/Kmgo00avvEw?si=dPZW_kdZlPOpXF3a) was used.
