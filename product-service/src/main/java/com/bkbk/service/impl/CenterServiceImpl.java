package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bkbk.entity.AllocationList;
import com.bkbk.entity.Inventory;
import com.bkbk.entity.Product;
import com.bkbk.entity.TaskList;
import com.bkbk.entity.form.ProductForm;
import com.bkbk.mapper.AllocationListMapper;
import com.bkbk.mapper.InventoryMapper;
import com.bkbk.mapper.ProductMapper;
import com.bkbk.mapper.TaskListMapper;
import com.bkbk.service.CenterService;
import com.bkbk.vo.ResultVo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static com.bkbk.util.Upload.multipartFileToFile;

@Service
public class CenterServiceImpl implements CenterService {


    @Autowired
    private AllocationListMapper allocationListMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private TaskListMapper taskListMapper;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public ResultVo getAllocationList(String keyword, Integer pageNumber, Integer pageSize) {
        QueryWrapper<AllocationList> queryWrapper = new QueryWrapper<>();
        if(keyword!=null && !keyword.isEmpty()){
            queryWrapper.like("id", keyword ).or().like("order_id",keyword);
        }
        queryWrapper.orderByDesc("create_time");
        PageHelper.startPage(pageNumber,pageSize);
        PageInfo pageInfo = new PageInfo( allocationListMapper.selectList(queryWrapper));
        return  ResultVo.success(pageInfo);
    }

    @Override
    public ResultVo allocationStart(AllocationList allocationList) {
        Integer id = allocationList.getId();

        AllocationList allocationList1 = allocationListMapper.selectById(id);

        if(allocationList1==null){
            return  ResultVo.fail("无此调度单");
        }
        else {
            if(allocationList1.getStatus()!=0){
                return ResultVo.fail("已分发");
            }
        }
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",allocationList1.getId());
        updateWrapper.set("status",1);
        Date now = new Date();
        updateWrapper.set("update_time",now);
        allocationListMapper.update(null, updateWrapper);

        return ResultVo.success();
    }

    @Override
    public ResultVo inventory(Integer productId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);

        return ResultVo.success(inventoryMapper.selectOne(queryWrapper));
    }

    @Override
    public ResultVo addProductNum(Integer productId, Integer num) {

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        Inventory inventory =  inventoryMapper.selectOne(queryWrapper);

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("product_id",productId);
        updateWrapper.set("quantity",inventory.getQuantity()+num);
        inventoryMapper.update(null,updateWrapper);

        return ResultVo.success();
    }

    @Override
    public ResultVo substationTaskListForCenter(String keyword, Integer pageNumber, Integer pageSize) {
        QueryWrapper<TaskList> queryWrapper = new QueryWrapper<>();
        if(keyword!=null && !keyword.isEmpty()){

            queryWrapper    .like("id", keyword ).or().like("order_id",keyword);
        }
        queryWrapper.eq("status",4);
        queryWrapper.orderByDesc("create_time");

        PageHelper.startPage(pageNumber,pageSize);
        PageInfo pageInfo = new PageInfo( taskListMapper.selectList(queryWrapper));

        return  ResultVo.success(pageInfo);
    }

    @Override
    public ResultVo newProduct(  MultipartFile[] img,  Map<String, Object> postInfo ) {



        if (null == img &&  img.length==0) {
            return ResultVo.fail("文件为空");
        }

        String mainImageUrl = "";
        if(mainImageUrl.equals("异常")){
            return ResultVo.fail(mainImageUrl);
        }
        String subImagesUrls = "";
        int i = 1;
        for (MultipartFile subImage : img) {
            String t =  doPostFormData(subImage);
            if(t.equals("异常")){
                return ResultVo.fail(t);
            }
            if(i==1){
                mainImageUrl = t;
            }else{
                subImagesUrls += t +',';
            }
            i++;
        }

        Product product = new Product();

        product.setCategoryId(  (Integer) postInfo.get("categoryId")   );
        product.setName(  (String) postInfo.get("name")   );
        product.setDetail(  (String) postInfo.get("detail")   );
        product.setPrice(  Double.parseDouble((String)postInfo.get("price"))   );
        product.setSubTitle(  (String) postInfo.get("subTitle")   );

        product.setMainImage(mainImageUrl);
        product.setSubImages(subImagesUrls);
        Date now = new Date();
        product.setCreateTime(now);
        product.setUpdateTime(now);
       productMapper.insert(product);

        Long cnt = inventoryMapper.selectCount(null);
        Inventory inventory = new Inventory();
        inventory.setProductId((int) (cnt+1));
        inventory.setAssigned(0);
        inventory.setQuantity(0);
        inventory.setCreateTime(now);
        inventory.setUpdateTime(now);
       inventoryMapper.insert(inventory);
        return ResultVo.success();
    }


    public   String doPostFormData(MultipartFile file) {



        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("multipart/form-data; boundary=--------------------------500812903788481473868931");

            String newFileName = UUID.randomUUID().toString()+file.getOriginalFilename();

            File tempfile =  multipartFileToFile(file,newFileName);

            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("smfile", newFileName,
                            RequestBody.create(MediaType.parse("application/octet-stream"),
                                    tempfile  ))
                    .build();
            Request request = new Request.Builder()
                    .url("https://smms.app/api/v2/upload")
                    .method("POST", body)
                    .addHeader("Authorization", "ZvaUPBNk2eUGSkOLO7fSlc4soUj7zUNb")
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .addHeader("Accept", "*/*")
                    .addHeader("Host", "smms.app")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Content-Type", "multipart/form-data; boundary=--------------------------500812903788481473868931")
                    .addHeader("Cookie", "PHPSESSID=5babcaa3-e606-bf37-812b-5e8b880a0cad; smms=rIuamMGvf7qF2K8NHiLOsPQSodVzb0Y9")
                    .build();
            Response  response = client.newCall(request).execute();
            String res = response.body().string();

            tempfile.delete();

            ObjectMapper mapper =new ObjectMapper();
            JsonNode node = mapper.readTree(res);


            if( node.get("success").toString().equals("true") ){
                node = node.get("data");
                String imageurl = node.get("url").toString();
                String imagedelete = node.get("delete").toString();
                imageurl = imageurl.trim().replace("\"", "");
//                System.out.println("成功");
//                System.out.println("imageurl:"+ imageurl);
//                System.out.println("imagedelete" + imagedelete);
                return  imageurl;

            }else if(node.get("message").toString().contains("Image upload repeated limit")){
                System.out.println("已存在");
                String imageurl = node.get("images").toString();
                imageurl = imageurl.trim().replace("\"", "");
                return imageurl;
            }else{
                System.out.println("异常");
                return "异常";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
