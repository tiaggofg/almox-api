package com.dev.godoy.almox.api;

import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);

    }
}