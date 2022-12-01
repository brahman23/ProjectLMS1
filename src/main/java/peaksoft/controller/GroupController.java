package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import peaksoft.model.Group;
import peaksoft.service.GroupService;

import java.io.IOException;

@Controller
public class GroupController {
    private final GroupService groupService;


    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups/{id}")
    public String getAllGroups(@PathVariable Long id, Model model) {
        model.addAttribute("groups", groupService.getAllGroup(id));
        model.addAttribute("courseId", id);
        return "/group/groups";
    }

    @GetMapping("/groups/{id}/addGroup")
    public String addGroup(@PathVariable Long id, Model model) {
        model.addAttribute("newGroup", new Group());
        model.addAttribute("courseId", id);
        return "/group/addGroup";
    }


    @PostMapping("/{id}/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group,
                            @PathVariable Long id) {
        groupService.addGroup(id, group);
        return "redirect:/groups/"+ id;
    }



    @GetMapping("/updateGroup/{courseId}/{id}")
    public String updateGroup(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);

        model.addAttribute("courseId", courseId);
        model.addAttribute("id",id);
        return "/group/update_group";
    }
    @PostMapping("/{courseId}/{id}/saveUpdateGroup")
    public String saveUpdateCourse(@PathVariable("courseId") Long courseId,
                                   @PathVariable("id") Long id,
                                   @ModelAttribute("group") Group group) {
        groupService.updateGroup(group,id);
        return "redirect:/groups/"+courseId;
    }
//    @GetMapping("/updateGroup/{id}")
//    public String updateGroup(@PathVariable("id") Long id, Model model) {
//        Group group = groupService.getGroupById(id);
//        model.addAttribute("group", group);
//        model.addAttribute("courseId", id);
//        return "/group/update_group";
//    }
//    @GetMapping("/updateGroup/{courseId}/{id}")
//    public String updateGroupByCourseId(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId, Model model) {
//        Group group = groupService.getGroupById(id);
//        model.addAttribute("group", group);
//        model.addAttribute("courseId", courseId);
//        return "/group/update_group";
//}

//    @PostMapping("/{courseId}/{id}/saveUpdateGroup")
//    public String saveUpdateCourse(@PathVariable("courseId") Long companyId,
//                                   @PathVariable("id") Long id,
//                                   @ModelAttribute("group") Group group) {
//        groupService.updateGroup(group,id);
//        return "redirect:/groups/"+companyId;
//    }



    @GetMapping("/{courseId}/{id}/deleteGroup")
    public String deleteGroup(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId) {
        groupService.deleteGroup(id);
        return "redirect:/groups/" + courseId;
    }


}
