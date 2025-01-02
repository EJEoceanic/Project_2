package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpResponse;

public class ProductRequest {
    private final String baseUri;

    public ProductRequest(String baseUri){
        this.baseUri = baseUri;
    }

    private JsonElement getJsonOutput(String productList, Integer statusCode){
        JsonArray rawProducts = (JsonArray) JsonParser.parseString(productList);
        JsonArray products = new JsonArray();

        for(JsonElement currentElement : rawProducts){
            JsonObject product = getJsonObject(currentElement);

            products.add(product);
        }

        JsonObject outJson = new JsonObject();
        outJson.addProperty("StatusCode", statusCode);
        outJson.add("products", products);


        return outJson;
    }

    private static JsonObject getJsonObject(JsonElement currentElement) {
        JsonObject currentProduct = currentElement.getAsJsonObject();
        JsonObject product = new JsonObject();

        product.addProperty("Nombre", currentProduct.get("name").toString());
        product.addProperty("Codigo", currentProduct.get("code").toString());
        product.addProperty("Detalles", currentProduct.get("details").toString());
        product.addProperty("Precio", currentProduct.get("price").getAsInt());

        JsonObject provider = currentProduct.get("supplier").getAsJsonObject();
        product.addProperty("Nombre_Provedor", provider.get("name").toString());
        return product;
    }

    public JsonElement getSupplierProductsBySupplierId(String supplierId) throws IOException, InterruptedException {
        String URI = baseUri + "?supplierId=" +supplierId;

        GetRequest getRequest = new GetRequest(URI);
        HttpResponse<String> getResponse = getRequest.send();

        return getJsonOutput(getResponse.body(), getResponse.statusCode());
    }

    public JsonElement getSupplierProductsBySupplierRFC(String supplierRFC) throws IOException, InterruptedException {
        String URI = baseUri + "?supplierRfc=" +supplierRFC;

        GetRequest getRequest = new GetRequest(URI);
        HttpResponse<String> getResponse = getRequest.send();

        return getJsonOutput(getResponse.body(), getResponse.statusCode());
    }
}
