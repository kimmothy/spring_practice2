package hello.servlet.roomrest;

import hello.servlet.domain.member.MemberRepository;
import hello.servlet.roomapi.Room;
import hello.servlet.roomapi.RoomRepository;
import hello.servlet.web.jwt.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Transactional
@Component
public class RoomService {

    private RoomRepository repository;
    private JWTManager jwtManager;

    @Autowired
    public RoomService(RoomRepository repository, JWTManager jwtManager) {
        this.repository = repository;
        this.jwtManager = jwtManager;
    }

    /**
     * 회원 가입
     */
    public Room createRoom(Room room) {

        String roomKey;
        while (true){
            roomKey = randomString();
            if (isUniqueKey(roomKey)){
                break;
            }

        }
        room.setRoomKey(roomKey);
        repository.save(room);
        return room;
    }

    private boolean isUniqueKey(String roomKey) {
        Room room = repository.findByRoomKey(roomKey);
        return (room == null);
    }

//    public List<Room> findRooms() {
//        return repository.findAll();
//    }

    public Room findRoom(String roomKey){
        Room room = repository.findByRoomKey(roomKey);
        return room;

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
