### REST API to store and retrieve frame data from BLAZBLUE CENTRALFICTION

## Tools and technologies to use:

Spring Boot (including Spring Data JPA, Spring Security), MySQL, Docker, JUnit, Postman, WebClient

## To-Do List

* ~~Scrape frame data from the BlazBlue frame data webpage.~~
 
* ~~Clean and normalize the scraped data for consistent storage.~~
 
* ~~Design a relational database schema for storing characters and move data.~~
 
* ~~Create REST API endpoints:~~
 
   *  ~~GET points for characters, their frame-data, etc~~
   *  ~~POST points to insert scraped data from Dustloop~~
   *  ~~PUT points to update frame data and characters~~
   
* ~~Add unit tests with JUnit for service and controller layers.~~
 
* Write integration tests for API endpoints.
 
* ~~Containerize the application using Docker.~~
 
* Set up CI/CD pipeline to automate testing and deployment:
 
  * Run tests automatically on new commits.
   
* Deploy API to a cloud platform (e.g., AWS, Heroku).
   
* Create documentation for API usage.
 
* Add pagination, filtering, and sorting for API responses (e.g., filter moves by frame properties).
 
* Explore incorporating this API into a Discord bot.
