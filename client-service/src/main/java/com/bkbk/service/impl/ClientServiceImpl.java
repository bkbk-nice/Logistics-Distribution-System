package com.bkbk.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bkbk.entity.Client;
import com.bkbk.entity.form.ClientPassword;
import com.bkbk.entity.vo.ClientInfo;
import com.bkbk.form.ClientForm;
import com.bkbk.mapper.ClientMapper;
import com.bkbk.service.ClientService;

import com.bkbk.vo.ResultVo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.*;
import java.util.Date;
import java.util.UUID;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientMapper clientMapper;
    @Override
    public ResultVo uploadAvatar(MultipartFile file,  Integer id)   {

        delLastAvatar(id);

        if (null == file && !file.isEmpty()) {
            return ResultVo.fail("文件为空");
        }
        if (null == id  ) {
            return ResultVo.fail("id为空");
        }
        String re = doPostFormData(file,id);
        if(re.equals("异常")){
            return ResultVo.fail(re);
        }
        return ResultVo.success(re);
    }

    @Override
    public ResultVo getAvatar(Integer id) {
        Client client =  clientMapper.selectById(id);
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setAvatatUrl(client.getImageUrl());
        clientInfo.setId(id);
        clientInfo.setName(client.getName());
        return ResultVo.success(clientInfo);
    }



    @Override
    public ResultVo updatePassword(Integer id, ClientPassword clientPassword) {
        QueryWrapper<Client> queryWrapper = new QueryWrapper<Client>();
        queryWrapper.eq("id",id);
        Client client = clientMapper.selectOne(queryWrapper);

        if(client == null  || !passwordEncoder.matches(clientPassword.getPassword(), client.getPassword())  ){
            return ResultVo.fail("密码错误");
        }


        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("password",passwordEncoder.encode(   clientPassword.getNewPassword() ));

        clientMapper.update(null, updateWrapper);
        return ResultVo.success("修改密码成功");
    }

    @Override
    public ResultVo editClientInfo(Integer id, ClientForm clientForm) {
        //验证手机号 身份证号码 邮箱 （验证唯一）
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", clientForm.getPhone() ).ne("id",id);
        if(clientMapper.selectOne(queryWrapper)!=null){
            return ResultVo.fail("手机号已存在");
        }

        QueryWrapper<Client> queryWrapper4 = new QueryWrapper<>();
        queryWrapper4.eq("name", clientForm.getName() ).ne("id",id);
        if(clientMapper.selectOne(queryWrapper4)!=null){
            return ResultVo.fail("用户名已存在");
        }


        QueryWrapper<Client> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("identity",clientForm.getIdentityNumber()).ne("id",id);
        if(clientMapper.selectOne(queryWrapper2)!=null){
            return ResultVo.fail("身份证号码已存在");
        }


        QueryWrapper<Client> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("email",clientForm.getEmail()).ne("id",id);
        if(clientMapper.selectOne(queryWrapper3)!=null){
            return ResultVo.fail("邮箱已存在");
        }

        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("name",clientForm.getName());
        updateWrapper.set("phone",clientForm.getPhone());
        updateWrapper.set("address",clientForm.getAddress());
        updateWrapper.set("email",clientForm.getEmail());
        updateWrapper.set("identity",clientForm.getIdentityNumber());
        updateWrapper.set("updatetime", new Date());
        clientMapper.update(null, updateWrapper);

        return ResultVo.success("修改信息成功");
    }

    @Override
    public ResultVo getClientInfo(Integer id) {
        Client client =  clientMapper.selectById(id);
       ClientForm clientForm = new ClientForm();
       clientForm.setName(client.getName());
       clientForm.setAddress(client.getAddress());
       clientForm.setCreateTime(client.getCreateTime());
       clientForm.setEmail(client.getEmail());
       clientForm.setPhone(client.getPhone());
       clientForm.setIdentityNumber(client.getIdentityNumber());
       clientForm.setImageUrl(client.getImageUrl());
        return ResultVo.success(clientForm);
    }


    public   String doPostFormData( MultipartFile file ,Integer id) {



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
            JsonNode node = mapper.readTree(res);// 这里的JsonNode和XML里面的Node很像


            if( node.get("success").toString().equals("true") ){
                node = node.get("data");
                String imageurl = node.get("url").toString();
                String imagedelete = node.get("delete").toString();
                System.out.println("成功");
                System.out.println("imageurl:"+ imageurl);
                System.out.println("imagedelete" + imagedelete);
                //导入数据库
                imageurl = imageurl.trim().replace("\"", "");
                imagedelete = imagedelete.trim().replace("\"", "");


                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("id", id);
                updateWrapper.set("image_url",imageurl);
                updateWrapper.set("image_delete",imagedelete);

                clientMapper.update(null, updateWrapper);

                return  imageurl;

            }else if(node.get("message").toString().contains("Image upload repeated limit")){
                System.out.println("已存在");
                String imageurl = node.get("images").toString();
                imageurl = imageurl.trim().replace("\"", "");



                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("id", id);
                updateWrapper.set("image_url",imageurl);

                clientMapper.update(null, updateWrapper);
                System.out.println("imageurl:"+ imageurl);
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
    public static File multipartFileToFile(MultipartFile file ,String filename) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(filename);
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delLastAvatar(Integer id){

    }

}


//{
//        "success": false,
//        "code": "image_repeated",
//        "message": "Image upload repeated limit, this image exists at: https://s2.loli.net/2023/06/24/73e2OU9wxpmYrEn.png",
//        "images": "https://s2.loli.net/2023/06/24/73e2OU9wxpmYrEn.png",
//        "RequestId": "3094CF35-FCBF-4678-B034-862EA14AF127"
//        }