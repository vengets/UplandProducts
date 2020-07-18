import models.Product;
import models.Sale;
import services.FileReader;
import services.ProductService;
import services.SalesService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UplandProductsApplication {
    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader();
        List<String> rows = fileReader.readFile("products.tab");

        final List<Product> products = rows.stream().map(row -> new Product(row)).collect(Collectors.toList());

        rows = fileReader.readFile("sales.tab");
        List<Sale> sales =   rows.stream().map(row -> new Sale(row)).collect(Collectors.toList());

        SalesService salesService = new SalesService(sales, new ProductService(products));

        System.out.println("What category has the highest average sales price?");
        var result = salesService.getHighestAverageSaleCategory();
        System.out.println(result.getKey());
        System.out.println(result.getValue());
        System.out.println("What is the minimum and maximum sale in the category 'Breakfast'");
        System.out.println(salesService.getMinimumSaleForCategory("Breakfast"));
        System.out.println(salesService.getMaximumSaleForCategory("Breakfast"));

    }


}
