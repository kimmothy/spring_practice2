package hello.servlet.roomapi;

import hello.servlet.web.jwt.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "roomCreateServlet", urlPatterns = "/servlet/rooms/create")
public class RoomCreateServlet extends HttpServlet {

    private RoomRepository repository;

    private JWTManager jwtManager;

    @Autowired
    public RoomCreateServlet(RoomRepository repository, JWTManager jwtManager) {
        this.jwtManager = jwtManager;
        this.repository = repository;

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/plain");
        response.setCharacterEncoding("utf-8");
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        String roomName = request.getParameter("roomName");
        String endTime = request.getParameter("endTime");

        //랜덤 문자열 7자로 키값 생성하여 룸 객체에 넣기
        String roomKey = randomString();

        //룸 객체에 생성하고 사용자에게서 json 데이터 받은 것 채워넣기
        Room newRoom = new Room(longitude, latitude, roomName, endTime, roomKey);

        //룸 객체 정보 토대로 jwt 토큰 생성하기
        String result = jwtManager.createRoomToken(newRoom);

        //몽고 DB에 데이터 저장하기
        repository.save(newRoom);


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
