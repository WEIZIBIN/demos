package github.weizibin.po;

import java.util.Date;

public class KillGoods {
    private Integer id;

    private String name;

    private Integer stock;

    private Boolean inStock;

    private Date startKillTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Date getStartKillTime() {
        return startKillTime;
    }

    public void setStartKillTime(Date startKillTime) {
        this.startKillTime = startKillTime;
    }
}