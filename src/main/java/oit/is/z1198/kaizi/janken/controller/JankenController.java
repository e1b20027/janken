package oit.is.z1198.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import oit.is.z1198.kaizi.janken.model.Entry;

@Controller
@RequestMapping("/janken")
public class JankenController {
  @Autowired
  private Entry room;

  @GetMapping("")
  public String sample31() {
    return "janken.html";
  }

  @GetMapping("jan")
  public String sample38(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    model.addAttribute("room", this.room);

    return "janken.html";
  }

}
