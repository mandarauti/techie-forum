# techie-forum
Service includes implementation of following features:

1. REST server for adding and updating employee in DB using XML payload.(Using Spring MVC)
2. For updating employee , validating for request is withing 24 hours made else log exception in DB.(Spring boot crudRepository for hibernate ORM)
3. Logging each method execution time ( using Spring Boot AOP)
4. While sending Response , adding custom headers for RESTURL and execution time.
5. REST client to call employee service.
6. retry mechanism for retrying service after every 10 sec and for 3 times.
7. Junit test cases for REST service. ( Not got much time to cover more test cases)
8. User defined exceptions.
