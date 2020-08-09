package com.company.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.bean.Admin;
import com.company.service.AdminService;
import com.company.service.BooksService;
import com.company.service.impl.AdminServiceImpl;
import com.company.service.impl.BooksServiceImpl;
import com.company.utils.CosUtil;
import com.company.utils.StringUtil;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/fileServlet")
public class FileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 文件上传的存储路径
    private static final String SAVE_PATH = "image";

    // 上传配置
    // 配置内存临界值
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
    // 配置最大文件大小
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    // 配置请求大小
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    private AdminService adminService = new AdminServiceImpl();
    private BooksService booksService  = new BooksServiceImpl();

    InputStream inp = FileServlet.class.getResourceAsStream("/jdbc.properties");
    Properties p = new Properties();


    public FileServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 解决post请求中文乱码问题
        req.setCharacterEncoding("UTF-8");
        // 解决响应的中文乱码
        resp.setContentType("text/html; charset=UTF-8");
        resp.addHeader("Access-Control-Allow-Origin","*");
        String action = req.getParameter("action");

        String updatePara = req.getParameter("updatePara");
        p.load(inp);
        String projectPath = p.getProperty("filepath");
        switch (action){
            case "uploadUserImg":
                String UserImgUploadPath = updatePara;
                System.out.println("UserImgUploadPath:" + UserImgUploadPath);
                upload(req,resp,UserImgUploadPath,1);

                break;
            case "uploadBookImg":
                String bookImgPath = p.getProperty("bookImgPath");
                String bookUploadPath = projectPath + "\\"+ bookImgPath ;
                upload(req,resp,bookUploadPath,2);
                break;

            case "addBookImg":
                String bookImgPath2 = p.getProperty("bookImgPath");
                String bookUploadPath2 = projectPath + "\\"+ bookImgPath2 ;
                upload(req,resp,bookUploadPath2,3);
                break;
        }
    }


    /**
     * 上传图片
     * @param request
     * @param response
     * @param path      上传图片的路径
     * @param flag      1：代表上传管理员图像        2：代表更换上传书籍图片   3:添加上传书籍图片
     * @throws ServletException
     * @throws IOException
     */
    protected void upload(HttpServletRequest request, HttpServletResponse response,String path,int flag)
            throws ServletException, IOException {

        // 检测是否为多媒体文件
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter out = response.getWriter();
            out.println("表单必须包含 enctype=multipart/form-data");
            out.flush();
            out.close();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置上传文件最大值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // 设置最大请求值(包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        //获取前端发来的上传标志，可能是管理员用户名或图书ID
        String updatePara = request.getParameter("updatePara");

        Map<String,Object> map = new HashMap<>();

        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                //迭代表单数据
                for (FileItem fileItem : formItems) {
                    //处理不在表单的字段
                    if(!fileItem.isFormField()) {
                        //获取原文件名字
                        String fileName1 = new File(fileItem.getName()).getName();
                        //编写存放的文件名字（当前时间+文件后缀名）
                        String fileName = StringUtil.createNewNameByDate()+"."+StringUtil.subFileType(fileName1);

                        File storeFile = new File(fileName);
                        // 在控制台输出文件的上传路径
                        System.out.println(fileName);
                        // 保存文件到硬盘
                        fileItem.write(storeFile);

                        int code = -1;
                        if(flag == 1){
                            //上传图片到对象存储
                            CosUtil.upLoad(updatePara,storeFile);
                            //删除本地的临时文件
                            storeFile.deleteOnExit();
                            code = adminService.updateUserImge(updatePara,fileName);
                        }else if(flag == 2){
                            p.load(inp);
                            String bookImgPath = p.getProperty("bookImgPath");
                            System.out.println(bookImgPath);
                            //上传图片到对象存储
                            CosUtil.upLoad(bookImgPath,storeFile);
                            //删除本地的临时文件
                            storeFile.deleteOnExit();
                            code = booksService.updateBookImg(updatePara,bookImgPath+"\\"+fileName);
                        }else if(flag == 3){
                            code = 1;
                        }

                        if(code == 1){
                            map.put("code",0);
                            map.put("imgPath",fileName);
                        }else {
                            map.put("code",2);
                        }

                    }
                }
            }
        } catch (Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            map.put("code",1);
        }finally {
            Gson gson = new Gson();
            String json = gson.toJson(map);
            response.getWriter().write(json);
        }
    }
}
