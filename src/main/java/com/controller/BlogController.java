package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dao.BlogDao;
import com.model.Blog;

import java.util.List;
import java.util.Map;


/**
 * Blog控制器
 * @author luoxn28
 * @date 2016.6.18
 */
@Controller
@RequestMapping
public class BlogController {

    // blog 表操作类
    @Autowired
    private BlogDao blogDao;

    @RequestMapping("/blog")
    public String handleRequest(@RequestParam(value="p")int p, Map<String, Object> map) {
        // 读取对应 blog(s)
        if (p == 0) {
            List<Blog> blogs = blogDao.getAll();
            map.put("blogs", blogs);
            return "blogs";
        } else {
            Blog blog = blogDao.getById(p);
            map.put("blog", blog);
            return "blog";
        }
    }

    @RequestMapping("/category")
    public String handleCategoryRequest(@RequestParam(value="category")String category, Map<String, Object> map) {
        // 根据种类查找数据库
        List<Blog> blogs = blogDao.getByCategory(category);
        map.put("blogs", blogs);
        return "blogs";
    }

    @RequestMapping("/search")
    public String handleBlogsRequest(@RequestParam(value="blogName")String blogName, Map<String, Object> map) {
        // 根据种类查找数据库
        List<Blog> blogs = blogDao.getByBlogName(blogName);
        map.put("blogs", blogs);
        return "blogs";
    }

    @RequestMapping("/delete")
    public String deleteBlog(@RequestParam(value="id")int id, Map<String, Object> map) {
        // 删除
        blogDao.delete(id);
        List<Blog> blogs = blogDao.getByBlogName("");
        map.put("blogs", blogs);
        return "manager";
    }
}

