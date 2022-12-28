package com.dev.godoy.almox.api.repositories;

import com.dev.godoy.almox.api.exceptions.ObjectNotFoundException;
import com.dev.godoy.almox.api.models.Warehouse;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class WarehouseRepository {

    private MongoCollection<Warehouse> collection;

    public WarehouseRepository(MongoDatabase database) {
        collection = database.getCollection("warehouse", Warehouse.class);
    }

    public List<Warehouse> findAll() {
        List<Warehouse> result = new ArrayList<>();
        for (Warehouse w : collection.find()) {
            result.add(w);
        }
        return result;
    }

    public Warehouse findByNumber(int number) {
        Warehouse warehouse = collection.find().filter(eq("number", number)).first();
        if (warehouse == null) {
            throw new ObjectNotFoundException("Almoxarifado número: " + number + " não encontrado!");
        }
        return warehouse;
    }

    public Warehouse save(Warehouse warehouse) {
        warehouse.setId(new ObjectId().toString());
        collection.insertOne(warehouse);
        return warehouse;
    }

    public void update(int number, Warehouse warehouse) {
        Warehouse oldWarehouse = findByNumber(number);
        warehouse.setId(oldWarehouse.getId());
        Bson filter = eq("number", number);
        collection.findOneAndReplace(filter, warehouse);
    }

    public void delete(int number) {
        Warehouse warehouse = collection.findOneAndDelete(eq("number", number));
        if (warehouse == null) {
            throw new ObjectNotFoundException("Almoxarifado número: " + number + " não encontrado!");
        }
    }
}
