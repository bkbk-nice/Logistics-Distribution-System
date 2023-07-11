package com.bkbk.entity.form;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ProductForm {


    @NotBlank(message = "种类不能为空")
    private Integer categoryId;

    @NotBlank(message = "名称不能为空")
    private String name;


    @NotBlank(message = "副标题不能为空")
    private String subTitle;

    @NotBlank(message = "详细描述不能为空")
    private String detail;

    @NotBlank(message = "价格不能为空")
    private Double price;

    public ProductForm() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
