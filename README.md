Zonky Marketplace Loans Checker
-------------------------------

**General**

Uses the Zonky public API

https://api.zonky.cz/loans/marketplace

to synchronize the newly added loans in the regular time intervals

The description of the API is available here

http://docs.zonky.apiary.io/#


**Run**

***Command line***

Prepare the all-in-one jar with

`mvn clean package`

Run the jar with

`java -jar mpchecker-1.0-SNAPSHOT-jar-with-dependencies.jar`

***IDE***

The entry point of the application is ZonkyMPCheckerRunner class.

**Implementation details**

The applications provides a LoansCheckerScheduler interface with
simple Quartz implementation. Other implementations can be
created, for example for the cluster environment, where Quartz
is not very suitable.

The applications provides a LoanWriter interface for outputting the newly
synchronized Loans with trivial implementation, which writes
the Loans to the logger output using toString() method. Other
implementations, suitable for business needs, can be created, e.g.
sending loans via email, push notifications etc.