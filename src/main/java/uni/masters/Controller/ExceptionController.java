//package uni.masters.Controller;
//
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Arrays;
//
//@RestController
//public class ExceptionController implements ErrorController {
//
//
//    @GetMapping(path = "/error")
//    public String errorMapping(){
//        return "An Error has occurred.\n"
//                + Arrays.toString(Thread.currentThread().getStackTrace());
//    }
//}
