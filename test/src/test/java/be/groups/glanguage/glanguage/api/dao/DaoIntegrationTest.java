package be.groups.glanguage.glanguage.api.dao;

import be.groups.common.database.*;
import be.groups.common.database.configuration.*;
import be.groups.common.database.sharding.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.domain.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;

@ComponentScan("be.groups")
@EntityScan("be.groups")
@EnableJpaRepositories(basePackages = "be.groups")
@SpringBootTest(classes = {DatabaseComponent.class})
public class DaoIntegrationTest {

  @Autowired
  private GroupSDataSourceHandler sourceHandler;

  @Before
  public void setUpBefore() throws Exception {
    sourceHandler.findById((99))
                 .ifPresent(DbContextHolder.INSTANCE::setOracleDb);
  }
}
