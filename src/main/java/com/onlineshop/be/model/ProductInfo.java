package com.onlineshop.be.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private String id;

    @Column
    private String productName;

    @Column
    private Double productPrice;

    @Column
    private Integer productStock;
    @Column
    private String productDescription;
    @Column
    private String productIcon;
    @Column
    private Integer productStatus;
    @Column
    private Integer categoryType;

    @Column
    private Date createTime;
    @Column
    private Date updateTime;
}
