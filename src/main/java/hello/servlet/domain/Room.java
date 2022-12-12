package hello.servlet.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Room {
    private int x;
    private int y;
    private String roomName;
    private String endTime;

//    private String roomKey;

    public Room(int x, int y, String roomName, String endTime) {
        this.x = x;
        this.y = y;
        this.roomName = roomName;
        this.endTime = endTime;
    }
}
