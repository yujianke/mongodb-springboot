package com.jbf.cn.controller;

import com.jbf.cn.model.Msg;
import com.jbf.cn.model.Person;
import com.jbf.cn.service.IpersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/mongo")
public class PersonController {
    @Resource
    private IpersonService ipersonService;

    @RequestMapping("/pagefind")
    public Msg pagefind(@RequestBody Map<String, Object> map) {
        PageRequest page = PageRequest.of(((Integer) map.get("pageNum")) - 1, (Integer) map.get("pageSize"));
        Page<Person> pageInfo = ipersonService.pagefind(
                map.get("name") == null ? "" : (String) map.get("name"),
                map.get("begin") == null ? 0 : (Integer) map.get("begin"),
                map.get("end") == null ? 100 : (Integer) map.get("end"),
                page);
        return Msg.success().add("data", pageInfo);
    }

    @RequestMapping("/findById/{id}")
    public Msg findById(@PathVariable("id") String id) {
        return Msg.success().add("data", ipersonService.findById(id));
    }

    @RequestMapping("/save")
    public Msg save(@RequestBody Person person) {
        person.set_id(UUID.randomUUID().toString());
        ipersonService.save(person);
        return Msg.success().add("data", "添加成功");
    }

    @RequestMapping("/update")
    public Msg update(@RequestBody Person person) {
        ipersonService.update(person);
        return Msg.success().add("data", "修改成功");
    }

    @RequestMapping("/updateAge/{id}")
    public Msg updateAge(@PathVariable("id") String _id) {
        ipersonService.updateAge(_id);
        return Msg.success().add("data", "修改成功");
    }

    @RequestMapping("/delete/{id}")
    public Msg delete(@PathVariable("id") String id) {
        ipersonService.deleteById(id);
        return Msg.success().add("data", "删除成功");
    }
}