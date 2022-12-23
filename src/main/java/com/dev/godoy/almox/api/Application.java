package com.dev.godoy.almox.api;

import com.dev.godoy.almox.api.config.Config;
import com.dev.godoy.almox.api.controllers.ProductController;
import com.dev.godoy.almox.api.repositories.ProductRepository;
import com.dev.godoy.almox.api.services.ProductService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Application {

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(Config.getApplicationPort());

        MongoClient client = Config.getMongoClient();
        MongoDatabase database = client.getDatabase(Config.getDatabase());

        ProductRepository repository = new ProductRepository(database);
        ProductService service = new ProductService(repository);
        ProductController productController = new ProductController(service);

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
        });
    }
}