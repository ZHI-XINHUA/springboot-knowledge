1、先启动zk

2、切换到目录
cd F:\tool\window\kafka_2.11-2.3.0\bin\windows

3、启动server
kafka-server-start.bat F:\tool\window\kafka_2.11-2.3.0\config\server.properties

4、创建topic

kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test