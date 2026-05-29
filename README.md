\# Global Class Offering Booking System



\## Project Overview



This project is a Spring Boot backend application for managing global class offerings and bookings.



The system allows:



\- Teachers to create offerings

\- Teachers to add sessions to offerings

\- Parents to view offerings

\- Parents to book offerings

\- Automatic booking conflict detection

\- Timezone-aware session scheduling



\---



\## Tech Stack



\- Java 17

\- Spring Boot 3

\- Spring Data JPA

\- MySQL 8

\- Maven

\- Lombok

\- Swagger/OpenAPI

\- Postman



\---



\## Features



\### Teacher APIs



\- Create Offering

\- Add Session

\- View Teacher Offerings



\### Parent APIs



\- View Available Offerings

\- Book Offering

\- View Parent Bookings



\### Business Logic



\- Timezone conversion support

\- Booking conflict detection

\- One booking per offering per parent

\- UTC session storage



\---



\## Database Schema



Tables:



1\. course

2\. offering

3\. sessions

4\. parents

5\. booking



Schema file:



database/schema.sql



\---



\## API Endpoints



\### Teacher



\#### Create Offering



POST



```http

/teacher/offerings

```



\#### Add Session



POST



```http

/teacher/offerings/{id}/sessions

```



\#### Get Teacher Offerings



GET



```http

/teacher/{teacherId}/offerings

```



\---



\### Parent



\#### View Offerings



GET



```http

/parent/{parentId}/offerings

```



\#### Book Offering



POST



```http

/parent/book

```



\#### View Parent Bookings



GET



```http

/parent/{parentId}/bookings

```



\---



\## Timezone Handling



Teacher sessions are created using the teacher timezone.



All session times are converted and stored in UTC.



Parent views automatically convert session times to the parent's timezone.



Example:



Teacher Timezone:



Asia/Kolkata



Session:



18:00 - 19:00



Stored:



12:30 UTC - 13:30 UTC



\---



\## Conflict Detection



Before booking an offering:



1\. Existing bookings of the parent are checked.

2\. Session timings are compared.

3\. Overlapping sessions are rejected.



Response:



HTTP 409 Conflict



```json

{

&#x20; "message": "Booking conflict detected"

}

```



\---



\## Assumptions



\- One teacher can create multiple offerings.

\- One offering can contain multiple sessions.

\- One parent can book multiple offerings.

\- Session overlap is not allowed for the same parent.

\- Time is stored in UTC.



\---



\## Setup Instructions



\### Clone Repository



```bash

git clone https://github.com/AROMALNAIR17/global-class-offering-booking-system.git

```



\### Database



Create MySQL database:



```sql

CREATE DATABASE booking;

```



Run:



database/schema.sql



\### Configure Database



Update:



```properties

src/main/resources/application.properties

```



Example:



```properties

spring.datasource.url=jdbc:mysql://localhost:3306/booking

spring.datasource.username=root

spring.datasource.password=password

```



\### Run Application



```bash

mvn spring-boot:run

```



Application:



```text

http://localhost:8080

```



Swagger:



```text

http://localhost:8080/swagger-ui.html

```



\---



\## API Documentation



Postman Collection:



```text

postman/Booking-System.postman\_collection.json

```



Swagger UI:



```text

http://localhost:8080/swagger-ui.html

```



\---



\## Repository



GitHub:



https://github.com/AROMALNAIR17/global-class-offering-booking-system

