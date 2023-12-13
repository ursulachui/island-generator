package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.geom.Path2D;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RiverPath implements Iterable<Structs.Vertex> {

        private Deque<Structs.Vertex> vertices;

        public RiverPath() {
            this.vertices = new LinkedList<>();
        }

        public void add(Structs.Segment v, Structs.Mesh mesh) {
            Structs.Vertex v1 = mesh.getVertices(v.getV1Idx());
            Structs.Vertex v2 = mesh.getVertices(v.getV2Idx());
            if(this.vertices.isEmpty()) {
                this.vertices.add(v1);
                this.vertices.add(v2); }
//            } else if(v1.equals(vertices.getLast())) {
//                this.vertices.addLast(v2);
//            }
            else {
                throw new IllegalArgumentException("Not a river segment");
            }
        }

        @Override
        public Iterator<Structs.Vertex> iterator() {
            return this.vertices.iterator();
        }
}

