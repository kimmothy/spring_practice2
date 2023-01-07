package hello.servlet.roomapi;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "roomSaveServletSample", urlPatterns = "/servlet/rooms/save/sample")
public class RoomService extends HttpServlet {


    private RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository){
        this.repository = repository;

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        PrintWriter w = resp.getWriter();;
        repository.deleteAll();
        w.write("dd");

        // save a couple of customers
//        repository.save(new Room(131, 37, "lunch", "13:00"));
//        repository.save(new Room(131.02, 37.33, "lunch", "14:02"));
//
//        // fetch all customers
//        System.out.println("Rooms found with findAll():");
//        System.out.println("-------------------------------");
//        for (Room room : repository.findAll()) {
//            System.out.println(room);
//        }
//
//        // fetch an individual customer
//        System.out.println("Rooms found with findByRoomName(\"lunch\"):");
//        System.out.println("--------------------------------");
//        System.out.println(repository.findByRoomName("lunch"));
//
//        System.out.println("Customers found with findByLastName('Smith'):");
//        System.out.println("--------------------------------");
//        for (Room room : repository.findByRoomName("lunch")) {
//            System.out.println(room);
//            w.write(room.toString());
//        }
    }
}
