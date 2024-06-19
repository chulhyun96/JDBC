package springboot.jdbc.connection;


import com.zaxxer.hikari.HikariDataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static springboot.jdbc.connection.ConnectionConst.*;


class DBConnectionUtilTest {

    @Test
    void connection() throws SQLException {
        Connection connection1 = DriverManager.getConnection(URL, USER, PASSWORD);
        Connection connection2 = DriverManager.getConnection(URL, USER, PASSWORD);

        Assertions.assertThat(connection1).isNotSameAs(connection2);

    }

    @Test
    void dataSourceDriverManager() throws SQLException {
        //DataSource 설정 -> 항상 새로운 커넥션을 얻어옴
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        //DataSource 사용
        useConnection(dataSource);
    }

    @Test
    void dataSoruceConnectionPool() throws SQLException, InterruptedException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");
        useConnection(dataSource);
        Thread.sleep(1000);
    }

    private void useConnection(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        Assertions.assertThat(con1).isNotSameAs(con2);
    }
}
