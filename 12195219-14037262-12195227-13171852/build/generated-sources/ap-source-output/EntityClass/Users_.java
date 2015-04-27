package EntityClass;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-25T23:12:01")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> userPwd;
    public static volatile SingularAttribute<Users, String> userName;
    public static volatile SingularAttribute<Users, String> userRole;
    public static volatile SingularAttribute<Users, Integer> userId;
    public static volatile SingularAttribute<Users, Date> createdOn;

}