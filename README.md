# beam-wordcount-sample

## Run on local

```
mvn package
java -jar ./target/beam-sample-0.1-shaded.jar
```

## Run on Google Dataflow
```
mvn package -Pdataflow-runner
java -jar ./target/beam-sample-0.1-shaded.jar --runner=DataflowRunner --project=xxxx --tempLocation=gs://<YOUR_GCS_BUCKET>/temp/
```

## Run on Amazon EMR (Flink)
```
mvn package -Pflink-runner
```

```
JAR の場所:command-runner.jar
メインクラス:なし
引数:flink run -m yarn-cluster -yn 2 /home/hadoop/hoge.jar --runner=FlinkRunner
失敗時の操作:次へ
```