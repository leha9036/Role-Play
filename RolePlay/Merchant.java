package RolePlay;

public class Merchant implements Seller{
    public String sell(Goods goods) {
        String result = "";
        if (goods ==Goods.POTION) {
            result = "potion";
        }
        return result;
    }
    public enum Goods {
        POTION
    }
}
