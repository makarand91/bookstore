# bookstore
## Please use swagger ui link to REST api documentation
http://localhost:8080/swagger-ui/
## Data is stored in SQLite database
### properties
#### spring.jpa.database-platform=com.sample.bookstore.config.SQLDialect
#### spring.jpa.hibernate.ddl-auto=update
#### spring.datasource.url = jdbc:sqlite:sqlitesample.db
#### spring.datasource.driver-class-name = org.sqlite.JDBC
#### spring.datasource.username = admin
#### spring.datasource.password = admin
#### spring.jpa.hibernate.ddl-auto=update
#### kafka.bootstrap-servers=KFK-DEN-DEV-01:9092,KFK-DEN-DEV-02:9092,KFK-DEN-DEV-03:9092
#### kafka.groupid=bookservice
#### audit.event.topic=sample.audit.event
