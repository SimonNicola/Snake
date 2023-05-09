/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawsnake.game;

import pedro.ieslaencanta.com.dawsnake.game.snake.Snake;
import com.sun.prism.Presentable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pedro.ieslaencanta.com.dawsnake.Clock;
import pedro.ieslaencanta.com.dawsnake.IWarnClock;


;

/**
 *
 * @author Pedro
 */
public class Game implements IWarnClock {

    //0 delante, 1 detras,2 arriba, 3 abajo, 4 disparo
    //private boolean[] keys_presed;
    public static Clock clock = new Clock(2.0f);
    private GraphicsContext ctx;
    private Size board_size;
    private Size cell_size;
    private Size boardcells_size;
    private int rows;
    private int cols;
    private GraphicsContext ctxbg;
    private boolean debug = false;
    private boolean ispaintendbackground = false;
    private Snake snake;
    private Food food;
    private Coordenada c;
    protected MediaPlayer player;
    //protected Image img;
    private Font font = new Font("8BIT WONDER Nominal", 18);
    private int score;

    private enum GameState {
        START,
        STOP
    }
    private GameState state;
   

    /**
     *
     * @param context objeto gráfico para pintar
     * @param board_size tamaño del tablero
     * @param cell_size tamaño de cada celda
     */
    public Game(GraphicsContext context, GraphicsContext bg_canvas, Size board_size, Size cell_size) {
        // this.keys_presed = new boolean[5];

        Game.clock.addIWarClock(this);
        this.board_size = board_size;
        this.cell_size = cell_size;
        this.ctx = context;
        this.ctxbg = bg_canvas;
        this.rows = this.board_size.getHeight() / this.cell_size.getHeight();
        this.cols = this.board_size.getWidth() / this.cell_size.getWidth();
        this.boardcells_size = new Size(this.cols, this.rows);
        //this.img = new Image(getClass().getResourceAsStream("/images.png"));

        this.snake = new Snake();
        this.snake.setPart_size(this.cell_size);
        this.state = GameState.START;
        this.initSound();
        this.ctx.setFont(font);
        this.ctxbg.setFont(font);
        this.c = new Coordenada(3, 3);
        this.food = new Food(c, cell_size, Color.RED, context, bg_canvas, board_size, cell_size);
        
    }

    public void start() {
        this.state = GameState.START;
        this.clock.start();
        this.player.play();
        this.score = 0;

    }

    public void stop() {
        this.state = GameState.STOP;
        this.clock.stop();
        this.player.stop();
       

    }

    public void OnKeyPress(KeyCode code) {
        if (this.state == GameState.START) {
          
           switch(code){
               case UP:
                   this.snake.setDirection(Direction.UP);
                   break;
              case DOWN:
                   this.snake.setDirection(Direction.DOWN);
                   break;
               case LEFT:
                   this.snake.setDirection(Direction.LEFT);
                   break;
               case RIGHT:
                   this.snake.setDirection(Direction.RIGHT);
                   break;
           }
        }
        if (this.state == GameState.STOP) {
            if (code == KeyCode.SPACE) {
                this.start();
            }
        }
    }

    public void OnKeyRelease(KeyCode code) {

    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
    
    private void initSound() {
        if (this.player == null) {
            this.player = new MediaPlayer(new Media(getClass().getResource("/music.mp3").toString()));
        }
        player.setVolume(0.2f);
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                player.seek(Duration.ZERO);
            }
        });
        player.play();
    }
    /**
     * cada vez que se produce un evento de reloj (marca la frecuencia, se ejecuta el código
     */
    @Override
    public synchronized void TicTac() {
        Snake.State resultado;
        this.draw(ctx);
        resultado = this.snake.move(this.boardcells_size);
    
       //TODO
    }
  
    /**
     * para la depuración
     * @param gc 
     */
    private void debug(GraphicsContext gc) {
        if (!this.ispaintendbackground) {
            gc.clearRect(0, 0, this.board_size.getWidth(), this.board_size.getHeight());
            gc.setFill(Color.GRAY);
            gc.fillRect(0, 0, this.board_size.getWidth(), this.board_size.getHeight());
            gc.setFill(Color.BROWN);
            gc.setStroke(Color.BROWN);
            for (int i = 0; i < this.rows; i++) {
                gc.moveTo(0, i * this.cell_size.getHeight());
                gc.lineTo(this.board_size.getWidth(), i * this.cell_size.getHeight());
                gc.stroke();
            }
            for (int i = 0; i < this.cols; i++) {
                gc.moveTo(i * this.cell_size.getWidth(), 0);
                gc.lineTo(i * this.cell_size.getWidth(), this.board_size.getHeight());
                gc.stroke();
            }

        }
        this.ispaintendbackground = true;

    }

    private void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BROWN);
        if (this.state == GameState.START) {
            gc.clearRect(0, 0, this.board_size.getWidth(), this.board_size.getHeight());
            if (this.isDebug()) {
                this.debug(this.ctxbg);
            }
            this.snake.draw(gc);
            gc.strokeText("SCORE " + this.score, this.board_size.getWidth() / 2 - 50, 50);
            gc.fillText("SCORE " + this.score, this.board_size.getWidth() / 2 - 50, 50);
            
        }
        if (this.state == GameState.STOP) {
            gc.strokeText("Pulsar espacio para reiniciar", 10, this.board_size.getHeight() / 2 - 20);
            gc.fillText("Pulsar espacio para reiniciar", 10, this.board_size.getHeight() / 2 - 20);
        }
        

    }

}
