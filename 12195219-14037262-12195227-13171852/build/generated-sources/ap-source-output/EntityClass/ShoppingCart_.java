package EntityClass;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-25T23:12:01")
@StaticMetamodel(ShoppingCart.class)
public class ShoppingCart_ { 

    public static volatile SingularAttribute<ShoppingCart, Date> modifiedOn;
    public static volatile SingularAttribute<ShoppingCart, Integer> quantity;
    public static volatile SingularAttribute<ShoppingCart, Integer> productId;
    public static volatile SingularAttribute<ShoppingCart, Double> totalPrice;
    public static volatile SingularAttribute<ShoppingCart, Integer> cartId;
    public static volatile SingularAttribute<ShoppingCart, String> cardReference;
    public static volatile SingularAttribute<ShoppingCart, Integer> userId;
    public static volatile SingularAttribute<ShoppingCart, Date> createdOn;

}