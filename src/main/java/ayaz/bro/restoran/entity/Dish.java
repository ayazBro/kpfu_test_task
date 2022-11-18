package ayaz.bro.restoran.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private int rating;


    @ManyToOne()
    @JoinColumn(name="author_id",referencedColumnName = "id")
    private Cook cooker;

    @ManyToMany
    @JoinTable(
            name="recept",
            joinColumns = @JoinColumn(name="dish_id"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> ingridients;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="orders",
            joinColumns = @JoinColumn(name="dish_id"),
            inverseJoinColumns = @JoinColumn(name="client_id"))
    private List<Client> clients;

    public List<Product> getIngridients() {
        return ingridients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setIngridients(List<Product> ingridients) {
        this.ingridients = ingridients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Cook getCooker() {
        return cooker;
    }

    public void setCooker(Cook cooker) {
        this.cooker = cooker;
    }
}
