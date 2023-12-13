package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ColorProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.TypeProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ThicknessProperty;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.Optional;

public class GraphicRenderer implements Renderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.2f);
        canvas.setStroke(stroke);
        drawPolygons(aMesh,canvas);
        drawRivers(aMesh, canvas);
    }

    private void drawPolygons(Mesh aMesh, Graphics2D canvas) {

        for(Structs.Polygon p: aMesh.getPolygonsList()){
            drawAPolygon(p, aMesh, canvas);
        }
    }

    private void drawAPolygon(Structs.Polygon p, Mesh aMesh, Graphics2D canvas) {
        Hull hull = new Hull();
        for(Integer segmentIdx: p.getSegmentIdxsList()) {
            hull.add(aMesh.getSegments(segmentIdx), aMesh);
        }
        Path2D path = new Path2D.Float();
        Iterator<Vertex> vertices = hull.iterator();
        Vertex current = vertices.next();
        path.moveTo(current.getX(), current.getY());
        while (vertices.hasNext()) {
            current = vertices.next();
            path.lineTo(current.getX(), current.getY());
        }
        path.closePath();
        canvas.draw(path);

        Optional<Color> fill = new ColorProperty().extract(p.getPropertiesList());

        if(fill.isPresent()) {
            Color old = canvas.getColor(); //black
            canvas.setColor(fill.get());
            canvas.fill(path);
            canvas.setColor(old); //back to black..
        }
    }

    private void drawRivers(Mesh aMesh, Graphics2D canvas) {

        for(Structs.Segment seg: aMesh.getSegmentsList()){
            Optional<String> type = new TypeProperty().extract(seg.getPropertiesList());
            if(type.isPresent() && type.get().equals("river")){
                drawARiver(seg, aMesh, canvas);
            }
        }
    }

    //Colours in one segment
    private void drawARiver(Structs.Segment seg, Structs.Mesh aMesh, Graphics2D canvas) {
        //draw the river path
        RiverPath riverPath = new RiverPath();

        Optional<Color> riverColor = new ColorProperty().extract(seg.getPropertiesList());
        Optional<Integer> riverThickness = new ThicknessProperty().extract(seg.getPropertiesList());

        riverPath.add(seg, aMesh);

        Path2D path = new Path2D.Float();
        Iterator<Vertex> vertices = riverPath.iterator();
        Vertex current = vertices.next();
        path.moveTo(current.getX(), current.getY());
        while (vertices.hasNext()) {
            current = vertices.next();
            path.lineTo(current.getX(), current.getY());
        }

        if(riverColor.isPresent() && riverThickness.isPresent()) {
            Color old = canvas.getColor(); //black
            Stroke oldStroke = canvas.getStroke();

            Stroke newStroke = new BasicStroke(riverThickness.get());
            canvas.setStroke(newStroke);

            canvas.setColor(riverColor.get());
            canvas.draw(path);

            canvas.setColor(old); //back to black..
            canvas.setStroke(oldStroke);
        }




    }

}
