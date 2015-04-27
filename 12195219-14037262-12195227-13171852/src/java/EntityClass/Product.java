/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClass;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/*
 Created on : Apr 16, 2015, 5:27:44 AM
 Author:
 Nader    12195219
 Mamnoon  14037262
 Khaled   12195227
 Yaser    13171852
 */
@Entity
@Table(name = "PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    // In Below queries I am setting the product ID
    @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :pid"),
    // In Below queries I am setting the product ID and the Product name
    @NamedQuery(name = "Product.findByProductNameandID", query = "SELECT p FROM Product p WHERE p.productName =:pname OR p.productCode =:pid "),
    // In Below queries I am setting the product name
    @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName like :pname "),
    @NamedQuery(name = "Product.findByProductPrice", query = "SELECT p FROM Product p WHERE p.productPrice = :productPrice"),
    // In Below queries I am setting the product code
    @NamedQuery(name = "Product.findByProductCode", query = "SELECT p FROM Product p WHERE p.productCode = :pid"),
    @NamedQuery(name = "Product.findByProductImage", query = "SELECT p FROM Product p WHERE p.productImage = :productImage"),
    @NamedQuery(name = "Product.findByProductQuantity", query = "SELECT p FROM Product p WHERE p.productQuantity = :productQuantity"),
    @NamedQuery(name = "Product.findByCreatedOn", query = "SELECT p FROM Product p WHERE p.createdOn = :createdOn"),
    @NamedQuery(name = "Product.findByModifiedOn", query = "SELECT p FROM Product p WHERE p.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "Product.findByComment", query = "SELECT p FROM Product p WHERE p.comment = :comment")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "PRODUCT_PRICE")
    private String productPrice;
    @Size(max = 250)
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Size(max = 250)
    @Column(name = "PRODUCT_IMAGE")
    private String productImage;
    @Size(max = 250)
    @Column(name = "PRODUCT_QUANTITY")
    private Integer productQuantity;

    @Lob
    @Column(name = "IMAGE_PATH")
    private Serializable imagePath;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MODIFIED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Size(max = 5000)
    @Column(name = "COMMENT")
    private String comment;

    public Product() {
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Product(Integer productId, String productName, String productPrice, Date createdOn, Date modifiedOn) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Serializable getImagePath() {
        return imagePath;
    }

    public void setImagePath(Serializable imagePath) {
        this.imagePath = imagePath;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClass.Product[ productId=" + productId + " ]";
    }
}
