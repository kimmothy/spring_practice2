package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:880/request-param?username=hello&age=20
 */

@WebServlet(name = "RequestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회 =- 시작");
        req.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> System.out.println(paramName + "= " +req.getParameter(paramName)));
        System.out.println("전체 파라미터 조회 =- 끝");
        System.out.println();
        System.out.println("단일 파라미터 조회");
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        System.out.println("age = " + age);
        System.out.println("username = " + username);

        System.out.println("same name");
        String[] usernames = req.getParameterValues("username");
        for (String name: usernames){
            System.out.println("name = " + name);
        }
    }
}
