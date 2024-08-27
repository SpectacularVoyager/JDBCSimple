package com.ankush;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;


import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    static DataSource source;

    @BeforeAll
    public static void init() {

        EmbeddedDataSource ds = new EmbeddedDataSource();

        // Set the database URL
        ds.setDatabaseName("myDatabase"); // The name of the database
        ds.setCreateDatabase("create");    // Create the database if it doesn't exist

        Resource initSchema = new FileSystemResource("scripts/schema-h2.sql");
        Resource initData = new FileSystemResource("scripts/data-h2.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
        DatabasePopulatorUtils.execute(databasePopulator, ds);

    }

    @Test
    public void test() {

    }
}
