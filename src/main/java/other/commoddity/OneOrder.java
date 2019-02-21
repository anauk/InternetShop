package other.commoddity;

public class OneOrder {
    //номер заказа
    private int idOrder;
    //номер товара
    private int productId;
    //товар
    private String productName;
    //количество товара
    private int quantity;
    // цена товара
    private int price;
    //цена всех товаров в корзине
    private int totalPrice;

    public OneOrder(int idOrder, int productId, String productName, int quantity, int price, int totalPrice) {
        this.idOrder = idOrder;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String product) {
        this.productName = product;
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
    @Override
    public String toString() {
        return String.format("ID: %d |ProductID: %d | Rroduct: %s | Quantity: %d| Price: %d | totalPrice: %d", this.idOrder, this.productId, this.productName,this.quantity,this.price,this.totalPrice);
    }
}
