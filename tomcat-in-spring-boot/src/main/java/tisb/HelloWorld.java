package tisb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author ISJINHAO
 * @Date 2021/2/1 11:28
 */
@Controller
@RequestMapping("/")
public class HelloWorld {

    @RequestMapping("helloworld")
    @ResponseBody
    public String helloWorld() {
        return "helloworld 啊";
    }

}
