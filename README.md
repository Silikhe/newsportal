# News Portal
##### Silas Silikhe

## Description
News Portal is a project where we practice using REST API for querying and retrieving information. As a user, you should be able to view all all departments present, news articles (general or department-specific) and create users within a department as well as view information regarding a particular department or user.

## Stacks used
1. Java & Gradle
2. Spark
3. Junit for testing
4. PostgreSQL database

## Setup & installation
###### Installation
+ Database
###### Type the following commands
+ Type `psql` command to connect to postgres server the server.
+ Proceed to create the database `wildlife-tracker` by typing: `CREATE DATABASE news_portal;`
+ Now connect to news portal database using`\c news_portal;`
+ Once connected, create the following tables by running these commands:
```
CREATE TABLE users (id serial primary key, name varchar, position varchar, role varchar, department varchar);
CREATE TABLE news (id serial primary key, title varchar, description varchar, type varchar, author varchar);
CREATE TABLE departments (id serial primary key, name varchar, description varchar, totalemployees int);
CREATE TABLE departments_users (id serial primary key, deptid int, userid int);
CREATE TABLE departments_news (id serial primary key, deptid int, newsid int, userid int);
CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;
```

###### Routes
+ Use the following paths.
+ `/users` Lists all the users
+ `/departments` Gets all the departments
+ `/departments/new` Gets an individual department using id
+ `/departments/:deptId/details` Post a new department
+ `/departments/:deptId/users/new` Creates new department from specific user
+ `/departments/:deptId/users/:userId/details"` Users from individual department
+ `/departments/:deptId/users/:userId/news` Users from individual department
+ `/departments/:deptId/users/:userId/news/new"` A user can post news
+ `/departments/:deptId/news` Get news from sspecific departmenr
+ `/users/:userId/news` Get news from specific use news
+ `/news` Get all news news



## Live Site
[Visit API website](https://news-portal-silikhe.herokuapp.com/sitemap)

## License
This projects has a MIT License [found here](LICENSE)