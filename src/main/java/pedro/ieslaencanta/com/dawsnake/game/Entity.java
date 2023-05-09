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
public class Entity extends Game implements IDrawable {

    public Coordenada position;
    public Size size;
    public Color color;

    public Entity() {
        super(null, null, null, null);
    }

    public Entity(Coordenada position, Size size, Color color, GraphicsContext context, GraphicsContext bg_canvas, Size board_size, Size cell_size) {
        super(context, bg_canvas, board_size, cell_size);
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
