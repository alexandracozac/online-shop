package org.fasttrackit.onlineshop.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) //incarca info doar daca cer, nu automat
    @MapsId
    private Customer customer;

    @ManyToMany(cascade = CascadeType.MERGE)  //stergere prod din cos, nu din tabela in cascada prin merge
    @JoinTable(name = "cart_product",
    joinColumns = @JoinColumn(name = "cart_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))

    private Set<Product> products = new HashSet<>(); //colectie de produse unicate

    public void addToCart(Product product){
        //adding product in current cart
        products.add(product);

        //adding current cart to the carts set of the received product
        product.getCarts().add(this);

    }

    public void removeFromCart(Product product){

        products.remove(product);

        product.getCarts().remove(this);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }




}
