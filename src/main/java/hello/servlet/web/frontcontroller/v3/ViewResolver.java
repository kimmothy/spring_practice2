package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ViewResolver {

    private String pathBase = "/WEB-INF/views/";
    private String extension = ".jsp";

    public MyView process(HttpServletRequest request, HttpServletResponse response, ModelView view){

        return new MyView(pathBase + view.getViewName() + extension);

    }
}
