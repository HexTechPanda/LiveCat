# LiveCat

## Pre environment:
JDK11, MySQL-5.7.28, maven, nacos-2.0.4, Redis, Postman

## How to start:
1. Find the livecat.sql and import the sql into your local database.
2. Use IDEA to open the project, find every pom.sml and reimpot the maven.
3. check the applicatioin.yml under every module, make sure the MySQL related configuration are right.
4. Start service livecat-ticket.
5. try to call below APIs with Postman

GET localhost:8001/ticket/events

GET localhost:8001/ticket/events/1244940138650423298

Schedule:
21/09/2022
build the main points of livecat-ticket.