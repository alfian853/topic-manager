# topic-manager


## API Spesifications

### 1. Registration
#### i. [POST] /register
##### Request body
```json
{
  "serviceName": "foo-service",
  "publishedTopics": ["foo-service-event-1","foo-service-event-2"],
  "subscribedTopics": ["bar-service-event-1","foobar-service-event-2"]
}
```
##### Response
```json
{
  "messages": [
    "register new topic: foo-service-event-1",
    "register new topic: foo-service-event-2",
    "subscribe to new topic: bar-service-event-1",
    "subscribe to new topic: foobar-service-event-2"
  ]
}
```

### 2. Service Info
#### i. [GET] /service/{serviceName}
##### Variable:
```
serviceName: "foo-service"
```
##### Response
```json
{
    "serviceName": "foo-service",
    "publishedTopics": [
        "foo-service-event-1",
        "foo-service-event-2"
    ],
    "subscribedTopics": [
        "bar-service-event-1",
        "foobar-service-event-2"
    ]
}
```

#### ii. [GET] /service/{serviceName}/analyze?options={options}
##### Variable:
```
serviceName: "foo-service"
options: SUBSCRIBED_TOPIC,PUBLISHED_TOPIC
```
##### Response
```json
{
  "publishedTopicsWarning": [
    "foo-service-event-1: has no registered subscriber",
    "foo-service-event-2: has no registered subscriber"
  ],
  "subscribedTopicsWarning": [
    "bar-service-event-1: has no registered publisher",
    "foobar-service-event-2: has no registered publisher"
  ]
}
```

### 3. Topic Info
#### i. [GET] /topic/{topicName}
##### Variable:
```
topicName: "foo-service-event-1"
```
##### Response
```json
{
    "topicName": "foo-service-event-1",
    "publisherServices": [
        "foo-service"
    ],
    "subscriberServices": []
}
```



