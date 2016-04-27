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

#### API's
* UsersResource
  - add user
  - list users
* UserResource
  - get user
  - delete user
* FeedsResource
  - add feed
  - list feeds
* FeedResource
  - get feed
  - get articles (in a feed)
* ArticlesResource
  - add article
* SubscriptionsResource
  - subscribe
  - unsubscribe
