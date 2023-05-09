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
public class Food extends Entity {
 
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(this.position.getX() * this.size.getHeight(), this.position.getY() * this.size.getWidth(), this.size.getHeight(), this.size.getWidth());

    }

    public Food() {
    
    }
       

    public Food( Coordenada position, Size size, Color color, GraphicsContext context, GraphicsContext bg_canvas, Size board_size, Size cell_size) {
        super(position, size, color, context, bg_canvas, board_size, cell_size);
   
    }
    
    
}
