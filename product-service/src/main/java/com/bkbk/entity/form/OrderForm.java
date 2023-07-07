package com.bkbk.entity.form;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class OrderForm {


   // @NotBlank(message = "商品id不能为空")
    private Integer productId;
   //@NotBlank(message = "数量不能为空")
    private Integer num;

    @NotBlank(message = "手机号不能为空")
    private String phone;
    @NotBlank(message = "地址不能为空")
    private String address;
    //@NotBlank(message = "备注不能为空")
    private String remark;
   // @NotBlank(message = "时间不能为空")
    private Date time;
   // @NotBlank(message = "类型不能为空")
    private  Integer type;


    public OrderForm() {

    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
