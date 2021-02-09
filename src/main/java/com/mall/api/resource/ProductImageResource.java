package com.mall.api.resource;

import com.mall.api.response.Response;
import com.mall.api.response.Responses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping(value = "images")
public class ProductImageResource {

    private final Logger logger = LoggerFactory.getLogger(ProductImageResource.class);

    @Value("${web.upload-path}")
    private String filePath;

    @PostMapping(value = "upload")
    public Response uploadImage(@RequestParam(value = "image") MultipartFile file,
                                HttpServletRequest request) {
        if (file.isEmpty()) {
            return Responses.errorResponse("图片不能为空");
        }

        String originFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originFileName.substring(originFileName.lastIndexOf("."));
        File dest = new File(filePath + "/" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
            String serverName = request.getServerName();
            int port = request.getServerPort();
            String url = "http://" + serverName + ":" + port + "/" + fileName;
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("url", url);
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Responses.errorResponse("上传失败");
    }

}
