package com.dev.godoy.almox.api;

import com.dev.godoy.almox.api.config.Config;
import com.dev.godoy.almox.api.controllers.ProductController;
import com.dev.godoy.almox.api.controllers.WarehouseController;
import com.dev.godoy.almox.api.repositories.ProductRepository;
import com.dev.godoy.almox.api.repositories.WarehouseRepository;
import com.dev.godoy.almox.api.services.ProductService;
import com.dev.godoy.almox.api.services.WarehouseService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Application {

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(Config.getApplicationPort());

        MongoClient client = Config.getMongoClient();
        MongoDatabase database = client.getDatabase(Config.getDatabase());

        ProductRepository productRepository = new ProductRepository(database);
        ProductService productService = new ProductService(productRepository);
        ProductController productController = new ProductController(productService);

        WarehouseRepository warehouseRepository = new WarehouseRepository(database);
        WarehouseService warehouseService = new WarehouseService(warehouseRepository);
        WarehouseController warehouseController = new WarehouseController(warehouseService);

        app.routes(() -> {
            path("product", () -> {
                get(productController::get);
                post(productController::save);
                path("{productCode}", () -> {
                    get(productController::getByCode);
                    put(productController::update);
                    delete(productController::delete);
                });
            });
            path("warehouse", () -> {
                get(warehouseController::get);
                post(warehouseController::post);
                path("{number}", () -> {
                    get(warehouseController::getByNumber);
                    put(warehouseController::put);
                    delete(warehouseController::delete);
                });
            });
        });
    }
}