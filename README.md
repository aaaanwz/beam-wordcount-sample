# beam-wordcount-sample
https://qiita.com/aaaanwz/items/62161c9c55f1f2bb8092

## Run on local

```
mvn package
java -jar ./target/beam-wordcount-sample-0.1-shaded.jar
```

## Run on Google Dataflow
```
mvn package -Pdataflow-runner
java -jar ./target/beam-wordcount-sample-0.1-shaded.jar --runner=DataflowRunner --project=xxxx --tempLocation=gs://<YOUR_GCS_BUCKET>/temp/
```

## Run on Amazon EMR (Flink)
```
mvn package -Pflink-runner
scp -i ~/.ssh/keypair.pem ./target/beam-wordcount-sample-0.1-shaded.jar ec2-user@ec2-xxx-xxx-xxx:/home/hadoop
```

```
JAR の場所:command-runner.jar
メインクラス:なし
引数:flink run -m yarn-cluster -yn 2 /home/hadoop/beam-wordcount-sample-0.1-shaded.jar --runner=FlinkRunner
失敗時の操作:次へ
```
