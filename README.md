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
All user stories are realized.
- To add multiple Xs to Y: Click the add button found in the header, enter a name and then a location then a hive will appear. Repeat this an arbitrary number of times (this must be repeated at least 11 times to trigger a scrollbar).
- First related action: To sort, load the program, click the sort button, click 'pollen source,' and enter 'Lavender;' hives will now be sorted by primary pollen source, secondar pollen source, and irrelevant. Pollen sources can be viewed beneath the name of the hive, the format for pollen source is:
> Pollens: *primary pollen source*; *secondary pollen source*
- Second related action: To edit a hive, load the program, click edit of any hive, select a field, enter a new name, click confirm, exit out of the edit frame, re-edit the previously editted hive, and observe the changed field. Note that you cannot edit a secondary pollen source until you have specified a primary pollen source.
- Visual component: Can be found at the top lefthand corner of the frame when first loading (the main directory).
- Save state: Click the save button found in the header, a message will appear to confirm the save.
- Load state: Click the load button found in the header, a message will appear to confirm a successful load; the program will now display all hives that were loaded.

## Sites Used
- [Wikimedia](https://commons.wikimedia.org/wiki/File:OpenMoji-black_1F41D.svg) was used under Creative Commons License for logo.
- Some code from [Bro Code JFrame tutorial](https://youtu.be/Kmgo00avvEw?si=dPZW_kdZlPOpXF3a) was used.
