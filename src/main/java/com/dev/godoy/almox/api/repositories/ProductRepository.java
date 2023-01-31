package com.dev.godoy.almox.api.repositories;

import com.dev.godoy.almox.api.dtos.ProductInvoiceDto;
import com.dev.godoy.almox.api.exceptions.ObjectNotFoundException;
import com.dev.godoy.almox.api.models.Product;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ProductRepository {

    MongoCollection<Product> collection;

    public ProductRepository(MongoDatabase database) {
        collection = database.getCollection("product", Product.class);
    }

    public List<Product> findAll() {
        List<Product> result = new ArrayList<>();
        for (Product p : collection.find()) {
            result.add(p);
        }
        return result;
    }

    public Product findByCode(String productCode) {
        Product product = collection.find(eq("code", productCode)).first();
        if (product == null) {
            throw new ObjectNotFoundException("Produto código: " + productCode + " não encontrado!");
        }
        return product;
    }

    public Product save(Product product) {
        product.setId(new ObjectId().toString());
        collection.insertOne(product);
        return product;
    }

    public void update(String code, Product product) {
        Product oldProduct = findByCode(code);
        product.setId(oldProduct.getId());
        product.setQuantity(oldProduct.getQuantity());
        Bson filter = eq("code", code);
        collection.findOneAndReplace(filter, product);
    }

    public void delete(String code) {
        Product product = collection.findOneAndDelete(eq("code", code));
        if (product == null) {
            throw new ObjectNotFoundException("Produto código: " + code + " não encontrado!");
        }
    }

    public static ProductInvoiceDto bsonDocumentToProduct(Document document) {
        String code = document.getString("code");
        String description = document.getString("description");
        String group = document.getString("group");
        String um = document.getString("um");
        double quantity = document.getDouble("quantity");
        double unitValue = document.getDouble("unitValue");
        double totalValue = document.getDouble("totalValue");
        return new ProductInvoiceDto(code, description, group, um, quantity, unitValue, totalValue);
    }
}
