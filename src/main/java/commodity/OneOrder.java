package commodity;

public class OneOrder {
    //номер заказа
    private int idOrder;
    //товар
    private Commodity product;
    //количество товара
    private int quantity;
    // цена товара
    private int price;
    //цена всех товаров в корзине
    private int totalPrice;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Commodity getProduct() {
        return product;
    }

    public void setProduct(Commodity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
