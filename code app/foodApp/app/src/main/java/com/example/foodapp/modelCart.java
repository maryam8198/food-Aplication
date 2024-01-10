package com.example.foodapp;

public class modelCart
{
    private String id_cart ,id_product ,id_user ;
    int price;
    private String pic_product;


    public modelCart(String id_cart, String id_product, String id_user, int price, String pic_product) {
        this.id_cart = id_cart;
        this.id_product = id_product;
        this.id_user = id_user;
        this.price = price;
        this.pic_product = pic_product;
    }

    public modelCart(String id_product, String id_user, int price, String pic_product)  {
        this.id_product = id_product;
        this.id_user = id_user;
        this.price = price;
        this.pic_product = pic_product;
    }

    public modelCart(String id_product, int price) {
        this.id_product = id_product;
        this.price = price;
    }

    public modelCart() {
    }

    public String getId_cart() {
        return id_cart;
    }

    public void setId_cart(String id_cart) {
        this.id_cart = id_cart;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPic_product() {
        return pic_product;
    }

    public void setPic_product(String pic_product) {
        this.pic_product = pic_product;
    }
}
