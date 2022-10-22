MADE BY: Dylan Bermudez Cardona | Victor Manuel Garzon Meneses | Luis Charria Meneses.


PROBLEM CONTEXT:

The project was elaborated with the purpose of allows managing the the entry and exit of patients of two laboratories (Hematology and General Purpose).

For the computer solution we took into account four main data structures: Hashtable, Queue, Priority Queue and Stack. Each of the previous structures allowed us to easily and efficiently solve the functionalities required to solve the problem. It should be emphasized that in various parts of the problem something different can be understood, therefore, we see it necessary to clarify some things. First, the database was made as a plain text file, which can be loaded and saved by the user of the program. Second, the patient information is stored in a Hashtable, where the user can search, add or edit a patient. Third, for those people with priority, we decided to use the Priority Queue, where the priority is given by the number of important underlying diseases, where the one with the most is the highest priority. Fourth, for people without major underlying diseases, the priority will be the same and they will be treated in order of arrival, so we decided to use a Queue for these patients. Fifth, we decided to implement the undo functionality, it works only for the "Check-in" and "Check-out" actions, also, for it, we determined that the Stack structure was the best option. Sixth, we believe it is necessary to validate that when a patient is in a queue (be it from one laboratory or another) it will not be possible to check-in for another queue. Finally, we thought about the user of the program and implemented an option to monitor or see the order of the patients of both laboratories.

