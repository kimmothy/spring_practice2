package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.domain.room.Room;
import hello.servlet.web.jwt.JWTManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/create-room")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    private JWTManager jwtManager = new JWTManager();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //COntent type: text/html; charset=utf-8
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        //사용자가 준 값 빼내기
        int x = Integer.parseInt(request.getParameter("x"));
        int y = Integer.parseInt(request.getParameter("y"));
        String roomName = request.getParameter("roomName");
        String endTime = request.getParameter("endTime");

        Room newRoom = new Room(x,y,roomName,endTime);
        //룸 객체에 사용자에게서 json 데이터 받은 것 채워넣기

        //랜덤 문자열 7자로 키값 생성하여 룸 객체에 넣기
        String roomKey = randomString();

        //룸 객체 정보 토대로 jwt 토큰 생성하기
        String result = jwtManager.createRoomToken(newRoom, roomKey);

        //몽고 DB에 데이터 저장하기


        //String result = objectMapper.writeValueAsString(newRoom);
        response.getWriter().write("room-Url: localhost:8080/" + roomKey);
        response.getWriter().write("\n");

        response.getWriter().write("jwt: " + result);

    }

    private String randomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
        return generatedString;
    }
}
