/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawsnake.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Simon Roca
 */
public class Entity implements IDrawable {

    protected Coordenada position;
    protected Size size;
    protected Color color;

    public Entity() {
    }

    public Entity(Coordenada position, Size size, Color color) {

        this.position = position;
        this.size = size;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(this.position.getX() * this.size.getHeight(), this.position.getY() * this.size.getWidth(), this.size.getHeight(), this.size.getWidth());

    }

}
