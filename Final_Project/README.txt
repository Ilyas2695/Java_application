=============
 Readme file
=============


=========================
 Supported Java Versions
=========================
The program was made on JDK 19, so it is runnable on all java versions


=========
 Running
=========
When the program starts, the user sees the landing page with login, registration, reset password and exit options. 
User has to choose one of these options to use a program. 
After that, user will see the main menu, where more options are available, such as: purchase history, game, market etc.


======
 Note
======
There are no databases created automatically, so it is highly recommended not to delete the databases.


========
 Access
========
The user needs to know his/her username and password, or register to use the program. If the user forgot the password, it is possible to reset it, using the email.


============
 How to use
============
The main feature of the program is that it has leaderboards and game, game is a quiz that doubles the prize with every right answer and resets the prize if the answer is wrong.
Program is very descriptive and easy to use, every part is understandable and provides the instructions how to use if the user has done something wrong.
The main goal of this program is to encourage users to learn the history of the UK, because the questions are only about the UK. The more user knows the more he/she can earn.
The main currency of this program is a Virtual Reality Coin (VRC), it is needed to purchase the items that vary from a mag to a car.
The leaderboards can be accessed through the "my profile" option in the main menu


====================
 How the code works
====================
The databases are not appended with the new data (except method "register" in UserData.java file, this method appends), they are always rewritten when the new data is added. The databases are not created automatically, so please do not delete them. You can change their names using the file called "FileNames.java".
The main class and method do not have any specific features, except the main program loop, everything important is in the "Rifter.java" file.
Program uses enums to better understand what index is used for a specific data.
You will see that in the .csv files each line starts with the username, this is needed to identify where to add data of a particular user.
Some .csv files are returned as the lists to delete or add data, only "userBalance.csv" is returned as a hash map, because it contains username as a key and VRC balance as a value.


===========
 Deviation
===========
This program does not contain any GUI parts or admin users. The program is only for the usual users. The only data that user provides is email, username and password, no credit cards are required.


===============
 Please, enjoy
===============
