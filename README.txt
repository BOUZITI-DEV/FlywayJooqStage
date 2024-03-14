creation of a postgresql docker image
	docker run --name local-postgres -e POSTGRES_PASSWORD=root -e POSTGRES_USER=admin -e POSTGRES_DB=stage -p 5470:5432 -d postgres

Steps to create a springboot project with all the necessary dependencies

	1.creation of the project with spring initializr
		list of all the used dependencies
			lombok
			spring web
			spring data jpa
			postgresql driver
			flyway migration
			jooq access layer
			swagger
			MapStruct
			
	2.configuration of the file application.properties
		spring.application.name=application_name
		server.port = <tomcat_port_number>
		spring.datasource.url = jdbc:postgresql://localhost:<db_port_number>/<db_name>?serverTimezone=UTC
		spring.datasource.username = 
		spring.datasource.password = 
		
		spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
		spring.jpa.show-sql = true
		spring.jpa.properties.hibernate.format_sql = true
		spring.jpa.hibernate.ddl-auto = update
		
		spring.flyway.enabled=true
		spring.flyway.locations=classpath:db/migration
		spring.flyway.baseline-on-migrate=true
		
		springdoc.api-docs.enabled=true
		springdoc.swagger-ui.enabled=true
		
		logging.level.org.jooq=debug
	
	3.Manually adding other dependencies in the pom.xml file
		swagger
			<dependency>
				<groupId>org.springdoc</groupId>
				<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
				<version>2.0.3</version>
			</dependency>
		MapStruct
			<dependency>
				<groupId>org.mapstruct</groupId>
				<artifactId>mapstruct</artifactId>
				<version>1.5.2.Final</version>
			</dependency>
			<dependency>
				<groupId>org.mapstruct</groupId>
				<artifactId>mapstruct-processor</artifactId>
				<version>1.4.2.Final</version>
				<scope>provided</scope>
			</dependency>
			
	4.Manually adding the jooq and MapStruct plugins in the pom.xml file
		jooq
			<plugin>
                <groupId>org.jooq</groupId>
                <artifactId>jooq-codegen-maven</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <jdbc>
                        <driver>org.postgresql.Driver</driver>
                        <url>jdbc:postgresql://localhost:<port_number>/>db_name></url>
                        <user>postgres</user>
                        <password>root</password>
                    </jdbc>
                    <generator>
                        <database>
                            <name>org.jooq.meta.postgres.PostgresDatabase</name>
							<includes>.*</includes> 
							<excludes></excludes> 
                            <inputSchema>public</inputSchema>
                        </database>
                        <target>
                            <packageName><package_name></packageName>
                            <directory>target/generated-sources/jooq</directory>
                        </target>
                    </generator>
                </configuration>
            </plugin>
		MapStruct
			<plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>1.18.30</version>
                        </path>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>1.5.2.Final</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
		PS: lombok should be placed before MapStruct so that the mappers can be generated properly
			
	5.creation of a migration directory where we put all of our sql scripts for migration
		resources
			db
				migration	
					script : for script that create or alter tables
						V1__name.sql (the file must respect this syntaxe)
					data   : for script that insert into or delete from tables
					views  : for views that created from lot1 tables
					
		
	run the cmd 'mvn clean install' for the migration to be done and for the jooq classes to be genrated 
	the generated jooq classes are placed in target/generated-sources/jooq
					
	6.creation of the project's packages
		model
			contains the entites used by the ORM
		repository
			contains the jooqrepositories and their implementations in which we'll write the jooq queries
		controller
			contains the controllers that send and receive http requests with response entities
		dto
			contains the dto
		mapper
			contains the mappers
			
	7.creation of a yaml file to manually document API endpoints
		resources
			openapi3
				<file_name>.yaml
		steps to access this new documentation
			vist: http://localhost:<tomcat_port_number>/swagger-ui/index.html
			in the explorer field type: http://localhost:<tomcat_port_number>/apoenapi3/<file_name>.yaml
	PS: step 7 and step 8 are connected !!!!
			
	8.Adding the SwaggerController that enables us to access our custom API documentation using a yaml file
		@RestController
		public class SwaggerController {
			@GetMapping(value = "/openapi3/<file_name>.yaml", produces = "text/plain")
			public String getSwaggerYaml() throws IOException {
				Resource resource = new ClassPathResource("openapi3/<file_name>.yaml");
				return new String(Files.readAllBytes(Paths.get(resource.getURI())));
			}
		}
		
	9.creation of the entities and dtos