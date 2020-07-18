package services;

import models.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {
    List<Product> products;

    public ProductService(List<Product> productList) {
        this.products = productList;
    }

    public String getCategory(String productName) {
        Optional<Product> foundProduct = products.stream().filter(p -> p.getName().equals(productName)).findAny();
        if(foundProduct.isPresent())
            return foundProduct.get().getCategoryName();
        return "Uncategorised";
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<String> getCategories() {
        var temp = products.stream().map(product -> ((Product)product).getCategoryName()).collect(Collectors.toList());
        return temp.stream().distinct().collect(Collectors.toList());
    }

    public List<Product> getProductsForCategory(String categoryName) {
        return products.stream().filter(product -> product.getCategoryName()
                .equals(categoryName)).collect(Collectors.toList());
    }
}
