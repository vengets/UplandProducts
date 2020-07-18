package services;

import javafx.util.Pair;
import models.Product;
import models.Sale;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;


public class SalesService {

    List<Sale> sales;
    ProductService productService;

    public SalesService(List<Sale> salesList, ProductService productService) {
        this.sales = salesList;
        this.productService = productService;
    }

    public double getHighestAverageSale() {
        List<Product> products = productService.getProducts();

        OptionalDouble average = sales.stream().map(sale -> sale.getPrice()).mapToDouble((a) -> a).average();
        if(average.isPresent())
            return average.getAsDouble();
        else return 0;
    }

    public Pair<String, Double> getHighestAverageSaleCategory() {
        List<String> categories = productService.getCategories();
        double highestAverage = 0;
        String resultCategory = "";
        for(var category: categories){
            OptionalDouble localAverage = this.getSalesForCategory(category).stream()
                    .map(sale -> sale.getPrice())
                    .mapToDouble((a)->a).average();
            if(localAverage.isPresent() && localAverage.getAsDouble()>highestAverage) {
                highestAverage = localAverage.getAsDouble();
                resultCategory = category;
            }
        }
        return new Pair<>(resultCategory, highestAverage);
    }

    public double getMinimumSaleForCategory(String categoryName) {
        OptionalDouble optionalDouble = getSalesForCategory(categoryName).stream().map(sale -> sale.getPrice()).mapToDouble((a) -> a).min();
        if(optionalDouble.isPresent())
            return optionalDouble.getAsDouble();
        return 0;
    }

    public double getMaximumSaleForCategory(String categoryName) {
        OptionalDouble optionalDouble = getSalesForCategory(categoryName).stream().map(sale -> sale.getPrice()).mapToDouble((a) -> a).max();
        if(optionalDouble.isPresent())
            return optionalDouble.getAsDouble();
        return 0;
    }

    private List<Sale> getSalesForCategory(String categoryName) {
        var products = productService.getProductsForCategory(categoryName)
                .stream().map(product -> product.getName()).collect(Collectors.toList());
        return sales.stream().filter(sale -> products.contains(sale.getProductName())).collect(Collectors.toList());
    }
}

