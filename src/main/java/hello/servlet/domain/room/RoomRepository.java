package hello.servlet.domain.room;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoomRepository extends MongoRepository<Room, String> {

//    public Room findByRoomKey(String roomKey);
    public List<Room> findByRoomName(String roomName);
}
