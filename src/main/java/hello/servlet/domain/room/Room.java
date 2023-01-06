package hello.servlet.domain.room;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter @Setter
public class Room {
    @Id
    private String id;
    private double longitude;
    private double latitude;
    private String roomName;
    private String endTime;

    public Room(){}

    public Room(double longitude, double latitude, String roomName, String endTime) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.roomName = roomName;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[roomKey=%s, roomName='%s']", id, roomName
        );
    }
}
