package main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import configuration.Config;

public class DataBasePool {
  HikariDataSource ds;
  HikariConfig config;
  
  public DataBasePool() {
    config = new HikariConfig();
    config.setMaximumPoolSize(10);
    config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
    config.addDataSourceProperty("serverName", Config.INSTANCE.getConfig().getUrl());
    config.addDataSourceProperty("port", "3306");
    config.addDataSourceProperty("databaseName", Config.INSTANCE.getConfig().getDatabase());
    config.addDataSourceProperty("user", Config.INSTANCE.getConfig().getUser());
    config.addDataSourceProperty("password", Config.INSTANCE.getConfig().getPassword());
    config.addDataSourceProperty("url", "jdbc:mysql://localhost:3306/" + Config.INSTANCE.getConfig().getDatabase() + "?autoReconnectForPools=true");

    this.ds = new HikariDataSource(config);
  }
  
  public HikariDataSource getConnectionPool(){
    return this.ds;
  }

}
