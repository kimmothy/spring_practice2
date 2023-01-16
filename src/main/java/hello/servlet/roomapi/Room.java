package hello.servlet.roomapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter @Setter
public class Room {
    @Id
    private String id;
    private double lng;
    private double lat;
    private String roomName;
    private String endTime;
    private String roomKey;

    public Room(){}

    public Room(double longitude, double latitude, String roomName, String endTime, String roomKey) {
        this.lng = lng;
        this.lat = lat;
        this.roomName = roomName;
        this.endTime = endTime;
        this.roomKey = roomKey;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[roomKey=%s, roomName='%s']", id, roomName
        );
    }
}
