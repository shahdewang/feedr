# feeds

### Design
* The service layer is based on Spring Boot.
* Uses MongoDB at the datastore layer.
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
**DB Connection:** 127.0.0.1/27017

#### Compiling and running the program
- The application based on Spring Boot
- Use **mvn clean package** to build the source. This will also run the tests.
- This will generate a runnable jar **target/feeds-0.1.jar**
- To execute the program: **java -Dserver.port=$PORT -jar target/feeds-0.1.jar**

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