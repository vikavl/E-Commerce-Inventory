package vikavl.cloud.computing.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import javax.xml.crypto.Data;

@Entity()
public class Product {
    @Id
    private Long id;
    private String sku;
    private String name;
    private String description;
    private float  price;
    private int stockQuantity;
    private Data createdAt;
    private Data updatedAt;

}
