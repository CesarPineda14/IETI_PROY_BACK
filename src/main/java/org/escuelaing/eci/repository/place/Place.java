package org.escuelaing.eci.repository.place;

import org.escuelaing.eci.repository.location.LocationA;
import org.escuelaing.eci.repository.product.Product;
import org.escuelaing.eci.repository.rating.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.io.Serializable;
import java.util.List;

@Document(collection = "place_collection")
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String description;
    private String passwordHash;
    private String foodType;
    private String value;
    private LocationA location;
    private Rating rating;
    private List<Product> products;


    public Place() {}

    public Place(String id, String name, String description, String password, String foodType, 
                 String value, LocationA location, Rating rating, List<Product> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
        this.foodType = foodType;
        this.value = value;
        this.location = location;
        this.rating = rating;
        this.products = products;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getFoodType() { return foodType; }
    public void setFoodType(String foodType) { this.foodType = foodType; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public LocationA getLocation() { return location; }
    public void setLocation(LocationA location) { this.location = location; }
    public Rating getRating() { return rating; }
    public void setRating(Rating rating) { this.rating = rating; }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", foodType='" + foodType + '\'' +
                ", value='" + value + '\'' +
                ", location=" + location +
                ", rating=" + rating +
                '}';
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

