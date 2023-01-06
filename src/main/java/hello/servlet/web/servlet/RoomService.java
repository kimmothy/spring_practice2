package hello.servlet.web.servlet;

import hello.servlet.domain.room.Room;
import hello.servlet.domain.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "roomSaveServlet", urlPatterns = "/servlet/rooms/save")
public class RoomService extends HttpServlet {

    @Autowired
    private RoomRepository repository;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter w = resp.getWriter();;
        w.write("dd");
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Room(131, 37, "lunch", "13:00"));
        repository.save(new Room(131.02, 37.33, "lunch", "14:02"));

        // fetch all customers
        System.out.println("Rooms found with findAll():");
        System.out.println("-------------------------------");
        for (Room room : repository.findAll()) {
            System.out.println(room);
        }

        // fetch an individual customer
        System.out.println("Rooms found with findByRoomName(\"lunch\"):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByRoomName("lunch"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Room room : repository.findByRoomName("lunch")) {
            System.out.println(room);
            w.write(room.toString());
        }


    }
}
