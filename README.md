# Week 4: Java - Advanced databases independent project

#### Band Tracker Application, 05/13/2016.

#### By Fernanda Lowe

## Description

This application let users track bands and the venues where they've played concerts.
With this application users will be able to:
 - 1. add, update, delete and list bands;
 - 2. add venues;
 - 3. add venues to a band to show where they have played;
 - 4. see all of the venues a band has played;

## Setup/Installation Requirements

* CREATE DATABASE band_tracker;
* CREATE TABLE bands (id serial PRIMARY KEY, name varchar);
* CREATE TABLE venues (id serial PRIMARY KEY, name varchar);
* CREATE TABLE bands_venues (id serial PRIMARY KEY, band_id int, venue_id int);
* Create application functionalities (back-end);
* Run and pass all back-end tests;
* Implement User Interface (front-end);
* Run and pass all front-end tests;

## Known Bugs

None;

## Support and contact details

f while using this application you run into any issues or have questions, ideas, concerns, or would like to make a contribution to the code, please contact me at fe_lowe@hotmail.com

## Technologies Used

Java, Spark, Gradle, Velocity, Bootstrap, Heroku, psql

### License

Application can be used under MIT licenses.

Copyright (c) 2016 Fernanda Lowe at Epicodus.
