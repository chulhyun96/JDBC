package springboot.jdbc.connection;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class DBConnectionUtilTest {

    @Test
    void connection() {
        Connection connection = DBConnectionUtil.getConnection();
        Assertions.assertThat(connection).isNotNull();
        // 여기까지 Connection을 통해서 JDBC 드라이버를 꺼내왔다. 현재 프로젝트에선 h2 드라이버를 꺼내온 것.
    }
}
