package hackerRank.predicatePrec;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class MockQuestionProduct {
    interface ProductFilter{
        void addCondition(Predicate<Product> condition);
        List<Product> filter(List<Product> products);
    }

    static class ProductFilterImpl implements ProductFilter{
        private final List<Predicate<Product>> conditions = new ArrayList<>();

        @Override
        public void addCondition(Predicate<Product> condition) {
            conditions.add(condition);
        }

        @Override
        public List<Product> filter(List<Product> products) {
            Predicate<Product> combined = conditions.stream()
                    .reduce(p -> true, Predicate::and);
            return products.stream().filter(combined).toList();
        }
    }

    static class Product{
        private final int price;
        private final String category;
        private final boolean available;

        public Product(int price, String category, boolean available) {
            this.price = price;
            this.category = category;
            this.available = available;
        }

        public int getPrice() {
            return price;
        }

        public String getCategory() {
            return category;
        }

        public boolean isAvailable() {
            return available;
        }
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();


        ProductFilter pf = new ProductFilterImpl();
        pf.addCondition(p -> p.getPrice() >= 2000);
        pf.addCondition(p -> p.isAvailable());

        List<Product> result = pf.filter(products);

    }
}
