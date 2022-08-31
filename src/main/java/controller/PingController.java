package controller;

import static spark.Spark.get;

public class PingController {
    public PingController(){
        get("/ping", ((request, response) -> "Aman Maszeh"));
    }
}
