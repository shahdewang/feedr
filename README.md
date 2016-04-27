# feeds

### Design
* The service layer is based on Spring Boot.
* Uses MongoDB at the datastore layer.<br>
  I chose MongoDB because each read/write operation to the DB can be independent, we do not need transaction management in this kind of application. Users/Feeds/Articles could get added/removed independently. MongoDB allows schema-less design so content of articles can change independently. MongoDB allows eventual consistency, it's okay in this kind of application where when an article is added to Feed, the article may surface up after some time to the user.
* The REST APIs are based on HATEOAS

#### Entities
* Users
  - Collection name: **Users**
```javascript
  {
    "username" : "<username>",
    "subscribed_feeds" : [ "feed1", "feed2", ... ]
  }
```
* Feeds
  - Collection name: **Feeds**
```javascript
  {
    "name" : "<feedname>",
    "articles": [ { ... }, { ... } ]
  }
```

The MongoDB can be configured in **com.bufferworks.feeds.MongoDbConfig** <br>
**DB Name:** feedsportal <br>
**DB Connection:** localhost/27017

#### Compiling and running the program
- The application based on Spring Boot
- Use **mvn clean package** to build the source. This will also run the tests.
- This will generate a runnable jar **target/feeds-0.1.jar**
- To execute the program: **java -jar target/feeds-0.1.jar**<br>
  Add `-Dserver.port=9090` to run the server on a different port<br>
  DB config parameters can be updated using -<br>
  `java -Dmongodb.host=localhost -Dmongodb.port=27017 -Dmongodb.database=test -jar target/feeds-0.1.jar`

#### Using API's
* The API's are implemented following the HATEOAS paradigm where response have links to follow specifying the actions available
* Root Resource lists all sub resources, available via <br>
  URL: `http://localhost:8080/jersey/api`<br>
  Headers: `Accepts: application/com.bufferworks.root+json`<br>
* Added **Feeds.json** that is a postman collection to with API requests
* All REST resources are under `com.bufferworks.feeds.restapi.resource` package.
* All media types for REST resources are under `com.bufferworks.feeds.restapi.media` package.
* MongoDB DB Services are under `com.bufferworks.feeds.persist` package.
* MongoDB entities are under `com.bufferworks.feeds.persist.entity` package.

#### API Resources
* UsersResource
* UserResource
* FeedsResource
* FeedResource
* ArticlesResource
* SubscriptionsResource
* UserArticlesResource

#### Integration for Test Resources
* ArticlesResourceIntegrationTest
* FeedResourceIntegrationTest
* FeedsResourceIntegrationTest
* SubscriptionsResourceIntegrationTest
* UserArticlesResourceIntegrationTest - This integration test creates a user, creates feeds and adds articles to the feeds. Subscribes the user to 3 feeds and then returns all articles associated with the subscribed feeds.
* UserResourceIntegrationTest
* UsersResourceIntegrationTest