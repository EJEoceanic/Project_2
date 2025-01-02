package org.example;

import com.google.gson.JsonElement;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProductRequest request = new ProductRequest("http://localhost:8080/v1/product");

        JsonElement outputById = request.getSupplierProductsBySupplierId("152");
        JsonElement outputByRfc = request.getSupplierProductsBySupplierRFC("YZAA41skdfjhgs0316JO3");

        System.out.println(outputById.toString());
        System.out.println(outputByRfc.toString());

    }


}