package open.schoolmanagement.contacts.importantcontactsservice.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration initializes Flayway and performs migrations.
 */
@Configuration
public class FlywayConfig {
  @Autowired
  private DataSource dataSource;

  /**
   * Instantiates a new FlywayConfig.
   *
   * @param dataSource the data source
   */
  public FlywayConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * Perform the flyway migration.
   */
  @PostConstruct
  public void performMigration() {
    Flyway flyway = new Flyway();

    flyway.setDataSource(dataSource);
    flyway.migrate();
  }
}
