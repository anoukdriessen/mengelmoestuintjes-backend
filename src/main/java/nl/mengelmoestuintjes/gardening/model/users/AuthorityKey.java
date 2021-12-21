package nl.mengelmoestuintjes.gardening.model.users;

import java.io.Serializable;
import java.util.Objects;

public class AuthorityKey implements Serializable {
    private String username;
    private String authority;

    @Override
    public boolean equals( Object o ) {
        boolean objectEqualsObject = this == o;
        boolean objectIsNullOrNoMatch = o == null || getClass() != o.getClass();

        if ( objectEqualsObject ) return true;
        if ( objectIsNullOrNoMatch ) return false;

        AuthorityKey that = ( AuthorityKey ) o;
        return username.equals(that.username) && authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                username, authority
        );
    }
}
