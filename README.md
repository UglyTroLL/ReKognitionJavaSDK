ReKognition Java SDK
==================

Java SDK for [ReKognition](https://rekognition.com/) face recognition APIs.

Highlights
---
+ **Strongly typed** - All the response is wrapped into Strongly typed Java Beans. No more Json parsing on your end. (But you can still get JsonObject or raw response string if you need flexibilities).
+ **Easy to use** - Well structrued code and easy to use APIs.
+ **Pooling HTTP Connections** - No memory leaks and open-forever HTTP connections for massive API calls.

Install
---
1. 'git clone'
2. 'mvn install' and added the following xml to your pom.xml:

```xml
<dependency>
  <groupId>com.imjojo</groupId>
  <artifactId>ReKognitionJavaSDK</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

3. Or...'mvn package' and put the jar into your project's library.

Examples
---
e.g. **Face Detect**

```java
FaceDetect instance = new FaceDetect(REKO_API_KEY, REKO_API_SECRET); //Init a FaceDetect instance
FaceAdapter result = instance.getResponse(faceDetectJobs, pictureUrl); //Got the response
double noseX = result.getFaces().get(0).getNose().getNoseX(); //Get whatever you want from the adapter
//That's it
```

All API usage examples can be found in the unit tests. I've disabled them for since running these unit tests needs valid ReKognition api key and ReKognition api serect. But you can refer to the code to get the idea how to use them.

Concepts
---
+ All API caller classes (FaceDetect, FaceAdd...etc.) are sub-classes of AbstractRekognitionAPI, they are **Thread Safe**, but you can just initilize a new one everytime you want to call ReKognition APIs since they are just some thin wrappers.
+ Adapters are...adapters, they convert JSONObjects into Java Beans. They can be easily expand to fit your own needs (e.g. get a new field from the response or convert an existing field into different data types). All concrete adapters are sub-classes of JsonResponseAdapter, so you can always get the raw response (in String or JSONObject) from any adapter.
+ Java Beans/Models, which represent the response data from ReKognition API. Every field in a Model is lazy-loaded and you will get a FieldNotFoundException if the field you want is not there.
