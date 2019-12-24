package com.withstars.controller;

import com.withstars.domain.Reply;
import com.withstars.domain.Tab;
import com.withstars.domain.Topic;
import com.withstars.domain.User;
import com.withstars.service.impl.ReplyServiceImpl;
import com.withstars.service.impl.TabServiceImpl;
import com.withstars.service.impl.TopicServiceImpl;
import com.withstars.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class TabController {

    @Autowired
    public TopicServiceImpl topicService;
    @Autowired
    public ReplyServiceImpl replyService;
    @Autowired
    public UserServiceImpl userService;
    @Autowired
    public TabServiceImpl tabService;

    /**
     * 增加板块操作
     * @param request
     * @return
     */
    @RequestMapping("/tab/add")
    public String addtab(HttpServletRequest request){
        Tab tab=new Tab();
        tab.setId(Integer.parseInt(request.getParameter("id")));
        tab.setTabName(request.getParameter("tab_name"));
        tab.setTabNameEn(request.getParameter("tab_name_en"));
        tabService.insert(tab);
        return "redirect:/";
    }



    /**
     * 管理员增加板块前期工作
     */
    @RequestMapping(value = "/newtab")
    public ModelAndView adda(HttpSession session){

        ModelAndView mv=new ModelAndView("addbankuai");
        //获取统计信息
        int topicsNum=topicService.getTopicsNum();
        int usersNum=userService.getUserCount();

        //获取用户信息
        Integer uid=(Integer) session.getAttribute("userId");
        User resultUser=userService.getUserById(uid);

        User user=userService.getUserById(uid);
        //最热主题
        List<Topic> hotestTopics=topicService.listMostCommentsTopics();


        mv.addObject("hotestTopics",hotestTopics);
        List<Tab> tabs=tabService.getAllTabs();
        mv.addObject("userInfo",resultUser);
        mv.addObject("topicsNum",topicsNum);
        mv.addObject("usersNum",usersNum);
        mv.addObject("user",user);
        mv.addObject("tabs",tabs);
        return mv;

    }

    @RequestMapping("/taball")
    public ModelAndView taball(HttpSession session){
        List<Tab> tabs=tabService.getAllTabs();
        ModelAndView modelAndView=new ModelAndView("tab");
        int topicsNum=topicService.getTopicsNum();
        int usersNum=userService.getUserCount();
        modelAndView.addObject("topicsNum",topicsNum);
        modelAndView.addObject("usersNum",usersNum);
        modelAndView.addObject("user",userService.getUserById((Integer) session.getAttribute("userId")));
        modelAndView.addObject("tabs",tabs);
        return modelAndView;
    }

    /**
     * 删除板块
     */
    @RequestMapping("/tabdelete/{id}")
    public String deletepage(@PathVariable("id")String id,HttpSession session){
        tabService.deleteByPrimaryKey((Integer.parseInt(id)));
        return "redirect:/taball";
    }

    /**
     获得帖子信息，用户数据回显
     **/
    @RequestMapping("/tabhuixian/{name}")
    public ModelAndView getTopicInfo(@PathVariable("name")String name,HttpSession session){
        Tab tab=tabService.getByTabNameEn(name);
        ModelAndView mv=new ModelAndView("updatetab");
        int topicsNum=topicService.getTopicsNum();
        int usersNum=userService.getUserCount();
        mv.addObject("topicsNum",topicsNum);
        mv.addObject("usersNum",usersNum);
        mv.addObject("user",userService.getUserById((Integer)session.getAttribute("userId")));
        mv.addObject("tab",tab);
        return mv;
    }

    /**
     * 修改帖子内容
     * @param id
     * @param request
     * @return
     */

    @RequestMapping(value = "/tabupdate/{id}", method = RequestMethod.POST)
    public String updateTopic(@PathVariable("id")String id,HttpServletRequest request){
        Tab tab=new Tab();
        tab.setId(Integer.parseInt(request.getParameter("id")));
        tab.setTabName(request.getParameter("tab_name"));
        tabService.updateByPrimaryKeySelective(tab);
        return "redirect:/taball";

    }
}
