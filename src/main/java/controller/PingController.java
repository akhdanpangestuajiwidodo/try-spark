package controller;

import static spark.Spark.get;

public class PingController extends AbstractController{
    public PingController(){

    }

    @Override
    public void registerApi(){
        get("/ping", ((request, response) -> "Aman Maszeh"));
    }
}
