package cn.las.domain;

public class Laboratory {
    private int id;
    private String name;
    private String type;
    private int size;
    private String location;
    private int status;

    public Laboratory(int id, String name, String type, int size, String location ,int status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.location = location;
        this.status= status;
    }

    public Laboratory() {
    }

    @Override
    public String toString() {
        return "Laboratory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
