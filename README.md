# Gondor Parking System

Postman Collection - https://www.getpostman.com/collections/0f08c4b7b93e16630e18

Gondor Parking Problem 
Target India 
April 2019 
1 Introduction 
	After the fall of Mordor, a lot of people from Middle Earth have come and settled in city of Minas Tirith. With increased people in capital, there is traffic chaos throughout the city. To fix the problem, the heir of Isildur, King Ellesar has decided to construct a multi level vehicle parking in the city centre. 
2 Problem Statement 
	You have to design a multi level vehicle parking system which among other features, gives a parking slot number for a given vehicle intending to park. Albus Dumbledore will tweak the system based on his preference to get max throughput of the system. 
3 Key Capabilities 
Below functionalities needs to be considered while designing 
	1. Parking slots closer to the exits. 
	2. Parking slots at lower levels for elderly. 
	3. Convert car parking to bike parking in case of increased traffic from 
	bikes. 
	1 
	4. On visits from royal family to multi level vehicle parking facility, slots around the assigned slot to royal family shouldn’t be allocated to any other customers. 
	5. Optional Give incentives to car pooled vehicles. 
	6. Optional Offers and discounts to repeat customers. 

4 Specifics 
	The multi level car parking is a super structure. Each floor has the ability to stack vehicles over each other. The floor plan for each stories from 0-N is 

	P P P P P 
	- - - - - 
	P P P P P 
	P P P P P 
	- - - - - 
	P P P P P 
P represents parking slot - represents drive through 
Each floor has 20 parking slots which accommodates 40 cars by stacking one over the other. Each parking slot can accommodate 2 bikes without stacking and 5 bikes with stacking. 


5 Input/Output 
	Create an application which takes below inputs: 
	No of floors as input. N at startup 
	Interval input: 
	1. Incoming Vehicle type 
	2. Incoming Vehicle no 
	3. Output Vehicle type 
	4. Output Vehicle no 
	Interval Output: 
	Slot no for the incoming vehicle. 
	Multi level car parking summary. 

6 Acceptance Criteria 
	The code should be following OOPs concepts. Code needs to be production ready.