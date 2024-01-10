package com.example.foodapp;

public class modelProduct
{
    private String id_product;
    private String name_product;
    private String price_product;
    private String dis_product;
    private String type_product;
    private String pic_product;

    public modelProduct(String id_product, String name_product, String price_product, String dis_product, String type_product,String pic_product) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.price_product = price_product;
        this.dis_product = dis_product;
        this.type_product = type_product;
        this.pic_product = pic_product;
    }

    public modelProduct(String name_product, String price_product, String dis_product ,String pic_product) {
        this.name_product = name_product;
        this.price_product = price_product;
        this.dis_product = dis_product;
        this.pic_product = pic_product;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getPrice_product() {
        return price_product;
    }

    public void setPrice_product(String price_product) {
        this.price_product = price_product;
    }

    public String getDis_product() {
        return dis_product;
    }

    public void setDis_product(String dis_product) {
        this.dis_product = dis_product;
    }

    public String getType_product() {
        return type_product;
    }

    public void setType_product(String type_product) {
        this.type_product = type_product;
    }

    public String getPic_product() {
        return pic_product;
    }

    public void setPic_product(String pic_product) {
        this.pic_product = pic_product;
    }
}

