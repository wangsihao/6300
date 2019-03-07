1.This requirement does not directly affect the design.
2.To realize this requirement, I added classes "User","Administrator", "Player" and "Start", where user is a superclass of administrator and player. The Start class will allow the user to choose which mode to start in. For the class User, attribute "Name", "Username" are added. 
3.To realize this requirement, I added the operations "addPlayers()", "addCryptogram" and "editCryptogram" to class "Administrators", which will add players to the system, operation addCryptogram and editCryptogram will add/edit cryptograms to the system. Player will update the database with the newly added player. Cryptogram will update the database with the newly added cryptogram.
4.To realize this requirement, I added the operations "chooseCryptogram()", "solveCryptogram()", "seeSolvedCryptogram()" and "viewPlayerListRating()" to class "Player", which will allow players to  (1) choose a cryptogram to solve, (2) solve cryptograms, (3) see previously solved cryptograms, and (4) view the list of player ratings.
5.To realize this requirement, the user class has attributes of name and unique name.
6.To realize this requirement, the operation "isUsernameUnique()" is added to class "User", which will check to see if the username is unique, and valid, and if so it will be added to the database.
7.To realize this requirement, the operation "viewPlayerListRating"  is added to class "User", which allow user to view the player list.
8.To realize this requirement, the player class has attribute cryptogramRating.
9.To realize this requirement, attribute "identifierGenerator" was assigned to class ExternalWebService, it has an operation called "sendConfirmationMessage", which can send a confirmation message and generate an unique identifier to the cryptogram.
10.To realize this requirement, operation "requestRating()" and "sendRating()" was added to the ExternalWebService class, it can request player list rating information from the database and send the list to player.
11.To realize this requirement, operation "requestCryptogram()" and "sendCryptogram" was added to the ExternalWebService class, it can request cryptogram list from the database and send the list to the player.
12.To realize this requirement, a class called "Cryptogram" was added. Three attributes, "solutionPhrase", "encodingPhrase" and "identifier" were added to Tourney. Cryptogram will update the database with the newly added cryptogram.
13.To realize this requirement, attributes "solutionPhrase", "encodePhrase", "identifier" were assigned to class cryptogram.
14.To realize this requirement, operations "sendResult()", "requestIdentifier()" were added to class cryptogram. operation sendResult can send back the result to player whether the solution is correct. operation requestIdentifier can request an identifier to external web service.
15.To realize this requirement, an association class solve was added, it has attributes "resultSubmission", "cryptogramSubmitted" and "cryptogramStarted".
16.To realize this requirement, I created a class of database, it has attributes player, cryptogram, cryptogramSolved, cryptogramSubmission, cryptogramStarted, playerRating.
17.The user interface must be intuitive and respontive.

