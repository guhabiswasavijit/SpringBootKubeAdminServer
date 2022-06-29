package org.self.controller;

import org.self.model.KubeCommand;
import org.self.request.CommandMessage;
import org.self.request.Greeting;
import org.self.request.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GreetingController {

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Greeting greeting(HelloMessage message) throws Exception {
    Thread.sleep(1000);
    log.debug("Got message {}",message.getName());
    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
  }
  
  @MessageMapping("/clusterAdm")
  @SendTo("/topic/commands")
  public KubeCommand runCommand(CommandMessage message) throws Exception {
    Thread.sleep(1000);
    log.debug("Got command {}",message.getCommand());
    return new KubeCommand("MyDesktop",HtmlUtils.htmlEscape(message.getCommand()),"java-demo-ns","kube-worker","NODE");
  }

}