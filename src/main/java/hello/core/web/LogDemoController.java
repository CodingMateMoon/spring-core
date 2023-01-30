package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    // 뷰 화면 없이 문자 반환할 경우 @ResponseBody. 고객 요청 시 뷰 템플릿 거쳐서 렌더링 후 나가야 하는데 그러지 않고 문자 그대로 보내는 경우
    @ResponseBody
    // 자바에서 제공하는 표준 서블릿 규약으로 고객 요청 정보 Request를 받습니다.
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURI().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
