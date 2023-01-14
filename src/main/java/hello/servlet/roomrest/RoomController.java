package hello.servlet.roomrest;


import hello.servlet.roomapi.Room;
import hello.servlet.roomapi.RoomRepository;
import hello.servlet.web.jwt.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoomController {

    private RoomService roomService;
    private JWTManager jwtManager;

    @Autowired
    public RoomController(RoomService roomService, JWTManager jwtManager) {
        this.roomService = roomService;
        this.jwtManager = jwtManager;
    }

    @ResponseBody
    @GetMapping("/rooms/{roomKey}")
    public String getRoomToken(@PathVariable String roomKey) {
        Room room = roomService.findRoom(roomKey);
        String roomToken = jwtManager.createRoomToken(room);
        return roomToken;
    }

    @ResponseBody
    @PostMapping("/rooms")
    public String createRoom(@RequestBody Room room) {

        Room createdRoom = roomService.createRoom(room);

        return room.getRoomKey();
    }

//    @GetMapping("/members")
//    public String list(Model model) {
//        List<Room> members = roomService.findRooms();
//        model.addAttribute("members", members);
//        return "members/memberList";
//
//    }
}
