package com.dev.godoy.almox.api;

import com.dev.godoy.almox.api.config.Config;
import com.dev.godoy.almox.api.controllers.InvoiceController;
import com.dev.godoy.almox.api.controllers.PersonController;
import com.dev.godoy.almox.api.controllers.ProductController;
import com.dev.godoy.almox.api.controllers.WarehouseController;
import com.dev.godoy.almox.api.exceptions.DefaultError;
import com.dev.godoy.almox.api.exceptions.ObjectNotFoundException;
import com.dev.godoy.almox.api.exceptions.RequestException;
import com.dev.godoy.almox.api.repositories.InvoiceRepository;
import com.dev.godoy.almox.api.repositories.PersonRepository;
import com.dev.godoy.almox.api.repositories.ProductRepository;
import com.dev.godoy.almox.api.repositories.WarehouseRepository;
import com.dev.godoy.almox.api.services.InvoiceService;
import com.dev.godoy.almox.api.services.PersonService;
import com.dev.godoy.almox.api.services.ProductService;
import com.dev.godoy.almox.api.services.WarehouseService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

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

        PersonRepository personRepository = new PersonRepository(database);
        PersonService personService = new PersonService(personRepository);
        PersonController personController = new PersonController(personService);

        InvoiceRepository invoiceRepository = new InvoiceRepository(database);
        InvoiceService invoiceService = new InvoiceService(invoiceRepository);
        InvoiceController invoiceController = new InvoiceController(invoiceService);

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
            path("person", () -> {
                get(personController::getAll);
                post(personController::post);
                path("physical", () -> {
                    get(personController::getAllPhysicalPerson);
                });
                path("legal", () -> {
                    get(personController::getAllLegalPerson);
                });
                path("{document}", () -> {
                    get(personController::getByDocument);
                    put(personController::put);
                    delete(personController::delete);
                });
            });
            path("invoice", () -> {
                post(invoiceController::post);
                get(invoiceController::getAll);
                path("{invoiceNumber}", () -> {
                    get(invoiceController::getByNumber);
                    put(invoiceController::put);
                    delete(invoiceController::delete);
                });
            });
        });

        app.exception(ObjectNotFoundException.class, (e, ctx) -> {
            HttpStatus status = HttpStatus.NOT_FOUND;
            String errorMessage = e.getMessage();
            String path = ctx.path();
            DefaultError error = new DefaultError(status.toString(), errorMessage, path);
            ctx.json(error).status(status);
        });

        app.exception(RequestException.class, (e, ctx) -> {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            String errorMessage = e.getMessage();
            String path = ctx.path();
            DefaultError error = new DefaultError(status.toString(), errorMessage, path);
            ctx.json(error).status(status);
        });
    }
}