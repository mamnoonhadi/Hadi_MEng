/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * This the Entity class of Shopping Cart Table
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
@Table(name = "SHOPPING_CART")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShoppingCart.findAll", query = "SELECT s FROM ShoppingCart s"),
    @NamedQuery(name = "ShoppingCart.findByCartId", query = "SELECT s FROM ShoppingCart s WHERE s.cartId = :cartId"),
    // In Below queries I am setting the product ID
    @NamedQuery(name = "ShoppingCart.findByProductId", query = "SELECT s FROM ShoppingCart s WHERE s.productId = :pid"),
    @NamedQuery(name = "ShoppingCart.findByUserId", query = "SELECT s FROM ShoppingCart s WHERE s.userId = :userid"),
    @NamedQuery(name = "ShoppingCart.findByTotalPrice", query = "SELECT s FROM ShoppingCart s WHERE s.totalPrice = :totalPrice"),
    @NamedQuery(name = "ShoppingCart.findByQuantity", query = "SELECT s FROM ShoppingCart s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "ShoppingCart.findByCardReference", query = "SELECT s FROM ShoppingCart s WHERE s.cardReference = :cardReference"),
    @NamedQuery(name = "ShoppingCart.findByCreatedOn", query = "SELECT s FROM ShoppingCart s WHERE s.createdOn = :createdOn"),
    @NamedQuery(name = "ShoppingCart.findByModifiedOn", query = "SELECT s FROM ShoppingCart s WHERE s.modifiedOn = :modifiedOn")})
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CART_ID")
    private Integer cartId;
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Column(name = "USER_ID")
    private Integer userId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Size(max = 500)
    @Column(name = "CARD_REFERENCE")
    private String cardReference;
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

    public ShoppingCart() {
    }

    public ShoppingCart(Integer cartId) {
        this.cartId = cartId;
    }

    public ShoppingCart(Integer cartId, Date createdOn, Date modifiedOn) {
        this.cartId = cartId;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCardReference() {
        return cardReference;
    }

    public void setCardReference(String cardReference) {
        this.cardReference = cardReference;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartId != null ? cartId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShoppingCart)) {
            return false;
        }
        ShoppingCart other = (ShoppingCart) object;
        if ((this.cartId == null && other.cartId != null) || (this.cartId != null && !this.cartId.equals(other.cartId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClass.ShoppingCart[ cartId=" + cartId + " ]";
    }
}
