package models;

public class Sale {
    private String productName;
    private double price;

    public Sale(String fileContent) {
        String[] content = fileContent.split("\t");
        if(content.length!=2) {
            throw new RuntimeException("Invalid sales record: " + fileContent);
        }
        this.productName = content[0];
        this.price = Double.parseDouble(content[1]);
    }

    public Sale(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return this.productName;
    }

    public double getPrice() {
        return this.price;
    }
}
