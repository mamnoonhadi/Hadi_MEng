package EntityClass;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-25T23:12:01")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> productQuantity;
    public static volatile SingularAttribute<Product, Date> modifiedOn;
    public static volatile SingularAttribute<Product, String> productCode;
    public static volatile SingularAttribute<Product, String> productImage;
    public static volatile SingularAttribute<Product, Integer> productId;
    public static volatile SingularAttribute<Product, Serializable> imagePath;
    public static volatile SingularAttribute<Product, String> comment;
    public static volatile SingularAttribute<Product, Date> createdOn;
    public static volatile SingularAttribute<Product, String> productName;
    public static volatile SingularAttribute<Product, String> productPrice;

}