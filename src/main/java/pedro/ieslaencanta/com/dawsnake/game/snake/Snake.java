/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawsnake.game.snake;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import pedro.ieslaencanta.com.dawsnake.game.Coordenada;
import pedro.ieslaencanta.com.dawsnake.game.Direction;
import pedro.ieslaencanta.com.dawsnake.game.IDrawable;
import pedro.ieslaencanta.com.dawsnake.game.Size;

/**
 *
 * @author Pedro
 */
public class Snake implements IDrawable {

    public enum State {
        OK,
        BOARD_COLISION,
        SNAKE_COLISION
    }
    private State state;
    private Direction direction;
    private int row, col;
    private Size part_size;
    public Snake() {
        this.state=State.OK;
        this.direction=Direction.LEFT;
        this.row=5;
        this.col=5;
    }

    /**
     * vuelve la serpiente
     *
     * @param s
     * @return devuelve el estado de la serpiente correcto,toca borde, se toca a
     * ella misma
     */
    public State move(Size s) {
       switch(this.direction){
           case UP:
               this.col--;
               break;
           case DOWN:
               this.col++;
               break;
           case LEFT:
               this.row--;
               break;
           case RIGHT:
               this.row++;
       }
        return this.state;
    }

     public void setDirection(Direction d) {
         this.direction=d;
  
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(this.row*this.part_size.getHeight(), this.col*this.part_size.getWidth(),this.part_size.getHeight(), this.part_size.getWidth());
       
    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return the part_size
     */
    public Size getPart_size() {
        return part_size;
    }

    /**
     * @param part_size the part_size to set
     */
    public void setPart_size(Size part_size) {
        this.part_size = part_size;
    }

}
