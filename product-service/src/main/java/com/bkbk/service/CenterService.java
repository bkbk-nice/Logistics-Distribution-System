package com.bkbk.service;

import com.bkbk.entity.AllocationList;
import com.bkbk.entity.TaskList;
import com.bkbk.entity.form.ProductForm;
import com.bkbk.vo.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CenterService {

    ResultVo getAllocationList( String  keyword, Integer pageNumber, Integer pageSize);


    ResultVo allocationStart(AllocationList allocationList);

    ResultVo inventory(Integer productId);

    ResultVo addProductNum(Integer productId,Integer num);

    ResultVo substationTaskListForCenter(String  keyword, Integer pageNumber, Integer pageSize);

    ResultVo newProduct( MultipartFile[] img, Map<String, Object> postInfo  );
}
