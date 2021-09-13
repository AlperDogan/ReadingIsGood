#ReadingIsGood Spring Boot Project with MongoDB with docker compose

#to run 
Clone repository and simple run 'docker-compose-up'.

#brief 
This project support has 4 main Entity,
-Customer
-Book
-Order
-Statistic

These Entities are using by Data Access Objects and MongoDB Repository.
Accesing DB, I used Mongo Repository existing CRUD queries services and write new ones.
The project have an initial database for Customers and Books. Orders and Statistics records created with calling Rest endpoints.

All the Business Logic in Controller Class.
CustomerController
It has registerCustomer, findCustomer and getCustomer order endPoints and validations.

BookController
You can use registerBook, updateBook endpoints for inserting new books and update books stock number. 

OrderController
Main business logic is here. When you call newOrder web service method, respectively
1)find order book,
2)control book stock number,
3)update book stock number,
4)make payment,
5)update order status every step,
6)register statistics. 

you can see orders with time range with findOrdersWithTime Range.

StatisticController
All customer statistic informations are get by getStatisticsByCustomerId.

For Testing and Monitorize
I used Swagger UI,
And PostMan Collection share.

All EndPoints TestClases develop with JUnit4 and Mockito. 
 



