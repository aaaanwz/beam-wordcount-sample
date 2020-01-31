# beam-wordcount-sample

## Run on local

```
mvn package
java -jar /target/beam-sample-0.1-shaded.jar
```

## Run on Google Dataflow
```
mvn package -Pdataflow-runner
java -jar /target/beam-sample-0.1-shaded.jar --runner=DataflowRunner --project=xxxx --tempLocation=gs://<YOUR_GCS_BUCKET>/temp/
```
