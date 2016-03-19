README for the Global Beverage Corporation Exchange project.
(Created by: Sandor Nagy)


**** Please see the Documentation.txt for the high level description of the project. ****
**** This README.txt file only contains the main ideas behind the implementation.   ****


Description: Implement Simple Stock Market trading system required functionality. All data need to be held in memory, no database, no GUI required.

Main ideas behind implementing the task: 

  Code Wise: 
	- Simplicity: Simplest possible solution. No over engineering.

	- Security: Pay attetion to avoid security holes e.g. throwing exceptions from constructors. Usage Builder or Static Factory Method and handling parameter checking in these methods.

	- Thread Safety: Syncronization of shared resources. Usage of immutable objects.

	- Type Safety: Wrap simple values inside classes instead of just using e.g. a String value. Else we can easily give a String or Integer value to the the wrong propertie.

	- Separate data and functionality: I think that this more Functional Programming approach will result in a clear, easily understandable implementation for problems where there are some, mostly static data structures and we need to define many operation on these data.   

	- Readibility: Give usefull comments but also write self descriptive code.
  

  Methodology Wise:
	- I used TDD where it was applicable (mainly the services functionality implementation part). Which meant that first I wrote the test, than I wrote the implementation which made the test green.


  Assumptions:
	- I assumed that this service will be used by other teams, services. So I defined an API that other services can use and implement that API in my impl package.
    - Assumed that the numbers for a stock property are all integers. And only used BigDecimal for the result calculations.


There is no main class for this task, because I though that it is not meant to be to run as a standalone program.  

Used: Java 8 SE, Gradle and IntelliJ 15 Community Edidtion.



  
 





 
