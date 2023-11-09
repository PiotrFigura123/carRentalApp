package pl.piotrFigura.backendcarrental.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/** Klasa zawiera konfigurację dotyczącą bazy danych oraz cache. */
@Configuration
@EnableJpaRepositories(
        basePackages = "pl.piotrFigura.backendcarrental.repository",
        entityManagerFactoryRef = "entityManagerDataFactory",
        transactionManagerRef = "transactionManagerData"
)
public class DatabaseDataConfiguration {

    @Value("${postgres.database.data.url}")
    private String postgresDatabaseUrl;

    @Value("${postgres.database.data.username}")
    private String postgresDatabaseUsername;

    @Value("${postgres.database.data.password}")
    private String postgresDatabasePassword;

    @Value("${postgres.database.driver}")
    private String postgresDatabaseDriver;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String springJpaHibernateDdlauto;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.jdbc.batch_size}")
    private String hibernateBatchSize;

    @Value("${hibernate.order_inserts}")
    private String hibernateOrderInserts;

    @Value("${hibernate.order_updates}")
    private String hibernateOrderUpdates;



    /**
     * Metoda tworzy obiekt reprezentujący konfigurację źródła danych.
     *
     * @return DataSource obiekt reprezentujący źródło danych.
     */
    @Bean(name = "dataSourceData")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(postgresDatabaseDriver);
        String url = prepareUrl();
        dataSource.setUrl(url);
        dataSource.setUsername(postgresDatabaseUsername);
        dataSource.setPassword(postgresDatabasePassword);
        return dataSource;
    }

    private String prepareUrl() {
        return postgresDatabaseUrl;
    }

    /**
     * Metoda konfiguruje bazę danych oraz ustawia skanowanie pakietów projektu.
     *
     * @return factoryBean obiekt menedżera fabryki encji
     */
    @Bean(name = "entityManagerDataFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSourceData") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("pl.piotrFigura.backendcarrental.model");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(setProperty());
        factoryBean.setPersistenceUnitName("carRentalIT");
        return factoryBean;
    }

    /**
     * Metoda wytwarza menedżera transakcji bazodanowych.
     *
     * @return PlatformTransactionManager menedżer transakcji bazodanowych.
     */
    @Primary
    @Bean(name = "transactionManagerData")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerDataFactory") LocalContainerEntityManagerFactoryBean managerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(managerFactoryBean.getObject());
        return transactionManager;
    }

    /**
     * Metoda tworzy tłumacza wyjątków bazodanowych.
     *
     * @return PersistenceExceptionTranslationPostProcessor tłumacz wyjątków warstwy bazy danych
     */
    @Bean(name = "exceptionTranslationData")
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    /**
     * Metoda pozwala na ustawienie preferencji mapowania relacyjno-bazodanowego
     *
     * @return Properties właściwości aplikacji.
     */
    private Properties setProperty() {
        Properties properties = new Properties();
        properties.setProperty("spring.jpa.hibernate.ddl-auto", springJpaHibernateDdlauto);
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.order_updates",hibernateOrderUpdates);
        properties.setProperty("hibernate.order_inserts", hibernateOrderInserts);

        return properties;
    }

}