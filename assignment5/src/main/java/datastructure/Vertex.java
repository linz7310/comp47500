package datastructure;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    int id;

    int value;
    int inDegree;
    int outDegree;

    public Vertex(int id, int value) {
        this.id = id;
        this.value = value;
        this.inDegree = 0;
        this.outDegree = 0;
    }

    // Getters and setters for ID
    public int getId() {
        return id;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    // Getters for inDegree and outDegree
    public int getInDegree() {
        return inDegree;
    }

    public void inDegreePlusOne() {
        this.inDegree++;
    }

    public void inDegreeMinusOne() {
        this.inDegree--;
    }

    public int getOutDegree() {
        return outDegree;
    }

    public void outDegreePlusOne() {
        this.outDegree++;
    }

    public void outDegreeMinusOne() {
        this.outDegree--;
    }
}

