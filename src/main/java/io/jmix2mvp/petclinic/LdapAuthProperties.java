package io.jmix2mvp.petclinic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "ldap.auth")
@ConstructorBinding
public class LdapAuthProperties {
    private final String url;
    private final String userDn;
    private final String password;
    private final String base;
    private final String userSearchBase;
    private final String userSearchFilter;
    private final String groupSearchBase;
    private final String groupSearchFilter;

    public LdapAuthProperties(@DefaultValue("ldap://localhost:389/dc=springframework,dc=org") String url,
                              String userDn,
                              @DefaultValue("admin") String password,
                              String base,
                              String userSearchBase,
                              @DefaultValue("(uid={0})") String userSearchFilter,
                              String groupSearchBase,
                              @DefaultValue("member={0}") String groupSearchFilter) {
        this.url = url;
        this.userDn = userDn;
        this.password = password;
        this.base = base;
        this.userSearchBase = userSearchBase;
        this.userSearchFilter = userSearchFilter;
        this.groupSearchBase = groupSearchBase;
        this.groupSearchFilter = groupSearchFilter;
    }

    /**
     * Specifies the ldap server URL. Defaults to: "dap://localhost:389/dc=springframework,dc=org".
     */
    public String getUrl() {
        return url;
    }

    /**
     * Username (DN) of the "admin" user identity (i.e. "uid=admin,ou=system")
     * which will be used to authenticate to a LDAP server.
     */
    public String getUserDn() {
        return userDn;
    }

    /**
     * The password for the admin DN. Defaults to "admin"
     */
    public String getPassword() {
        return password;
    }

    /**
     * Base suffix from which all operations should originate. Default is "dc=springframework,dc=org"
     */
    public String getBase() {
        return base;
    }

    /**
     * Search base for user searches. Defaults to ""
     * For example "ou=users"
     */
    public String getUserSearchBase() {
        return userSearchBase;
    }

    /**
     * The LDAP filter used to search for users (optional). Defaults to "(uid={0})"
     * The substituted parameter is the user's login name.
     */
    public String getUserSearchFilter() {
        return userSearchFilter;
    }

    /**
     * The search base for group membership searches. Defaults to "".
     * For example "ou=users"
     */
    public String getGroupSearchBase() {
        return groupSearchBase;
    }

    /**
     * The LDAP filter to search for groups. Defaults to "member={0}".
     * The substituted parameter is the DN of the user.
     */
    public String getGroupSearchFilter() {
        return groupSearchFilter;
    }
}
