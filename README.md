# Test OkHttp with chunked response

Using <http://httpbin.org/>

```console
$ gradle run
:compileJava
:processResources UP-TO-DATE
:classes
:run
=== with OkHttpClient
contentLength=-1
bytes().length=1024
Header#0 Connection=keep-alive
Header#1 Server=meinheld/0.6.1
Header#2 Date=Fri, 18 Aug 2017 11:51:10 GMT
Header#3 Content-Type=application/octet-stream
Header#4 Access-Control-Allow-Origin=*
Header#5 Access-Control-Allow-Credentials=true
Header#6 X-Powered-By=Flask
Header#7 X-Processed-Time=0.000473022460938
Header#8 Transfer-Encoding=chunked
Header#9 Via=1.1 vegur
Header#10 OkHttp-Sent-Millis=1503057068507
Header#11 OkHttp-Received-Millis=1503057069344
=== with OkApacheClient
contentLength=-1
chunked=false
contentEncoding=null
repeatable=false
streaming=true
bytes().length=1024
Header#0 Connection=keep-alive
Header#1 Server=meinheld/0.6.1
Header#2 Date=Fri, 18 Aug 2017 11:51:10 GMT
Header#3 Content-Type=application/octet-stream
Header#4 Access-Control-Allow-Origin=*
Header#5 Access-Control-Allow-Credentials=true
Header#6 X-Powered-By=Flask
Header#7 X-Processed-Time=0.000484943389893
Header#8 Transfer-Encoding=chunked
Header#9 Via=1.1 vegur
Header#10 OkHttp-Sent-Millis=1503057069369
Header#11 OkHttp-Received-Millis=1503057070134

BUILD SUCCESSFUL

Total time: 6.611 secs
```

This access <http://httpbin.org/stream-bytes/1024?chunk_size=256> with both
OkHttpClient and OkApacheClient.
