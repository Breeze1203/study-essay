package org.javaboy.vhr.controller;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.JObLevel;
import org.javaboy.vhr.service.JobLevelService;
import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Breeze
 * @address Shenzhen China
 * @email 3548297839@qq.com
 */
@RestController
@RequestMapping("/api/sys/basic")
public class JobLevelController {
    @Resource(name = "JobLevelService")
    JobLevelService jobLevelService;

    @GetMapping("/getAllJobLevel")
    public List<JObLevel> getAllJobLevel() {
        return jobLevelService.getAllJobLevel();
    }

    // 添加职称
    @PostMapping("/addJobLevel")
    public StatusUtils addJobLevel(@RequestParam("name") String name,
                                   @RequestParam("titleLevel") String titleJobLevel, @RequestParam("enabled") boolean enabled) {
        int i = jobLevelService.addJobLevel(new JObLevel(name, titleJobLevel, new Date(), enabled));
        if (i == 1) {
            return new StatusUtils("添加成功");
        } else {
            return new StatusUtils("数据异常，请稍后再试");
        }
    }

    @PostMapping("/deleteJobLevel")
    public StatusUtils deleteJobLevel(@RequestParam("id") Integer id) {
        int i = jobLevelService.deleteJobLevel(id);
        if (i == 1) {
            return new StatusUtils("删除成功");
        } else {
            return new StatusUtils("数据异常，请稍后再试");
        }
    }

    @PostMapping("/updateJobLevel")
    public StatusUtils updateJobLevel(@RequestParam("id") Integer id, @RequestParam("name") String name,
                                      @RequestParam("titleLevel") String titleJobLevel, @RequestParam("enabled") boolean enabled) {
        int i = jobLevelService.updateJobLevel(new JObLevel(id, name, titleJobLevel, new Date(), enabled));
        if (i == 1) {
            return new StatusUtils("修改成功");
        } else {
            return new StatusUtils("数据异常，请稍后再试");
        }
    }

    @PostMapping("/deleteJobLevelByIds")
    public StatusUtils deleteJobLevelIds(@RequestParam("ids") Integer[] ids) {
        int i = jobLevelService.deleteJobIds(ids);
        if (i == ids.length) {
            return new StatusUtils("删除成功");
        } else {
            return new StatusUtils("数据异常，请稍后再试");
        }
    }
}
