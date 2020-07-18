package models;

public class Product {
    private String name;
    private String categoryName;

    public Product(String fileContent) {
        String[] content = fileContent.split("\t");
        if(content.length!=2) {
            throw new RuntimeException("Invalid Product record: " + fileContent);
        }
        this.name = content[0];
        this.categoryName = content[1];
    }

    public Product(String name, String categoryName) {
        this.name = name;
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
