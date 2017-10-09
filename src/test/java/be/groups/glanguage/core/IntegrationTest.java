package be.groups.glanguage.core;

import be.groups.common.database.DatabaseComponent;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("be.groups")
@EntityScan("be.groups")
@EnableJpaRepositories(basePackages = "be.groups")
@SpringBootTest(classes = {DatabaseComponent.class})
public class IntegrationTest {
  //@Autowired
  //private GroupSDataSourceHandler sourceHandler;

  //@Before
  //public void setUpBefore() throws Exception {
  //  sourceHandler.findById((99))
  //               .ifPresent(DbContextHolder.INSTANCE::setOracleDb);
  //}
}
