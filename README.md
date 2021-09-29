# Petclinic Project with LDAP authentication.

## Run application

* Navigates to compose directory and execute:
    ```
    docker-compose openldap
    ``` 
* Run Spring Boot application. Try to authenticate with `user01/password1`

## Additional

### LDAP server configuration

* LDAP server contains management user `admin` with password `admin`.
* LDAP Group `admin` which contains users: `user01` and `user02`.
* Credentials: `user01` password is `password1`, `user02` password is `password2`

### LDAP server browse from GUI

* Install [Apache Directory Studio](http://directory.apache.org/studio/)
* Create Ldap connection with parameters:
    * Hostname: `localhost`
    * Port: `1389`
    * Bind DN or user: `cn=admin,dc=example,dc=org`
    * Bind password: `admin`

### Spring Boot application configuration

* Add dependency in `build.gradle`
    ```
    implementation 'org.springframework.security:spring-security-ldap'
    ```
* Add configuration properties class `LdapAuthProperties` for external LDAP authentication configuration. Specify LDAP
  property values `ldap.auth.*` in `application.properties` file.
* Add `WebSecurityConfigurerAdapter` configuration:
    ```
    @Autowired
    protected LdapAuthProperties ldapAuthProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .contextSource()
                .url(ldapAuthProperties.getUrl())
                .root(ldapAuthProperties.getBase())
                .managerPassword(ldapAuthProperties.getPassword())
                .and()
                .userSearchBase(ldapAuthProperties.getUserSearchBase())
                .userSearchFilter(ldapAuthProperties.getUserSearchFilter())
                .groupSearchBase(ldapAuthProperties.getUserSearchBase())
                .groupSearchFilter(ldapAuthProperties.getGroupSearchFilter());
    }
    ```