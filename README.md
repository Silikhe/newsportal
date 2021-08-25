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

##### Java
+ Run `java --version` to check which version of java you have installed in your device. If you have java installed, you should see an output slightly similar to the one below...  
  _**openjdk 10.0.2 2018-07-17**_  
  _**OpenJDK Runtime Environment (build 10.0.2+13-Ubuntu-1ubuntu0.18.04.4)**_  
  ... then you need to install java by running this : `sudo apt install default-jre`

+ Clone the repository into a desired folder by running the following link in your terminal: `https://github.com/silikhe/news-portal.git`

###### (Running the app)
+ Build and Run the project.
+ Open your browser or postman (preferred) in order to interact with the API
+ Use the path `/sitemap` in the url when running the project to get a list of all paths in a summarized form


## Live Site
[Visit API website](https://news-portal-silikhe.herokuapp.com/sitemap)

## License
This projects has a MIT License [found here](LICENSE)