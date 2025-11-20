# Ubuntu ベースのコンテナを作る
FROM ubuntu:22.04

# 必要なものをインストール（Java17 + MySQL）
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk mysql-server && \
    apt-get clean

# MySQL 設定（ローカル接続のみ）
RUN sed -i "s/bind-address.*/bind-address = 127.0.0.1/" /etc/mysql/mysql.conf.d/mysqld.cnf

# MySQL のデータ保存場所（Render の Persistent Disk と接続される場所）
VOLUME /var/lib/mysql

# JAR をコンテナにコピー
COPY target/*.jar /app.jar

# 起動スクリプト
CMD service mysql start && \
    mysql -u root -e "CREATE DATABASE IF NOT EXISTS mydb;" && \
    java -jar /app.jar
	
	
	
FROM ubuntu:22.04

# Java + MySQL をインストール
RUN apt-get update && \
	apt-get install -y openjdk-17-jdk mysql-server && \
	apt-get clean

# MySQL をローカル接続のみに設定（外部アクセス禁止）
RUN sed -i "s/bind-address.*/bind-address = 127.0.0.1/" /etc/mysql/mysql.conf.d/mysqld.cnf

# MySQL データは Render のディスクに保存される
VOLUME /var/lib/mysql

# Spring Boot JAR をコピー
COPY target/*.jar /app.jar

# 起動：MySQL → DB 作成 → Spring Boot
CMD service mysql start && \
	mysql -u root -e "CREATE DATABASE IF NOT EXISTS mydb;" && \
	java -jar /app.jar

