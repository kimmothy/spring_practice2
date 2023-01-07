package hello.servlet.roomapi;

import hello.servlet.web.jwt.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "roomGetServlet", urlPatterns = "/servlet/rooms/token")
public class RoomGetServlet extends HttpServlet {

    private JWTManager jwtManager;

    private RoomRepository repository;

    @Autowired
    public RoomGetServlet(JWTManager jwtManager, RoomRepository repository){
        this.repository = repository;
        this.jwtManager = jwtManager;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/plain");
        response.setCharacterEncoding("utf-8");

        String roomKey = request.getParameter("roomKey");

        Room room = repository.findByRoomKey(roomKey);

        String roomToken = jwtManager.createRoomToken(room);
        PrintWriter writer = response.getWriter();
        writer.write(roomToken);


    }
}
