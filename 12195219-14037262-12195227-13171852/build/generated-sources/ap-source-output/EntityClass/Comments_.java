package EntityClass;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-25T23:12:01")
@StaticMetamodel(Comments.class)
public class Comments_ { 

    public static volatile SingularAttribute<Comments, Integer> productId;
    public static volatile SingularAttribute<Comments, Integer> commentId;
    public static volatile SingularAttribute<Comments, String> comment;
    public static volatile SingularAttribute<Comments, Integer> userId;
    public static volatile SingularAttribute<Comments, Date> createdOn;

}